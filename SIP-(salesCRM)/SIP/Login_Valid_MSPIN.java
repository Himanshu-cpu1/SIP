package SIP;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_Valid_MSPIN {
    public static WebDriver driver;
    public WebDriverWait wait;
    
    String MSPIN_VALUE = "28857";            //  MSPIN 
    String TEST_MSPIN_VALUE = "28857";       //  Test MSPIN 
    String CAPTCHA_VALUE = "X1Y2Z4G5H8J7U9"; //  Captcha 
    String OTP_VALUE = "5555";               //  OTP 
    
    @SuppressWarnings("deprecation")
	@BeforeClass
    public void setup() {         
    	 WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
         driver.get("https://dev.marutisuzukicjap.co.in/sip/booking-list");
         driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(16, TimeUnit.SECONDS);
      }
  //........................MSPIN Fileds.............................................................................  	         
        @Test
        public void MSPIN() throws InterruptedException {
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement MSPIN = wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@placeholder=\"MSPIN\"]")));

            if (MSPIN.isEnabled() && MSPIN.getAttribute("readonly") == null) {
                MSPIN.sendKeys(MSPIN_VALUE);
                System.out.println("MSPIN entered successfully.");
            } else {
                Assert.fail("MSPIN field is not writable. Test Failed!");
            }
        }
//................................................................................................
        @Test(dependsOnMethods = {"MSPIN"})
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
//.................................................................................................
        @Test(dependsOnMethods = {"SendOTP"})
        public void TestMSPIN() throws InterruptedException {
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement TestMSPIN = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder=\"TEST MSPIN\"]")));

            if (TestMSPIN.isEnabled() && TestMSPIN.getAttribute("readonly") == null) {
                TestMSPIN.sendKeys(TEST_MSPIN_VALUE);
                System.out.println("TestMSPIN entered successfully.");
            } else {       
            	Assert.fail("TestMSPIN field is not writable. Test Failed!");
            }
        }
   //.................................................................................................
        @Test(dependsOnMethods = {"TestMSPIN"})
        public void Captcha() throws InterruptedException {
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement Captcha = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder=\"Enter Captcha\"]")));
            
            if (Captcha.isEnabled() && Captcha.getAttribute("readonly") == null) {
                Captcha.sendKeys(CAPTCHA_VALUE);
                System.out.println("Captcha entered successfully.");
            } else {
                Assert.fail("Captcha field is not writable. Test Failed!");
            }
        }
    //.................................................................................................

        @Test(dependsOnMethods = {"Captcha"})
        public void VerifyCaptcha() throws InterruptedException {
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
            try {
                WebElement VerifyCaptcha = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Verify Captcha \"]")));
                if (VerifyCaptcha.isDisplayed() && VerifyCaptcha.isEnabled()) {
                    VerifyCaptcha.click();
                    System.out.println("VerifyCaptcha button clicked successfully.");
                } else {
                    Assert.fail("VerifyCaptcha button is not clickable. Test Failed!");
                }
            } catch (Exception e) {
                Assert.fail("Exception occurred: " + e.getMessage());
            }
        }
//............................................................................................
        @Test(dependsOnMethods = {"VerifyCaptcha"})
        public void OTP() throws InterruptedException {
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
            try {
                WebElement OTP = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder=\"OTP\"]")));
                if (OTP.isEnabled() && OTP.getAttribute("readonly") == null) {
                    OTP.sendKeys(OTP_VALUE);
                    System.out.println("OTP entered successfully.");
                } else {
                    Assert.fail("OTP field is not writable. Test Failed!");
                }
            } catch (Exception e) {
                Assert.fail("OTP field is not writable. Test Failed!");
            }
        }
      //.................................................................................................
        @Test(dependsOnMethods = {"OTP"})
        public void click_On_Login() throws InterruptedException {
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
            try {
                WebElement click_On_Login = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Login ']")));
                click_On_Login.click();

                WebElement loginSuccessMessage = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),\"Login Successfully\")]")));
                if (loginSuccessMessage.isDisplayed()) {
                    System.out.println("Test Passed: Login was successful");
                } else {
                    Assert.fail("Test Failed: Login success message not displayed");
                }
            } catch (Exception e) {
                Assert.fail("Test Failed: Login success message not displayed");
            }           
        }
  
 //.................................................................................................. 
        
        @AfterClass
        public void tearDown() {
        	 driver.quit();
      }
    }
