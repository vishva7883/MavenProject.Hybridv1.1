//<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >

package lib.pom.lightning;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.api.java.en.Then;
import lib.utility.ProjectSpecificMethods;

public class FinalTestcaseValidationsLightning extends ProjectSpecificMethods {

	public FinalTestcaseValidationsLightning(RemoteWebDriver driver) {
		this.driver = driver;
	}

	@Then("Campaign_Verify the newly created Campaign (.*)") 
	public FinalTestcaseValidationsLightning s2051createcampaign_end_validation(String campaign_Name) {
		
			WebElement eleCampaignNameDisplay = findElementBy("xpath", "//div[text()='Campaign']//following-sibling::div/div/span", "Campaign Name");
			
			//Verify the campaign creation
			verifyExactText(eleCampaignNameDisplay, campaign_Name, "Campaign Name");
			
			return this;
		}
	
	@Then("Campaign_Verify the Attachment upload (.*)")
	public FinalTestcaseValidationsLightning S2052AttachDocument_end_validation(String file_Name) {
		
			WebElement eleFileNameDisplay = findElementBy("xpath", "//span[@class='itemTitle slds-text-body--regulardesktop uiOutputText']", "File Name");
		
			//Verify the file Upload
			verifyExactText(eleFileNameDisplay, file_Name, "File Name");
			
			return this;
	}
	
	

	@Then("Campaign_Verify Attachment deletion (.*)")
	public FinalTestcaseValidationsLightning S2053DeleteAttachment_end_validation() {
					
				try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='emptyContentInner slds-text-align_center']")));
				}
				catch(Exception e) {}
				
				WebElement ele_NRF = findElementBy("xpath","//div[@class='emptyContentInner slds-text-align_center']", "Search Results"); 
				String str = getText(ele_NRF, "Search Results");
				
				if (str.contentEquals("No results found"))
					System.out.println("Deletion Successfull. "+str);
				else
					System.out.println("Deletion Unsuccessfull. "+str);
		return this;
	}
	
	@Then("Campaign_Verify Campaign changes (.*) and (.*)")
	public FinalTestcaseValidationsLightning S2054EditCampaign_end_validation(String inpexpdRevenue, String inpbgtCost) {
		
//		
//		WebElement eleED = findElementBy("xpath", "//span[@class='test-id__field-label'][text()='End Date']/parent::div/following-sibling::div/span/span", "End Date");
//		verifyExactText(eleED, inpexpDate, "End Date");
//		

		WebElement eleER = findElementBy("xpath", "//span[@class='test-id__field-label'][text()='Expected Revenue in Campaign']/parent::div/following-sibling::div/span/span", "Expected Revenue");
		verifyExactText(eleER, inpexpdRevenue, "Expected Revenue");

		
		WebElement eleBC = findElementBy("xpath", "//span[@class='test-id__field-label'][text()='Budgeted Cost in Campaign']/parent::div/following-sibling::div/span/span", "Budgeted Cost");
		verifyExactText(eleBC, inpbgtCost, "Budgeted Cost");

		return this;
	}
	
	@Then("Campaign_Verify Campaign deletion (.*)")
	public FinalTestcaseValidationsLightning  S2055DeleteCampaign_end_validation(String vcname)
	{
		String str = "";
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='emptyContentInner slds-text-align_center']")));
			str = driver.findElementByXPath("//div[@class='emptyContentInner slds-text-align_center']").getText();

			if (str.contentEquals("No items to display."))
			{
				System.out.println("Deletion Successfull. "+str);
			}
			else
			{
				System.out.println("Campaign "+vcname+" deletion unSuccessfully");
				}
			}
		
			catch(Exception e) {
				e.printStackTrace();
			}
		return this;
	}
	
}

	


	

