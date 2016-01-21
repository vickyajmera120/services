package com.ishi.model;

public class Student {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String address;
	private String city;
	private String pincode;
	private String state;
	private String country;
	private String courses;
	
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getCourses() {
		return courses;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public int getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public String getPincode() {
		return pincode;
	}

	public String getState() {
		return state;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public void setState(String state) {
		this.state = state;
	}
}