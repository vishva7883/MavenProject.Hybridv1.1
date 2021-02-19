package lib.pom.lightning;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.en.And;
import lib.utility.ProjectSpecificMethods;
import test.Campaign_S3_POMCucumberTransition.S2051CreateCampaign;


public class AppLauncherPage extends ProjectSpecificMethods {

	public AppLauncherPage(RemoteWebDriver driver) {
		this.driver = driver;
	}

	@And("Click Sales link")
	public PageMenus appLauncher_Sales_Click() {
		WebElement eleSales = findElementBy("xpath", "//p[text()='Sales']", "Sales App Launch");
		click(eleSales, "Sales App Launch");
        return new PageMenus(driver);
    }
	
	public PageMenus appLauncher_ServiceConsole_Click() {
		WebElement eleSales = findElementBy("xpath", "//p[text()='Service Console']", "Service Console App Launch");
		click(eleSales, "Service Console Launch");
        return new PageMenus(driver);
    }

}
