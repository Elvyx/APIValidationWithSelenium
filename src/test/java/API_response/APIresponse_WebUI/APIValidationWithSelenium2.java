package API_response.APIresponse_WebUI;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;





public class APIValidationWithSelenium2 {

	
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		
		String apiEndpoint = "https://demoqa.com/Account/v1/User";
		
		String requestData = "{\n" +
                "    \"userName\": \"Lulabi\",\n" +
                "    \"password\": \"Password123*\"\n" +
                "}";

        try (CloseableHttpClient httpClient= HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(apiEndpoint);
            httpPost.setEntity(new StringEntity(requestData));
            httpPost.setHeader("Content-type", "application/json");
            
            CloseableHttpResponse response = httpClient.execute(httpPost);

            String responseBody = EntityUtils.toString(response.getEntity());
          
            // Handle the responseBody as needed
            System.out.println("API Response Body:");
            System.out.println(responseBody);
            
            JSONObject jsonResponse = new JSONObject(responseBody);
            
         // Check if the response contains an error message
            if (jsonResponse.has("code") && jsonResponse.has("message")) {
                String errorCode = jsonResponse.getString("code");
                String errorMessage = jsonResponse.getString("message");
                System.out.println("API Error Response:");
                System.out.println("Code: " + errorCode);
                System.out.println("Message: " + errorMessage);
            } else {
            	
            	
            }
            String userId = jsonResponse.getString("userID");
            String username = jsonResponse.getString("username");
            
            
URL url= new URL(apiEndpoint);
            
            HttpURLConnection conn=   (HttpURLConnection) url.openConnection();
    		conn.setRequestMethod("POST");
    		conn.connect();
    		
    		int respCode=conn.getResponseCode();
    		System.out.println(respCode);
    		String ct=conn.getContentType();
    		System.out.println(ct);
    		String respMessage=conn.getResponseMessage();
    		System.out.println(respMessage);
    		
    		
    		
    		
        
  
            
            // Perform Selenium WebDriver automation and validation here
            performAutomationAndValidation(userId, username);
            

        } catch (Exception e) {
            e.printStackTrace();
      
        
        }
        
	}
	
        

         private static void performAutomationAndValidation(String userId,  String username) throws IOException, InterruptedException 
        
        {
        	 
        String	 webUrl= "https://demoqa.com/register";
            // Set the path to ChromeDriver executable
            WebDriverManager.chromedriver().setup();

            // Initialize ChromeDriver
            WebDriver driver = new ChromeDriver();
            
            driver.manage().window().maximize();

            // Selenium WebDriver Automation - Open web application
            driver.get(webUrl);
            

            // Fill out form and create a record using API data
            WebElement firstName = driver.findElement(By.id("firstname"));
            firstName.sendKeys("Thelma");

            WebElement lastName = driver.findElement(By.id("lastname"));
            lastName.sendKeys("Teddy");
            
            
            WebElement userName = driver.findElement(By.id("userName"));
            userName.sendKeys("Thel-Tee");
            
            WebElement loginPassword = driver.findElement(By.id("password"));
            loginPassword.sendKeys("Password123*");
          
            
            
            WebElement captcha= driver.findElement(By.xpath("//div[@id='g-recaptcha']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", captcha);

            captcha.click();
            
            WebElement regButton= driver.findElement(By.cssSelector("#register"));
            regButton.click();
            
            
            

            // Validation - Retrieve data from the web application and compare with API response
            Alert alert = driver.switchTo().alert();
            String uiResponseData=alert.getText();
            alert.accept();
            
            
            URL url= new URL(webUrl);
            
            HttpURLConnection conn=   (HttpURLConnection) url.openConnection();
    		conn.setRequestMethod("POST");
    		  
    		// Set Content-Type header
    		conn.setRequestProperty("Content-Type", "application/json");
    		conn.connect();
    		 
       
    		int respCode=conn.getResponseCode();
    		System.out.println(respCode);
    		String connType=conn.getContentType();
    		System.out.println(connType);
           String respMessage= conn.getResponseMessage();
           System.out.println(respMessage);

          

            if (uiResponseData.equals("User Register Successfully.")) {
                System.out.println("Validation successful");
            } else {
                System.out.println("Validation failed");
            }

            // Close the browser
            
            driver.quit();
        }


		
		
	}
	
