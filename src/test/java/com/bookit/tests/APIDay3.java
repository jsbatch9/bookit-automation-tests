package com.bookit.tests;



import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.bookit.beans.Country;
import com.bookit.beans.CountryResponse;
import com.bookit.beans.Region;
import com.bookit.beans.RegionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.xjc.model.SymbolSpace;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
public class APIDay3 {
	
	
	String baseurl = "http://35.170.244.28:1000/ords/hr";

	/*Given Accept Type is JSON
	 * And Content-Type is JSON
	 * When I sent  to POST request to URL/regions/
	 * with following JSON BODY
	 * {
	 * 	"region_id":11,
  		"region_name":"myregion"
	 * 
	 * }
	 * 
	 * Then status code must be 201
	 * and response body should match request body 
	 */
	
	
	
  @Test
  public void regionPOSTv1() {
  
	  String requestBody = "{\"region_id\":15,\"region_name\":\"myregion\"}";
	  
	  Response response = given().accept(ContentType.JSON)
			  			.and().contentType(ContentType.JSON).and().body(requestBody)
			  			.when().post(baseurl+"/regions/");
			  
	  //verify status code for POST request
	  assertEquals(response.statusCode(), 201);
	  
	  JsonPath json =response.jsonPath();
	  //verify region name
	  assertEquals(json.getString("region_name"), "myregion");
	  //verify region id
	  assertEquals(json.getInt("region_id"), 15);
  
  }
  
  @Test
  public void regionPOSTv2() {
	  
	  //not a good way to pass JSON Request BODY
	  //String requestBody = "{\"region_id\":15,\"region_name\":\"myregion\"}";
	  
	  Map requestMap = new HashMap<>();
	  
	  requestMap.put("region_id", 124);
	  requestMap.put("region_name", "mytest");

	  //map will be converted to JSON 
	  Response response = given().accept(ContentType.JSON)
			  			.and().contentType(ContentType.JSON).and().body(requestMap)
			  			.when().post(baseurl+"/regions/");
			  
	  //verify status code for POST request
	  assertEquals(response.statusCode(), 201);
	  
	  JsonPath json =response.jsonPath();
	  //verify region name
	  assertEquals(json.getString("region_name"), "mytest");
	  //verify region id
	  assertEquals(json.getInt("region_id"), 124);
  
  }
  
  @Test
  public void regionPOSTv3() {
	  
	  Region region = new Region();
	  
	  region.setRegion_id(111);
	  region.setRegion_name("mynewregion");
	  
	  
	  //map will be converted to JSON 
	  Response response = given().accept(ContentType.JSON)
			  			.and().contentType(ContentType.JSON).and().body(region)
			  			.when().post(baseurl+"/regions/");
			  
	  //verify status code for POST request
	  assertEquals(response.statusCode(), 201);
	  
	  JsonPath json =response.jsonPath();
	  //verify region name
	  assertEquals(json.getString("region_name"), "mynewregion");
	  //verify region id
	  assertEquals(json.getInt("region_id"), 111);
	    
  }
  
  @Test
  public void regionPOSTv4() {
	  
	  Region region = new Region();
	  
	  region.setRegion_id(156);
	  region.setRegion_name("mynewregion");
	  
	  
	  //map will be converted to JSON 
	  Response response = given().accept(ContentType.JSON)
			  			.and().contentType(ContentType.JSON).and().body(region)
			  			.when().post(baseurl+"/regions/");
			  
	  //verify status code for POST request
	  assertEquals(response.statusCode(), 201);
	  
	  //create our POJO object for response body
	  
	  RegionResponse regionresponse = response.as(RegionResponse.class);
	  
	  assertEquals(regionresponse.getRegion_id(), region.getRegion_id());
	  assertEquals(regionresponse.getRegion_name(), region.getRegion_name());
	  
	    
  }
  
  /*Given Accept Type is JSON
	 * And Content-Type is JSON
	 * When I sent  to POST request to URL/countries/
	 * with following JSON BODY
	 * {
	 "country_id": "AA",
     "country_name": "Cybertek",
     "region_id": 2
		}
	 * 
	 * Then status code must be 201
	 * and response body should match request body 
	 */
	
	  @Test
	  public void countryPOST() {
		  
		 Country country = new Country();
		 
		 country.setCountry_id("AA");
		 country.setCountry_name("Cybertek");
		 country.setRegion_id(2);
		 
		 Response response = given().accept(ContentType.JSON)
				 .and().contentType(ContentType.JSON).and().body(country)
				 .when().post(baseurl+"/countries/");
		 
		 //start testing
		 
		 //status code
		 assertEquals(response.statusCode(), 201);
		 
		 
		 //get JSON body from response and convert it to JAva object(countryResponse)
		 CountryResponse countryResponse = response.as(CountryResponse.class);
		 
		 
		 //verify req body and response body
		 assertEquals(countryResponse.getCountry_id(), country.getCountry_id());
		 assertEquals(countryResponse.getCountry_name(), country.getCountry_name());
		 assertEquals(countryResponse.getRegion_id(), country.getRegion_id()); 
		  
	  }
  
  
	  @Test
	  public void countryPUT() {
		  
		 Country country = new Country();
		 
		 country.setCountry_id("AA");
		 country.setCountry_name("CCC");
		 country.setRegion_id(2);
		 
		 Response response = given().accept(ContentType.JSON)
				 .and().contentType(ContentType.JSON).and().body(country)
				 .when().put(baseurl+"/countries/"+country.getCountry_id());
		 
		 //start testing
		 
		 //status code
		 assertEquals(response.statusCode(), 200);
		 
		 
		 //get JSON body from response and convert it to JAva object(countryResponse)
		 CountryResponse countryResponse = response.as(CountryResponse.class);
		 
		 
		 //verify req body and response body
		 assertEquals(countryResponse.getCountry_id(), country.getCountry_id());
		 assertEquals(countryResponse.getCountry_name(), country.getCountry_name());
		 assertEquals(countryResponse.getRegion_id(), country.getRegion_id()); 
		  
	  }
  
	  
	  @Test
	  public void CountryDELETE() {
		  Response response = given().accept(ContentType.JSON).when().delete(baseurl+"/countries/AA");
		  
		  assertEquals(response.statusCode(), 200);
		  
		  JsonPath json = response.jsonPath();
		  
		  assertEquals(json.getInt("rowsDeleted"), 1);
		  		  
	  }
  
	 //GSON AND JACKSON
	  
	  @Test
	  public void jacksonExample() throws JsonProcessingException {
		  
		  Country country = new Country();
		
			 country.setCountry_id("AA");
			 country.setCountry_name("CCC");
			 country.setRegion_id(2);
		  
	  ObjectMapper mapper = new ObjectMapper();
	  
	  String jsonRequest = mapper.writeValueAsString(country);
	  
	  System.out.println(jsonRequest);
	  
	  
	  }
  
  
  
  
  
  
  
}
