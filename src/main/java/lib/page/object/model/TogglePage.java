package lib.page.object.model;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import lib.utility.ProjectSpecificMethods;


public class TogglePage extends ProjectSpecificMethods {

	public TogglePage(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public TogglePage verifyTitle(String expectedTitle) {
		verifyExactTitle(expectedTitle);
		return this;
	}
	
	public TogglePage clickToggle() {
		WebElement eleAppLauncher = findElementBy("xpath", "//div[@class='appLauncher slds-context-bar__icon-action']", "Toggle menu");
		click(eleAppLauncher, "Toggle menu");
        return this;
    }
	
	public AppLauncherPage viewAll() {
		WebElement eleViewAll = findElementBy("xpath", "//button[contains(text(),'View All')]/parent::lightning-button", "View All link");
		click(eleViewAll, "View All link");
        return new AppLauncherPage(driver);
	}
	
}
