package lib.page.object.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import lib.utility.ProjectSpecificMethods;

public class PageMenus extends ProjectSpecificMethods {

	public PageMenus(RemoteWebDriver driver) {
		this.driver = driver;
	}


	public CampaignsPage campaigns_Click() {
		WebElement eleCampaignsTab = findElementBy("xpath", "//a[@title='Campaigns']", "Campaigns tab");
		executor.executeScript("arguments[0].click();", eleCampaignsTab);
        return new CampaignsPage(driver);
    }
	
	public OpportunityPage opportunities_Click() {
		WebElement eleCampaignsTab = findElementBy("xpath", "//a[@title='Opportunities']", "Opportunities tab");
		executor.executeScript("arguments[0].click();", eleCampaignsTab);
        return new OpportunityPage(driver);
    }
	
	
	public PageMenus scroll()
	{
		executor.executeScript("window.scrollBy(0,1000)");
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		return this;
	}
    
}

