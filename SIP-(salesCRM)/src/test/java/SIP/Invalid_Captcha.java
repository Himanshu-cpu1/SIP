package SIP;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Invalid_Captcha  {
    public static WebDriver driver;
    public WebDriverWait wait;
        
    String InvalidCaptcha = "2Z4G5H8J7U9";  
    String Valid_MSPIN = "28857";    
  
    //..................................................................................................
    @SuppressWarnings("static-access")
    @BeforeClass
    public void SMR() throws InterruptedException {
        this.driver = Invalid_MSPIN.driver;
    }
    //.................................................................................................
    @Test(priority=1)
    public void MSPIN() throws InterruptedException {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
       driver.navigate().refresh();
        
       WebElement MSPIN = wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@placeholder=\"MSPIN\"]")));

        if (MSPIN.isEnabled() && MSPIN.getAttribute("readonly") == null) {
            MSPIN.sendKeys(Valid_MSPIN);
            System.out.println("MSPIN entered successfully.");
        } else {
            Assert.fail("MSPIN field is not writable. Test Failed!");
        }
    }
//................................................................................................
    @Test(priority=2)
    public void SendOTP() throws InterruptedException {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement SendOTP = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Send OTP \"]")));
            if (SendOTP.isDisplayed() && SendOTP.isEnabled()) {
                SendOTP.click();
                System.out.println("Send OTP button clicked successfully.");
            } else {
                Assert.fail("Send OTP button is not clickable. Test Failed!");
            }
        } catch (Exception e) {
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }
    
    @Test(priority=3)
        public void Captcha() throws InterruptedException {
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement Captcha = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder=\"Enter Captcha\"]")));
            
            if (Captcha.isEnabled() && Captcha.getAttribute("readonly") == null) {
                Captcha.sendKeys(InvalidCaptcha);
                System.out.println("Captcha entered successfully.");
            } else {
                Assert.fail("Captcha field is not writable. Test Failed!");
            }
        }
      //.................................................................................................
    @Test(priority=4)
        public void VerifyCaptcha() throws InterruptedException {
         
    	 WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
         
         WebElement VerifyCaptcha = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Verify Captcha \"]")));
         VerifyCaptcha.click();
         
         WebElement Errormessage = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\" Please Enter Valid Captcha \"]")));
      if (Errormessage.isDisplayed()) {
     	 System.out.println("Test Passed : Passed with Invalid Captcha");
      }else {
     	 Assert.fail("Test Failed : Validation message will not showing 'Please Enter Valid Captcha'  ");
      }
     }            
         
    //......................................................................................      
       
    }
