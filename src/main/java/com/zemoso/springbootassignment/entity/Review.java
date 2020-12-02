package com.zemoso.springbootassignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="restaurant_id")
	private int restaurant_id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="feedback")
	private String feedback;
	
	public Review() {
		
	}
	
	public Review(int id, int restaurant_id) {
		this.id = id;
		this.restaurant_id = restaurant_id;
	}
	
	public Review(int id, int restaurant_id, String username) {
		this.id = id;
		this.restaurant_id = restaurant_id;
		this.username = username;
	}
	
	public Review(int id, int restaurant_id, String username, String feedback) {
		this.id = id;
		this.restaurant_id = restaurant_id;
		this.username = username;
		this.feedback = feedback;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", restaurant_id=" + restaurant_id + ", username=" + username + ", feedback="
				+ feedback + "]";
	}	
}
