package SIP;

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

public class Additional_FillterSEMO extends Dealer_User_Login {
	
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
        @Test(priority = 5)
        public void verifyBookingDateFilter() throws InterruptedException {
            executeFilter(this::selectBookingDate, "Booking Date");
        }

        @Test(priority = 6)
        public void verifyModelFilter() throws InterruptedException {
            executeFilter(this::selectModel, "Model");
        }

        @Test(priority = 7)
        public void verifyVariantFilter() throws InterruptedException {
            executeFilter(this::selectVariant, "Variant");
        }

        @Test(priority = 8)
        public void verifyBookingAmountFilter() throws InterruptedException {
            executeFilter(this::selectBookingAmount, "Booking Amount");
        }

        private void executeFilter(Runnable filterMethod, String filterName) {
            try {
                filterMethod.run();
                clearFilter();
            } catch (Exception e) {
                Assert.fail("TEST FAILED :: " + filterName + " filter did not execute correctly.");
            }
        }

        private void selectBookingDate() {
            clickElement("(//*[@aria-label='Open calendar'])[1]");
            clickElement("//mat-calendar-header//button[2]/span[2]");
            clickElement("//mat-month-view//tr[2]/td[1]/button");
            clickElement("//mat-month-view//tr[2]/td[7]/button");
            clickElement("(//*[text()='Search'])[1]");
            verifyBookingDateData();
        }

        private void verifyBookingDateData() {
            List<WebElement> bookingDates = getElements("//app-booking-table//table/tbody//td[2]");
            if (bookingDates.isEmpty()) Assert.fail("Booking date data is NULL");
            for (WebElement dateElement : bookingDates) {
                int day = Integer.parseInt(dateElement.getText().trim().substring(0, 2));
                if (day < 1 || day > 7) Assert.fail("Invalid Booking Date: " + dateElement.getText());
            }
        }

        private void selectModel() {
            String selectedModel = selectDropdownOption("Model", 4);
            verifyFilterResults("//table/tbody/tr/td[4]", selectedModel);
        }

        private void selectVariant() {
            String selectedVariant = selectDropdownOption("Variant", 3);
            verifyFilterResults("//table/tbody/tr/td[5]", selectedVariant);
        }

        private void selectBookingAmount() {
            String selectedAmount = selectDropdownOption("Booking Amount", 3);
            verifyAmount(selectedAmount);
        }

        private String selectDropdownOption(String filterName, int optionIndex) {
            clickElement("//*[text()='" + filterName + "']");
            clickElement("(//*[text()='Select'])[2]");
            WebElement option = waitForElement("(//mat-option[contains(@id, 'mat-option-')])[" + optionIndex + "]");
            String text = option.getText().trim();
            option.click();
            return text;
        }

        private void verifyFilterResults(String columnXpath, String expectedValue) {
            for (WebElement element : getElements(columnXpath)) {
                if (!element.getText().trim().equals(expectedValue)) {
                    Assert.fail("Mismatch Found. Expected: " + expectedValue + " but got: " + element.getText());
                }
            }
        }

        private void verifyAmount(String expectedAmount) {
            for (WebElement element : getElements("//table/tbody/tr/td[8]")) {
                int value = Integer.parseInt(element.getText().trim());
                if (value < 1000 || value > 11000) Assert.fail("Invalid Amount: " + value);
            }
        }

        private void clearFilter() {
            clickElement("//*[text()='Clear']");
        }

        private void clickElement(String xpath) {
            waitForElement(xpath).click();
        }

        private WebElement waitForElement(String xpath) {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        }

        private List<WebElement> getElements(String xpath) {
            return driver.findElements(By.xpath(xpath));
        }
        //.....................................................................
    	public void clickElementUsingJS(WebDriver driver, WebElement element) {
    		JavascriptExecutor js = (JavascriptExecutor) driver;
    		js.executeScript("arguments[0].click();", element);
    	}
    }

