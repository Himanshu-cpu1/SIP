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

public class Customer_Query extends Dealer_User_Login {
	
	    private String query;
	    private String selectedOption;
	    private String category;
	    private String customerRemarks;
	    private final String CRE_REMARKS = "Closed";
	    private String closedStatus;
	
    public static WebDriver driver;
    public WebDriverWait wait;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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
	          Assert.fail("Test FAILED  ::In (Pending Booking),Vehicle Details is not showing after Open Customer page::" );
	      }
  	  
  	  }
    //................For PRODUCT......................................................
    @Test(priority = 6)
    public void Product_Query_Flow() {
        executeQueryFlow("Product", 2);
    }

    @Test(priority = 7)
    public void Validate_Product_Query() {
        validateQueryStatus("Product");  //verify and match Product Query has Punched
    }
    //................For Payment......................................................

    @Test(priority = 8)
    public void Payment_Query_Flow() {
        executeQueryFlow("Payment", 3);
    }

    @Test(priority = 9)
    public void Validate_Payment_Query() {  // verify and match Payment Query has Punched
        validateQueryStatus("Payment");
    }
    //................For Extended_Warranty......................................................
  
    @Test(priority = 10)
    public void Extended_Warranty_Query_Flow() {
        executeQueryFlow("Extended Warranty", 4);
    }

    @Test(priority = 10)
    public void Extended_Warranty_Payment_Query() {
        validateQueryStatus("Extended Warranty");  //  verify and match Extended Warranty Query has Punched
    }

    //................For Maruti Insurance......................................................
    
    @Test(priority = 11)
    public void Maruti_Insurance_Query_Flow() {
        executeQueryFlow("Maruti Insurance", 5);
    }

    @Test(priority = 12)
    public void Maruti_Insurance_Query() {
        validateQueryStatus("Maruti Insurance");  //  verify and match Maruti Insurance Query has Punched
    }
 //................For Loyalty Card......................................................
    
    @Test(priority = 13)
    public void Loyalty_Card_Query_Flow() {
        executeQueryFlow("Loyalty Card", 6);
    }

    @Test(priority = 14)
    public void Loyalty_Card_Query() {
        validateQueryStatus("Loyalty Card");  //  verify and match Loyalty Card Query has Punched
    }
    
//................For True Value......................................................
    
    @Test(priority = 15)
    public void True_Value_Query_Flow() {
        executeQueryFlow("True Value", 7);
    }

    @Test(priority = 16)
    public void True_Value_Query() {
        validateQueryStatus("True Value");  //  verify and match True Value Query has Punched
    }
//................For Delivery......................................................
    
    @Test(priority = 17)
    public void Delivery_Query_Flow() {
        executeQueryFlow("Delivery", 8);
    }

    @Test(priority = 18)
    public void Delivery_Query() {
        validateQueryStatus("Delivery");  //  verify and match Delivery Query has Punched
    }
    
//................For MSGA......................................................
    
    @Test(priority = 19)
    public void MSGA_Flow() {
        executeQueryFlow("MSGA", 9);
    }

    @Test(priority = 20)
    public void MSGA_Query() {
        validateQueryStatus("MSGA");  //  verify and match MSGA Query has Punched
    }
//................For Booking Amendment......................................................
    
    @Test(priority = 21)
    public void Booking_Amendment_Flow() {
        executeQueryFlow("Booking Amendment", 10);
    }

    @Test(priority = 22)
    public void Booking_Amendment_Query() {
        validateQueryStatus("Booking Amendment");  //  verify and match Booking Amendment Query has Punched
    }
//................For Others......................................................
    
    @Test(priority = 23)
    public void Others_Flow() {
        executeQueryFlow("Others", 11);
    }

    @Test(priority = 24)
    public void Others_Query() {
        validateQueryStatus("Others");  //  verify and match Others Query has Punched
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
 
    //...............HelperMETHOD....................................................................    
    public void Click_On_Customer_Query() throws InterruptedException {	       	    
    	  try {
    	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
        WebElement Customer_Query = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Customer Query \"]")));
  	    clickElementUsingJS(driver, Customer_Query);
  	    
    	  } catch (Exception e) {
  	          Assert.fail("(In-Pending Booking),Customer Query Is not clikable " );
  	      }
      }
  
    //......................................................................................   
      public String SelectQuery(WebDriverWait wait, String xpath) throws InterruptedException {	       	    
  	 
  	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
  	  Thread.sleep(1000);
  	  WebElement Select = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"Select\"]")));
	    clickElementUsingJS(driver, Select);
	    Thread.sleep(500);
	 // selected Product 
        WebElement option = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        String PRODUCT = option.getText();
  	 
  	    clickElementUsingJS(driver, option);
  	  
  	    return PRODUCT; // Return the selected product
    }
   //.....................................................................................   
      public void Query_Remarks(String  Remarks) throws InterruptedException {	       	    
    
    	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
    	  Thread.sleep(500);
    	  WebElement QUERY = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@formcontrolname=\"customerRemark\"]")));
  	       QUERY.sendKeys(Remarks);
  	      
  	      
  	       WebElement save = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Save \"]")));
 	      clickElementUsingJS(driver, save); 
 	       Thread.sleep(1500);
    	  }
      
    //.....................................................................................   
      public void CRE_Remarks(String  CRE_Remarks) throws InterruptedException {	       	    
    
    	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
    	   WebElement CRE_Remark = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id, 'mat-input')]")));
    	   CRE_Remark.sendKeys(CRE_Remarks);
    	   
    	Thread.sleep(500);
        WebElement Mark_close = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\" Mark this query as closed \"]")));
   	    WebElement Update = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\" Update \"]")));
   	    
   	    Mark_close.click();
   	    Update.click();
   	   Thread.sleep(500);
      }
  //....................................................................................    
      public String  Query_History() throws InterruptedException {
    	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));	    	
           WebElement Query_History = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"Query History\"]")));
    	    clickElementUsingJS(driver, Query_History);
    	    
    	    Thread.sleep(500);
      	    WebElement Category = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-common-customer-table//table//tr[1]/td[4]")));
      	    String  CATEGORY=Category.getText(); 
      	  
      	   WebElement View_Details = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()=\"View Details\"])[2]")));
    	   View_Details.click();
    	   Thread.sleep(500);  
    	   return CATEGORY;
      	     	    
      }
    //....................................................................................    
           public String  CUSTOMER_Remarks() throws InterruptedException {
    	   WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));	    	
    	   Thread.sleep(500);
    	   WebElement Customer_Remarks = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class=\"bold-para\"])[5]")));
   	       String  CRE_Remarks=Customer_Remarks.getText();
    	    
   	        return CRE_Remarks;    	    
      }
           
        //....................................................................................    
           public String  Status() throws InterruptedException {
    	   WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));	    	
    	   Thread.sleep(1500);
    	   WebElement Status2 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-common-customer-table//table//tr[1]/td[5]")));
   	        String  STATUS=Status2.getText();
    	    
   	        return STATUS;    	    
      }
  //.....................................................................
		public void clickElementUsingJS(WebDriver driver, WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}
		
//............................................................................................		
		 private void executeQueryFlow(String queryType, int optionIndex) {
		        try {
		            Click_On_Customer_Query();
		            selectedOption = SelectQuery(wait, "(//mat-option[contains(@id, 'mat-option-')])" + "[" + optionIndex + "]");
		            System.out.println(queryType + " Selected: " + selectedOption);
		            
		            query = queryType;
		            System.out.println("Query  :: " + query);
		            Query_Remarks(query);
		            
		            category = Query_History();
		            System.out.println("Category: " + category);
		            
		            customerRemarks = CUSTOMER_Remarks();
		            System.out.println("Customer remarks: " + customerRemarks);
		            
		            CRE_Remarks(CRE_REMARKS);
		            System.out.println("CRE Remarks: " + CRE_REMARKS);
		            
		            Thread.sleep(2000);
		            closedStatus = Status();
		            System.out.println("Closed Status: " + closedStatus);
		        } catch (Exception e) {
		            Assert.fail("TEST Failed :: " + queryType + " Query Flow: " + e.getMessage());
		        }
		    }

		    private void validateQueryStatus(String queryType) {
		        try {
		            Thread.sleep(500);
		            Assert.assertEquals(selectedOption, category, "Selected '" + queryType + "' does not show in 'Category' after Open Query history");
		            Thread.sleep(500);
		            Assert.assertEquals(query, customerRemarks, "CRE remarks not shown after open View Details");
		            Thread.sleep(500);
		            Assert.assertEquals(closedStatus, CRE_REMARKS, "Status 'CLOSED' not shown when query is closed");
		        } catch (Exception e) {
		            Assert.fail("TEST Failed :: Validation for " + queryType + " Query: " + e.getMessage());
		        }
		    }

}
