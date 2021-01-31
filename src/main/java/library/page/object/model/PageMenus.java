package library.page.object.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import library.utility.Utility;

public class PageMenus extends Utility {

	public PageMenus(RemoteWebDriver driver) {
		this.driver = driver;
	}


	public CampaignsPage campaigns_Click() {
		WebElement eleCampaignsTab = findElementBy("xpath", "//a[@title='Campaigns']", "Campaigns tab");
		executor.executeScript("arguments[0].click();", eleCampaignsTab);
        return new CampaignsPage(driver);
    }
	
	
	public PageMenus scroll()
	{
		executor.executeScript("window.scrollBy(0,1000)");
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		return this;
	}
    
}

