package UAT_SIP2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

public class Additional_Fillter extends Dealer_User_Login {
	
	 public static WebDriver driver;
	    public WebDriverWait wait;
//.......................................................................	
	@SuppressWarnings("static-access")
	@BeforeClass
	public void SIP() {
		this.driver=Dealer_User_Login.driver;		
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
 	  	   	   
       }
  //..................................................................................................
    @Test(priority = 5)
    public void Verify_Booking_Date_Fillter() throws InterruptedException {
    	try {
    	Select_BookingDate(); 
    	Booking_Date_Verify_Data();
    	Clear();
    	}catch(Exception e){
    		Assert.fail("TEST  FAILED :: \n Booking Date has not been Executed Correctly from additional fillter");
    	}
    }    
    
  //..................................................................................................
    @Test(priority = 6)
    public void Verify_MODEL_Fillter() throws InterruptedException {
    	try {
    		MODEL();    	   		
    		Clear();
    	}catch(Exception e){
    		Assert.fail("TEST  FAILED :: \n Selected MODEL has not been Executed Correctly from additional fillter");
    	}
    }      
   //..................................................................................................
      @Test(priority = 7)
      public void Verify_Varient_Fillter() throws InterruptedException {
      	try {
      		Varient();    	   		
      		Clear();
      	}catch(Exception e){
      		Assert.fail("TEST  FAILED :: \n Selected Varient has not been Executed Correctly from additional fillter");
      	}
      }
    //..................................................................................................
      @Test(priority =8)
      public void Verify_Booking_Amount_Fillter() throws InterruptedException {
      	try {
      		Booking_Amount();    	   		
      		Clear();
      	}catch(Exception e){
      		Assert.fail("TEST  FAILED :: \n Selected Booking Amount has not been Executed Correctly from additional fillter");
      	}
      }
      //..................................................................................................
      @Test(priority =9)
      public void Verify_Sales_Executive_NAME_Fillter() throws InterruptedException {
      	try {
      		Sales_Executive();    	   		
      		Clear();
      	}catch(Exception e){
      		Assert.fail("TEST  FAILED :: \n Selected Sales Executive NAME has not been Executed Correctly from additional fillter");
      	}
      }
      
      
    //..................................................................................................
      @Test(priority =10)
      public void Verify_Tentative_DeliveryDATE_Fillter() throws InterruptedException {
      	try {
      		Tentative_Delivery_DATE();    	   		
      		Clear();
      	}catch(Exception e){
      		Assert.fail("TEST  FAILED :: \n Selected Tentative_DeliveryDATE has not been Executed Correctly from additional fillter");
      	}
      }
    //..................................................................................................
      @Test(priority =11)
      public void Close_Additional_Fillter() throws InterruptedException {
      	try {
      		 WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));  
      		 WebElement Close = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"close-btn\"]")));
      		Close.click();	
      	}catch(Exception e){
      		Assert.fail("TEST  FAILED :: \n Additional fillter not closed ");
      	}
      }
  //........................................................................................... 
    public void Select_BookingDate() throws InterruptedException {
    	try {
    	 WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));          	  	
       	 WebElement Calendar = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@aria-label=\"Open calendar\"])[1]")));
       	 Calendar.click();
       	 
       	 Thread.sleep(500);
       	 WebElement previousMonth = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-calendar-header//button[2]/span[2]")));
         Actions action1 =new Actions(driver);
         action1.doubleClick(previousMonth).perform();
         Thread.sleep(500);
        
         // wait for some time when fillter button is visible 
         wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()=\"DEC 2024\"])[1]")));
        
         // select date from 1 to 10
       	 WebElement Date_Select_1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-month-view//tr[2]/td[1]/button")));
       	 WebElement Date_Select_2 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-month-view//tr[2]/td[7]/button")));

       	 Date_Select_1.click();
       	 Thread.sleep(500);
       	 Date_Select_2.click();
       	 
       	 WebElement Search = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()=\" Search \"])[1]")));
       	 Search.click();
    	}catch(Exception e){
    		Assert.fail("TEST FAILED :: Select BookingDate in Fillter failed.");
    	}
    }
    //..........................................................................................
    public void Clear() {
    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
    	WebElement CLEARR = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"Clear\"]")));
        Actions action = new Actions(driver);
    	action.doubleClick(CLEARR).perform();
    }
        
  //................................................................................................
    public void Booking_Date_Verify_Data() {    	
    try {	
    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(7)); 
        wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loading-spinner')]"))); // Replace with actual locator for buffering element

        List<WebElement> ALL_Booking_Date = driver.findElements(By.xpath("//app-booking-table//table/tbody//td[2]")); 
        if (ALL_Booking_Date.isEmpty()) {
        	Assert.fail("Booking date data is NULL");
        	
        }
        boolean hasInvalidData = false;
        StringBuilder invalidDataMessage = new StringBuilder("\nInvalid Booking Dates Found:\n");

        for (int i = 0; i < ALL_Booking_Date.size(); i++) {
            String dateText = ALL_Booking_Date.get(i).getText().trim();
            System.out.println("All date :: " + dateText);  // Print each date

            // Extract the day part of the date
            int day = Integer.parseInt(dateText.substring(0, 2)); // Extract "DD" from "DD-MM-YYYY"

            // Check if day is within the allowed range (01 to 07)
            if (day < 1 || day > 7) {
                hasInvalidData = true;
                invalidDataMessage.append("Index: ").append(i).append(" - Date: ").append(dateText).append("\n");
            }
        }

        // If any invalid data is found, print and fail the test
        if (hasInvalidData) {
            System.out.println(invalidDataMessage.toString());
            Assert.fail(invalidDataMessage.toString());
        }
    }catch(Exception e){
    	Assert.fail("TEST FAILED :: Booking date verification failed.");
    }
    }

//.....................................................................................................
     public void MODEL() throws InterruptedException {
    	try {
    	 WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
    	WebElement model = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Model \"]")));
    	clickElementUsingJS(driver, model);
    	
    	Thread.sleep(500);
    	WebElement Select = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()=\"Select\"])[2]")));
    	clickElementUsingJS(driver, Select);
    	
    	Thread.sleep(700);
    	WebElement Select_ECO = wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//mat-option[contains(@id, 'mat-option')])[4]")));
    	// Get text using JavaScript (works even if element is hidden)
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	
    	// Get the text of the selected ECO element
    	String ECOO = (String) js.executeScript("return arguments[0].textContent;", Select_ECO);
    	ECOO = ECOO.trim(); // Ensure no leading/trailing spaces
    	System.out.println("TEXT1 :: " + ECOO);

    	// Scroll to the element to make it visible
    	js.executeScript("arguments[0].scrollIntoView(true);", Select_ECO);
    	Thread.sleep(500); 

    	// Click using JavaScript
    	js.executeScript("arguments[0].click();", Select_ECO);

    	// Wait until the loading spinner disappears
    	wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loading-spinner')]"))); 

    	// List of all models after selecting model name from filter
    	List<WebElement> ALL_Model = driver.findElements(By.xpath("//table/tbody/tr/td[4]"));

    	List<String> mismatchedModels = new ArrayList<>();
    	for (WebElement MODEL : ALL_Model) {
    	    String MoDEL = MODEL.getText().trim();
    	    System.out.println("MODEL_Name : " + MoDEL);
    	    
    	    // Compare model name with ECOO text
    	    if (!MoDEL.equals(ECOO)) {
    	        mismatchedModels.add(MoDEL);
    	    }
    	}

    	// If mismatches exist, fail and print details
    	if (!mismatchedModels.isEmpty()) {
    	    String errorMessage = "Test Failed! Mismatched Models Found:\n"
    	            + "Expected: " + ECOO + "\n"
    	            + "Mismatched Values: " + mismatchedModels;
    	    
    	    System.out.println(errorMessage);
    	    Assert.fail(errorMessage);
    	} else {
    	    System.out.println("All models match ECOO. Test Passed!");
    	}
    	}catch(Exception e){
    		Assert.fail("Model name select FAILED");
    	}
     }
     
   //.....................................................................................................
     public void Varient() throws InterruptedException {
    	try {
    	 WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
    	WebElement Variant = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Variant \"]")));
    	clickElementUsingJS(driver, Variant);
    	
    	Thread.sleep(500);
    	WebElement Select = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()=\"Select\"])[3]")));
    	clickElementUsingJS(driver, Select);
    	
    	Thread.sleep(700);
    	WebElement Select_Alto = wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//mat-option[contains(@id, 'mat-option-')])[3]")));
    	String ALTO =Select_Alto.getText();
    	System.out.println("Varient name ::"+ALTO);
    	Thread.sleep(500);
    	Select_Alto.click();

    	// Wait until the loading spinner disappears
    	wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loading-spinner')]"))); 

    	// List of all models after selecting model name from filter
    	List<WebElement> ALL_Varient = driver.findElements(By.xpath("//table/tbody/tr/td[5]"));

    	List<String> mismatchedModels = new ArrayList<>();
    	for (WebElement VARIENT : ALL_Varient) {
    	    String VaRient = VARIENT.getText().trim();
    	    System.out.println("VARIENT_Name : " + VaRient);
    	    
    	    // Compare model name with ECOO text
    	    if (!VaRient.equals(ALTO)) {
    	        mismatchedModels.add(VaRient);
    	    }
    	}

    	// If mismatches exist, fail and print details
    	if (!mismatchedModels.isEmpty()) {
    	    String errorMessage = "Test Failed! Mismatched Varient Found:\n"
    	            + "Expected: " + ALTO + "\n"
    	            + "Mismatched Values: " + mismatchedModels;
    	    
    	    System.out.println(errorMessage);
    	    Assert.fail(errorMessage);
    	} else {
    	    System.out.println("All Varient match ALTO. Test Passed!");
    	}
    	}catch(Exception e){
    		Assert.fail("Varient name select FAILED");
    	}
     }
     
     //.....................................................................................
     public void Booking_Amount() throws InterruptedException {
     	
     	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
     	WebElement Amount = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Booking Amount \"]")));
     	clickElementUsingJS(driver, Amount);
     	
     	Thread.sleep(500);
     	WebElement Select = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()=\"Select\"])[4]")));
     	clickElementUsingJS(driver, Select);
    
     	Thread.sleep(700);
    	WebElement Select_Amount = wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//mat-option[contains(@id, 'mat-option-')])[3]")));
    	// Get text using JavaScript (works even if element is hidden)
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	
    	// Get the text of the selected ECO element
    	String amount = (String) js.executeScript("return arguments[0].textContent;", Select_Amount);
    	amount = amount.trim(); // Ensure no leading/trailing spaces
    	System.out.println("amount  :: " + amount);

    	// Scroll to the element to make it visible
    	js.executeScript("arguments[0].scrollIntoView(true);", Select_Amount);
    	Thread.sleep(500); 

    	// Click using JavaScript
    	js.executeScript("arguments[0].click();", Select_Amount);
         
    	// Wait until the loading spinner disappears
    	wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loading-spinner')]"))); 

    	// List of all models after selecting model name from filter
    	List<WebElement> Total_Amount = driver.findElements(By.xpath("//table/tbody/tr/td[8]"));

    	List<String> mismatchedAmount = new ArrayList<>();
    	for (WebElement AMOUNT : Total_Amount) {
    	    String AmOunt = AMOUNT.getText().trim();
    	    System.out.println("AMOUNT : " + AmOunt);
    	    
    	    // Convert the extracted text to an integer (assuming numeric values)
    	    try {
    	        int value = Integer.parseInt(AmOunt);

    	        // Validate that the amount is within the expected range
    	        if (value < 1000 || value > 11000) {
    	            mismatchedAmount.add(AmOunt);
    	        }
    	    } catch (NumberFormatException e) {
    	        mismatchedAmount.add(AmOunt + " (Invalid Number Format)");
    	    }
    	}

    	// Assert failure if mismatched amounts are found
    	if (!mismatchedAmount.isEmpty()) {
    	    Assert.fail("Mismatched Amounts Found: " + mismatchedAmount);
    	}
     }
     
     //.....................................................................................
   
	public void Sales_Executive() throws InterruptedException {
     	try {
     	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
     	WebElement Executive = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Sales Executive \"]")));
     	clickElementUsingJS(driver, Executive);
     	
     	Thread.sleep(500);
     	WebElement Select = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()=\"Select\"])[5]")));
     	clickElementUsingJS(driver, Select);
    
     	Thread.sleep(700);
    	WebElement Select_Name = wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//mat-option[contains(@id, 'mat-option-')])[4]")));
    	// Get text using JavaScript (works even if element is hidden)
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	
    	// Get the text of the selected ECO element
    	String ExecutiveNAME = (String) js.executeScript("return arguments[0].textContent;", Select_Name);
    	ExecutiveNAME = ExecutiveNAME.trim(); // Ensure no leading/trailing spaces
    	System.out.println("ExecutiveNAME  :: " + ExecutiveNAME);

    	// Scroll to the element to make it visible
    	js.executeScript("arguments[0].scrollIntoView(true);", Select_Name);
    	Thread.sleep(500); 

    	// Click using JavaScript
    	js.executeScript("arguments[0].click();", Select_Name);
         
    	// Wait until the loading spinner disappears
    	wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loading-spinner')]"))); 

    	// List of all models after selecting model name from filter
    	List<WebElement> ALLNAme = driver.findElements(By.xpath("//table/tbody/tr/td[10]"));

    	List<String> mismatchedNAme = new ArrayList<>();
    	for (WebElement executive : ALLNAme) {
    	    String EXECUTIVE = executive.getText().trim();
    	    System.out.println("Sales Executive Name : " + EXECUTIVE);
    	    
    	    // Compare model name with ECOO text
    	    if (!ExecutiveNAME.equals(EXECUTIVE)) {
    	    	mismatchedNAme.add(ExecutiveNAME);
    	    }
    	}

    	// If mismatches exist, fail and print details
    	if (!mismatchedNAme.isEmpty()) {
    	    String errorMessage = "Test Failed! Mismatched Sales Executive Found:\n"
    	            + "Expected: " + ExecutiveNAME + "\n"
    	            + "Mismatched Values: " + mismatchedNAme;
    	    
    	    System.out.println(errorMessage);
    	    Assert.fail(errorMessage);
    	} else {
    	    System.out.println("All Sales Executive match. Test Passed!");
    	}
    	}catch(Exception e){
    		Assert.fail("Sales Executive name  select FAILED");
    	}
     }
	//................................................................................................
    public void Tentative_Delivery_DATE() {    	
    try {	
    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(17)); 

    	WebElement Tentative_Delivery = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()=\" Tentative Delivery Date \"])[2]")));
     	clickElementUsingJS(driver, Tentative_Delivery);
     	
     	WebElement Calendar = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@aria-label=\"Open calendar\"])[2]")));
      	Calendar.click();
      	 
      	 Thread.sleep(500);
      	 WebElement previousMonth = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-calendar-header//button[2]/span[2]")));
        Actions action1 =new Actions(driver);
        action1.doubleClick(previousMonth).perform();
        Thread.sleep(500);
       
        // wait for some time when fillter button is visible 
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()=\"DEC 2024\"])[1]")));
       
        // select date from 1 to 10
      	 WebElement Date_Select_1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-month-view//tr[2]/td[1]/button")));
      	 WebElement Date_Select_2 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-month-view//tr[2]/td[7]/button")));

      	 Date_Select_1.click();
      	 Thread.sleep(500);
      	 Date_Select_2.click();
      	 
      	 WebElement Search = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()=\" Search \"])[2]")));
      	 Search.click();
    
     	wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loading-spinner')]"))); 

        List<WebElement> ALL_Booking_Date = driver.findElements(By.xpath("//app-booking-table//table/tbody//td[9]")); 
        if (ALL_Booking_Date.isEmpty()) {
        	Assert.fail("Tentative delivery date data is NULL");
        	
        }
        boolean hasInvalidData = false;
        StringBuilder invalidDataMessage = new StringBuilder("\nTentative delivery Dates Found:\n");

        for (int i = 0; i < ALL_Booking_Date.size(); i++) {
            String dateText = ALL_Booking_Date.get(i).getText().trim();
            System.out.println("All date :: " + dateText);  // Print each date

            // Extract the day part of the date
            int day = Integer.parseInt(dateText.substring(0, 2)); // Extract "DD" from "DD-MM-YYYY"

            // Check if day is within the allowed range (01 to 07)
            if (day < 1 || day > 7) {
                hasInvalidData = true;
                invalidDataMessage.append("Index: ").append(i).append(" - Date: ").append(dateText).append("\n");
            }
        }

        // If any invalid data is found, print and fail the test
        if (hasInvalidData) {
            System.out.println(invalidDataMessage.toString());
            Assert.fail(invalidDataMessage.toString());
        }
    }catch(Exception e){
    	Assert.fail("TEST FAILED :: Tentative delivery verification failed.");
    }
    } 	    
    //.....................................................................
	public void clickElementUsingJS(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	
	
}
