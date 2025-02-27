package UAT_SIP2;

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

      public class Tentative_Waiting_and_Final_Delivery_Date {
	  public static WebDriver driver;
	    public WebDriverWait wait;
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	  
	    String B_id;
	    String BOOKING_Number;
	    //..................................................................................................
	    @SuppressWarnings("static-access")
	    @BeforeClass
	    public void SIP() throws InterruptedException {
	        this.driver = Cancelled_Booking_Sorting.driver;	 
	    }
	  //..................................................................................................
	    @Test(priority = 4)
	    public void Click_On_Pending_Booking() throws InterruptedException {
	    	Thread.sleep(2000); 
	    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement Pending_Booking = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Pending Booking \"]")));
            clickElementUsingJS(driver, Pending_Booking);
            
	    }
	    
	    //..................................................................................................
	    @Test(priority = 5)
	    public void Booking_Id() throws InterruptedException {	       	    
	    	try {
	    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));  	
	    	Thread.sleep(3000); 
	    	WebElement BOOKING_Id = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-booking-table//table/tbody//td[1]")));
	    	  B_id=BOOKING_Id.getText();
	    	  System.out.println("Booking id :: "+ B_id);	
	    	}catch(Exception e){
	    		Assert.fail("TEST FAILED :: \n  Booking ID is not Visible");
	    	}
	    	}
	//...................................................................    
	      @Test(priority = 6)
	        public void Click_Customer() throws InterruptedException {	       	    
	    	 Thread.sleep(2000);
	    	  try {
	    	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
	        WebElement Click_On_Customer = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-booking-table//table/tbody//td[3]")));
    	    clickElementUsingJS(driver, Click_On_Customer);
    	    
    	    wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-vehicle-status//div[contains(@class, 'vehicle-status')]//div[position()=6]/h6")));
	    	  } catch (Exception e) {
		          Assert.fail("Test FAILED  \n ::In (Pending Booking),Vehicle Details is not showing after Open Customer page::" );
		      }
	    	  
	    	  }
	    //...................................................................    
		    @Test(priority = 7)
		      public void Booking_NO() throws InterruptedException {	       	    
		    	try {
		    		BOoking_Number();
		    	}catch(Exception e){
		    		Assert.fail("TEST FAILED :: \n BOoking Number is NULL");
		    	}
	  	  }
	
	    //...................................................................    
	      @Test(priority = 9)
	      public void Tentative_Waiting_Period_Difference() throws InterruptedException {	       	    
	    	  try {
	    	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
	      	
	          WebElement Booking_Date = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-vehicle-status//div[contains(@class, 'vehicle-status')]//div[position()=5]/h6")));
	          WebElement Tentative_Delivery_date = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-vehicle-status//div[contains(@class, 'vehicle-status')]//div[position()=6]/h6")));
	          WebElement Tentative_Waiting_Period = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-vehicle-status//div[contains(@class, 'vehicle-status')]//div[position()=7]/h6")));
	          Thread.sleep(1000);
	          String B_Date = Booking_Date.getText();  
	          String T_Delivery_Date = Tentative_Delivery_date.getText(); 
	          String T_Waiting_Period = Tentative_Waiting_Period.getText();  
	          
	          Thread.sleep(1000);
	          // Convert strings to LocalDate
	          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	          LocalDate bookingDate = LocalDate.parse(B_Date, formatter);
	          LocalDate waitingPeriodDate = LocalDate.parse(T_Waiting_Period, formatter);
	          
	          // Calculate the difference in days
	          long expectedDaysDifference = ChronoUnit.DAYS.between(bookingDate, waitingPeriodDate);
	          
	          // Convert Tentative Delivery Date to long
	          long actualDeliveryDays = Long.parseLong(T_Delivery_Date);
	          Thread.sleep(1000);
	          
	          System.out.println("Booking Date: " + bookingDate);
	          System.out.println("Tentative Waiting Period: " + waitingPeriodDate);
	          System.out.println("Expected Delivery Days Difference: " + expectedDaysDifference);
	          System.out.println("Actual Tentative Delivery Days: " + actualDeliveryDays);
	          
	          // Assert the difference
	          Assert.assertEquals(actualDeliveryDays, expectedDaysDifference, "(Pending Booking)Tentative Delivery Date does not match the expected difference!");
	    	  } catch (Exception e) {
		          Assert.fail("TEST FAILED :: In(Pending Booking),Error occurred while Tentative Waiting Period Difference. Possible LoadTime issue  ::");
		      }
	    	  
	    	  }
	      
	    //...................................................................    
	      @Test(priority = 10)
	      public void Edit_Final_Delivery_Date() throws InterruptedException {	       	    
	    	  try {
	    	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
	    	  Thread.sleep(2000);
	         
	    	  WebElement Final_Delivery_Date = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"box-icon-new\"]")));
	          clickElementUsingJS(driver,Final_Delivery_Date);
	         
	          Thread.sleep(2000);
	          WebElement calender = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@aria-haspopup=\"dialog\"])[2]")));
	          calender.click();
	          
	          WebElement Select_27 = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" 27 \"]")));
	          Select_27.click();
	          Thread.sleep(2000);	          
	        
	          WebElement Date1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@aria-haspopup=\"dialog\"])[1]")));
	          String D1 = Date1.getAttribute("value").replace("/", "-");  // Replace "/" with "-"
	          System.out.println("date1  ::" + D1);
	          Thread.sleep(2000);	  
	          
	          WebElement click_On_Ok = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"Ok\"]")));
	          clickElementUsingJS(driver,click_On_Ok);	          
	          Thread.sleep(3000);
	         
	          // Find the  date text which has 	been submitted
	          WebElement Date2 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-vehicle-status//div[contains(@class, 'vehicle-status')]//div[position()=8]/h6")));
	          String D2 = Date2.getText();
	          System.out.println("date2  ::"  +D2);
	          
	          Assert.assertEquals(D1, D2,"(Pending Booking),Final delivery date has not been Edited");
	      
	      } catch (Exception e) {
	          Assert.fail("TEST FAILED :: In(Pending Booking),Error occurred while editing final delivery date. Possible LoadTime issue ");
	      }
	    	  
	      }
	      //...................................................................    
		    @Test(priority =11)
		      public void Verify_Booking_ID() throws InterruptedException {	       	    
		    	  Assert.assertEquals(B_id,BOOKING_Number,"Booking Number not match with Booking Id ");		    	
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
 
	    //.....................................................................
 		private void clickElementUsingJS(WebDriver driver, WebElement element) {
 			JavascriptExecutor js = (JavascriptExecutor) driver;
 			js.executeScript("arguments[0].click();", element);
 		}

}
