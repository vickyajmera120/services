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

	@Given("^user is on registration page$")
	public void user_is_on_registration_page() throws Throwable {
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Firefox has started");
        driver.get("http://localhost:8080/app/#/register");
	}

	@When("^student fills his/her details$")
	public void student_fills_his_her_details() throws Throwable {
		driver.findElement(By.id("first_name")).sendKeys("Vicky"); 	 
	    driver.findElement(By.id("last_name")).sendKeys("Ajmera");
	    driver.findElement(By.id("register")).click();
	}

	@Then("^student should be shown the message registration successful$")
	public void student_should_be_shown_the_message_registration_successful() throws Throwable {
		System.out.println("Registration Successful");
	}

}
