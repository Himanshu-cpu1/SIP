package SIP;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

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

public class Select_MarutiInsurance_On_Customer_Query extends Dealer_User_Login {
	 String  Maruti_Insurance ;  // Selected Fillter
	 String Query;  // Query remarks 
	 String CATEGORY; // category in QuerY history
	 String CRE_Remarks;  // Cre Remarks after open view Details.
	 String CRE_Value;   // CRE Remarks enter for closed Query.
	 String STATUS2;    // Status should be closed to match.
	
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
	    
	    wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Customer Query \"]")));
  	  } catch (Exception e) {
	          Assert.fail("(Pending Booking),Error occurred while Click on the Customer. Possible server issue ::" );
	      }
  	  
  	  }
       
    //........................................................................................    
    @Test(priority = 6)
      public void Click_On_Customer_Query() throws InterruptedException {	       	    
  	  try {
  	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
      WebElement Customer_Query = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Customer Query \"]")));
	    clickElementUsingJS(driver, Customer_Query);
	    
	    wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-vehicle-status//div[contains(@class, 'vehicle-status')]//div[position()=6]/h6")));
  	  } catch (Exception e) {
	          Assert.fail("(In-Pending Booking),Tentative Waiting period not visible as Expected Time. " );
	      }
    }
  	//.............................................................................................    
      @Test(priority = 7)
        public void Click_On_Select_MarutiInsurance() throws InterruptedException {	       	    
    	
    	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));	    	
        WebElement Select = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"Select\"]")));
  	    clickElementUsingJS(driver, Select);
  	    Thread.sleep(2000);
  	    // selected Product 
  	    WebElement Select_Maruti_Insurance  = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//mat-option[contains(@id, 'mat-option-')])[5]"))); 	   
  	    Maruti_Insurance =Select_Maruti_Insurance .getText();
  	    System.out.println(" Maruti Insurance      == "+ Maruti_Insurance ); 	    
  	    clickElementUsingJS(driver, Select_Maruti_Insurance);  // select maruti Insurance
	    
	    Thread.sleep(2000);
	    Query="Maruti Insurance";	  // Query remarks have to match in view details.  
	    WebElement QUERY = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@formcontrolname=\"customerRemark\"]")));
	    QUERY.sendKeys(Query);
	    
	    WebElement save = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Save \"]")));
	    clickElementUsingJS(driver, save);
	    Thread.sleep(2000);	     
  	  }
  	//...................................................................    
      @Test(priority = 8)
        public void Click_On_Query_History() throws InterruptedException {	       	    
    	
    	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));	    	
           WebElement Query_History = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"Query History\"]")));
  	       clickElementUsingJS(driver, Query_History);
  	    
  	    Thread.sleep(2000);
  	   
  	    WebElement Category = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-common-customer-table//table//tr[1]/td[4]")));
  	    WebElement Status = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-common-customer-table//table//tr[1]/td[5]")));

	    CATEGORY=Category.getText();  //  match  category that selected category will be there .
	    String STATUS=Status.getText();      //
	   
	   System.out.println("CATEGORY of Maruti_Insurance -:"+CATEGORY);
	   System.out.println("Status  -:"+STATUS);
	   
	   Thread.sleep(500);
	  
	   WebElement View_Details = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()=\"View Details\"])[2]")));
	   View_Details.click();

	   WebElement Customer_Remarks = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class=\"bold-para\"])[5]")));
	   CRE_Remarks=Customer_Remarks.getText();
	   System.out.println("CRE_Remarks of Maruti_Insurance :: "+CRE_Remarks);
	   
	    Thread.sleep(2000);
	    CRE_Value="Closed";	  // CRE Value have to enter  
	    WebElement CRE_Remmarks = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id, 'mat-input')]")));
	    CRE_Remmarks.sendKeys(CRE_Value);
	   
	    WebElement Mark_close = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\" Mark this query as closed \"]")));
	    WebElement Update = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\" Update \"]")));
	    
	    Mark_close.click();
	    Update.click();
	    
	    Thread.sleep(2000);
  	    WebElement Status2 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-common-customer-table//table//tr[1]/td[5]")));
  	    STATUS2=Status2.getText();
  	    System.out.println("STATUS of Maruti_Insurance  -:"+STATUS2);
  	    Thread.sleep(2000);
  	  }
   //......................................................................................   
      @Test(priority = 9)
      public void Match_ExtendWarranty_Query_will_punched() throws InterruptedException {	
    	  Thread.sleep(500);
    	  Assert.assertEquals(Maruti_Insurance, CATEGORY," Selected 'Maruti_Insurance' does not show in 'Category' after Click on Query history ");
    	  Assert.assertEquals(CRE_Remarks, Query,"CRE remarks not show after open View Deatils");
    	  Assert.assertEquals(STATUS2,CRE_Value, " Status 'CLOSED' will not show when we closed the Query ");  	  
    	  
          }
     
  //.....................................................................
		public void clickElementUsingJS(WebDriver driver, WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}

}
