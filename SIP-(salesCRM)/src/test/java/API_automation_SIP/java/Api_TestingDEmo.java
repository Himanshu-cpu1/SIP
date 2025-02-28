package API_automation_SIP.java;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Api_TestingDEmo {

   Response response;	 
   String TOKEN_Generated="eyJraWQiOiJ5ZVdZdG0rZldUd3ZpVEF1XC9UYlFrU2d5WlVKZjdiMWJSNTdod3dGZCsrVT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJmNTM0NGE1Yi0yNTEwLTRjYzUtYWQ5NC00NTcwYzBiMmI2MWUiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGgtMS5hbWF6b25hd3MuY29tXC9hcC1zb3V0aC0xX0l1U0gwUnNjMCIsImV4dGVybmFsX2lkIjoiMjg4NTciLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiIyODg1NyIsInNkazphdXRoZW50aWNhdGlvbkZsb3dUeXBlIjoiVVNFUl9TUlBfQVVUSCIsInNkazp1c2VyUG9vbFdlYkNsaWVudElkIjoiMWxrZHB0cTJkcmZ0NGIyZGNuNXE3ZzUyMjIiLCJzZGs6Z2F0ZXdheVVybCI6Imh0dHBzOlwvXC95MnZzeWQ0N2VjLmV4ZWN1dGUtYXBpLmFwLXNvdXRoLTEuYW1hem9uYXdzLmNvbVwvY29tbW9uLWRldi1tdW1iYWlcLyIsImF1dGhfdGltZSI6MTc0MDc0NDAzMSwic2RrOmF1dGhTZXJ2ZXJVcmwiOiJodHRwczpcL1wveTJ2c3lkNDdlYy5leGVjdXRlLWFwaS5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2NvbW1vbi1kZXYtbXVtYmFpXC8iLCJleHAiOjE3NDA3NDc2MzEsImlhdCI6MTc0MDc0NDAzMSwianRpIjoiMTdjYTM5ZGMtOGZkNC00MTE5LWFiOTktYmY4ZDI0OGZkOTJmIiwiZW1haWwiOiJhbmlsa2FicmEuc2lrYXJAZ21haWwuY29tIiwic2RrOnVzZXJQb29sSWQiOiJhcC1zb3V0aC0xX0l1U0gwUnNjMCIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJncm91cHMiOiJTSVAtUUNFfFBTU0YtTlJNLVFDRXxBTEwtUEVSTUlTU0lPTi1HUk9VUCIsInBob25lX251bWJlcl92ZXJpZmllZCI6dHJ1ZSwiY29nbml0bzp1c2VybmFtZSI6ImQxMjMwYWQwLWI1MzYtNDhjNS05MmJhLTI0NmNiYWUyNzMzNCIsIm9yaWdpbl9qdGkiOiJiNjBlYjcxOC1iZTY5LTQzMzctYWZhOC1iNTBmY2JlMTRkODAiLCJhdWQiOiIxbGtkcHRxMmRyZnQ0YjJkY241cTdnNTIyMiIsImV2ZW50X2lkIjoiMWMwODRhOWEtMDFmZi00ZmU0LWE0YzctZWZhNWI0NTljN2Y3IiwidG9rZW5fdXNlIjoiaWQiLCJuYW1lIjoiQU5JTCBLVU1BUiBLQUJSQSIsInNkazphdXRoU2VydmVyIjoibXNpbC10b2tlbi1kZXYtc2VydmVyLmF1dGguYXAtc291dGgtMS5hbWF6b25jb2duaXRvLmNvbSIsInBob25lX251bWJlciI6Iis5MTk2OTQwODY1MDEiLCJzZGs6cmVnaW9uIjoiYXAtc291dGgtMSJ9.GJVgoLdSRDaqwlArRsJPRYMR2KzGEZbR9vpmbeANP7-8QYKhxBgL4C1FZMHczvcTlm5-SxhjOkhahgF_dpJDrv_nnoPl-z79FD635JzsgJBsdEXIApTBKY_AFThb1Xb65RH-TwFzySYUx7yl4TgzwPDHQerANLHCMhNSqz65zOV4mweaD4eF6qVuzREp4EPShDAAzoM50WqmZPM_8ZPPWQW2XhH_rzf0kT_ZGTh5Egcw-LNy4xFvU9eyn_e9sZlcEXwvBrfAbOpSEm59uBxyjgKfWdQbplofzVGW6WQcPKLC1O7obEhkUpXx4-n9m_cACfyzu11ElVXHhtGlnG305A";
	
   @Test(priority=1)
	public void EmployeeDataByMspin() {	   
	    RestAssured.baseURI = "https://psfsales-backend-preprod.dealercrm.co.in";
	    String requestBody = "{\"mspin\": 28857}";
	    String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
	    
	    // Sending POST request with Authorization and API Key headers
	     response = given()
	            .relaxedHTTPSValidation()
	            .contentType(ContentType.JSON)
	            .header("authorization", TOKEN_Generated) // 
	            .header("apikey", apiKey) // Change from "x-api-key" if needed
	            //.header("Accept", "application/json")
	            .body(requestBody)
	            .when()
	            .post("/psfData/employeeDataByMspin") // Ensure correct endpoint
	            .then()
	            .extract()
	            .response();

	     StatusCode();  // Get Status Code with Message
	}
//...................................................................................	
	 @Test(priority=2)
	    public void getColumnNames() {		    
	        RestAssured.baseURI = "https://psfsales.dev.dealercrm.co.in";	      
	        String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
	       	        
	     // Sending POST request with Authorization and API Key headers
		     response = given()
		            .relaxedHTTPSValidation()
		            .contentType(ContentType.JSON)
		            .header("authorization", TOKEN_Generated) // 
		            .header("apikey", apiKey) // Change from "x-api-key" if needed
		            
		            .when()
		            .get("/sip-api/getColumnNames") // Ensure correct endpoint
		            .then()
		            .extract()
		            .response();

		     StatusCode(); // Get Status Code with Message
	    }
	 
	//...................................................................................	
	 @Test(priority=3)
	    public void getCustomerTAGList() {			
	        RestAssured.baseURI = "https://psfsales.dev.dealercrm.co.in";	    
	        String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
	       	        
	     // Sending GET request with Authorization and API Key headers
		     response = given()
		            .relaxedHTTPSValidation()
		            .contentType(ContentType.JSON)
		            .header("authorization", TOKEN_Generated) // 
		            .header("apikey", apiKey) // Change from "x-api-key" if needed
		            
		            .when()
		            .get("/sip-api/getCustomerTagList") // Ensure correct endpoint
		            .then()
		            .extract()
		            .response();

		     StatusCode(); // Get Status Code with Message
	    }
	  
	//...................................................................................	
		 @Test(priority=4)
		    public void getBookingList() {						 
		        RestAssured.baseURI = "https://psfsales.dev.dealercrm.co.in";
		       
		        String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
		        // Request body
		        String requestBody = "{"
		        	    + "\"sortBy\": \"\","
		        	    + "\"pageNumber\": 1,"
		        	    + "\"pageSize\": 10,"
		        	    + "\"column\": \"\","
		        	    + "\"filters\": ["
		        	    + "    {\"key\": \"bookingType\", \"value\": \"PB\", \"from\": null, \"to\": null}"
		        	    + "],"
		        	    + "\"searchTerm\": \"\","
		        	    + "\"sortDirection\": \"\""
		        	    + "}";
	        
		     // Sending POST request with Authorization and API Key headers
			     response = given()
			            .relaxedHTTPSValidation()
			            .contentType(ContentType.JSON)
			            .header("authorization", TOKEN_Generated) // 
			            .header("apikey", apiKey) // Change from "x-api-key" if needed
			            .body(requestBody)
			            .when()
			            .post("/sip-api/getBookingList") // Ensure correct endpoint
			            .then()
			            .extract()
			            .response();

			        StatusCode(); // Get Status Code with Message
		    }
		  
		//...................................................................................	
		 @Test(priority=5)
		    public void getSubTabs() {			
			
		        RestAssured.baseURI = "https://psfsales.dev.dealercrm.co.in";
		      
		        String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
		       
		        // Change Request Body if Needed 
		    //    String requestBody = "{\"order\": 1, \"value\": \"Open Queries\", \"bookingType\": \"activeQuery\"}";
        
		     // Sending POST request with Authorization and API Key headers
			     response = given()
			            .relaxedHTTPSValidation()
			            .contentType(ContentType.JSON)
			            .header("authorization", TOKEN_Generated) // 
			            .header("apikey", apiKey) // Change from "x-api-key" if needed
			  //          .body(requestBody)
			            .when()
			            .post("/sip-api/getSubTabs") // Ensure correct endpoint
			            .then()
			            .extract()
			            .response();

			     StatusCode(); // Get Status Code with Message
		    }
	
		//...................................................................................	
		 @Test(priority=6)
		    public void UserLoginData() {					
		        RestAssured.baseURI = "https://uat.marutisuzukicjap.co.in";		        			      
		        String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
		       		   
		        String requestBody = "{"
		                + "\"mspin\": 28857,"
		                + "\"name\": \"ANIL KUMAR KABRA\","
		                + "\"dealership\": \"JAMU AUTOMOBILES PVT LTD\","
		                + "\"designation\": \"GMS\""
		                + "}";

		     // Sending POST request with Authorization and API Key headers
		         response = given()
		                .relaxedHTTPSValidation()
		                .contentType(ContentType.JSON)
		                .header("authorization", TOKEN_Generated)
		                .header("apikey", apiKey)
		                .body(requestBody)
		                .when()
		                .post("/sip-api/userLoginData")
		                .then()
		                .extract()
		                .response();
		         
		                 StatusCode();  // Get Status Code with Message  
		            }
		//...................................................................................	
		 @Test(priority=7)
		    public void UpdateVehicleStatus() {			
			  RestAssured.baseURI = "https://psfsales.dev.dealercrm.co.in";		        			      
		        String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
		        String bookingIdAv = "SOB24000033JAMUFT112955";
		        boolean status = true;  // Ensuring it's a boolean, not a string

		        // JSON payload
		        String requestBody = "{"
		                + "\"bookingIdAv\": \"" + bookingIdAv + "\","
		                + "\"status\": " + status
		                + "}";

		        Response response = given()
		                .relaxedHTTPSValidation()
		                .contentType(ContentType.JSON)
		                .header("authorization", TOKEN_Generated)
		                .header("apikey", apiKey)
		                .body(requestBody)  // Sending JSON payload in request body
		                .when()
		                .post("/sip-api/updateVehicleStatus") // API endpoint
		                .then()
		                .extract()
		                .response();

		        // Print status and response body for debugging
		        System.out.println("Status Code: " + response.getStatusCode());
		        System.out.println("Response Body: " + response.getBody().asString());

		        // Validate response status
		        StatusCode();
		    }

		//...................................................................................	
		 @Test(priority = 8)  //Query Param
		    public void Update_CustomerExpected_Date() {					
		        RestAssured.baseURI = "https://psfsales.dev.dealercrm.co.in";
		        
		        String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
		        String bookingIdAv = "SOB24001687JAMUSIK10203";
		        String preferedDeliveryDate = "21-12-2024";
		        
		        Response response = given()
		                .relaxedHTTPSValidation()
		                .contentType(ContentType.JSON)
		                .header("authorization", TOKEN_Generated)
		                .header("apikey", apiKey)
		                .queryParam("bookingIdAv", bookingIdAv)  // Passing as query parameter
		                .queryParam("preferedDeliveryDate", preferedDeliveryDate) // Corrected date format
		                .when()
		                .post("/sip-api/updateCustomerExpectedDate")  // No need to append params in URL
		                .then()
		                .extract()
		                .response();

		        // Print status and response body for debugging
		        System.out.println("Status Code: " + response.getStatusCode());
		        System.out.println("Response Body: " + response.getBody().asString());

		        // Custom method to validate response status code
		        StatusCode();
		    }
		 
		//...................................................................................	
		 @Test(priority=9)
		    public void UpdateCustomerTAG() {					
		        RestAssured.baseURI = "https://psfsales.dev.dealercrm.co.in";		        			      
		        String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
		       		   
		        String bookingIdAv = "SOB24001687JAMUSIK10203";
		        String customerTag = "VIP,Marriage,Birthday,Anniversary, ljjdhash"; // All tag we have pass
		     // Sending POST request with Authorization and API Key headers
		         response = given()
		                .relaxedHTTPSValidation()
		                .contentType(ContentType.JSON)
		                .header("authorization", TOKEN_Generated)
		                .header("apikey", apiKey)
		                .queryParam("bookingIdAv", bookingIdAv)  // Passing as query parameter
		                .queryParam("customerTag", customerTag) // Corrected date format
		                .when()
		                .post("/sip-api/updateCustomerTag")
		                .then()
		                .extract()
		                .response();
		         
		                 StatusCode();  // Get Status Code with Message  
		            }
		//...................................................................................	
		 @Test(priority=10)
		    public void getReceiptDetails() {					
		        RestAssured.baseURI = "https://psfsales.dev.dealercrm.co.in";		        			      
		        String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
		       		   
		        String bookingIdAv = "SOB24001697JAMUSIK10203";
		     // Sending POST request with Authorization and API Key headers
		         response = given()
		                .relaxedHTTPSValidation()
		                .contentType(ContentType.JSON)
		                .header("authorization", TOKEN_Generated)
		                .header("apikey", apiKey)
		                .queryParam("bookingIdAv", bookingIdAv)  // Passing as query parameter		            
		                .when()
		                .post("sip-api/getReceiptDetails")
		                .then()
		                .extract()
		                .response();
		         
		                 StatusCode();  // Get Status Code with Message  
		            }
		//...................................................................................	
		 @Test(priority=11)
		    public void getLoanStatus() {			
		
		        RestAssured.baseURI = "https://psfsales.dev.dealercrm.co.in";
		        			      
		        String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
		       		   
		        String requestBody = "{\"bookingIdAv\": \"SOB24001653JAMUSIK10203\"}";
        
		     // Sending POST request with Authorization and API Key headers
		         response = given()
		                .relaxedHTTPSValidation()
		                .contentType(ContentType.JSON)
		                .header("authorization", TOKEN_Generated)
		                .header("apikey", apiKey)
		                .body(requestBody)
		                .when()
		                .post("/sip-api/getLoanStatus?bookingIdAv=SOB24001653JAMUSIK10203")
		                .then()
		                .extract()
		                .response();
		         
		                   StatusCode();  // Get Status Code with Message  
		            }
		//...................................................................................	
		 @Test(priority=12)
		    public void getDocumentList() {			
				    RestAssured.baseURI = "https://psfsales.dev.dealercrm.co.in";		        			      
			        String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
			       		   
			        String bookingIdAv = "SOB24001697JAMUSIK10203";
			     // Sending POST request with Authorization and API Key headers
			         response = given()
			                .relaxedHTTPSValidation()
			                .contentType(ContentType.JSON)
			                .header("authorization", TOKEN_Generated)
			                .header("apikey", apiKey)
			                .queryParam("bookingIdAv", bookingIdAv)  // Passing as query parameter		            
			                .when()
			                .post("/sip-api/getDocumentList")
			                .then()
			                .extract()
			                .response();
			         
			                 StatusCode();  // Get Status Code with Message  
			            }
//...................................................................................	
		 @Test(priority=13)
		    public void getCustomerSVOC() {			
				    RestAssured.baseURI = "https://psfsales.dev.dealercrm.co.in";		        			      
			        String apiKey = "1lkdptq2drft4b2dcn5q7g5222";
			       		   
			        String bookingIdAv = "SOB24001697JAMUSIK10203";
			     // Sending POST request with Authorization and API Key headers
			         response = given()
			                .relaxedHTTPSValidation()
			                .contentType(ContentType.JSON)
			                .header("authorization", TOKEN_Generated)
			                .header("apikey", apiKey)
			                .queryParam("bookingIdAv", bookingIdAv)  // Passing as query parameter		            
			                .when()
			                .post("/sip-api/getCustomerSvoc")
			                .then()
			                .extract()
			                .response();
			         
			                 StatusCode();  // Get Status Code with Message  
			            }
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	 
	     	 private  void   StatusCode() {
			 // Validate response status code
		        int statusCode = response.getStatusCode();
		        String statusMessage = getHttpStatusMessage(statusCode);

		        Assert.assertEquals(statusCode, 200,"Expected status code 200 OK, but received: " + statusCode + " (" + statusMessage + ")");
		                
		        // Print response body
		        System.out.println("RESPONSE BODY :::::: >>>>>>>>>> \n  " +"\n "+ response.getBody().asString());
			 
		 }
//...............................................................................................
		    private static String getHttpStatusMessage(int statusCode) {
		        switch (statusCode) {
		            case 200: return "OK";
		            case 400: return "Bad Request";
		            case 401: return "Unauthorized";
		            case 403: return "Forbidden";
		            case 404: return "Not Found";
		            case 500: return "Internal Server Error";
		            case 502: return "Bad Gateway";
		            case 503: return "Service Unavailable";
		            case 504: return "Gateway Timeout";
		            default: return "Unknown Status";
		        }
		    }
		}
