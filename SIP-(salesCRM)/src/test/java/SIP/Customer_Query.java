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
	
	String Query, category, CUSTOMER_Remarks,Closed_Status,cre_Remarks;
    String Payment,product;
	
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
	    
	    wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Customer Query \"]")));
  	  } catch (Exception e) {
	          Assert.fail("(Pending Booking),Error occurred while Click on the Customer. Possible server issue ::" );
	      }
  	  
  	  }
 //.......................Punch Query Remarks of Product................................................
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
    @Test(priority = 6)
    public void Customer_Query_of_Product() {
        try {
            Click_On_Customer_Query();
        } catch (Exception e) {
            Assert.fail("TEST Failed :: Customer_Query_of_Product: " + e.getMessage());
        }
    }

    @Test(priority = 7)
    public void Select_PRODUCT() {
        try {
            product = SelectQuery(wait, "(//mat-option[contains(@id, 'mat-option-')])[2]");
            System.out.println("PRODUCT == " + product);
        } catch (Exception e) {
            Assert.fail("TEST Failed :: On Select PRODUCT: " + e.getMessage());
        }
    }

    @Test(priority = 8)
    public void Write_Query_And_SAVE_for_Product() {
        try {
            Query = "Product";
            System.out.println("Query  :: " + Query);
            Query_Remarks(Query);
        } catch (Exception e) {
            Assert.fail("TEST Failed :: Write_Query_And_SAVE_for_Product : " + e.getMessage());
        }
    }

    @Test(priority = 9)
    public void Click_On_Query_History_for_Product() {
        try {
            category = Query_History();
            System.out.println("Category of Product ::" + category);
        } catch (Exception e) {
            Assert.fail("TEST Failed :: Click_On_Query_History_for_Product: " + e.getMessage());
      }
    }

    @Test(priority = 10)
    public void Customer_Remarks_for_Product() {
        try {
            CUSTOMER_Remarks = CUSTOMER_Remarks();
            System.out.println("Customer remarks of Product  ::" + CUSTOMER_Remarks);
        } catch (Exception e) {
            Assert.fail("TEST Failed :: Customer_Remarks_for_Product: " + e.getMessage());
    }
    }

    @Test(priority = 11)
    public void CRE_Remarks_for_Product() {
        try {
            cre_Remarks = "Closed";
            System.out.println("cre_Remarks  :: " + cre_Remarks);
            CRE_Remarks(cre_Remarks);
        } catch (Exception e) {
            Assert.fail("TEST Failed :: CRE_Remarks_for_Product: " + e.getMessage());
        }
    }

    @Test(priority = 12)
    public void Status_Closed_for_Product() {
        try {
            Closed_Status = Status();
            System.out.println("Closed Status of Product  ::" + Closed_Status);
        } catch (Exception e) {
            Assert.fail("TEST Failed :: Status_Closed_for_Product: " + e.getMessage());
        }
    }
    @Test(priority =13)
    public void  Match_status_For_Product() throws InterruptedException {	
    	Thread.sleep(500);
  	  Assert.assertEquals(product, category," Selected 'product' does not show in 'Category' after Open Query history ");
  	  Assert.assertEquals(Query, CUSTOMER_Remarks,"CRE remarks not show after open View Deatils");
  	  Assert.assertEquals(Closed_Status,cre_Remarks, " Status 'CLOSED' will not show when we closed the Query ");  	  
  	  
        }   	   
  //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
    //.......................Punch Query Remarks of Payment................................................
      @Test(priority = 14)
      public void Customer_Query_of_Payment() {
          try {
              Click_On_Customer_Query();
          } catch (Exception e) {
              Assert.fail("TEST Failed :: Customer_Query_of_Payment: " + e.getMessage());
          }
      }

      @Test(priority = 15)
      public void Select_Payment() {
          try {
        	  Payment = SelectQuery(wait, "(//mat-option[contains(@id, 'mat-option-')])[3]");
              System.out.println("PRODUCT == " + Payment);
          } catch (Exception e) {
              Assert.fail("TEST Failed :: On Select Payment: " + e.getMessage());
          }
      }

      @Test(priority = 16)
      public void Write_Query_And_SAVE_for_Payment() {
          try {
              Query = "Payment";
              System.out.println("Query  :: " + Query);
              Query_Remarks(Query);
          } catch (Exception e) {
              Assert.fail("TEST Failed :: Write_Query_And_SAVE_for_Payment : " + e.getMessage());
          }
      }

      @Test(priority = 17)
      public void Click_On_Query_History_for_Payment() {
          try {
              category = Query_History();
              System.out.println("Category of Product ::" + category);
          } catch (Exception e) {
              Assert.fail("TEST Failed :: Click_On_Query_History_for_Payment: " + e.getMessage());
        }
      }

      @Test(priority = 18)
      public void Customer_Remarks_for_Payment() {
          try {
              CUSTOMER_Remarks = CUSTOMER_Remarks();
              System.out.println("Customer remarks of Product  ::" + CUSTOMER_Remarks);
          } catch (Exception e) {
              Assert.fail("TEST Failed :: Customer_Remarks_for_Payment: " + e.getMessage());
      }
      }

      @Test(priority = 19)
      public void CRE_Remarks_for_Payment() {
          try {
              cre_Remarks = "Closed";
              System.out.println("cre_Remarks  :: " + cre_Remarks);
              CRE_Remarks(cre_Remarks);
          } catch (Exception e) {
              Assert.fail("TEST Failed :: CRE_Remarks_for_Payment: " + e.getMessage());
          }
      }

      @Test(priority = 20)
      public void Status_Closed_for_Payment() {
          try {
              Closed_Status = Status();
              System.out.println("Closed Status of Product  ::" + Closed_Status);
          } catch (Exception e) {
              Assert.fail("TEST Failed :: Status_Closed_for_Payment: " + e.getMessage());
          }
      }
      @Test(priority =21)
      public void  Match_status_For_Payment() throws InterruptedException {	
      	Thread.sleep(500);
    	  Assert.assertEquals(Payment, category," Selected 'Payment' does not show in 'Category' after Open Query history ");
    	  Assert.assertEquals(Query, CUSTOMER_Remarks,"CRE remarks not show after open View Deatils");
    	  Assert.assertEquals(Closed_Status,cre_Remarks, " Status 'CLOSED' will not show when we closed the Query ");  	  
    	  
          }      
    //...............HelperMETHOD....................................................................    
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
  
    //......................................................................................   
      public String SelectQuery(WebDriverWait wait, String xpath) throws InterruptedException {	       	    
  	 
  	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
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
    	   WebElement QUERY = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@formcontrolname=\"customerRemark\"]")));
  	       QUERY.sendKeys(Remarks);
  	       Thread.sleep(500);
  	      
  	       WebElement save = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Save \"]")));
 	      clickElementUsingJS(driver, save); 
    	
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
           public String  CUSTOMER_Remarks() {
    	   WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));	    	
    	   WebElement Customer_Remarks = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class=\"bold-para\"])[5]")));
   	       String  CRE_Remarks=Customer_Remarks.getText();
    	    
   	        return CRE_Remarks;    	    
      }
           
        //....................................................................................    
           public String  Status() {
    	   WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));	    	
     	    WebElement Status2 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-common-customer-table//table//tr[1]/td[5]")));
   	        String  STATUS=Status2.getText();
    	    
   	        return STATUS;    	    
      }
  //.....................................................................
		public void clickElementUsingJS(WebDriver driver, WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}

}
