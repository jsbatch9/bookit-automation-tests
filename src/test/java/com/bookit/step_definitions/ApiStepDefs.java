package com.bookit.step_definitions;

import com.bookit.pages.SelfPage;
import com.bookit.utilities.BrowserUtils;
import com.bookit.utilities.ConfigurationReader;
import com.bookit.utilities.DBUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ApiStepDefs {
	
	String token;
	JsonPath json;
	String fullname;
	String email;
	
	
	@Given("I logged Bookit api using {string} and {string}")
	public void i_logged_Bookit_api_using_and(String username, String password) {
		//get token url from configuration file and assign to new string variable 
		String tokenurl = ConfigurationReader.getProperty("qa1_tokenurl");
		
		//create one map to keep query param information inside it 
		Map<String,String> loginParams = new HashMap<>();
		 loginParams.put("email", username);
		 loginParams.put("password", password);
		 
		//send get request to get accessToken
		 Response response = given().accept(ContentType.JSON).and().params(loginParams)
				 .when().get(tokenurl);
		
		 //verify status code 
		  assertEquals(response.statusCode(), 200);
		  
		  //convert response to json format 
		  JsonPath json2 = response.jsonPath();
		  //get token value from response JSON Body and assign to one variable 
		  token = json2.getString("accessToken");
		  
		fullname=password;
		email=username;
	}

	@When("I get the current user information from api")
	public void i_get_the_current_user_information_from_api() {
	     
		String url = ConfigurationReader.getProperty("qa1_baseurl") +"/students/me";
		
		Response response = given().accept(ContentType.JSON).and().header("Authorization",token)
					.when().get(url);
		
		//status code
		assertEquals(response.statusCode(), 200);

		json = response.jsonPath();
		
		
		
	}

	@Then("the information about current user should be returned")
	public void the_information_about_current_user_should_be_returned() {
	 
	     //compare api firstname and lastname with fullname(password)
		
		String actualFullname =json.getString("firstName").toLowerCase()+json.getString("lastName").toLowerCase();
			
		//compare fullname from api and feature file 
		assertEquals(fullname, actualFullname);
		
	}
	
	@Then("the information about current user from api and database should be match")
	public void the_information_about_current_user_from_api_and_database_should_be_match() {
	    
		//get your query 
		String query = "SELECT id,firstname,lastname,role\n" + 
				"FROM users\n" + 
				"WHERE email ='"+email+"'";
		
		//send query to database get the result and assign java object
		Map<String,Object> result = DBUtils.getRowMap(query);
		
		//assign db information to expected variables
		String expectedFirstName = (String) result.get("firstname");
		String expectedLastName = (String) result.get("lastname");
		String expectedRole = (String) result.get("role");
		
		//assign api information to actual variables
		String actualID = json.getString("id");
		String actualFirstName = json.getString("firstName");
		String actualLastName = json.getString("lastName");
		String actualRole = json.getString("role");
		
		//compare Database information against API information
		assertEquals(result.get("id").toString(), actualID);
		assertEquals(expectedFirstName, actualFirstName);
		assertEquals(expectedLastName, actualLastName);
		assertEquals(expectedRole, actualRole);

		
	}
	
	
	@Then("UI,API and Database user information must be match")
	public void ui_API_and_Database_user_information_must_be_match() {
	    
		 
		//get your query 
		String query = "SELECT id,firstname,lastname,role\n" + 
				"FROM users\n" + 
				"WHERE email ='"+email+"'";
		
		//send query to database get the result and assign java object
		Map<String,Object> result = DBUtils.getRowMap(query);
		
		//-------------------UI-----------
		SelfPage selfPage = new SelfPage();
		
		//wait until got the user information table
		BrowserUtils.waitFor(3);

		//getting values from UI and assigning to variables 
		String actualUIFullName = selfPage.name.getText();
		String actualUIRole = selfPage.role.getText();
		
				
		//assign db information to expected variables
		String expectedFirstName = (String) result.get("firstname");
		String expectedLastName = (String) result.get("lastname");
		String expectedRole = (String) result.get("role");
		String expectedFullname =expectedFirstName+" "+expectedLastName;

		//assign api information to actual variables
		String actualID = json.getString("id");
		String actualFirstName = json.getString("firstName");
		String actualLastName = json.getString("lastName");
		String actualRole = json.getString("role");
		
		//compare Database information against API information
		assertEquals(result.get("id").toString(), actualID);
		assertEquals(expectedFirstName, actualFirstName);
		assertEquals(expectedLastName, actualLastName);
		assertEquals(expectedRole, actualRole);

		//UI TO API
		
		String apiFullname = actualFirstName+" "+actualLastName;
		
		assertEquals(actualUIFullName, apiFullname);
		assertEquals(actualUIRole, actualRole);
		
		//DB to UI 
		assertEquals(expectedFullname, actualUIFullName);
		assertEquals(expectedRole, actualUIRole);

		
		
		
		
	}

}







