package lib.pom.lightning;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import lib.utility.ProjectSpecificMethods;

public class LoginPage extends ProjectSpecificMethods {
	
	public LoginPage(RemoteWebDriver driver) {
		this.driver = driver;
	}
		  
	public LoginPage login_UserName_Click() { 
		WebElement eleUserNameClick = findElementBy("id", "username", "User Name");
		click(eleUserNameClick, "User Name");
		return this;
	}
	
	public LoginPage login_UserName_Clear()
	{
	    WebElement eleUserNameClear = findElementBy("id", "username", "User Name");
		clear(eleUserNameClear, "User Name");
		return this;
	}   
	
	public LoginPage login_UserName_Type(String usrnme)
	{
	    WebElement eleUserNameType = findElementBy("id", "username", "User Name");
		type(eleUserNameType, usrnme, "User Name");
		return this;
	}
	

	public LoginPage login_Password_Click() { 
		WebElement elePasswordClick = findElementBy("id", "password", "Password");
		click(elePasswordClick, "Password");
		return this;
	}
	public LoginPage login_Password_Clear()
	{
	    WebElement elePasswordClear = findElementBy("id", "password", "Password");
		clear(elePasswordClear, "Password");
		return this;
	}   
	public LoginPage login_Password_Type(String password)
	{
	    WebElement elePasswordType = findElementBy("id", "password", "Password");
		type(elePasswordType, password, "Password");
		return this;
	}

	
	public TogglePage login_Submit_Click() {
		WebElement eleSubmitClick = findElementBy("id", "Login", "Submit");
		click(eleSubmitClick, "Submit");
		return new TogglePage(driver);
	    }
	

	
}
