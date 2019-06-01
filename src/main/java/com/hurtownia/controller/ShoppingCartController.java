package com.hurtownia.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hurtownia.domain.CartItem;
import com.hurtownia.domain.ShoppingCart;
import com.hurtownia.domain.User;
import com.hurtownia.domain.Wine;
import com.hurtownia.service.CartItemService;
import com.hurtownia.service.HurtowniaService;
import com.hurtownia.service.ShoppingCartService;
import com.hurtownia.service.UserService;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private HurtowniaService hurtowniaService;
	
	@RequestMapping("/cart")
	public String shoppingCart(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", shoppingCart);
		
		return "shoppingCart";
				
	}
	
	@RequestMapping("/addItem")
	public String addItem (
		@ModelAttribute("wine") Wine wine,
		@ModelAttribute("qty") String qty,
		Model model, Principal principal
	) {
		User user = userService.findByUsername(principal.getName());
		wine = hurtowniaService.findOne(wine.getId());
		
		if (Integer.parseInt(qty) > wine.getInStockNumber()) {
			model.addAttribute("notEnoughStock" , true);
			return "forward:/wineDetail?id="+wine.getId();
		}
		
		CartItem cartItem = cartItemService.addWineToCartItem(wine, user, Integer.parseInt(qty));
		model.addAttribute("addWineSuccess", true);		
		return "forward:/wineDetail?id="+wine.getId();
	}
	


}
