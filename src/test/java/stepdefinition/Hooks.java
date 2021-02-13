package stepdefinition;

import java.awt.AWTException;
import java.awt.Robot;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import dataProviders.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks extends AGlobalDecleration{
	
	
	@Before
	public void preCondition(Scenario sc) throws AWTException
	{	
			configFileReader = new ConfigFileReader();
			
			String url = configFileReader.getApplicationUrl();
			String uid = configFileReader.getApplicationUid();
			String pwd = configFileReader.getApplicationPwd();
		
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-extensions");
			options.addArguments("--incognito");
			
		    WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			
			System.out.println("Chrome is intialized and launched successfully");
			
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			
			driver.get(url);
			driver.manage().window().maximize();
		
			action = new Actions(driver);
			executor = (JavascriptExecutor)driver;
			robot = new Robot();

			//Enter SF login uid
			driver.findElementById("username").clear();
			driver.findElementById("username").sendKeys(uid);
			
			//Enter SF login password
			driver.findElementById("password").clear();
			driver.findElementById("password").sendKeys(pwd);
			
			//Click login button
			driver.findElementById("Login").click();

			//Wait for the Home screen to load.
			try {Thread.sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("-1");
		
	}
	@After	
	public void postCondition(Scenario sc)
	{
		driver.close();
	}
}
