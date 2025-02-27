package SIP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface Interface_SIP_All_Path_List {
	
	
    String BaseURL="https://uat.marutisuzukicjap.co.in/projects";
	
	By MSPIN_path=By.xpath("//*[@placeholder='MSPIN']");
	By OTP_path=By.xpath("//*[text()=' Send OTP ']");
	
	String ValidMSPIN="28857";
	String ValidOTP="5555";
	
	//............................Check On Road Price Xpath..............................................
	 // vehicle details 
	
	By Model1=By.xpath("(//app-vehicle-status//h6)[2]");  
	By Varient1=By.xpath("(//app-vehicle-status//h6)[3]");  
	By Colour1=By.xpath("(//app-vehicle-status//h6)[4]");  

//................price details..........................................................................
	By Ex_showroomPRICE=By.xpath("//app-price-details//h6");  
	By FAST_Tag_Charge=By.xpath("(//app-price-details//h6)[4]");  
	By Registration_Charge=By.xpath("(//app-price-details//h6)[6]"); 
	By On_Road_PRICE=By.xpath("(//app-price-details//h6)[9]");  

 //..............Check On Road Price.......................................................................
	
	By Click_Check_On_Road_PRICE=By.xpath("//*[text()=\" Check Onroad Price \"]"); 
	By Ex_showroomPRICE2=By.xpath("(//*[@class=\"left-align\"])[1]");  
	By Model2=By.xpath("//div[contains(@id, 'cdk-accordion-child')]/div/div/div/div[1]/h6");  
	By Varient2=By.xpath("//div[contains(@id, 'cdk-accordion-child')]/div/div/div/div[2]/h6");  
	By Colour2=By.xpath("//div[contains(@id, 'cdk-accordion-child')]/div/div/div/div[3]/h6");
	
	By REGISTRATION=By.xpath("(//*[@class=\"left-align\"])[5]");  	
	By FAST_Tag_Charge2=By.xpath("(//*[@class=\"left-align2\"])[4]");  
	

}


