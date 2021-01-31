//<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >

package library.page.object.model;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import library.utility.Utility;

public class FinalTestcaseValidations extends Utility {

	public FinalTestcaseValidations(RemoteWebDriver driver) {
		this.driver = driver;
	}


	public FinalTestcaseValidations s2051createcampaign_end_validation(String campaign_Name) {
		
			WebElement eleCampaignNameDisplay = findElementBy("xpath", "//div[text()='Campaign']//following-sibling::div/div/span", "Campaign Name");
			
			//Verify the campaign creation
			verifyExactText(eleCampaignNameDisplay, campaign_Name, "Campaign Name");
			
			browser_closeActiveBrowser();
			return this;
		}
	

	public FinalTestcaseValidations S2052AttachDocument_end_validation(String file_Name) {
		
			WebElement eleFileNameDisplay = findElementBy("xpath", "//span[@class='itemTitle slds-text-body--regulardesktop uiOutputText']", "File Name");
		
			//Verify the file Upload
			verifyExactText(eleFileNameDisplay, file_Name, "File Name");
			
			browser_closeActiveBrowser();
			return this;
	}
	
	public FinalTestcaseValidations S2053DeleteAttachment_end_validation() {
	

		List<WebElement> tabRow = findElementsBy("xpath", "(//table[@data-aura-class='uiVirtualDataTable'])[2]/tbody/tr", "Table of Attachments");
		int initTabRowCount = tabRow.size();
		logger.info("Total no of files added : "+initTabRowCount);
		
		if (initTabRowCount < 1)
		{
			logger.info("There are no files uploaded to delete");
		}
		else
		{
			List<WebElement> tabDropdown = findElementsBy("xpath", "(//table[@data-aura-class='uiVirtualDataTable'])[2]/tbody/tr/td[5]/span/div/a", "Action List in table record");
			for(WebElement dd : tabDropdown)
			{
				click(dd,"Action Dropdown of First record");
				try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
				
				//8. Select Delete and Confirm the delete
				//*******************************************************
				//Choose 'Delete' action from action dropdown
				WebElement ele_DL = findElementBy("xpath", "//div[@class='branding-actions actionMenu']/ul/li/a/div[@title='Delete']", "Delete Action");
				//action.moveToElement(ele_DL).click().perform();
				click(ele_DL,"Delete Action");
				logger.info("Delete has been clicked from the action dropdown");
				
			
				//Click 'Delete' confirmation button
				WebElement ele_CDL = findElementBy("xpath", "//button[@type='button'][@title='Delete']", "Confirm Delete");
				click(ele_CDL,"Confirm Delete");
				
				try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='emptyContentInner slds-text-align_center']")));
				}
				catch(Exception e) {}
				
				WebElement ele_NRF = findElementBy("xpath","//div[@class='emptyContentInner slds-text-align_center']", "Search Results"); 
				String str = getText(ele_NRF, "Search Results");
				
				if (str.contentEquals("No results found"))
				{
					logger.info("Deletion Successfull. "+str);
					break;
				}
				else
				{
					//Re-Count the number of attachment rows available for validation.
					List<WebElement> lattabRow = findElementsBy("xpath","(//table[@data-aura-class='uiVirtualDataTable'])[2]/tbody/tr","Table of Attachments");
					int latTabRowCount = lattabRow.size();
					logger.info("Total no of files listed after deletion : "+latTabRowCount);
					
					if (latTabRowCount == (initTabRowCount - 1))
					{
						logger.info("File Deleted Successfully");
					}
					
					break;
				}
			}
			
		}
		
		browser_closeActiveBrowser();
		return this;
	}
	

	public FinalTestcaseValidations S2054EditCampaign_end_validation(String inpexpdRevenue, String inpbgtCost) {
		
//		
//		WebElement eleED = findElementBy("xpath", "//span[@class='test-id__field-label'][text()='End Date']/parent::div/following-sibling::div/span/span", "End Date");
//		verifyExactText(eleED, inpexpDate, "End Date");
//		

		WebElement eleER = findElementBy("xpath", "//span[@class='test-id__field-label'][text()='Expected Revenue in Campaign']/parent::div/following-sibling::div/span/span", "Expected Revenue");
		verifyExactText(eleER, inpexpdRevenue, "Expected Revenue");

		
		WebElement eleBC = findElementBy("xpath", "//span[@class='test-id__field-label'][text()='Budgeted Cost in Campaign']/parent::div/following-sibling::div/span/span", "Budgeted Cost");
		verifyExactText(eleBC, inpbgtCost, "Budgeted Cost");

		browser_closeActiveBrowser();
		return this;
	}
}

	


	

