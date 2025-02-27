package SIP;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

public class Check_Offer extends Dealer_User_Login  {
	 public static WebDriver driver;
	    public WebDriverWait wait;
	    
	    String MODEL;
	    String VARIENT;	
	   
	    String MODEL2;
	    String VARIENT2;	
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
	      public void Vehicle_Details() throws InterruptedException {	       	    
	    	try {
	    		VEHICLE_Detail();
	    	}catch(Exception e){
	    		Assert.fail("TEST FAILED :: \n Vehicle Details is NULL");
	    	}
  	  }
	    
	    //...................................................................    
	    @Test(priority = 8)
	      public void CHECK_Offer_TAB() throws InterruptedException {	       	    
	    	try {
	    		Check_Offer();
	    	}catch(Exception e){
	    		Assert.fail("TEST FAILLED :: Model/Varient is not  is not showing in Check offer TAB ");
	    	}
  	  }
	    
	    
	    //...................................................................    
	    @Test(priority =9)
	      public void Verify_Check_Offer_TAB() throws InterruptedException {	       	    
	    	List<String> failedAssertions = new ArrayList<>();

	          if (!MODEL.equals(MODEL2)) {
	              failedAssertions.add("Model names are different in Check Offer TAB");
	          }
	          if (!VARIENT.equals(VARIENT2)) {
	              failedAssertions.add("Model names are different in Check Offer TAB");
	         
	          if (!failedAssertions.isEmpty()) {
	              Assert.fail(String.join("\n", failedAssertions));
	          }
	      }	    	}
	  //..............................................................................
	  //..........................................................................  
	    public void VEHICLE_Detail() {
	    try {
	    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));	   
	    	WebElement Model3 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//app-vehicle-status//h6)[2]")));
	    	WebElement Varient3 = driver.findElement(By.xpath("(//app-vehicle-status//h6)[3]"));
	    
	    	 MODEL = Model3.getText();
	    	 VARIENT = Varient3.getText();
	    	 
	    	System.out.println("Vehicle_Details_Model :: "   +  MODEL );
	    	System.out.println("Vehicle_Details_Varient  ::"   +  VARIENT );
	    }catch(Exception e){
	    	Assert.fail("TEST FAILLED :: Vehicle Details is not diplay ");
	    }
	    }
	    
	    //..........................................................................  
	    public void Check_Offer() {
	    try {
	    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));	   
	    	WebElement Offer = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\" Check Offers \"]")));
	    	Offer.click();
	    	Thread.sleep(1000);
	    	
	    	WebElement Model3 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-dialog-content//form//section//div[1]/div[1]/p")));
	    	WebElement Varient3 = driver.findElement(By.xpath("//mat-dialog-content//form//section//div[1]/div[2]/p"));
	    
	    	 MODEL2 = Model3.getText();
	    	 VARIENT2 = Varient3.getText();
	    	 
	    	System.out.println("Check_Offer_Model :: "   +  MODEL2 );
	    	System.out.println("Check_Offer_Varient  ::"   +  VARIENT2 );
	    	
	    	WebElement Cancell = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"Close\"]")));
	    	clickElementUsingJS(driver,Cancell);
	    	
	    }catch(Exception e){
	    	Assert.fail("TEST FAILLED :: Model/Varient is not  is not showing in Check offer TAB ");
	    }
	    }
	    //.....................................................................
		public void clickElementUsingJS(WebDriver driver, WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}
		

}
