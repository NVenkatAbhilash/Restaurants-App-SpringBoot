package com.zemoso.springbootassignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="userdetails")
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Length(min=3,message="username must be atleast 3 characters")
	@NotEmpty(message="is required")
	@Column(name="username")
	private String username;

	@Length(min=4,message="password must be atleast 4 characters")
	@NotEmpty(message="is required")
	@Column(name="password")
	private String password;
	
	@NotEmpty(message="is required")
	@Column(name="confirmpassword")
	private String confirmpassword;
	/*
	@NotEmpty(message="is required")
	@Column(name="authority")
	private String authority;
	 */
	public UserDetails() {
	}

	public UserDetails(String username, String password,
			String confirmpassword,String authority) {
		this.username = username;
		this.password = password;
		this.confirmpassword = confirmpassword;
	//	this.authority = authority;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	/*
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}	
	*/
}
