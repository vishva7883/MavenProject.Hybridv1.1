package stepdefinition;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Campaigns extends AGlobalDecleration{
	
	public static String campaign_Name = "";
	public static int initAttachmentCount, initCampaignCount = 0;
	public static int lattAttachmentCount, lattCampaignCount = 0;
	
	@And("Click toggle menu button from the left corner")
	public void click_toggle_menu() {
		WebElement toggle = driver.findElementByXPath("//div[@class='appLauncher slds-context-bar__icon-action']");
		if (toggle.isDisplayed())
		{
			if (toggle.isEnabled())
			{
				System.out.println("3");
				driver.findElementByXPath("//div[@class='appLauncher slds-context-bar__icon-action']").click();;
				System.out.println("Toggle clicked successfully");
				try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}	
			}
		}
		else
		{
			WebElement lightningExp = driver.findElementByXPath("//div[@class='linkElements']/a[@class='switch-to-lightning']");
			if (lightningExp.isDisplayed())
			{
				if (lightningExp.isEnabled())
				{
					driver.findElementByXPath("//div[@class='linkElements']/a[@class='switch-to-lightning']").click();;
					System.out.println("Classic SF launched. Switched to Lightning");
					try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}	
					driver.findElementByXPath("//div[@class='appLauncher slds-context-bar__icon-action']").click();;
					System.out.println("Toggle clicked successfully");
					try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}	
				}
			}
		}
	}
	
	@And("Click View All and trigger App Launcher")
	public void click_View_All() {
		driver.findElementByXPath("//button[contains(text(),'View All')]/parent::lightning-button").click();;
		System.out.println("ViewAll clicked successfully");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	@And("Click Sales link")
	public void click_AppLauncher_Sales() {
		driver.findElementByXPath("//p[text()='Sales']").click();
		System.out.println("Sales clicked successfully");
		try {Thread.sleep(8000);} catch (InterruptedException e) {e.printStackTrace();}
		
	}
	
	@And("Click Campaigns tab")
	public void click_pageMenu_Campaigns() {
		WebElement element = driver.findElementByXPath("//a[@title='Campaigns']");
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		System.out.println("Campaign clicked successfully");
		
	}
	
	//*********************************
	// CAMPAIGN - LIST
	//*********************************
	@And("Click CampaignName link (.*)")
	public void pickCampaign(String cname) {			
		List<WebElement> campaignName = driver.findElementsByXPath("//table[@data-aura-class='uiVirtualDataTable']//following-sibling::tbody/tr/th/span/a"); 
		for (WebElement cn : campaignName) {
			String acc = cn.getText();		
			if (acc.contains("Bootcamp_Vishnu"))
			{
				cn.click();
				break;
			}
		}
	}
	
	@And("Search Campaign as (.*)")
	public void search_Campaign(String cname) {
			cname = "Bootcamp_Vishnu";
			driver.findElementByXPath("//input[@name='Campaign-search-input']").sendKeys(cname);;
			System.out.println("Campaign Name : "+cname+ "entered for search");
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			driver.findElementByXPath("//button[@name='refreshButton']").click();
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	
	
	
	@And("Click Campaign Action dropdown")
	public void campaignActionDropdown() {
		
		try {
				List<WebElement> campaigns = driver.findElementsByXPath("//table[@data-aura-class='uiVirtualDataTable']//tbody//th");
				initCampaignCount = campaigns.size();
				System.out.println("Total no of campaigns listed for the search: "+initCampaignCount);
		}catch(Exception e) {}
			
			
		List<WebElement> actionDropdown = driver.findElementsByXPath("//table[@data-aura-class='uiVirtualDataTable']//tbody//td[10]/span/div");
		for(WebElement ad : actionDropdown)
		{
			ad.click();
			System.out.println("Action Dropdown of First matching record has been clicked");
			try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
			break;
		}
	}
		
	
	//*********************************
	// CAMPAIGN - NEW
	//*********************************
	@And("Click New button")
	public void click_Campaigns_New() {
			driver.findElementByXPath("//a[@title='New']").click();
			System.out.println("New clicked successfully");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			
	}
			
	@And("Enter Campaign name as (.*) Get the text and Store it") 
	public void enter_CampaignName(String cname) {

			driver.findElementByXPath("//span[text()='Campaign Name']//following-sibling::span//parent::label/following::input").click();
			driver.findElementByXPath("//span[text()='Campaign Name']//following-sibling::span//parent::label/following::input").sendKeys(campaign_Name);
			System.out.println("Caimpaign Name "+campaign_Name+" fed successfully");
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			
	}
			
	
	@And("Choose Start date as Tomorrow (.*)")
	public void enter_CampaignStartDate(int dayscount) {
		if (dayscount == 0) dayscount = 1;
		driver.findElementByXPath("//span[text()='Start Date']//parent::label//following-sibling::div/a").click();
		System.out.println("Start Date Calender clicked successfully");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 
		//get current date time with Date() and add a day to it
		Date rawstartDate = new Date(new Date().getTime() + (dayscount*24*60*60*1000));
			 
		// Now format the date
		String startDate = dateFormat.format(rawstartDate);
			 
		//Enter the start date
		String dateXPath = "//td[@data-datevalue='"+startDate+"']";
		driver.findElementByXPath(dateXPath).click();
		System.out.println("Start Date :"+startDate+" selected successfully");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}	
		
	}
			
		
	@And("Choose End date as Tomorrow plus one (.*)")
	public void enter_CampaignEndDate(int dayscount) {
			driver.findElementByXPath("//span[text()='End Date']//parent::label//following-sibling::div/a").click();
			System.out.println("End Date Calender clicked successfully");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			
			 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 
			 //get current date time with Date() and add two days to it
			 Date rawendDate = new Date(new Date().getTime() + (dayscount*24*60*60*1000));
			 
			 // Now format the date
			 String endDate = dateFormat.format(rawendDate);
			 		 
			//Enter the end date
			String dateXPath = "//td[@data-datevalue='"+endDate+"']";
			driver.findElementByXPath(dateXPath).click();
			System.out.println("End Date : "+endDate+" selected successfully");
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}	
			
	}
	
	
	@When("click Save")
	public void click_Submit_Create_Lead() 
	{
		driver.findElementByXPath("//button[@title='Save']").click();
		System.out.println("Save clicked successfully");
		try {Thread.sleep(8000);} catch (InterruptedException e) {e.printStackTrace();}	
	}
	 

	
	
	

	
	
	//*********************************
	// CAMPAIGN - RELATED
	//*********************************
	
	@And("Click Upload file (.*) from path (.*)")
	public void uploadFiles(String fileName, String filePath) {
		
		String compfilePath = "C:\\Users\\VISHNU S\\"+fileName;
		
		executor.executeScript("window.scrollBy(0,1000)");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.findElementByXPath("//a[@title='Upload Files']").click();
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}	
		
		StringSelection pdfFilePath = new StringSelection(compfilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pdfFilePath, null);
		 
		 try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		     // press Contol+V for pasting
		 robot.keyPress(KeyEvent.VK_CONTROL);
		 robot.keyPress(KeyEvent.VK_V);
		 
		    // release Contol+V for pasting
		 robot.keyRelease(KeyEvent.VK_CONTROL);
		 robot.keyRelease(KeyEvent.VK_V);
		 
		    // for pressing and releasing Enter
		 robot.keyPress(KeyEvent.VK_ENTER);
		 robot.keyRelease(KeyEvent.VK_ENTER);
		 
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}	
	}

	@When("Complete File Upload")
	public void confirmUpload() {	
		driver.findElementByXPath("(//button[@data-aura-class = 'uiButton--default uiButton--brand uiButton'])[2]").click();
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}	
		
	}

	@And("Click Attachments View All")
	public void attachmentsViewAll() {
		driver.findElementByXPath("(//span[@title='Opportunities']/preceding::div[@data-aura-class='forceRelatedListCardHeader'])[2]//following::span[@class='view-all-label']").click();
	}
	
	
	//table[@data-aura-class='uiVirtualDataTable']//tbody//td[10]/span/div
	
	@And("Click Attachments Action dropdown")
	public void attachmentsActionDropdown() {
	
		try {
			List<WebElement> attachments = driver.findElementsByXPath("(//table[@data-aura-class='uiVirtualDataTable'])[2]/tbody/tr");
			initAttachmentCount = attachments.size();
			System.out.println("Total no of files added before deletion: "+initAttachmentCount);
		}catch(Exception e) {}
		
		
		List<WebElement> actionDropdown = driver.findElementsByXPath("(//table[@data-aura-class='uiVirtualDataTable'])[2]/tbody/tr/td[5]/span/div/a");
		for(WebElement ad : actionDropdown)
		{
			ad.click();
			System.out.println("Action Dropdown of First record has been clicked");
			try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
			break;
		}
	}
	
	@And("Click Delete Action")
	public void deleteAction() {
		
		List<WebElement> deleteAction = driver.findElementsByXPath("//a[@role='menuitem'][@title='Delete']");
		for(WebElement da : deleteAction)
		{
			da.click();
			System.out.println("Delete has been clicked from Action Dropdown of First record");
			try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
			break;
		};
	}	
	
	@When("Click Confirm Delete")
	public void confirmDelete() {
		driver.findElementByXPath("//button[@type='button'][@title='Delete']").click();
		System.out.println("Confirm delete has been click performed");
		try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
	}
	

	
	//*********************
	//CAMPAIGN - DETAILS
	//*********************
	@And("Click Details tab")
	public void detailsTab() {
		driver.findElementByXPath("//a[@class = 'tabHeader'][@title = 'Details']").click();
		System.out.println("Campaings > Details tab clicked successfully");
	
	}	
	
	@And("Scroll a bit")
	public void scroll() {
		executor.executeScript("window.scrollBy(0,1000)");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	
	@And("Edit EndDate add (.*) days")
	public void EditEndDate(int num) {
		
		if (num == 0) num = 1;
		
		driver.findElementByXPath("//button[@title='Edit End Date']").click();
		System.out.println("Campaings > End Date edit has been initiated");
		driver.findElementByXPath("(//a[@class='datePicker-openIcon display'])[2]").click();
		System.out.println("End Date calender opened successfully");
		DateFormat caldateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date rawexpDate = new Date(new Date().getTime() + (num*24*60*60*1000));
		String calexpDate = caldateFormat.format(rawexpDate);
		
		String dateXPath = "//td[@data-datevalue='"+calexpDate+"']";
		System.out.println("End Date :"+calexpDate+" selected successfully");
		driver.findElementByXPath(dateXPath).click();
		try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		
	}
	
	@And("Edit Revenue as (.*)")
	public void editRevenue(String expRev) {

		WebElement ele_EditEndDate = driver.findElementByXPath("//span[text()='Expected Revenue in Campaign']/parent::label/following-sibling::input");
		executor.executeScript("arguments[0].scrollIntoView(true);", ele_EditEndDate);
		ele_EditEndDate.sendKeys(Keys.CONTROL, Keys.chord("a"));
		ele_EditEndDate.sendKeys(Keys.BACK_SPACE);
		ele_EditEndDate.sendKeys(expRev);
		try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		System.out.println("Revenue :"+expRev+" updated successfully");
	}
	
	@And("Edit Budget as (.*)")
	public void editBudget(String bgtCost){
		WebElement ui_EditBudgetCost = driver.findElementByXPath("//span[text()='Budgeted Cost in Campaign']/parent::label/following-sibling::input");
		executor.executeScript("arguments[0].scrollIntoView(true);", ui_EditBudgetCost);
		ui_EditBudgetCost.sendKeys(Keys.CONTROL, Keys.chord("a"));
		ui_EditBudgetCost.sendKeys(Keys.BACK_SPACE);
		ui_EditBudgetCost.sendKeys(bgtCost);
		try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		System.out.println("Budget :"+bgtCost+" updated successfully");
		
	} 
	
	@And("Click EditSave")		
	public void EditSave(){
		driver.findElementByXPath("//button[@title='Save']").click();
		try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		System.out.println("Edit Save has been confirmed successfully");
	}

	
	
	
	
	@Then("Campaign_Verify the newly created Campaign (.*)")
	public void verify_Create_Lead(String cName)
	{
		
		String createdCampName = driver.findElementByXPath("//div[text()='Campaign']//following-sibling::div/div/span").getText();
		System.out.println("Camp Name created in Application was : "+createdCampName);
		
		//Verify the campaign creation
		if (createdCampName.equals(campaign_Name)) {
			System.out.println("The campaign Name [" + createdCampName + "] matching the given text <" + campaign_Name + ">");
			
		} else {
			System.out.println("The campaign Name [" + createdCampName + "] doesnt match with the given text <" + campaign_Name + ">");
		}
		
		driver.close();
		driver.quit();

	}
	
		
	
	@Then("Campaign_Verify the Attachment upload (.*)")
	public void verifyUpload(String vfile_Name)
	{
			WebElement ele_ufn = driver.findElementByXPath("//span[@class='itemTitle slds-text-body--regulardesktop uiOutputText']");
		
			String FileNameDisplay = ele_ufn.getText();

			if (FileNameDisplay.equals(vfile_Name)) {
				System.out.println("The file Name [" + FileNameDisplay + "] matching the given text <" + vfile_Name + ">");
				
			} else {
				System.out.println("The file Name [" + FileNameDisplay + "] doesnt match with the given text <" + vfile_Name + ">");
			}
			
			driver.close();
			driver.quit();
			
	}
	
	
	
	@Then("Campaign_Verify Attachment deletion (.*)")
	public void verifyDeleteAttachment(String vfile_Name)
	{
		String str = "";
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='emptyContentInner slds-text-align_center'])[2]")));
			str = driver.findElementByXPath("(//div[@class='emptyContentInner slds-text-align_center'])[2]").getText();

			if (str.contentEquals("No items to display."))
			{
				System.out.println("Deletion Successfull. "+str);
			}
			else
			{
				List<WebElement> attachments = driver.findElementsByXPath("(//table[@data-aura-class='uiVirtualDataTable'])[2]/tbody/tr/th");
				lattAttachmentCount = attachments.size();
				System.out.println("Total no of files listed after deletion : "+lattAttachmentCount);
			
				if (lattAttachmentCount == (initAttachmentCount - 1))
				{
					System.out.println("File Deleted Successfully");
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			driver.close();
			driver.quit();			
		}
	}
	
	
	@Then("Campaign_Verify Campaign changes (.*) and (.*)")
	public void verifyChanges(String inpexpdRevenue, String inpbgtCost) {
		try {Thread.sleep(2000);} catch (InterruptedException e1) {e1.printStackTrace();}
		try {
			String eleER = driver.findElementByXPath("//span[@class='test-id__field-label'][text()='Expected Revenue in Campaign']/parent::div/following-sibling::div/span/span").getText();
			String eleBC = driver.findElementByXPath("//span[@class='test-id__field-label'][text()='Budgeted Cost in Campaign']/parent::div/following-sibling::div/span/span").getText();

			if (eleER.equals(inpexpdRevenue)) {
				System.out.println("Displayed Revenue [" + eleER + "] matching the given <" + inpexpdRevenue + ">");
				
			} else {
				System.out.println("Displayed Revenue [" + eleER + "] doesnt match with the given <" + inpexpdRevenue + ">");
			}
			
			if (eleBC.equals(inpbgtCost)) {
				System.out.println("Displayed Budget [" + eleBC + "] matching the given <" + inpbgtCost + ">");
				
			} else {
				System.out.println("Displayed Budget [" + eleBC + "] doesnt match with the given <" + inpbgtCost + ">");
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
		driver.close();
		driver.quit();	
		}
	}
	
	
	
	@Then("Campaign_Verify Campaign deletion (.*)")
	public void verifyDeleteCampaign(String vcname)
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
				List<WebElement> campaigns = driver.findElementsByXPath("(//table[@data-aura-class='uiVirtualDataTable'])[2]/tbody/tr/th");
				lattCampaignCount = campaigns.size();
				System.out.println("Total no of files listed after deletion : "+lattAttachmentCount);
			
				if (lattCampaignCount == (initCampaignCount - 1))
				{
					System.out.println("Campaign "+vcname+" deleted Successfully");
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			driver.close();
			driver.quit();			
		}
	}
	
}
