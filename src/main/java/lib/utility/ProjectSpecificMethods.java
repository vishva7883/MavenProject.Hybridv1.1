package lib.utility;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ProjectSpecificMethods extends PropertiesConfig {	

	/** *******************************************************************************************
	 * Method Name 	:: launchApplication()
	 * Method Desc	:: This method will launch testable application
	 * Called by 	:: All the page classes (*.java) located in "lib.page.object.model" package.
	 * Parameters	:: None
	 * Calling 		:: All the page classes (*.java) located in "lib.page.object.model" package, 
	 * 					These methods will retrieve browser and url details from Properties file located in "./PropertiesFile > Configuration.properties"
	 * Throws 		:: AWTException, IOException due to file handling and Robot class usage.
	 * Author 		:: Vishnu S
	 * Latest Change:: 13 Feb 2021, 11.30 AM 
	 * ********************************************************************************************/
	
	@BeforeMethod
	public static void launchApplication() throws AWTException, IOException
	{	
			launchBrowser(getBrowser(), getApplicationUrl()); // Called methods are in
	}

	
	
	/** *******************************************************************************************
	 * Method Name 	:: closeBrowser()
	 * Method Desc	:: This method will close the active test browser at the end of the testable application
	 * Called by 	:: Post-requisite Method. Triggered by @After annotation.
	 * Parameters	:: None
	 * Calling 		:: closeAllBrowsers() ; located in same class, "lib.utility > ProjectSpecificMethods.java" file.
	 * Throws 		:: None
	 * Author 		:: Vishnu S
	 * Latest Change:: 13 Feb 2021, 11.30 AM
	 * ********************************************************************************************/
	
	@AfterMethod
	public static void closeBrowser() {
		driver.close();
		closeAllBrowsers();
	}

	
	
	/** *******************************************************************************************
	 * Method Name 	:: closeAllBrowser()
	 * Method Desc	:: Method to close all the open browsers at the end of the testable application
	 * Author 		:: Vishnu S
	 * Called by 	:: closeBrowser(); located in same class, "lib.utility > ProjectSpecificMethods.java" file.
	 * Parameters	:: None
	 * Calling 		:: none 
	 * Throws 		:: None
	 * Author 		:: Vishnu S
	 * Latest Change:: 13 Feb 2021, 11.30 AM
	 * ********************************************************************************************/
	
	public static void closeAllBrowsers() {
		driver.quit();
	}
	
}
