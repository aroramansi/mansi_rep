package test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.java.xml.User;
import com.java.xml.Users;

import utility.BrowserUtility;


public class ReadTestFromXML {

	WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		driver = BrowserUtility.createBrowser("chrome");
		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.linkText("SignIn")).click();
	}
	
	@Test
	public void loginTestXML() {
		readFromXML();
	}
	
	public void readFromXML()
	{
		try {
			//Create an Object of Unmarshaller from JAXBContext
			JAXBContext context=JAXBContext.newInstance(Users.class);
			Unmarshaller unmarshal=context.createUnmarshaller();
			
			File file=new File(".//src//test//resources//Data//LoginData.xml");
			
			//Convert XML Elements into JAVA objects 
			Users users=(Users)unmarshal.unmarshal(file);
			List<User> allUsers=users.getUsers();
			
			for(User u1:allUsers)
			{
				doLogin(u1.getUsername(),u1.getPassword());
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			
		}
	}
	public void doLogin(String uname,String pwd) throws InterruptedException
	{
		driver.findElement(By.linkText("SignIn")).click();
		driver.findElement(By.id("userName")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.name("Login")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("SignOut")).click();
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
