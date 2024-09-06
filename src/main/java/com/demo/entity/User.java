package com.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String phno;
    private String dob;
    private String gender;
    private String location;
    private String profession;
    private String linkedin_url;
    private String github_url;
    
    

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Cart> cartItems;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Learning> learningCourses;
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getLinkedin_url() {
		return linkedin_url;
	}

	public void setLinkedin_url(String linkedin_url) {
		this.linkedin_url = linkedin_url;
	}

	public String getGithub_url() {
		return github_url;
	}

	public void setGithub_url(String github_url) {
		this.github_url = github_url;
	}

	public List<Cart> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<Cart> cartItems) {
		this.cartItems = cartItems;
	}

	public List<Learning> getLearningCourses() {
		return learningCourses;
	}

	public void setLearningCourses(List<Learning> learningCourses) {
		this.learningCourses = learningCourses;
	}
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", phno="
				+ phno + ", dob=" + dob + ", gender=" + gender + ", location=" + location + ", profession=" + profession
				+ ", linkedin_url=" + linkedin_url + ", github_url=" + github_url + ", cartItems=" + cartItems
				+ ", learningCourses=" + learningCourses + "]";
	}

}
