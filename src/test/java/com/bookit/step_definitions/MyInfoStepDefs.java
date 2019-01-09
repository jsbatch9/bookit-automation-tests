package com.bookit.step_definitions;

import com.bookit.pages.SignInPage;
import com.bookit.utilities.ConfigurationReader;
import com.bookit.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyInfoStepDefs {

	@Given("user logs in using {string} {string}")
	public void user_logs_in_using(String email, String password) {
	    Driver.getDriver().get(ConfigurationReader.getProperty("url"));
	    Driver.getDriver().manage().window().maximize();
	    SignInPage signInPage = new SignInPage();
	    signInPage.email.sendKeys(email);
	    signInPage.password.sendKeys(password);
	    signInPage.signInButton.click();
	    	    
	}

	@When("user is on the my self page")
	public void user_is_on_the_my_self_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("user info should match with the database records for {string}")
	public void user_info_should_match_with_the_database_records_for(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
}
