package UAT_SIP2;

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

public class SVOC   {
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
	        this.driver = Check_On_Road_Price.driver;	    
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
		    @Test(priority = 10)
		      public void Booking_NO() throws InterruptedException {	       	    
		    	try {
		    		BOoking_Number();
		    	}catch(Exception e){
		    		Assert.fail("TEST FAILED :: \n BOoking Number is NULL");
		    	}
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
