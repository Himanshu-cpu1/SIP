package SIP;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dealer_User_Login {

    public static  WebDriver driver;
    public WebDriverWait wait;

    @SuppressWarnings("deprecation")
	@BeforeClass
    public void setup() {

//        // Set up ChromeDriver using WebDriverManager
//        WebDriverManager.chromedriver().setup();
//        
//        // Chrome options
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-minimized"); // Open Chrome in minimized mode
//
//        // Initialize the ChromeDriver with options
//        driver = new ChromeDriver(options);
//
//        // Open the target URL
//        driver.get("https://dealercrm.co.in/login");
//        
//        // Minimize window programmatically
//        driver.manage().window().setPosition(new Point(-2000, 0)); // Moves window out of visible screen
//        driver.manage().timeouts().implicitlyWait(16, TimeUnit.SECONDS);
//    	   // Set up ChromeDriver with the desired download directory
               
    	 WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
         driver.get("https://dev.marutisuzukicjap.co.in/sip/booking-list");
         driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
  //.......................................................................................... 
    //.......................................................................................... 
    @Test(priority = 1)
    public void Dealer_User_Login1() throws InterruptedException {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement MSPIN = wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@placeholder='MSPIN']")));
        MSPIN.sendKeys("28857");

        WebElement SendOTP = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Send OTP ']")));
        SendOTP.click();

//        WebElement TestMSPIN = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='TEST MSPIN']")));
//        TestMSPIN.sendKeys("28857");

        WebElement Captcha = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Enter Captcha']")));
        Captcha.sendKeys("X1Y2Z4G5H8J7U9");

        WebElement VerifyCaptcha = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Verify Captcha ']")));
        VerifyCaptcha.click();   
       
        ErrorMessage();   //  check for the validation message is display     
       
        WebElement OTP = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='OTP']")));
        OTP.sendKeys("5555");

        WebElement click_On_Login = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Login ']")));
        click_On_Login.click();
    }

    
    @Test(priority = 2)
    public void Click_On_SIP() throws InterruptedException {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement SIP = wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@alt='SIP']")));
            Actions actions = new Actions(driver);
            actions.doubleClick(SIP).perform();

            boolean urlChanged = wait1.until(ExpectedConditions.urlToBe("https://dev.marutisuzukicjap.co.in/sip/booking-list"));
            if (urlChanged) {
                System.out.println("Test Passed: Navigated to the expected URL");
            } else {
                Assert.fail("Test Failed: Wrong Booking list Url");
            }
        } catch (Exception e) {
            Assert.fail("Test Failed: Wrong Booking list Url");
        }
    }
    //...................................................................................    
 
  //..............................Helper Method........................................................		
    public void ErrorMessage() {	
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
            WebElement Errormessage = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\" Enter valid MSPIN \"]")));
            if (Errormessage.isDisplayed()) {
                Assert.fail("Test Failed: Validation message 'Enter valid MSPIN' is displayed.");
                driver.quit();
            }
        } catch (TimeoutException e) {
            // If error message is not found, continue with next test case
            System.out.println("No validation message displayed, proceeding to the next test case.");
        }
    }
  
 
}