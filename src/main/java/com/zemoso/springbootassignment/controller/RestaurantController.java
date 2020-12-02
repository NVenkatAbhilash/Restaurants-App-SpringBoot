package com.zemoso.springbootassignment.controller;

import java.util.List;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zemoso.springbootassignment.entity.Restaurant;
import com.zemoso.springbootassignment.entity.Review;
import com.zemoso.springbootassignment.service.RestaurantService;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	
	public RestaurantController(RestaurantService theRestaurantService) {
		restaurantService = theRestaurantService;
	}


	@GetMapping("/list")
	public String listRestaurants(@RequestParam(required = false) String orderBy,Model theModel) {
		
		List<Restaurant> theRestaurants=null;
		if(orderBy==null) {
			theRestaurants = restaurantService.findAll();
		}
		else if(orderBy.equals("rating")){
			theRestaurants = restaurantService.findAllByOrderByRatingDesc();
		}
		theModel.addAttribute("restaurants", theRestaurants);
		
		return "restaurants/list-restaurants";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Restaurant theRestaurant = new Restaurant();
		
		theModel.addAttribute("restaurant", theRestaurant);
		
		return "restaurants/restaurant-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("restaurantId") int theId,
									Model theModel) {

		Restaurant theRestaurant = restaurantService.findById(theId);

		theModel.addAttribute("restaurant", theRestaurant);
		
		return "restaurants/restaurant-form";
	}
	
	
	@PostMapping("/save")
	public String saveRestaurant(
			@Valid @ModelAttribute("restaurant") Restaurant theRestaurant,
			BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "restaurants/restaurant-form";
		}
		restaurantService.save(theRestaurant);

		return "redirect:/restaurants/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("restaurantId") int theId) {
		
		restaurantService.deleteById(theId);

		return "redirect:/restaurants/list";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("nameOrCity") String theName,
						 Model theModel) {
		
		List<Restaurant> theRestaurants = restaurantService.searchBy(theName);
		theModel.addAttribute("restaurants", theRestaurants);
		return "/restaurants/list-restaurants";
		
	}
	
	@GetMapping("/reviews")
	public String reviews(@RequestParam("id") int theId,
							Model theModel) {
		String username = restaurantService.getUsername();
		Review theReview = new Review(0,theId,username);
		List<Review> reviews = restaurantService.findAllReviews(theId);
		theModel.addAttribute("name",
				restaurantService.getRestaurantName(theId));
		theModel.addAttribute("review", theReview);
		theModel.addAttribute("reviews", reviews);
		return "/restaurants/list-reviews";		
	}	
	
	@PostMapping("/addReview")
	public String addReview(@ModelAttribute("review") Review theReview) {
		restaurantService.addReview(theReview);
		return "redirect:/restaurants/reviews?id="+theReview.getRestaurant_id();
	}
}












