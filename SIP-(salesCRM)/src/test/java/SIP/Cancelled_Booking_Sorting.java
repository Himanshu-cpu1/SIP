package SIP;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Cancelled_Booking_Sorting {
	    public static WebDriver driver;
	    public WebDriverWait wait;
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    //..................................................................................................
	    @SuppressWarnings("static-access")
	    @BeforeClass
	    public void SMR() throws InterruptedException {
	        this.driver = Pending_Booking_Sorting.driver;	    
            }
	
	  //..................................................................................................
	    @Test(priority = 5)
	    public void Click_On_Cancelled_Booking() throws InterruptedException {
	    	Thread.sleep(2000); 
	    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement Cancelled_Booking = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Cancelled Booking \"]")));
            clickElementUsingJS(driver, Cancelled_Booking);
            
	    }
	  //..................................................................................................
	    @Test(priority = 6)
	    public void Click_On_Items_Per_Page() throws InterruptedException {
	        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement Items_Per_Page = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id, 'mat-select-value')]")));
	        clickElementUsingJS(driver, Items_Per_Page);
            
            WebElement Select_100 = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class=\"mdc-list-item__primary-text\"])[4]")));
            clickElementUsingJS(driver, Select_100);
           
             }	    
	  //............................................................................................
	    @Test(priority = 7)
	    public void Sale_Executive_Name_Sorting() throws InterruptedException {
	    	 Thread.sleep(2000);
	    	// Locate and click on the "Sale Executive" sorting button
	        WebElement Sale_Executive = driver.findElement(By.xpath("//*[text()=\" Sale Executive \"]"));
	        clickElementUsingJS(driver, Sale_Executive);

	        // Capture the names from the table AFTER sorting
	        List<WebElement> nameElements = driver.findElements(By.xpath("//table/tbody/tr/td[10]")); // Adjust column index as needed

	        // Store names in a list
	        List<String> actualSortedNames = new ArrayList<>();
	        List<String> nonUppercaseNames = new ArrayList<>();

	        for (WebElement nameElement : nameElements) {
	            String name = nameElement.getText().trim();
	            actualSortedNames.add(name);
	            if (!name.equals(name.toUpperCase())) {
	                nonUppercaseNames.add(name);
	            }
	        }
	        // print All Small case name
	        if (!nonUppercaseNames.isEmpty()) {
	            String failedNames = String.join(", ", nonUppercaseNames);
	            int failedCount = nonUppercaseNames.size();
	            Assert.fail("(Cancelled-Booking) Total Count of Sales Executive Name In Lowercase: " + failedCount + 
	                    "\nThe following Sales Executive Name are not in uppercase: " + failedNames);
	        }

	        // Create a copy of the actual names list and sort it using Java
	        List<String> expectedSortedNames = new ArrayList<>(actualSortedNames);
	        Collections.sort(expectedSortedNames, String.CASE_INSENSITIVE_ORDER);

	        // Compare both lists
	        Assert.assertEquals(actualSortedNames, expectedSortedNames, "Sorting failed,(Cancelled-Booking) in Sales Executive Name");
	    }
//.......................................................................................................................................................
	    @Test(priority = 8)
	    public void Cutomer_Name_Sorting() throws InterruptedException {
	        // Locate and click on the "Sale Executive" sorting button
	        Thread.sleep(2000);
	    	WebElement Cutomer_Name = driver.findElement(By.xpath("//*[text()=\" Customer Name \"]"));
	        clickElementUsingJS(driver, Cutomer_Name);

	        // Capture the names from the table AFTER sorting
	        List<WebElement> nameElements = driver.findElements(By.xpath("//table/tbody/tr/td[3]")); // Adjust column index as needed

	        // Store names in a list
	        List<String> actualSortedNames = new ArrayList<>();
	        List<String> nonUppercaseNames = new ArrayList<>();

	        for (WebElement nameElement : nameElements) {
	            String name = nameElement.getText().trim();
	            actualSortedNames.add(name);
	            if (!name.equals(name.toUpperCase())) {
	                nonUppercaseNames.add(name);
	            }
	        }
	        // print All Small case name
	        if (!nonUppercaseNames.isEmpty()) {
	            String failedNames = String.join(", ", nonUppercaseNames);
	            int failedCount = nonUppercaseNames.size();
	            Assert.fail("(Cancelled-Booking) Total Count of customers name In Lowercase: " + failedCount + 
	                    "\nThe following customers name are not in uppercase: " + failedNames);
	        }

	        // Create a copy of the actual names list are in ascending order
	        List<String> expectedSortedNames = new ArrayList<>(actualSortedNames);
	        Collections.sort(expectedSortedNames, String.CASE_INSENSITIVE_ORDER);

	        // Compare both lists
	        Assert.assertEquals(actualSortedNames, expectedSortedNames, "Sorting failed,(Cancelled-Booking) in Customer Name");
	    }  
//.........................................................................................
	    @Test(priority = 9)
	    public void verify_Booking_Date_Sorting() throws InterruptedException {
	    	Thread.sleep(1000);
	      // Locate and click the sorting button
	        WebElement sortButton = driver.findElement(By.xpath("//*[text()=\"Booking Date\"]")); // Adjust the locator
	        sortButton.click();
	
	        // Capture booking dates after sorting
	        List<WebElement> bookingDateElements = driver.findElements(By.xpath("//app-booking-table//table/tbody//td[2]")); // Adjust the XPath
	        List<LocalDate> actualDates = new ArrayList<>();

	        for (WebElement element : bookingDateElements) {
	            actualDates.add(LocalDate.parse(element.getText(), formatter));
	        }

	        // Create a sorted copy of actualDates
	        List<LocalDate> expectedSortedDates = new ArrayList<>(actualDates);
	        Collections.sort(expectedSortedDates);

	        // Assertion: Check if actual list matches expected sorted list
	        Assert.assertEquals(actualDates, expectedSortedDates, "(Cancelled-Booking) Booking dates are not sorted in ascending order!");
	    }
	  //.......................................................................................................................................................
	    @Test(priority = 10)
	    public void Model_Name_Sorting() throws InterruptedException {
	        // Locate and click on the "Sale Executive" sorting button
	        Thread.sleep(2000);
	    	WebElement Model_Name = driver.findElement(By.xpath("//*[text()=\"Model\"]"));
	        clickElementUsingJS(driver, Model_Name);

	        // Capture the names from the table AFTER sorting
	        List<WebElement> nameElements = driver.findElements(By.xpath("//table/tbody/tr/td[4]")); // Adjust column index as needed

	        // Store names in a list
	        List<String> actualSortedNames = new ArrayList<>();
	        List<String> nonUppercaseNames = new ArrayList<>();

	        for (WebElement nameElement : nameElements) {
	            String name = nameElement.getText().trim();
	            actualSortedNames.add(name);
	            if (!name.equals(name.toUpperCase())) {
	                nonUppercaseNames.add(name);
	            }
	        }
        // print All Small case name
	        if (!nonUppercaseNames.isEmpty()) {
	            String failedNames = String.join(", ", nonUppercaseNames);
	            int failedCount = nonUppercaseNames.size();
	            Assert.fail("(Cancelled-Booking) Total Count of Model name In Lowercase: " + failedCount + 
	                    "\nThe following Model name are not in uppercase: " + failedNames);
	        }

	        // Create a copy of the actual names list are in ascending order
	        List<String> expectedSortedNames = new ArrayList<>(actualSortedNames);
	        Collections.sort(expectedSortedNames, String.CASE_INSENSITIVE_ORDER);

	        // Compare both lists
	        Assert.assertEquals(actualSortedNames, expectedSortedNames, "Sorting failed (Cancelled-Booking) in Model Name");
	    }  	
	//.............................................................................................    
	    @Test(priority = 11)
	    public void Varient_Sorting() throws InterruptedException {
	        Thread.sleep(2000);
	        WebElement Varient = driver.findElement(By.xpath("//*[text()=\"Model\"]"));
	        clickElementUsingJS(driver, Varient);

	        List<WebElement> nameElements = driver.findElements(By.xpath("//table/tbody/tr/td[5]"));

	        // Store names in a list
	        List<String> actualSortedNames = new ArrayList<>();
	        List<String> nonUppercaseNames = new ArrayList<>();

	        for (WebElement nameElement : nameElements) {
	            String name = nameElement.getText().trim();
	            actualSortedNames.add(name);
	            if (!name.equals(name.toUpperCase())) {
	                nonUppercaseNames.add(name);
	            }
	        }
        // print All Small case name
	        if (!nonUppercaseNames.isEmpty()) {
	            String failedNames = String.join(", ", nonUppercaseNames);
	            int failedCount = nonUppercaseNames.size();
	            Assert.fail("(Cancelled-Booking) Total Count of Varient In Lowercase: " + failedCount + 
	                    "\nThe following Varient are not in uppercase: " + failedNames);
	        }

	        // Create a copy of the actual names list are in ascending order
	        List<String> expectedSortedNames = new ArrayList<>(actualSortedNames);
	        Collections.sort(expectedSortedNames, String.CASE_INSENSITIVE_ORDER);
	        // Compare both lists
	        Assert.assertEquals(actualSortedNames, expectedSortedNames, "Sorting failed (Cancelled-Booking) in Varient");
	    }  		    
	  //.............................................................................................    
	    @Test(priority = 12)
	    public void Colour_Sorting() throws InterruptedException {
	        Thread.sleep(2000);
	        WebElement Colour = driver.findElement(By.xpath("//*[text()=\"Colour\"]"));
	        clickElementUsingJS(driver, Colour);
	        List<WebElement> nameElements = driver.findElements(By.xpath("//table/tbody/tr/td[6]"));

	        // Store names in a list
	        List<String> actualSortedNames = new ArrayList<>();
	        List<String> nonUppercaseNames = new ArrayList<>();

	        for (WebElement nameElement : nameElements) {
	            String name = nameElement.getText().trim();
	            actualSortedNames.add(name);
	            if (!name.equals(name.toUpperCase())) {
	                nonUppercaseNames.add(name);
	            }
	        }
        // print All Small case name
	        if (!nonUppercaseNames.isEmpty()) {
	            String failedNames = String.join(", ", nonUppercaseNames);
	            int failedCount = nonUppercaseNames.size();
	            Assert.fail("(Cancelled-Booking) Total Count of Colour In Lowercase: " + failedCount + 
	                    "\nThe following Colour are not in uppercase: " + failedNames);
	        }

	        // Create a copy of the actual names list are in ascending order
	        List<String> expectedSortedNames = new ArrayList<>(actualSortedNames);
	        Collections.sort(expectedSortedNames, String.CASE_INSENSITIVE_ORDER);
	        // Compare both lists
	        Assert.assertEquals(actualSortedNames, expectedSortedNames, "Sorting failed (Cancelled-Booking) :: in Colour");
	    }  	
	  //............................................................................................
	    @Test(priority = 13)
	    public void Booking_ID_Sorting() throws InterruptedException {
	        Thread.sleep(2000);
	        // Locate and click on the "Sale Executive" sorting button
	        WebElement Booking_ID = driver.findElement(By.xpath("//*[text()=\"Booking ID\"]"));
	        clickElementUsingJS(driver, Booking_ID);

	        // Capture the booking IDs from the table AFTER sorting
	        List<WebElement> ALL_Booking_ID = driver.findElements(By.xpath("//app-booking-table//table/tbody//td[1]")); // Adjust column index as needed
	        List<String> invalidBookingIDs = new ArrayList<>();

	        for (WebElement bookingIDElement : ALL_Booking_ID) {
	            String bookingID = bookingIDElement.getText().trim();
	            // Check if the booking ID starts with "SOB" and has exactly 11 characters
	            if (!bookingID.startsWith("SOB") || bookingID.length() != 11) {
	                invalidBookingIDs.add(bookingID);
	            }
	        }

	        if (!invalidBookingIDs.isEmpty()) {
	            String errorMessage = "(Cancelled-Booking) Invalid Booking IDs found :\nCount: " + invalidBookingIDs.size() 
	            + "\n" + String.join("\n", invalidBookingIDs);
	            Assert.fail(errorMessage);
	        }	        
	    }
	    @AfterClass
	    public void tearDown() {
	    	 driver.quit();
	  }   
	 
	    //.....................................................................
 		public void clickElementUsingJS(WebDriver driver, WebElement element) {
 			JavascriptExecutor js = (JavascriptExecutor) driver;
 			js.executeScript("arguments[0].click();", element);
 		}
}