package library.utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import library.data.reader.ReadExcel;

public class Utility extends ReadExcel{	

	public static RemoteWebDriver driver;  //WebDriver is Interface. RemoteWebDriver is super most class
	//public static WebDriver driver;  //WebDriver is Interface. RemoteWebDriver is super most class
	public static WebDriverWait wait;
	public static JavascriptExecutor executor;
	public static Actions action;
	public static Robot robot;
	public static String cls;
	public static Logger logger; 

	public static void Configlogger() {
		logger = Logger.getLogger("Pre.class");
		BasicConfigurator.configure(); 
	}

	public static void Logger(String str) {
		logger = Logger.getLogger(str);
	}
	

	
	public static void browser_Selections(String brw, String url) throws AWTException
	{
		Configlogger();
		Logger(cls); 
		switch (brw.toUpperCase())
		{
			case "CHROME": {
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-notifications");
					options.addArguments("--disable-extensions");
					options.addArguments("--incognito");
					options.addExtensions(new File("extension_0_4_7_0.crx"));
					
				    WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver(options);
					brw = "Chrome";
				
					break;
				}
			case "FIREFOX": {
				WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver(); 
					brw = "Firefox";
					break;
			}
	
			case "IE": {
					WebDriverManager.iedriver().setup();
					driver = new InternetExplorerDriver(); 
					brw = "IE";
					break;
			}
	
			case "EDGE": {
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();	
					brw = "Edge";
					break;
			}
	
			default : {
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-notifications");
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver(options);
					brw = "Chrome";
					break;
				}
		}		
		
		logger.info(brw+" is intialized and launched successfully");
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.get(url);
		driver.manage().window().maximize();
	
		action = new Actions(driver);
		executor = (JavascriptExecutor)driver;
		robot = new Robot();

}

	public static WebElement findElementBy(String locator, String locValue, String fieldDesc) {
		
		try {
			switch (locator.toUpperCase()) {
			case "ID":
				logger.info("The field, '"+fieldDesc+"' located by (id) : '" + locator + "' has been found successfully");
				return driver.findElement(By.id(locValue));

			case "XPATH":
				logger.info("The field, '"+fieldDesc+"' located by (xpath) : '" + locator + "' has been found successfully");
				return driver.findElement(By.xpath(locValue));

			case "CLASS":
				logger.info("The field, '"+fieldDesc+"' located by (class) : '" + locator + "' has been found successfully");
				return driver.findElement(By.className(locValue));

			case "LINK":
				logger.info("The field, '"+fieldDesc+"' located by (link) : '" + locator + "' has been found successfully");
				return driver.findElement(By.linkText(locValue));

			case "NAME":
				logger.info("The field, '"+fieldDesc+"' located by (name) : '" + locator + "' has been found successfully");
				return driver.findElement(By.name(locValue));

			case "LIST":
				logger.info("The field, '"+fieldDesc+"' located by (list) : " + locator + "has been found successfully");
				return driver.findElement(By.name(locValue));

			default:
				break;
			}
		} catch (NoSuchElementException e) {
			logger.error("Element Not Found!! Field : '"+fieldDesc+"'Locator : '" + locator + "', Value : '" + locValue+ "'. Exception Details : " + e);
		} catch (IllegalArgumentException e) {
			logger.error("Invalid Argument passed!! Field : '"+fieldDesc+"' Locator : '" + locator + "', Value : '" + locValue+ "'. Exception Details : " + e);
		}
		return null;
	}


	
	public static List<WebElement> findElementsBy(String locator, String locValue, String fieldDesc) {
		
		try {
			switch (locator.toUpperCase()) {
			case "ID":
				logger.info("The field, '"+fieldDesc+"' located by (id) : '" + locator + "' has been found successfully");
				return driver.findElements(By.id(locValue));

			case "XPATH":
				logger.info("The field, '"+fieldDesc+"' located by (xpath) : '" + locator + "' has been found successfully");
				return driver.findElements(By.xpath(locValue));

			default:
				break;
			}
		} catch (NoSuchElementException e) {
			logger.error("Element Not Found!! Field : '"+fieldDesc+"'Locator : '" + locator + "', Value : '" + locValue+ "'. Exception Details : " + e);
		} catch (IllegalArgumentException e) {
			logger.error("Invalid Argument passed!! Field : '"+fieldDesc+"' Locator : '" + locator + "', Value : '" + locValue+ "'. Exception Details : " + e);
		}
		return null;
	}



	
	
	public static void clear(WebElement ele, String fieldDesc) {
		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
			executor.executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.clear();
			logger.info("The prefilled text in the field, '"+fieldDesc+"'; element '" + ele + "' cleared successfully");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			logger.error("Element Not Found!! The field, '"+fieldDesc+"'; element '" + ele + "'. Exception Details : " + e);
		} catch (NullPointerException e) {
			logger.error("Invalid/Incorrect argument experienced for the field, '"+fieldDesc+"'; element '" + ele + "'. Exception Details : " + e);
		}
	}
	
	
	@SuppressWarnings("null")
	public static void click(WebElement ele, String fieldDesc) {
		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			executor.executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.click();
			logger.info("The field, '"+fieldDesc+"'; element '" + ele + "' clicked successfully by '.click' method");
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			
				try {
					System.out.println(".Java Executor");
					executor.executeScript("arguments[0].scrollIntoView(true);", ele);
					executor.executeScript("arguments[0].click();", ele);
					logger.info("The field, '"+fieldDesc+"'; element '" +ele + "' clicked successfully by 'java executeScript.click' method");
					} 
					catch (ElementClickInterceptedException ex) {
					
							try {
								action.moveToElement(ele).click().perform();
								logger.info("The field, '"+fieldDesc+"'; element '" +ele + "' clicked successfully by 'action.click' method");
								} 
								catch (org.openqa.selenium.NoSuchElementException exe) {
									logger.error("Element Not Found!! The field, '"+fieldDesc+"'; element '" + ele + "'. Details : '" + exe +"'"+ "Exception Details : " + e);
								}
					}
		}
		catch (Exception e) {
			logger.error("Exception occured when handling the field, '"+fieldDesc+"'; element '" + ele + "'. Details : '" + e +"'"+ "Exception Details : " + e);
		}
	}
	
	
	
	
	public static void type(WebElement ele, String data, String fieldDesc) {

		try {
			
			wait.until(ExpectedConditions.visibilityOf(ele));
			executor.executeScript("arguments[0].scrollIntoView(true);", ele);
			clear(ele,fieldDesc);
			ele.sendKeys(data);
			logger.info("The value " + data + " is entered successfully in the field '"+fieldDesc+", Locator '"+ ele+"'");
		} catch (IllegalStateException e) {
			logger.error("Element Not in input acceptable state !! Field : "+fieldDesc+"; Locator : " + ele + "; Value : " + data + "Exception Details : " + e);
		} catch (TypeNotPresentException e) {
			logger.error("Type option not enabled !! Field : "+fieldDesc+"; Locator : " + ele + "; Value : " + data + "Exception Details : " + e);
		} catch (NullPointerException e) {
			logger.error("Invalid/Incorrect argument!! Field : "+fieldDesc+"; Locator : " + ele + "; Value : " + data + "Exception Details : " + e);
		}
	}
	
	
	

	public static String getText(WebElement ele, String fieldDesc) {

		String txt = "";
		try {
			txt = ele.getText();
			if (txt != "") {
				logger.info("The text from the field : "+fieldDesc+"; element :" + ele + " has been extracted");
			} else {
				logger.error("The text from the field : "+fieldDesc+"; element :" + ele + " is BLANK or NULL");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			logger.error("Element Not Found!! Field : "+fieldDesc+"; Webelement : " + ele + "Exception Details : " + e);
		} catch (IllegalArgumentException e) {
			logger.error("Invalid/Incorrect argument Field : "+fieldDesc+"; Webelement : " + ele + "Exception Details : " + e);
		} catch (NullPointerException e) {
			logger.error("Invalid/Incorrect argument Field : "+fieldDesc+"; Webelement : " + ele + "Exception Details : " + e);
		}

		return txt;

	}
	
	
	public boolean verifyExactTitle(String expectedTitle) {
		
		boolean bReturn = false;

		try {
			String titleofpage = driver.getTitle();
			if (titleofpage.equals(expectedTitle)) {
				logger.info("The given title <" + expectedTitle + "> is matching with the UI title [" + titleofpage + "]");
				bReturn = true;
			} else {
				logger.info("The given title <" + expectedTitle + "> is NOT matching with the UI title [" + titleofpage	+ "]");
			}
		} catch (NoSuchElementException e) {
			logger.error("Element Not Found!! Webelement : " + driver.getTitle() + "Exception Details : " + e);
		} catch (IllegalArgumentException e) {
			logger.error("Invalid/Incorrect argument from Webelement : " + driver.getTitle() + "Exception Details : " + e);
		} catch (NullPointerException e) {
			logger.error("Invalid/Incorrect argument from Webelement : " + driver.getTitle() + "Exception Details : " + e);
		}
		return bReturn;
	}
	
	public void verifyExactText(WebElement ele, String expectedText, String fieldName) {
		
		try {
			String vercompnam = getText(ele, fieldName);

			if (vercompnam.equals(expectedText)) {
				logger.info("For the field : "+fieldName+"; The UI text [" + vercompnam + "] matching the given text <" + expectedText + ">");
				
			} else {
				logger.error("For the field : "+fieldName+"; The UI text [" + vercompnam + "] doesnt match with the given text <" + expectedText + ">");
			}
		} catch (NoSuchElementException e) {
			logger.error("Element Not Found!! For the field "+fieldName+";  Webelement : " + ele + " expected text " + expectedText);
		} catch (IllegalArgumentException e) {
			logger.error("Invalid/Incorrect argument For the field : "+fieldName+"; Webelement : " + ele + " expected text " + expectedText);
		} catch (NullPointerException e) {
			logger.error("Invalid/Incorrect argument For the field : "+fieldName+"; Webelement : " + ele + " expected text " + expectedText);
		}

	}

	public void verifyPartialText(WebElement ele, String expectedText, String fieldName) {
		// TODO Auto-generated method stub

		try {
			String vercompnam = getText(ele, fieldName);

			if (vercompnam.contains(expectedText)) {
				logger.info("For the field : "+fieldName+"; The UI text [" + vercompnam + "] contains the given text <" + expectedText + ">");
	
			} else {
				logger.error("For the field : "+fieldName+"; The UI text [" + vercompnam + "] doesnt contain the given text <" + expectedText + ">");
			}
		} catch (NoSuchElementException e) {
			logger.error("Element Not Found!! For the field : "+fieldName+"; Webelement : " + ele + " expected text " + expectedText);
		} catch (IllegalArgumentException e) {
			logger.error("Invalid/Incorrect argument "+fieldName+"; Webelement : " + ele + " expected text " + expectedText);
		} catch (NullPointerException e) {
			logger.error("Invalid/Incorrect argument "+fieldName+"; Webelement : " + ele + " expected text " + expectedText);
		}

	}


	
	public static void browser_closeActiveBrowser() {
		driver.close();
		browser_closeAllBrowsers();
	}

	public static void browser_closeAllBrowsers() {
		driver.quit();
	}
	
}
