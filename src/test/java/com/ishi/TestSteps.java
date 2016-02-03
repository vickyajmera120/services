package com.ishi;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestSteps {

	public static WebDriver driver;

	@Given("^user is on homepage$")
	public void user_is_on_home_page() throws Throwable {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Firefox has started");
		driver.get("http://localhost:8080/app");
	}

	@When("^user clicks Register from navigation bar$")
	public void user_clicks_register() throws Throwable {
		driver.get("http://localhost:8080/app/#/register");
	}

	@When("^student fills \"(.*)\" and \"(.*)\"$")
	public void student_fills_his_her_details(String firstname, String lastname) throws Throwable {
		driver.findElement(By.id("first_name")).sendKeys(firstname);
		driver.findElement(By.id("last_name")).sendKeys(lastname);
		driver.findElement(By.id("register")).click();
	}

	@Then("^student should be shown the message registration successful$")
	public void student_should_be_shown_the_message_registration_successful() throws Throwable {
		System.out.println("Registration Successful");
	}

	@When("^user clicks Students from navigation bar$")
	public void user_clicks_students() throws Throwable {
		driver.get("http://localhost:8080/app/#/students");
	}

	@When("^user clicks showStudents button$")
	public void user_clicks_showStudents_button() throws Throwable {
		driver.findElement(By.id("showStudents")).click();
	}

	@Then("^list of all students should be shown$")
	public void list_of_all_students_should_be_shown() throws Throwable {
		System.out.println("LIst of all the students is shown!");
	}

}
