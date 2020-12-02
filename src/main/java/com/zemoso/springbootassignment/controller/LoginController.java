package com.zemoso.springbootassignment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.zemoso.springbootassignment.entity.Restaurant;
import com.zemoso.springbootassignment.entity.UserDetails;
import com.zemoso.springbootassignment.service.UserDetailsService;

@Controller
public class LoginController {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		return "login";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
		
	}
	

	@GetMapping("/showMySignUpPage")
	public String showMySignUpPage(Model theModel) {
		
		UserDetails userDetails = new UserDetails();
		
		theModel.addAttribute("userdetails", userDetails);
		
		return "signUp";
	}
	
	@PostMapping("/saveSignUp")
	public String saveSignUp(
			@Valid @ModelAttribute("userdetails") UserDetails theUser,
			BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "signUp";
		}
		
		if(!theUser.getPassword().equals(theUser.getConfirmpassword())) {
			return "redirect:/showMySignUpPage?missmatch";
		}
		
		userDetailsService.save(theUser);
		
		return "redirect:/showMyLoginPage?register";
	}
}
