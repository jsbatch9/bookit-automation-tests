package com.bookit.step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefintions {

	@Given("the user is on the login page")
	public void the_user_is_on_the_login_page() {
		System.out.println("Opening login page");
	}

	@When("the user logs in as a teacher")
	public void the_user_logs_in_as_a_teacher() {
		System.out.println("logging in as teacher");
	}

	@Then("the user should be logged in")
	public void the_user_should_be_logged_in() {
		System.out.println("I am logged in");
	}

	@When("the user logs in as a team lead")
	public void the_user_logs_in_as_a_team_lead() {
		System.out.println("loggin in as team lead");
	}

}
