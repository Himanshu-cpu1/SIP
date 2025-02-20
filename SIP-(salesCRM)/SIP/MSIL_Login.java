package SIP;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MSIL_Login {
	 public static WebDriver driver;
	    public WebDriverWait wait;

	    @SuppressWarnings("deprecation")
		@BeforeClass
	    public void setup() {              
	    	 WebDriverManager.chromedriver().setup();
	         driver = new ChromeDriver();
	         driver.get("https://dev.marutisuzukicjap.co.in/sip/booking-list");
	         driver.manage().window().maximize();
	         driver.manage().timeouts().implicitlyWait(16, TimeUnit.SECONDS);
	     }
	    
	  //........................On_MSIL_User.............................................................................  	         
        @Test(priority=1)
        public void Click_On_MSIL_User() throws InterruptedException {
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement MSIL_User = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"MSIL User\"]")));
             if(!MSIL_User.isDisplayed()) {
            	 Assert.fail("Failed :: MSIL User not display ");             
              }else{
        	   MSIL_User.click();
            }
          }
        
    //..........................................................................
        @Test(priority=2)
        public void Click_On_Window_Credentials() throws InterruptedException {
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement Window_Credentials = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Click here to login with windows Credentials \"]")));
             if(!Window_Credentials.isDisplayed()) {
            	 Assert.fail("Failed :: Click here to login with windows Credentials , not display ");             
              }else{
            	  Window_Credentials.click();
            }
          }
      //............................................................................................
        
            @Test(priority = 3)
            public void Login_with_UserID_Password() throws InterruptedException {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // 1. Login with Invalid credentials
                attemptLogin(wait, "Maruti\\9786", "Ranjita@0233");
                verifyLoginFailure(wait, "Incorrect user ID or password. Type the correct user ID and password, and try again.");
                clearFields(wait);  // clear username & password
               
                
                //2. Login with Invalid credentials
                attemptLogin(wait, "Maruti\\000", "Ranita@0233");
                verifyLoginFailure(wait, "Incorrect user ID or password. Type the correct user ID and password, and try again.");
                clearFields(wait);  // clear username & password
                
                
             // Login with Valid credentials
                attemptLogin(wait, "Maruti\\267597", "Ranjita@0987");
                verifyLoginSuccess(wait);
            }
  //............................................................................................          
            @Test(priority = 4)
            public void Click_On_SIP() throws InterruptedException {
                WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
                try {
                    WebElement SIP = wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@alt='SIP']")));
                    Actions actions = new Actions(driver);
                    actions.doubleClick(SIP).perform();

                    boolean urlChanged = wait1.until(ExpectedConditions.urlToBe("https://dev.marutisuzukicjap.co.in/sip/booking-list"));
                    if (urlChanged) {
                        System.out.println("Test Passed: Navigated to the expected URL");
                    } else {
                        Assert.fail("Test Failed: Wrong Booking list Url after MSIL Login");
                    }
                } catch (Exception e) {
                    Assert.fail("Test Failed: Wrong Booking list Url after MSIL Login");
                }
            }
        
//...................................................................................................
           @AfterClass
            public void tearDown() {
             driver.quit();
          }
                                              
            // Helper method for logging in
            private void attemptLogin(WebDriverWait wait, String username, String password) {
                WebElement Username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name=\"UserName\"]")));
                WebElement Password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type=\"password\"]")));
                WebElement SignIn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"Sign in\"]")));

                Username.sendKeys(username);
                Password.sendKeys(password);
                SignIn.click();
            }

            // Helper method to verify login failure
            private void verifyLoginFailure(WebDriverWait wait, String expectedErrorMessage) {
                try {
                    WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\"" + expectedErrorMessage + "\"]")));
                    Assert.assertTrue(errorMessage.isDisplayed(), "Test Failed: Login with invalid credentials should have failed.");
                } catch (Exception e) {
                    Assert.fail("Test Failed: Expected error message not found, login might have succeeded unexpectedly.");
                }
            }

            // Helper method to clear fields
            private void clearFields(WebDriverWait wait) throws InterruptedException {
                WebElement Username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name=\"UserName\"]")));
                WebElement Password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type=\"password\"]")));

                Username.clear();
                Thread.sleep(300);
                Password.clear();
                Thread.sleep(300);
            }

            // Helper method to verify login success
            private void verifyLoginSuccess(WebDriverWait wait) {
             try {
                    WebElement SIP = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@alt='SIP']")));
                  if(!SIP.isDisplayed()) {
                	  Assert.fail("Test Failed : With valid credentials");
                  }
             }catch(Exception e){  
            	 Assert.fail("Test Failed : With valid credentials");
             }
            }
            
}


