package test;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.ReadExcelUtil;

public class LoginTest {
	WebDriver driver;
	@BeforeTest
	public void beforeTest()
	{
		System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://demowebshop.tricentis.com/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);	
	}
	
  @Test(dataProvider = "XLSXData")
  public void testLoginValidData(String email,String password) {
	  driver.findElement(By.linkText("Log in")).click();
	  driver.findElement(By.id("Email")).sendKeys(email);
	  //driver.findElement(By.id("Password")).sendKeys(password);
	  //to encrypt pwd 
	  System.out.println(new String(Base64.getDecoder().decode(password)));
	  driver.findElement(By.id("Password")).sendKeys(new String(Base64.getDecoder().decode(password)));
	  
	  driver.findElement(By.cssSelector("input[value='Log in']")).click();
	  Assert.assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed());
	  driver.findElement(By.linkText("Log out")).click();
  }
  
 @DataProvider(name="XLSXData")
 public Object [][] getDataFromXL()
 {
	 return ReadExcelUtil.readFromXL("Login");
 }
}



