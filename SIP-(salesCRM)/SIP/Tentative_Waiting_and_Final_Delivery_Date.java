package SIP;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

      public class Tentative_Waiting_and_Final_Delivery_Date extends Dealer_User_Login{
	  public static WebDriver driver;
	    public WebDriverWait wait;
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    //..................................................................................................
	    @SuppressWarnings("static-access")
	    @BeforeClass
	    public void SMR() throws InterruptedException {
	        this.driver = Dealer_User_Login.driver;
	    
         }
	    //..................................................................................................
	    @Test(priority = 3)
	    public void Click_On_SIP() throws InterruptedException {
	       	    
	    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	try {
	    	WebElement SIP = wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@alt='SIP']")));
	    	Actions actions = new Actions(driver);
	    	actions.doubleClick(SIP).perform();     
	    	boolean urlChanged = wait1.until(ExpectedConditions.urlToBe("https://dev.marutisuzukicjap.co.in/sip/booking-list"));

	    	// Validate the URL for Booking List
	   
	    	if (urlChanged) {
	    	    System.out.println("Test Passed: Navigated to the expected URL");
	    	} else {
	    	    
	    	    Assert.fail("Test Failed: Wrong Booking list Url");
	    	}
	    	}catch(Exception e){
	    		Assert.fail("Test Failed: Wrong Booking list Url");
	    	}
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
     	   Thread.sleep(5000);
     	   
	       }   
	//...................................................................    
	      @Test(priority = 5)
	        public void Click_Customer() throws InterruptedException {	       	    
	    	  try {
	    	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
	        WebElement Click_On_Customer = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-booking-table//table/tbody//td[3]")));
    	    clickElementUsingJS(driver, Click_On_Customer);
    	    
    	    wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-vehicle-status//div[contains(@class, 'vehicle-status')]//div[position()=6]/h6")));
	    	  } catch (Exception e) {
		          Assert.fail("(Pending Booking),Error occurred while Click on the Customer. Possible server issue ::" );
		      }
	    	  
	    	  }
	      
	    //...................................................................    
	      @Test(priority = 6)
	      public void Tentative_Waiting_Period_Difference() throws InterruptedException {	       	    
	    	  try {
	    	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
	      	
	          WebElement Booking_Date = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-vehicle-status//div[contains(@class, 'vehicle-status')]//div[position()=5]/h6")));
	          WebElement Tentative_Delivery_date = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-vehicle-status//div[contains(@class, 'vehicle-status')]//div[position()=6]/h6")));
	          WebElement Tentative_Waiting_Period = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-vehicle-status//div[contains(@class, 'vehicle-status')]//div[position()=7]/h6")));

	          String B_Date = Booking_Date.getText();  
	          String T_Delivery_Date = Tentative_Delivery_date.getText(); 
	          String T_Waiting_Period = Tentative_Waiting_Period.getText();  
	          
	          // Convert strings to LocalDate
	          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	          LocalDate bookingDate = LocalDate.parse(B_Date, formatter);
	          LocalDate waitingPeriodDate = LocalDate.parse(T_Waiting_Period, formatter);
	          
	          // Calculate the difference in days
	          long expectedDaysDifference = ChronoUnit.DAYS.between(bookingDate, waitingPeriodDate);
	          
	          // Convert Tentative Delivery Date to long
	          long actualDeliveryDays = Long.parseLong(T_Delivery_Date);
	          
	          System.out.println("Booking Date: " + bookingDate);
	          System.out.println("Tentative Waiting Period: " + waitingPeriodDate);
	          System.out.println("Expected Delivery Days Difference: " + expectedDaysDifference);
	          System.out.println("Actual Tentative Delivery Days: " + actualDeliveryDays);
	          
	          // Assert the difference
	          Assert.assertEquals(actualDeliveryDays, expectedDaysDifference, "(Pending Booking)Tentative Delivery Date does not match the expected difference!");
	    	  } catch (Exception e) {
		          Assert.fail("(Pending Booking),Error occurred while Tentative Waiting Period  Difference. Possible server issue ::");
		      }
	    	  
	    	  }
	      
	    //...................................................................    
	      @Test(priority = 7)
	      public void Edit_Final_Delivery_Date() throws InterruptedException {	       	    
	    	  try {
	    	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
	      	
	          WebElement Final_Delivery_Date = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"box-icon-new\"]")));
	          clickElementUsingJS(driver,Final_Delivery_Date);
	         
	          Thread.sleep(1000);
	          WebElement calender = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@aria-haspopup=\"dialog\"])[2]")));
	          calender.click();
	          
	          WebElement Select_27 = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" 27 \"]")));
	          Select_27.click();
	          Thread.sleep(1000);	          
	        
	          WebElement Date1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@aria-haspopup=\"dialog\"])[1]")));
	          String D1 = Date1.getAttribute("value").replace("/", "-");  // Replace "/" with "-"
	          System.out.println("date1  ::" + D1);
	          Thread.sleep(1000);	  
	          
	          WebElement click_On_Ok = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"Ok\"]")));
	          clickElementUsingJS(driver,click_On_Ok);	          
	          Thread.sleep(500);
	         
	          // Find the  date text which has 	been submitted
	          WebElement Date2 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-vehicle-status//div[contains(@class, 'vehicle-status')]//div[position()=8]/h6")));
	          String D2 = Date2.getText();
	          System.out.println("date2  ::"  +D2);
	          
	          Assert.assertEquals(D1, D2,"(Pending Booking),Final delivery date has not been Edited");
	      
	      } catch (Exception e) {
	          Assert.fail("(Pending Booking),Error occurred while editing final delivery date. Possible server issue ");
	      }
	    	  
	      }
	      
	      
	    //.....................................................................
 		private void clickElementUsingJS(WebDriver driver, WebElement element) {
 			JavascriptExecutor js = (JavascriptExecutor) driver;
 			js.executeScript("arguments[0].click();", element);
 		}

}
