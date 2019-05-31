package com.hurtownia.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hurtownia.domain.User;
import com.hurtownia.domain.Wine;
import com.hurtownia.domain.security.PasswordResetToken;
import com.hurtownia.domain.security.Role;
import com.hurtownia.domain.security.UserRole;
import com.hurtownia.service.HurtowniaService;
import com.hurtownia.service.UserService;
import com.hurtownia.service.impl.UserSecurityService;
import com.hurtownia.utility.MailConstructor;
import com.hurtownia.utility.SecurityUtility;

@Controller
public class HomeController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailConstructor mailConstructor;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private HurtowniaService hurtowniaService;
	
	
	@RequestMapping("/")
	public String index () {
		return "index";
	}
	
//	@RequestMapping("/myAccount")
//	public String myAccount() {
//		return "myAccount";
//	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("classActiveLogin", "active");
		return "myAccount";
	}
	
	@RequestMapping("/hurtowniashelf")
	public String hurtowniashelf(Model model) {
		List<Wine> wineList = hurtowniaService.findAll();
		model.addAttribute("wineList", wineList);
		 return "hurtowniashelf";
	}
	
	@RequestMapping("/wineDetail")
	public String WineDetail(
			@PathParam("id") Long id, Model model, Principal principal
			) {
		if(principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		Wine wine = hurtowniaService.findOne(id);
		
		model.addAttribute("wine", wine);
		
		List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		model.addAttribute("qtyList", qtyList);
		model.addAttribute("qty", 1);
		
		return "WineDetail";
	}
	
	@RequestMapping("/forgetPassword")
	public String forgetPassword(
			HttpServletRequest request,
			@ModelAttribute("email") String userEmail,
			Model model		
			)
		{
		model.addAttribute("classActiveForgetPassword", "active");
		
		User user = userService.findByEmail(userEmail);
		
		if(user == null) {
			model.addAttribute("emailNotExists", true);
			return "myAccount";
		}
		
		String password = SecurityUtility.randomPassword();
		
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		
		userService.save(user);
		
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user, token);
		
		String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		
		SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);
		
		mailSender.send(newEmail);
		
		model.addAttribute("forgetPasswordEmailSent", "true");
		
		return "myAccount";
		
		
	}

	
	@RequestMapping(value = "/newUser", method=RequestMethod.POST)
	public String nuewUserPost(
			HttpServletRequest request,
			@ModelAttribute("email") String userEmail,
			@ModelAttribute("username") String username,
			Model model
			) throws Exception {
				model.addAttribute("classActiveNewAccount", true);
				model.addAttribute("email", userEmail);
				model.addAttribute("username", username);
			
	
				if(userService.findByUsername(username) != null) {
					model.addAttribute("usernameExists", true);
					
					return "myAccount";
				}
				
				if(userService.findByEmail(userEmail) != null) {
					model.addAttribute("emailExists", true);
					
					return "myAccount";
				}
				User user = new User();
				user.setUsername(username);
				user.setEmail(userEmail);
				
				String password = SecurityUtility.randomPassword();
				
				String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
				user.setPassword(encryptedPassword);
				
				Role role = new Role();
				role.setRoleID(1);
				role.setName("ROLE_USER");
				Set<UserRole> userRoles = new HashSet<>();
				userRoles.add(new UserRole (user, role));
				userService.createUser(user, userRoles);
				
				String token = UUID.randomUUID().toString();
				userService.createPasswordResetTokenForUser(user, token);
				
				String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
				
				SimpleMailMessage email = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);
				
				mailSender.send(email);
				
				model.addAttribute("emailSent", "true");
				
				return "myAccount";
			}
	
	@RequestMapping("/newUser")
	public String newUser(
			Locale locale,
			@RequestParam("token") String token,
			Model model) {
		PasswordResetToken passToken = userService.getPasswordResetToken(token);
		
		if(passToken == null) {
			String message = "Token niepoprawny.";
			model.addAttribute("message", message);
			return "redirect:/badRequest";
		}
		
		User user = (User) passToken.getUser();
		String username = user.getUsername();
		
		UserDetails userDetails = userSecurityService.loadUserByUsername(username);
			
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, 
						userDetails.getPassword(), userDetails.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);	
		
		model.addAttribute("user", user);
		
		model.addAttribute("classActiveEdit", true);
		return "myProfile";
		
	}
	
	
}
