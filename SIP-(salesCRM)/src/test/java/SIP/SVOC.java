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

public class SVOC extends Dealer_User_Login  {
	 public static WebDriver driver;
	    public WebDriverWait wait;
	    String B_id;
	    String CUSTOMER_Name;
	    String SvOC;
	    String BOOKING_Number;
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
	  //..................................................................................................
	    @Test(priority = 5)
	    public void Booking_Id() throws InterruptedException {	       	    
	    	 WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));  	
	    	 WebElement BOOKING_Id = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-booking-table//table/tbody//td[1]")));
	    	   B_id=BOOKING_Id.getText();
	    	  System.out.println("Booking id :: "+B_id);	    	  
	    	}
	  //...................................................................    
	    @Test(priority = 6)
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
		    @Test(priority = 7)
		      public void Customer_Name() throws InterruptedException {	       	    
		    	try {
		    	CustomerName();
		    	}catch(Exception e){
		    		Assert.fail("TEST FAILED :: \n Customer name is NULL");
		    	}
	  	  }
		  //...................................................................    
		    @Test(priority = 8)
		      public void SVOC_Customer_Name() throws InterruptedException {	       	    
		    	try {
		    		SVOC();
		    	}catch(Exception e){
		    		Assert.fail("TEST FAILED :: \n Customer name in SVOC is NULL");
		    	}
	  	  }
		  //...................................................................    
		    @Test(priority =9)
		      public void Verify_Customer_NAME_with_SVOC() throws InterruptedException {	       	    
		    	  Assert.assertEquals(CUSTOMER_Name,SvOC,"Customer name not match with SVOC Customer ");		    	
		    	}
		    
		  //...................................................................    
		    @Test(priority = 10)
		      public void Booking_NO() throws InterruptedException {	       	    
		    	try {
		    		BOoking_Number();
		    	}catch(Exception e){
		    		Assert.fail("TEST FAILED :: \n BOoking Number is NULL");
		    	}
	  	  }
			  //...................................................................    
		    @Test(priority =11)
		      public void Verify_Booking_ID() throws InterruptedException {	       	    
		    	  Assert.assertEquals(B_id,BOOKING_Number,"Booking Number not match with Booking Id ");		    	
		    	}
//...........................................................................................	
	    public void CustomerName() {
	    	try {
	  	  	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
	  	      WebElement Customer_NAME = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-header-dashboard//h5")));
	  		    CUSTOMER_Name=Customer_NAME.getText();
	  		   System.out.println("Customer NAme ::"+ CUSTOMER_Name);
	  		    
	  		    wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-header-dashboard//h5")));
	  	  	  } catch (Exception e) {
	  		          Assert.fail("Test FAILED  :: In (Pending Booking),Customer Name is not showing ::" );
	  		      }
	    }
	    	//...........................................................................................	
	    public void BOoking_Number() {
	        try {
	            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));    	
	            WebElement BOOKING_No = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),\"Booking No.:\")]")));
	            String BOOKING_Text = BOOKING_No.getText();

	            // Extract only the booking number
	             BOOKING_Number = BOOKING_Text.replace("Booking No.: ", "").trim();
	            
	            System.out.println("Booking Number: " + BOOKING_Number);
	        } catch (Exception e) {
	            Assert.fail("Test FAILED :: In (Pending Booking), Booking No. is not showing ::");
	        }
	    }

	    
	  //...........................................................................................	
	    public void SVOC() {
	    	try {
	  	  	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
	  	      WebElement Svoc = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\" SVOC \"]")));	  		  
	  		    Svoc.click();
		      wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//app-svoc-pops//mat-dialog-content//h6)[1]")));
 
	  		  WebElement CustomerName_SVOC=wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//app-svoc-pops//mat-dialog-content//h6)[1]")));
	  		  SvOC=CustomerName_SVOC.getText();
	  		 System.out.println("SVOC- Customer Name ::"+ SvOC);
	  		 
	  		 Thread.sleep(500);
	  		  WebElement Cancelled=wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\"Cancel\"]")));
	  		  Cancelled.click();
	  		  
	  	  	  } catch (Exception e) {
	  		          Assert.fail("Test FAILED  :: In (Pending Booking),Customer Name is not showing in SVOC  ::" );
	  		      }
	  	  	  
	  	  	  }
	  
	    //.....................................................................
		public void clickElementUsingJS(WebDriver driver, WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}
		

}
