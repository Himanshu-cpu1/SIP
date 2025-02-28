package API_automation_SIP.java;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Generate_TokenDEMO {

	String tokenResponse;
	
	@Test
	public void Gettoken() {
		 tokenResponse = getAuthToken();
        if (tokenResponse != null) {
            System.out.println("Token Response: " + tokenResponse);
        } else {
            System.out.println("Failed to retrieve token.");
        }
    }
	
//........................................................................................			 		 
		 @SuppressWarnings("deprecation")
		public static String getAuthToken() {
		        String apiUrl = "https://api.preprod.developersatmarutisuzuki.in/auth/consumers/gettoken";
		        String jsonPayload = "{\"clientId\": \"2u4lituvbpc4cd13qitchjpomg\", \"clientSecret\": \"1tn02rc269em37frg5ho3hmsmb76ch9jcpqnkainulod1ms25iqi\"}";

		        try {
		            // Create a URL object
		            URL url = new URL(apiUrl);
		            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		            // Set request method to POST
		            connection.setRequestMethod("POST");
		            connection.setRequestProperty("Content-Type", "application/json");
		            connection.setDoOutput(true);

		            // Send the request payload
		            try (OutputStream os = connection.getOutputStream()) {
		                byte[] input = jsonPayload.getBytes("utf-8");
		                os.write(input, 0, input.length);
		            }

		            // Read the response
		            int responseCode = connection.getResponseCode();
		            if (responseCode == 200) {
		                try (Scanner scanner = new Scanner(connection.getInputStream(), "utf-8")) {
		                    String response = scanner.useDelimiter("\\A").next();
		                    return response; // Returns raw JSON response containing the token
		                }
		            } else {
		                System.out.println("Failed to get token. Response Code: " + responseCode);
		                return null;
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }
}
		 
		 
		
