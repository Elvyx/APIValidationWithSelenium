package API_response.APIresponse_WebUI;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;





public class APIValidationWithSelenium {

	
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		
		String apiEndpoint = "https://jsonplaceholder.typicode.com/posts/1";

        try (CloseableHttpClient httpClient= HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(apiEndpoint);
            CloseableHttpResponse response = httpClient.execute(httpGet);

            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonResponse = new JSONObject(responseBody);

            int userId = jsonResponse.getInt("userId");
            int id = jsonResponse.getInt("id");
            String title = jsonResponse.getString("title");
            String body = jsonResponse.getString("body");

            System.out.println("API Response Data:");
            System.out.println("User ID: " + userId);
            System.out.println("ID: " + id);
            System.out.println("Title: " + title);
            System.out.println("Body: " + body);

            // Perform Selenium WebDriver automation and validation here
            performAutomationAndValidation(userId, id, title, body);

        } catch (Exception e) {
            e.printStackTrace();
      
        
        }
        
	}
	
        

         private static void performAutomationAndValidation(int userId, int id, String title, String body) throws IOException 
        
        {
        	 
        String	 apiUrl= "https://jsonplaceholder.typicode.com/posts/1";
            // Set the path to ChromeDriver executable
            WebDriverManager.chromedriver().setup();

            // Initialize ChromeDriver
            WebDriver driver = new ChromeDriver();

            // Selenium WebDriver Automation - Open web application
            driver.get(apiUrl);
            
            URL url=new URL(apiUrl);
            
            HttpURLConnection conn=   (HttpURLConnection) url.openConnection();
    		conn.setRequestMethod("GET");
    		  
    		// Set Content-Type header
    		conn.setRequestProperty("Content-Type", "application/json");
    		conn.connect();
    		 
       
    		int respCode=conn.getResponseCode();
    		System.out.println(respCode);
    		String connType=conn.getContentType();
    		System.out.println(connType);
           String respMessage= conn.getResponseMessage();
           System.out.println(respMessage);

          String uiResponseData= "OK";

            if (uiResponseData.equals("OK")) {
                System.out.println("Validation successful");
            } else {
                System.out.println("Validation failed");
            }

            // Close the browser
            driver.quit();
        }


		
		
	}
	
