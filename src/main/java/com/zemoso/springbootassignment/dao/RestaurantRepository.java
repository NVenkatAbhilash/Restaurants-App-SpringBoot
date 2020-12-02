package com.zemoso.springbootassignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zemoso.springbootassignment.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    public List<Restaurant> findByNameContainsOrCityContainsAllIgnoreCase(String theName, String City);
}
