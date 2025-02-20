	package SIP;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Invalid_OTP {
	public static WebDriver driver;
    public WebDriverWait wait;
        
    String validCaptcha = "X1Y2Z4G5H8J7U9";  
    String Valid_MSPIN = "28857";    
    String Invalid_OTP = "55455";   
    //..................................................................................................
    @SuppressWarnings("static-access")
    @BeforeClass
    public void SMR() throws InterruptedException {
        this.driver = Invalid_Captcha.driver;
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
                Captcha.sendKeys(validCaptcha);
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
         
    }   
  //............................................................................................
    @Test(priority=5)
    public void OTP() throws InterruptedException {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement OTP = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder=\"OTP\"]")));
            if (OTP.isEnabled() && OTP.getAttribute("readonly") == null) {
                OTP.sendKeys(Invalid_OTP);
                System.out.println("OTP entered successfully.");
            } else {
                Assert.fail("OTP field is not writable. Test Failed!");
            }
        } catch (Exception e) {
            Assert.fail("OTP field is not writable. Test Failed!");
        }
    }
  //.................................................................................................
    @Test(priority=6)
    public void click_On_Login() throws InterruptedException {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement click_On_Login = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Login ']")));
            click_On_Login.click();

            WebElement loginSuccessMessage = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\"Please Enter Valid OTP\"]")));
            if (loginSuccessMessage.isDisplayed()) {
                System.out.println("Test Passed: Login was successful");
            } else {
                Assert.fail("Test Failed: OTP Validation message not reflect after entering invalid OTP ");
            }
        } catch (Exception e) {
            Assert.fail("Test Failed: OTP Validation message not reflect after entering invalid OTP ");
        }
     }
  //......................................................................................      
    @AfterClass
    public void teardown() {
    	 driver.quit();
     }
}
