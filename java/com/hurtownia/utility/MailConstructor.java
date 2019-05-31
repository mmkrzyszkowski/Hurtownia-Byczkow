package com.hurtownia.utility;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.hurtownia.domain.User;

@Component
public class MailConstructor {
	
	@Autowired
	private Environment env;
	
	public SimpleMailMessage constructResetTokenEmail(
			String contextPath, Locale locale, String token, User user, String password
			) {
		
			String url = contextPath + "/newUser?token="+token;
			String message = "\n Kliknij żeby zweryfikować adres email i edytować swoje dane. Twoje hasło to: \n"+password;
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(user.getEmail());
			email.setSubject("Hurtownia Byczków - Nowy Użytkownik");
			email.setText(url+message);
			email.setFrom(env.getProperty("support.email"));
			
			return email;
	}
	
}
