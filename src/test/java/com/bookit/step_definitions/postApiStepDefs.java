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

public class postApiStepDefs {
	
	String token;
	Response res;
	
	@Given("I logged BookIT api as a student")
	public void i_logged_BookIT_api_as_a_student() {
		//get token url from configuration file and assign to new string variable 
			String tokenurl = ConfigurationReader.getProperty("qa1_tokenurl");
				
				//create one map to keep query param information inside it 
				Map<String,String> loginParams = new HashMap<>();
				 loginParams.put("email", ConfigurationReader.getProperty("qa1_member_username"));
				 loginParams.put("password", ConfigurationReader.getProperty("qa1_member_password"));
				 
				//send get request to get accessToken
				 Response response = given().accept(ContentType.JSON).and().params(loginParams)
						 .when().get(tokenurl);
				
				 //verify status code 
				  assertEquals(response.statusCode(), 200);
				  
				  //convert response to json format 
				  JsonPath json2 = response.jsonPath();
				  //get token value from response JSON Body and assign to one variable 
				  token = json2.getString("accessToken");
	}

	@When("I try to register new user")
	public void i_try_to_register_new_user() {
	    
		//url for sending post request
		String url = ConfigurationReader.getProperty("qa1_baseurl")+"/students/student";
		
		//create params for new user information
		Map<String,String> postParams = new HashMap<>();
		
		postParams.put("first-name", "dami");
        postParams.put("last-name", "Mapam");
        postParams.put("email", "wcef1ewasd@gmail.com");
        postParams.put("password", "terimapam");
        postParams.put("role", "dami");
        postParams.put("batch-number", "8");
        postParams.put("team-name", "dami");
        postParams.put("campus-location", "dami");
		
        res = given().accept(ContentType.JSON).header("Authorization",token).params(postParams)
        				.when().post(url);
        		
	}

	@Then("system should return only teachers can register message")
	public void system_should_return_only_teachers_can_register_message() {
	    
		//status code
        assertEquals(res.statusCode(), 403);
		
        String expectedMessage ="only teacher allowed to modify database.";
		String actualMessage = res.body().asString();
		
		
		System.out.println(expectedMessage+"=="+actualMessage);
		//compare expected message with api result
		assertEquals(expectedMessage, actualMessage);
		
		
		
	}

}
