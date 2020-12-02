package com.zemoso.springbootassignment.service;

import java.util.List;

import com.zemoso.springbootassignment.entity.Restaurant;
import com.zemoso.springbootassignment.entity.Review;

public interface RestaurantService {
	
	public List<Restaurant> findAll();
	
	public Restaurant findById(int theId);
	
	public void save(Restaurant theRestaurant);
	
	public void deleteById(int theId);

	public List<Restaurant> searchBy(String theName);

	List<Restaurant> findAllByOrderByRatingDesc();
	
	public String getUsername();
	
	public List<Review> findAllReviews(int theId);
	
	public void addReview(Review theReview);
	
	public String getRestaurantName(int theId);
}
