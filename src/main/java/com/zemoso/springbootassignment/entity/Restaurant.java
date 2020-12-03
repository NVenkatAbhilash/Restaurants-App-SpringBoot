package com.zemoso.springbootassignment.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="restaurant")
@Getter @Setter @NoArgsConstructor @ToString
public class Restaurant {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Valid
	@NotEmpty(message="is required")
	@Column(name="name")
	private String name;
	
	@NotEmpty(message="is required")
	@Pattern(regexp = "^[a-zA-Z,0-9 ]*$", message="must not contain special characters")
	@Column(name="city")
	private String city;
	
	//"^[www.]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)"
	@Column(name="website")
	@Pattern(regexp="(^(www.|http://|https://)[a-zA-Z0-9@:%_-|\\+.~#?&//=]+(.com|.in|.info)[a-zA-Z0-9@:%_-|\\\\+.~#?&//=]*|)",
			message="not a valid website")
	private String website;
	
	@Size(min=0,max=200,message="upto {max} characters are allowed")
	@Column(name="details")
	private String details;
	
	@Min(1)
	@Max(10)
	@Column(name="rating")
	private double rating;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="restaurant_id")
	private List<Review> reviews;

	public Restaurant(int id, @Valid @NotEmpty(message = "is required") String name,
			@NotEmpty(message = "is required") @Pattern(regexp = "^[a-zA-Z ]*$", message = "must contain only alphabets") String city,
			String website, @Size(min = 0, max = 200, message = "upto {max} characters are allowed") String details,
			@NotEmpty(message = "is required") double rating) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.website = website;
		this.details = details;
		this.rating = rating;
	}

	public void addReview(Review theReview) {
		if(reviews==null)
			reviews = new ArrayList<>();
		reviews.add(theReview);
	}

    @PrePersist
    public void onPrePersist() {

        Logger myLogger =
                Logger.getLogger(getClass().getName());
        myLogger.info("\n$$$$$$ Restaurant INSERTED $$$$$$");
    }

    @PreUpdate
    public void onPreUpdate() {
        Logger myLogger =
                Logger.getLogger(getClass().getName());
        myLogger.info("\n$$$$$$ Restaurant UPDATED $$$$$$");
    }

    @PreRemove
    public void onPreRemove() {
        Logger myLogger =
                Logger.getLogger(getClass().getName());
        myLogger.info("\n$$$$$$ Restaurant DELETED $$$$$$");
    }
}