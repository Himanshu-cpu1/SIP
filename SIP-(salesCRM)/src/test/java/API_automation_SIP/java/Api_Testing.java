package API_automation_SIP.java;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Api_Testing {


	@Test(priority=1)
	public void EmployeeDataByMspin() {
	  
		RestAssured.baseURI = "https://psfsales-backend-preprod.dealercrm.co.in";

	    // Request body
	    String requestBody = "{\"mspin\": 28857}";

	    // API Key
	    String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
	    
	    String Token =Generate_Token.getAuthToken();
	    System.out.println(Token);
	    // Sending POST request with Authorization and API Key headers
	    Response response = given()
	            .relaxedHTTPSValidation()
	            .contentType(ContentType.JSON)
	            .header("authorization", Token) // 
	            .header("apikey", apiKey) // Change from "x-api-key" if needed
	            //.header("Accept", "application/json")
	            .body(requestBody)
	            .when()
	            .post("/psfData/employeeDataByMspin") // Ensure correct endpoint
	            .then()
	            .extract()
	            .response();

	    // Print response for debugging
	    System.out.println("Response Body: " + response.getBody().asString());
	   
	    System.out.println("Response Headers: " + response.getHeaders());

	    // Validate status code
	    int statusCode = response.getStatusCode();
	    Assert.assertEquals(statusCode,200, "Expected status code is not returned");
	}
//...................................................................................	
	 @Test(priority=2)
	    public void getColumnNames() {
		
		 // API Base URL
	        RestAssured.baseURI = "https://psfsales.dev.dealercrm.co.in";

	        // API Key and Authorization Token
	        String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
	        String Token =Generate_Token.getAuthToken();
	        
	     // Sending POST request with Authorization and API Key headers
		    Response response = given()
		            .relaxedHTTPSValidation()
		            .contentType(ContentType.JSON)
		            .header("authorization", Token) // 
		            .header("apikey", apiKey) // Change from "x-api-key" if needed
		            
		            .when()
		            .get("/sip-api/getColumnNames") // Ensure correct endpoint
		            .then()
		            .extract()
		            .response();

	        // Validate response status code is 200 OK
	        Assert.assertEquals(response.getStatusCode(),200  ,  "Status code is not 200 OK");

	        // Print response body (optional)
	        System.out.println("Response Body: " + response.getBody().asString());
	    }
	 
	 
	 
	 
	
}