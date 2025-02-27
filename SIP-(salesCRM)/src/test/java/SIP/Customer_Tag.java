package SIP;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Customer_Tag extends Dealer_User_Login  {
	 public static WebDriver driver;
	    public WebDriverWait wait;
	  
	    String CUSTOMER;
	    String customerCategory;
	    //..................................................................................................
	    @SuppressWarnings("static-access")
	    @BeforeClass
	    public void sip() throws InterruptedException {
	        this.driver = Dealer_User_Login.driver;	    
	        }

	    //..................................................................................................
	    @Test(priority = 4)
	    public void Click_On_Additional_Fillter_To_Clear_Data() throws InterruptedException {
	       	    
	    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    // We have to check that Additional fillter is clikable or not	
	    	try {    	
	    	 WebElement Additional_Fillter_Button = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"filter-span\"]")));
	    	   	    	
	    	 if (Additional_Fillter_Button.isDisplayed() && Additional_Fillter_Button.isEnabled()) {
	    		 Additional_Fillter_Button.click();
		           // System.out.println("Additional Fillter_Button clicked successfully.");
		        } else {
		            System.out.println("Additional Fillter_Button  is not clickable. Test Failed!");
		            throw new RuntimeException("Additional Fillter_Button is not clickable.");
		        }
		    } catch (Exception e) {
		    	Assert.fail("Additional Fillter_Button could not be clickable.");
		        throw new RuntimeException("Additional Fillter_Button could not be clickable.");
		    }	    	
	    	// click on clear button to clear the data 	    		    
		      WebElement Clear = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"Clear\"]")));
	 	  clickElementUsingJS(driver, Clear);
	 	  
	 	  WebElement Close_Button = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"close-btn\"]")));
		  clickElementUsingJS(driver, Close_Button);
		 
	   // wait for some Time 
		  Thread.sleep(5000);   	   
	       }  
	    
	  //...................................................................    
	    @Test(priority = 5)
	      public void Click_On_Customer_Page() throws InterruptedException {	       	    
	  	  try {
	  	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
	      WebElement Click_On_Customer = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-booking-table//table/tbody//td[3]")));
		    clickElementUsingJS(driver, Click_On_Customer);
		    
		    wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-vehicle-status//div[contains(@class, 'vehicle-status')]//div[position()=7]/h6")));
	  	  } catch (Exception e) {
		          Assert.fail("Test FAILED  ::In (Pending Booking),Customer Details is not showing ::" );
		      }
	  	  
	  	  }
	    
	  //...................................................................    
	    @Test(priority = 6)
	      public void Verify_VIP() throws InterruptedException {	
	    	Click_On_Customer_TAG("VIP"); // click on Customer Tag
	    	Customer_Category(1);  // select VIP
	    	Thread.sleep(1000);
	    	VerifyCustomerTag("VIP ");  // verify VIP tag is diplay
	    
	    }
	    
	  //...................................................................    
	    @Test(priority = 7)
	      public void Verify_Marriage() throws InterruptedException {	
	    	Click_On_Customer_TAG("Marriage"); // click on Customer Tag
	    	Customer_Category(2);
	    	Thread.sleep(1000);
	    	VerifyCustomerTag("Marriage");     // verify marriage tag is diplay
	    }
	 //...................................................................    
	    @Test(priority = 8)
	      public void Verify_Birthday () throws InterruptedException {	
	    	Click_On_Customer_TAG("Birthday"); // click on Customer Tag
	    	Customer_Category(3); 
	    	Thread.sleep(1000);
	    	VerifyCustomerTag("Birthday");     // verify Birthday tag is diplay
	    }
	    
	  //...................................................................    
	    @Test(priority = 9)
	      public void Verify_Anniversary  () throws InterruptedException {	
	    	Click_On_Customer_TAG("Anniversary"); // click on Customer Tag
	    	Customer_Category(4); 
	    	Thread.sleep(1000);
	    	VerifyCustomerTag("Anniversary");     // verify Anniversary tag is diplay
	    }
	    
	    //...................................................................    
	    @Test(priority = 10)
	      public void Verify_Other  () throws InterruptedException {	
	    	Click_On_Customer_TAG("Others"); // click on Customer Tag
	    	Customer_Category(5); 
	    	Thread.sleep(1000);
	    	VerifyCustomerTag("Others");     // verify Anniversary tag is diplay
	    }
//>>>>>>>>>>>>>>>>>>>>>>>>....HELPER METHOD.....>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	    public void Click_On_Customer_TAG(String Tag) throws InterruptedException {	       	    
		  	  try {
		  	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
		      WebElement Click_On_Customer_TAG = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Customer Tag \"]")));
			    clickElementUsingJS(driver, Click_On_Customer_TAG);
			    
			    wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\"Customer Categorization\"]")));
		  	  } catch (Exception e) {
			          Assert.fail("Test FAILED  ::In (Pending Booking), \n Customer-TAG is not open After Click on ,Customer TAG for "
			          		+ Tag );
			      }	  	  
		  	  }
//....................................................................................	    

	    public void VerifyCustomerTag(String TAGS) {
	    	 WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
		      WebElement CustomerTag = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Customer Tag']/following::h6[1]")));
			  CUSTOMER=CustomerTag.getText(); 
			  System.out.println("CustomerTag  ::"+CUSTOMER);
			  
	            Assert.assertEquals(CUSTOMER,customerCategory,"Selected '" + TAGS + "' does not show in Customer Tag");

	    }
//............................................................................................	    
	    public void Customer_Category(int optionIndex) throws InterruptedException {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));    
	        WebElement category = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class=\"mdc-label\"])[" + optionIndex + "]")));	        
	        category.click();	        	        
	         customerCategory = category.getText(); 
	        System.out.println("Customer_Category  ::" + customerCategory);
	        
	        Thread.sleep(500);
	        OtherRemarks();
	      
	        WebElement Update = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"Update\"]")));	        
	        Update.click();	
	        Thread.sleep(1000);
	    }
	 //.................................................................................
	  
	    public void OtherRemarks() throws InterruptedException {
	        try {
	            // Ensure implicit wait does not interfere
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

	            // Using a separate wait with 1-second timeout
	            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
	            WebElement Remarks = shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='text']")));

	            if (Remarks.isDisplayed()) {
	                Remarks.sendKeys("Others");
	                System.out.println("Text entered in Remarks field.");
	            }
	        } catch (TimeoutException e) { // Catch TimeoutException instead of generic Exception
	            System.out.println("Remarks field is not visible within ");
	        } finally {
	            // Restore implicit wait to avoid affecting other parts of execution
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	        }
	    }


	    //.....................................................................
		public void clickElementUsingJS(WebDriver driver, WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}
		

}
