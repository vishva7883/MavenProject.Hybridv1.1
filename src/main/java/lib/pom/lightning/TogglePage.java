package lib.pom.lightning;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import lib.pom.classic.ClassicPageMenus;
import lib.utility.ProjectSpecificMethods;


public class TogglePage extends ProjectSpecificMethods {

	public TogglePage(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public TogglePage verifyTitle(String expectedTitle) {
		verifyExactTitle(expectedTitle);
		return this;
	}
	
	@Given("Click toggle menu button from the left corner")
	public TogglePage clickToggle() {
		String ui_FieldName = "Toggle Menu";	
		
		WebElement eleAppLauncher = findElementBy("xpath", "//div[@class='appLauncher slds-context-bar__icon-action']", ui_FieldName);
		click(eleAppLauncher, ui_FieldName);
        return this;
    }
	
	public TogglePage clickViewProfile() {
		String ui_FieldName = "Switch to Lightning";	
			
		WebElement eleLightning = findElementBy("xpath", "(//a[text()='Switch to Lightning Experience'])[1]", ui_FieldName);
		if (eleLightning.isDisplayed())
		{
			
		}
		else
		{
			ui_FieldName = "View Profile";	
			WebElement eleViewProfile = findElementBy("xpath", "//span[@data-aura-class='oneUserProfileCardTrigger']/button/div", ui_FieldName);
			click(eleViewProfile, ui_FieldName);
	    		
		}
	    return this;
    }
	
	public ClassicPageMenus clickSwitchtoClassic() {
		String ui_FieldName = "Switch to Lightning";	
		
		WebElement eleLightning = findElementBy("xpath", "(//a[text()='Switch to Lightning Experience'])[1]", ui_FieldName);
		if (eleLightning.isDisplayed())
		{
			
		}
		else
		{
			ui_FieldName = "Switch to Classic";	
			WebElement eleViewProfile = findElementBy("xpath", "//a[text()='Switch to Salesforce Classic']", ui_FieldName);
			click(eleViewProfile, ui_FieldName);
		}
		return new ClassicPageMenus(driver);
    }
	
	
	@And ("Click View All and trigger App Launcher")
	public AppLauncherPage viewAll() {
		String ui_FieldName = "View All link";	
		
		WebElement eleViewAll = findElementBy("xpath", "//button[contains(text(),'View All')]/parent::lightning-button", ui_FieldName);
		click(eleViewAll, ui_FieldName);
        return new AppLauncherPage(driver);
	}
	
}
