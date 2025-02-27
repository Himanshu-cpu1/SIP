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

public class Customer_Tag   {
	 public static WebDriver driver;
	    public WebDriverWait wait;
	  
	    String CUSTOMER;
	    String customerCategory;
	    //..................................................................................................
	    @SuppressWarnings("static-access")
	    @BeforeClass
	    public void sip() throws InterruptedException {
	        this.driver = Customer_Query.driver;	    
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
	    	Customer_Category(2);             // select Marriage
	    	Thread.sleep(1000);
	    	VerifyCustomerTag("Marriage");     // verify marriage tag is diplay
	    }
	 //...................................................................    
	    @Test(priority = 8)
	      public void Verify_Birthday () throws InterruptedException {	
	    	Click_On_Customer_TAG("Birthday"); // click on Customer Tag
	    	Customer_Category(3);             // select Birthday
	    	Thread.sleep(1000);
	    	VerifyCustomerTag("Birthday");     // verify Birthday tag is diplay
	    }
	    
	  //...................................................................    
	    @Test(priority = 9)
	      public void Verify_Anniversary  () throws InterruptedException {	
	    	Click_On_Customer_TAG("Anniversary"); // click on Customer Tag
	    	Customer_Category(4);             // select Anniversary
	    	Thread.sleep(1000);
	    	VerifyCustomerTag("Anniversary");     // verify Anniversary tag is diplay
	    }
	    
	    //...................................................................    
	    @Test(priority = 10)
	      public void Verify_Other  () throws InterruptedException {	
	    	Click_On_Customer_TAG("Others"); // click on Customer Tag
	    	Customer_Category(5);             // select Anniversary
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
