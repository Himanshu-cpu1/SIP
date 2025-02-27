package UAT_SIP2;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Check_On_Road_Price {
	//.........Price Details............................
	String EX_Showroom_Price; 
	String Fast_Tag_Charge; 
	String RegistrationCHARGE;
	String OnRoad_PRICE;
 //............Vehicle Details.........................
	String MODEL;
	String VARIENT;
	String COLOR;
//.......Check On Road price Details..............................
	String extractedPrice_Registration;
	String extractedPrice2_FAST_Tag;
	String extractedPrice3_EX_show;
	String extractedPrice3_ON_ROAD;
	String Model;
	String Varient;
	String Colour;
//......................................................................	
	public static WebDriver driver;
    public WebDriverWait wait;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    //..................................................................................................
    @SuppressWarnings("static-access")
    @BeforeClass
    public void sip() throws InterruptedException {
        this.driver = Customer_Tag.driver;	    
        }
   
    //...................................................................    
    @Test(priority = 6)
      public void Click_ChecK_On_ROAD_Price() throws InterruptedException {	       	    
  	  try {
  	  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));	    	
  	    WebElement Home = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"Home\"]")));
	    clickElementUsingJS(driver, Home);
  	   Thread.sleep(500);	
  	   
  	   WebElement ROAD_Price = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\" Check Onroad Price \"]")));
	    clickElementUsingJS(driver, ROAD_Price);
	    
	    wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\"Cancel\"]")));
  	  } catch (Exception e) {
	          Assert.fail("Test FAILED  :: \n  In (Pending Booking),Check on Road Price data is not Showing::" );
	      }
    }
  	//...................................................................................    
      @Test(priority = 7)
        public void All_Details_Of_OnRoad_Price() throws InterruptedException {	       	    
        try {
    	  Check_On_road_PRICE_Details(); 	
        }catch(Exception e){
   		 Assert.fail("TEST FAILED : Check On road PRICE Details is null on Customer Page");
   	 }
 	  }
      
    //...................................................................................    
      @Test(priority = 8)
        public void All_Vehicle_Details() throws InterruptedException {	       	    
    	 try {
    	  Vehicle_Details(); 
    	 }catch(Exception e){
    		 Assert.fail("TEST FAILED : Vehicle Details is null on Customer Page");
    	 }
  	  }
      
      //...................................................................................    
      @Test(priority = 9)
        public void All_PRICE_Details() throws InterruptedException {	       	    
    	  try {
    	  PRICE_Details(); 	
    	  }catch(Exception e){
     		 Assert.fail("TEST FAILED : PRICE Details is null on Customer Page");
     	 }
   	  }
    //...................................................................................         
      @Test(priority = 10)
      public void Validate_Vehicle_Details_with_Check_On_Road_PriceTAB() throws InterruptedException {	        
          List<String> failedAssertions = new ArrayList<>();

          if (!MODEL.equals(Model)) {
              failedAssertions.add("Model names are different in Vehicle details");
          }
          if (!VARIENT.equals(Varient)) {
              failedAssertions.add("VARIENTs are different in Vehicle details");
          }
          if (!COLOR.equals(Colour)) {
              failedAssertions.add("COLORs are different in Vehicle details");
          }

          if (!failedAssertions.isEmpty()) {
              Assert.fail(String.join("\n", failedAssertions));
          }
      }
          //...................................................................................         
          @Test(priority = 11)
          public void Validate_Check_On_Road_Price_With_PRICE_DETAILS() throws InterruptedException {	        
              List<String> failedAssertions = new ArrayList<>();

              if (!EX_Showroom_Price.equals(extractedPrice3_EX_show)) {
                  failedAssertions.add("EX Showroom Price are different in price details");
              }
              if (!RegistrationCHARGE.equals(extractedPrice_Registration)) {
                  failedAssertions.add("Registration Charges are different in price details");
              }
              if (!Fast_Tag_Charge.equals(extractedPrice2_FAST_Tag)) {
                  failedAssertions.add("Fast Tag Charge are different in price details");
              }
              if (!OnRoad_PRICE.equals(extractedPrice3_ON_ROAD)) {
                  failedAssertions.add("OnRoad PRICE are different in price details");
              }

              if (!failedAssertions.isEmpty()) {
                  Assert.fail(String.join("\n", failedAssertions));
              }
      }

  //.....................HELPER METHOD.................................................................  
    public void Check_On_road_PRICE_Details() {
    	//..............Check On Road Price.......................................................................
    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));	   
    	WebElement Ex_showroomPRICE2 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class=\"left-align\"])[1]")));
    	WebElement Model2 = driver.findElement(By.xpath("//div[contains(@id, 'cdk-accordion-child')]/div/div/div/div[1]/h6"));
    	WebElement Varient2 = driver.findElement(By.xpath("//div[contains(@id, 'cdk-accordion-child')]/div/div/div/div[2]/h6"));
    	WebElement Colour2 = driver.findElement(By.xpath("//div[contains(@id, 'cdk-accordion-child')]/div/div/div/div[3]/h6"));
    	WebElement REGISTRATION = driver.findElement(By.xpath("(//*[@class=\"left-align\"])[5]"));
    	WebElement FAST_Tag_Charge2 = driver.findElement(By.xpath("(//*[@class=\"left-align2\"])[3]"));
    	WebElement ON_ROAD_Price = driver.findElement(By.xpath("(//*[@class=\"left-align2\"])[4]"));

    	String EX_show = Ex_showroomPRICE2.getText();
    	 Model = Model2.getText();
    	 Varient = Varient2.getText();
    	 Colour = Colour2.getText();
    	String REG = REGISTRATION.getText();
    	String FAST_Tag = FAST_Tag_Charge2.getText();
    	String ON_ROAD = ON_ROAD_Price.getText();
    	
    	 extractedPrice_Registration = REG.replaceAll("[^0-9,]", "");
    	 extractedPrice2_FAST_Tag = FAST_Tag.replaceAll("[^0-9,]", "");
    	 extractedPrice3_EX_show = EX_show.replaceAll("[^0-9,]", "");
    	 extractedPrice3_ON_ROAD = ON_ROAD.replaceAll("[^0-9,]", "");

    	System.out.println("Ex_showroomPRICE2  :: "   +  extractedPrice3_EX_show );
    	System.out.println("Model2 :: "   +  Model );
    	System.out.println("Varient2  ::"   +  Varient );
    	System.out.println("Colour2  ::"   +  Colour );
    	System.out.println("REGISTRATION  ::"   +  extractedPrice_Registration );
    	System.out.println("FAST_Tag_Charge2 :: "   +  extractedPrice2_FAST_Tag );
    	
    	WebElement Click_On_Cancel = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\"Cancel\"]")));
    	Click_On_Cancel.click();  // click    on cancel to close the POPUP
    }
  //..........................................................................  
    public void Vehicle_Details() {
    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));	   
    	WebElement Model3 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//app-vehicle-status//h6)[2]")));
    	WebElement Varient3 = driver.findElement(By.xpath("(//app-vehicle-status//h6)[3]"));
    	WebElement COLOR3 = driver.findElement(By.xpath("(//app-vehicle-status//h6)[4]"));
    
    	 MODEL = Model3.getText();
    	 VARIENT = Varient3.getText();
    	 COLOR = COLOR3.getText();
    	
    	System.out.println("Vehicle_Details_Model :: "   +  MODEL );
    	System.out.println("Vehicle_Details_Varient  ::"   +  VARIENT );
    	System.out.println("Vehicle_Details_Colour  ::"   +  COLOR );
    }
 //............................................................................................   
    public void PRICE_Details() {
    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));	   
    	WebElement Ex_showroomPRICE = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-price-details//h6")));
    	WebElement FAST_Tag_Charge = driver.findElement(By.xpath("(//app-price-details//h6)[4]"));
    	WebElement Registration_Charge = driver.findElement(By.xpath("(//app-price-details//h6)[6]"));
    	WebElement On_Road_PRICE = driver.findElement(By.xpath("(//app-price-details//h6)[9]"));
  
    	 EX_Showroom_Price = Ex_showroomPRICE.getText();
    	 Fast_Tag_Charge = FAST_Tag_Charge.getText();
    	 RegistrationCHARGE = Registration_Charge.getText();
    	 OnRoad_PRICE = On_Road_PRICE.getText();
    	
    	System.out.println("PRICE_Details_Ex_showroomPRICE :: "   +  EX_Showroom_Price );
    	System.out.println("PRICE_Details_FAST_Tag_Charge  ::"   +  Fast_Tag_Charge );
    	System.out.println("PRICE_Details_Registration_Charge  ::"   +  RegistrationCHARGE );
    	System.out.println("PRICE_Details_On_Road_PRICE  ::"   +  OnRoad_PRICE );
    }
    //.....................................................................
	public void clickElementUsingJS(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

}
