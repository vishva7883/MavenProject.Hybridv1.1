package lib.utility;

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
import lib.dataProviders.ReadExcel;

public class GenericWrapper {	

	public static RemoteWebDriver driver;  //WebDriver is Interface. RemoteWebDriver is super most class
	//public static WebDriver driver;  //WebDriver is Interface. RemoteWebDriver is super most class
	public static WebDriverWait wait;
	public static JavascriptExecutor executor;
	public static Actions action;
	public static Robot robot;
	public static String cls;
	public static Logger logger; 

	/** *******************************************************************************************
	 * Method Name	:: launchBrowser
	 * Method Desc	:: This Method will initialize driver and launch testable application
	 * Called by 	:: launchApplication(); located in "lib.utility > ProjectSpecificMethods.java" file.
	 * Parameters	:: 'brw' - to carry browser details ; 'url' - to carry testable application url 
	 * Calling 		:: none 
	 * Throws 		:: AWTException due to Robot class declaration
	 * Author 		:: Vishnu S
	 * Latest Change:: 13 Feb 2021, 11.30 AM
	 * ********************************************************************************************/

	public static void launchBrowser(String brw, String url) throws AWTException
	{

		switch (brw.toUpperCase())
		{
			case "CHROME": {
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-notifications");
					options.addArguments("--disable-extensions");
					options.addArguments("--incognito");
					
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
		
		System.out.println(brw+" is intialized and launched successfully");
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.get(url);
		driver.manage().window().maximize();
	
		action = new Actions(driver);
		executor = (JavascriptExecutor)driver;
		robot = new Robot();

}

	/** *******************************************************************************************
	 * Method Name	:: findElementBy
	 * Method Desc	:: This Method will initialize a single webElement based on locator supplied
	 * Called by 	:: All the page classes (*.java) located in "lib.page.object.model" package,
	 * Parameters	:: 'locator' - to carry by which locator the WebElement to be Initialized
	 * 					'locValue' - DOM identity of the element 
	 * 					'fieldDesc' - Actual field Description from UI for better report generation purpose
	 * Calling 		:: none 
	 * Return Type	:: WebElement
	 * Throws 		:: none
	 * Author 		:: Vishnu S
	 * Latest Change:: 13 Feb 2021, 11.30 AM
	 * ********************************************************************************************/

	public static WebElement findElementBy(String locator, String locValue, String fieldDesc) {
		
		try {
			switch (locator.toUpperCase()) {
			case "ID":
				System.out.println("The field, '"+fieldDesc+"' located by (id) : '" + locator + "' has been found successfully");
				//return driver.findElement(By.id(locValue));
				return driver.findElementById(locValue);

			case "XPATH":
				System.out.println("The field, '"+fieldDesc+"' located by (xpath) : '" + locator + "' has been found successfully");
				//return driver.findElement(By.xpath(locValue));
				return driver.findElementByXPath(locValue);

			case "CLASS":
				System.out.println("The field, '"+fieldDesc+"' located by (class) : '" + locator + "' has been found successfully");
				//return driver.findElement(By.className(locValue));
				return driver.findElementByClassName(locValue);
				
			case "LINK":
				System.out.println("The field, '"+fieldDesc+"' located by (link) : '" + locator + "' has been found successfully");
				//return driver.findElement(By.linkText(locValue));
				return driver.findElementByLinkText(locValue);

			case "NAME":
				System.out.println("The field, '"+fieldDesc+"' located by (name) : '" + locator + "' has been found successfully");
				//return driver.findElement(By.name(locValue));
				return driver.findElementByName(locValue);

			case "TAGNAME":
				System.out.println("The field, '"+fieldDesc+"' located by (list) : " + locator + "has been found successfully");
				//return driver.findElement(By.TagName(locValue));
				return driver.findElementByTagName(locValue);

			case "PARTIALLINK":
				System.out.println("The field, '"+fieldDesc+"' located by (list) : " + locator + "has been found successfully");
				//return driver.findElement(By.TagName(locValue));
				return driver.findElementByPartialLinkText(locValue);

			default:
				break;
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found!! Field : '"+fieldDesc+"'Locator : '" + locator + "', Value : '" + locValue+ "'. Exception Details : " + e);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid Argument passed!! Field : '"+fieldDesc+"' Locator : '" + locator + "', Value : '" + locValue+ "'. Exception Details : " + e);
		}
		return null;
	}


	/* *******************************************************************************************
	 * Method Name	:: findElementsBy
	 * Method Desc	:: This Method will initialize group of simillar webElements based on locator supplied
	 * Called by 	:: All the page classes (*.java) located in "lib.page.object.model" package,
	 * Parameters	:: 'locator' - to carry by which locator the WebElement to be Initialized
	 * 					'locValue' - DOM identity of the element 
	 * 					'fieldDesc' - Actual field Description from UI for better report generation purpose
	 * Calling 		:: none 
	 * Return Type	:: WebElement
	 * Throws 		:: none
	 * Author 		:: Vishnu S
	 * Latest Change:: 13 Feb 2021, 11.30 AM
	 * ********************************************************************************************/

	public static List<WebElement> findElementsBy(String locator, String locValue, String fieldDesc) {
		
		try {
			switch (locator.toUpperCase()) {
			case "ID":
				System.out.println("The field, '"+fieldDesc+"' located by (id) : '" + locator + "' has been found successfully");
				return driver.findElements(By.id(locValue));

			case "XPATH":
				System.out.println("The field, '"+fieldDesc+"' located by (xpath) : '" + locator + "' has been found successfully");
				return driver.findElements(By.xpath(locValue));

			default:
				break;
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found!! Field : '"+fieldDesc+"'Locator : '" + locator + "', Value : '" + locValue+ "'. Exception Details : " + e);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid Argument passed!! Field : '"+fieldDesc+"' Locator : '" + locator + "', Value : '" + locValue+ "'. Exception Details : " + e);
		}
		return null;
	}


	
	/** *******************************************************************************************
	 * Method Name	:: type
	 * Method Desc	:: This Method will type the in to the text field
	 * Called by 	:: All the page classes (*.java) located in "lib.page.object.model" package,
	 * Parameters	:: 'ele' - to carry Webelement locator details which webelement to be targeted in DOM
	 * 					'data' - Value to be fed in to Text field in UI 
	 * 					'fieldDesc' - Actual field Description from UI for better report generation purpose
	 * Calling 		:: clear 
	 * Parameters	:: 'ele' - to carry Webelement locator details which webelement to be targeted in DOM
	 * 					'fieldDesc' - Actual field Description from UI for better report generation purpose
	 * 
	 * Return Type	:: Void
	 * Throws 		:: none
	 * Author 		:: Vishnu S
	 * Latest Change:: 13 Feb 2021, 11.30 AM
	 * ********************************************************************************************/

	public static void type(WebElement ele, String data, String fieldDesc) {

		try {
			
			wait.until(ExpectedConditions.visibilityOf(ele));
			executor.executeScript("arguments[0].scrollIntoView(true);", ele);
			clear(ele,fieldDesc);
			ele.sendKeys(data);
			System.out.println("The value " + data + " is entered successfully in the field '"+fieldDesc+", Locator '"+ ele+"'");
		} catch (IllegalStateException e) {
			System.out.println("Element Not in input acceptable state !! Field : "+fieldDesc+"; Locator : " + ele + "; Value : " + data + "Exception Details : " + e);
		} catch (TypeNotPresentException e) {
			System.out.println("Type option not enabled !! Field : "+fieldDesc+"; Locator : " + ele + "; Value : " + data + "Exception Details : " + e);
		} catch (NullPointerException e) {
			System.out.println("Invalid/Incorrect argument!! Field : "+fieldDesc+"; Locator : " + ele + "; Value : " + data + "Exception Details : " + e);
		}
	}
	
	
	/** *******************************************************************************************
	 * Method Name	:: clear
	 * Method Desc	:: This Method will clear the prefilled value from the text field
	 * Called by 	:: type() located in this same "lib.utility" package,
	 * Parameters	:: 'ele' - to carry Webelement locator details which webelement to be targeted in DOM 
	 * 					'fieldDesc' - Actual field Description from UI for better report generation purpose
	 * Calling 		:: none 
	 * Return Type	:: void
	 * Throws 		:: none
	 * Author 		:: Vishnu S
	 * Latest Change:: 13 Feb 2021, 11.30 AM
	 * ********************************************************************************************/
	
	public static void clear(WebElement ele, String fieldDesc) {
		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
			executor.executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.clear();
			System.out.println("The prefilled text in the field, '"+fieldDesc+"'; element '" + ele + "' cleared successfully");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Element Not Found!! The field, '"+fieldDesc+"'; element '" + ele + "'. Exception Details : " + e);
		} catch (NullPointerException e) {
			System.out.println("Invalid/Incorrect argument experienced for the field, '"+fieldDesc+"'; element '" + ele + "'. Exception Details : " + e);
		}
	}
	
	
	@SuppressWarnings("null")
	public static void click(WebElement ele, String fieldDesc) {
		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			executor.executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.click();
			System.out.println("The field, '"+fieldDesc+"'; element '" + ele + "' clicked successfully by '.click' method");
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			
				try {
					System.out.println(".Java Executor");
					executor.executeScript("arguments[0].scrollIntoView(true);", ele);
					executor.executeScript("arguments[0].click();", ele);
					System.out.println("The field, '"+fieldDesc+"'; element '" +ele + "' clicked successfully by 'java executeScript.click' method");
					} 
					catch (ElementClickInterceptedException ex) {
					
							try {
								action.moveToElement(ele).click().perform();
								System.out.println("The field, '"+fieldDesc+"'; element '" +ele + "' clicked successfully by 'action.click' method");
								} 
								catch (org.openqa.selenium.NoSuchElementException exe) {
									System.out.println("Element Not Found!! The field, '"+fieldDesc+"'; element '" + ele + "'. Details : '" + exe +"'"+ "Exception Details : " + e);
								}
					}
		}
		catch (Exception e) {
			System.out.println("Exception occured when handling the field, '"+fieldDesc+"'; element '" + ele + "'. Details : '" + e +"'"+ "Exception Details : " + e);
		}
	}
	
	
	
	
	
	

	public static String getText(WebElement ele, String fieldDesc) {

		String txt = "";
		try {
			txt = ele.getText();
			if (txt != "") {
				System.out.println("The text from the field : "+fieldDesc+"; element :" + ele + " has been extracted");
			} else {
				System.out.println("The text from the field : "+fieldDesc+"; element :" + ele + " is BLANK or NULL");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Element Not Found!! Field : "+fieldDesc+"; Webelement : " + ele + "Exception Details : " + e);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid/Incorrect argument Field : "+fieldDesc+"; Webelement : " + ele + "Exception Details : " + e);
		} catch (NullPointerException e) {
			System.out.println("Invalid/Incorrect argument Field : "+fieldDesc+"; Webelement : " + ele + "Exception Details : " + e);
		}

		return txt;

	}
	
	
	public boolean verifyExactTitle(String expectedTitle) {
		
		boolean bReturn = false;

		try {
			String titleofpage = driver.getTitle();
			if (titleofpage.equals(expectedTitle)) {
				System.out.println("The given title <" + expectedTitle + "> is matching with the UI title [" + titleofpage + "]");
				bReturn = true;
			} else {
				System.out.println("The given title <" + expectedTitle + "> is NOT matching with the UI title [" + titleofpage	+ "]");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found!! Webelement : " + driver.getTitle() + "Exception Details : " + e);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid/Incorrect argument from Webelement : " + driver.getTitle() + "Exception Details : " + e);
		} catch (NullPointerException e) {
			System.out.println("Invalid/Incorrect argument from Webelement : " + driver.getTitle() + "Exception Details : " + e);
		}
		return bReturn;
	}
	
	public void verifyExactText(WebElement ele, String expectedText, String fieldName) {
		
		try {
			String vercompnam = getText(ele, fieldName);

			if (vercompnam.equals(expectedText)) {
				System.out.println("For the field : "+fieldName+"; The UI text [" + vercompnam + "] matching the given text <" + expectedText + ">");
				
			} else {
				System.out.println("For the field : "+fieldName+"; The UI text [" + vercompnam + "] doesnt match with the given text <" + expectedText + ">");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found!! For the field "+fieldName+";  Webelement : " + ele + " expected text " + expectedText);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid/Incorrect argument For the field : "+fieldName+"; Webelement : " + ele + " expected text " + expectedText);
		} catch (NullPointerException e) {
			System.out.println("Invalid/Incorrect argument For the field : "+fieldName+"; Webelement : " + ele + " expected text " + expectedText);
		}

	}

	public void verifyPartialText(WebElement ele, String expectedText, String fieldName) {
		// TODO Auto-generated method stub

		try {
			String vercompnam = getText(ele, fieldName);

			if (vercompnam.contains(expectedText)) {
				System.out.println("For the field : "+fieldName+"; The UI text [" + vercompnam + "] contains the given text <" + expectedText + ">");
	
			} else {
				System.out.println("For the field : "+fieldName+"; The UI text [" + vercompnam + "] doesnt contain the given text <" + expectedText + ">");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found!! For the field : "+fieldName+"; Webelement : " + ele + " expected text " + expectedText);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid/Incorrect argument "+fieldName+"; Webelement : " + ele + " expected text " + expectedText);
		} catch (NullPointerException e) {
			System.out.println("Invalid/Incorrect argument "+fieldName+"; Webelement : " + ele + " expected text " + expectedText);
		}

	}


	
}
