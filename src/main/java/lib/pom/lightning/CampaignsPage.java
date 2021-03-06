//<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >

package lib.pom.lightning;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import lib.utility.ProjectSpecificMethods;

public class CampaignsPage extends ProjectSpecificMethods {

//Campaign List view

			public CampaignsPage(RemoteWebDriver driver) {
				this.driver = driver;
	}

			@And("Click CampaignName link (.*)")	
			public CampaignsPage campaigns_PickCampaignfromList_Click(String campaign_Name) {			
				String ui_FieldName = "Table > Campaign Name";
				List<WebElement> campaignName = findElementsBy("xpath","//table[@data-aura-class='uiVirtualDataTable']//following-sibling::tbody/tr/th/span/a",ui_FieldName);
				
				for (WebElement cn : campaignName) {
					String acc = getText(cn, ui_FieldName);		
					if (acc.contentEquals(campaign_Name))
					{
						click(cn, ui_FieldName);
						break;
					}
				}
				return this;
			}
		
			@And("Click New button")
			public CampaignsPage campaigns_New_Click() {
				String ui_FieldName = "New";	
				
				WebElement eleNewCampaign = findElementBy("xpath", "//a[@title='New']", ui_FieldName);
				click(eleNewCampaign, ui_FieldName);
				return this;
		    }

			public CampaignsPage campaigns_Scroll() {
				executor.executeScript("window.scrollBy(0,1000)");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				return this;
			}
		
			
			@And("Search Campaign as (.*)")
			public CampaignsPage search_Campaign(String cname) {
				String ui_FieldName = "Enter Search criteria ";
				cname = "Bootcamp_Vishnu";
				WebElement ele_CampaignSearchCriteria = findElementBy("xpath","//input[@name='Campaign-search-input']",ui_FieldName);
				type(ele_CampaignSearchCriteria,cname,ui_FieldName);

				ui_FieldName = "Campaign Search";
				WebElement ele_CampaignSearch = findElementBy("xpath","//button[@name='refreshButton']",ui_FieldName);
				click(ele_CampaignSearch,ui_FieldName);

				return this;
			}
			
			
			@And("Click Campaign Action dropdown")
			public CampaignsPage campaignActionDropdown() {
				String ui_FieldName = "Campaign > Action menu";	
				try {
						List<WebElement> campaigns = findElementsBy("xpath","//table[@data-aura-class='uiVirtualDataTable']//tbody//th",ui_FieldName);
				}catch(Exception e) {}
				
				List<WebElement> actionDropdown = findElementsBy("XPath","//table[@data-aura-class='uiVirtualDataTable']//tbody//td[10]/span/div",ui_FieldName);
				for(WebElement ad : actionDropdown)
				{
					click(ad,"Action dropdown of first match");
					break;
				}
				
				return this;
			}
			
			@And("Click Campaign Delete Action")
			public CampaignsPage campaignDeleteAction() {
				String ui_FieldName = "Delete Action of First record";	
				List<WebElement> deleteAction = driver.findElementsByXPath("//a[@role='menuitem'][@title='Delete']");
				for(WebElement da : deleteAction)
				{
					click(da,ui_FieldName);
					break;
				}
				return this;
			}	
			
			@When("Click Campaign Confirm Delete")  
			public FinalTestcaseValidationsLightning campaignConfirmDelete() {
				
				String ui_FieldName = "Confirm Delete";	
				WebElement ele_confidelete = findElementBy("xpath","//a[@role='menuitem'][@title='Delete']",ui_FieldName);
				click(ele_confidelete,ui_FieldName);
				return new FinalTestcaseValidationsLightning(driver);
			}
			

		
//Campaign New
		

		@And("Enter Campaign name as (.*) Get the text and Store it")
		public CampaignsPage campaigns_NewCampaigns_CampaignName_Type(String campname) {
			String ui_FieldName = "Campaign Name";	
			
			WebElement eleCampaignNameT = findElementBy("xpath","//span[text()='Campaign Name']//following-sibling::span//parent::label/following::input",ui_FieldName);
			type(eleCampaignNameT, campname, ui_FieldName);
			return this;
		}
		
		@And("Choose Start date as Tomorrow (.*)")
		public CampaignsPage campaigns_NewCampaigns_StartDate_Click(int num) {
			String ui_FieldName = "Start Date";	
			
			WebElement eleCampStartDate = findElementBy("xpath","//span[text()='Start Date']//parent::label//following-sibling::div/a",ui_FieldName);
			click(eleCampStartDate, ui_FieldName);
		
			ui_FieldName = "Start Date > Calender";	
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 
			//get current date time with Date() and add a day to it
			Date rawstartDate = new Date(new Date().getTime() + (num*24*60*60*1000));
			 
			// Now format the date
			String startDate = dateFormat.format(rawstartDate);
			 
			//Enter the start date
			String dateXPath = "//td[@data-datevalue='"+startDate+"']";
		
			WebElement eleCampStartDateCalPick = findElementBy("xpath",dateXPath,ui_FieldName);
			click(eleCampStartDateCalPick, ui_FieldName);
			return this;
		}
		
		
		@And("Choose End date as Tomorrow plus one (.*)")
		public CampaignsPage campaigns_NewCampaigns_EndDate_Click(int num) {
			String ui_FieldName = "End Date";	
			
			WebElement eleCampEndDate = findElementBy("xpath","//span[text()='End Date']//parent::label//following-sibling::div/a",ui_FieldName);
			click(eleCampEndDate, ui_FieldName);
		
			ui_FieldName = "End Date > Calender";	
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 
			 //get current date time with Date() and add two days to it
			 Date rawendDate = new Date(new Date().getTime() + (num*24*60*60*1000));
			 
			 // Now format the date
			 String endDate = dateFormat.format(rawendDate);
			 
			//Enter the start date
			String dateXPath = "//td[@data-datevalue='"+endDate+"']";
		
			WebElement eleCampEndDateCalPick = findElementBy("xpath",dateXPath,ui_FieldName);
			click(eleCampEndDateCalPick, ui_FieldName);
			return this;
		}

		
		@When("click Save") 
		public FinalTestcaseValidationsLightning campaigns_NewCampaigns_Save_Click() {
			String ui_FieldName = "Save";	
			
			WebElement eleSave = findElementBy("xpath","//button[@title='Save']",ui_FieldName);
			click(eleSave, ui_FieldName);
			return new FinalTestcaseValidationsLightning(driver);
		}
		
		
//Campaign Related
		
		
		@And("Click Attachments View All")
		public CampaignsPage campaigns_Related_Attachments_ViewAll_Click() {
			String ui_FieldName = "View All";	
			
			WebElement ele = findElementBy("xpath","(//span[@title='Opportunities']/preceding::div[@data-aura-class='forceRelatedListCardHeader'])[2]//following::span[@class='view-all-label']",ui_FieldName);
			click(ele, ui_FieldName);	
			return this;
		}
		
		@And ("Click Upload file (.*) from path (.*)")
		public CampaignsPage campaigns_Related_Attachments_Upload_Click(String fileName, String filePath) {
			String ui_FieldName = "Upload Files";	
			
			WebElement ele = findElementBy("xpath","//a[@title='Upload Files']",ui_FieldName);
			click(ele, ui_FieldName);
			
			 StringSelection pdfFilePath = new StringSelection(filePath);
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
			    
			return this;
		}
	
		@When("Complete File Upload")
		public FinalTestcaseValidationsLightning campaigns_Related_Attachments_CompleteFileUpload() {
			String ui_FieldName = "Done";		
	
			WebElement ele = findElementBy("xpath","(//button[@data-aura-class = 'uiButton--default uiButton--brand uiButton'])[2]",ui_FieldName);
			click(ele, ui_FieldName);
			return new FinalTestcaseValidationsLightning(driver);
		}


		@And("Click Attachments Action dropdown")
		public CampaignsPage campaigns_Related_Attachments_Table_Actions() throws InterruptedException {
			
			String ui_FieldName = "Table of Attachments";
			List<WebElement> tabRow = findElementsBy("xpath", "(//table[@data-aura-class='uiVirtualDataTable'])[2]/tbody/tr", ui_FieldName);
			int initTabRowCount = tabRow.size();
			System.out.println("Total no of files listed : "+initTabRowCount);
		
			if (initTabRowCount < 1)
			{
				System.out.println("There are no files uploaded to delete");
			}	
			else
			{
				List<WebElement> tabDropdown = findElementsBy("xpath", "(//table[@data-aura-class='uiVirtualDataTable'])[2]/tbody/tr/td[5]/span/div/a", "Action List in table record");
				for(WebElement dd : tabDropdown)
				{
					click(dd,"Action Dropdown of First record");
					sleep(1);
					break;
				}
			}
			return this;
		}
		
		@And("Click Delete Action")
		public CampaignsPage campaigns_Related_Attachments_TableActions_Delete() {
			String ui_FieldName = "Delete action of First record";
			
			WebElement ele_DL = findElementBy("xpath","//div[@class='branding-actions actionMenu']/ul/li/a/div[@title='Delete']",ui_FieldName);
			click(ele_DL,ui_FieldName);
			return this;
			}

	
		@When("Click Confirm Delete")
		public FinalTestcaseValidationsLightning campaigns_Related_Attachments_Confirm_Delete() {
	
			String ui_FieldName = "Confirm Delete Action";
			WebElement ele_CDL = findElementBy("xpath", "//button[@type='button'][@title='Delete']", "Confirm Delete");
			click(ele_CDL,ui_FieldName);
			return new FinalTestcaseValidationsLightning(driver);
		}
		
		public CampaignsPage campaigns_Related_Opportunities_ViewAll_Click() {
			
			
			return this;
		}
	
		public CampaignsPage campaigns_Related_CampaignMembers_ViewAll_Click() {
			return this;
		}
	
//Campaign Details
		
		@And("Click Details tab")
		public CampaignsPage campaigns_Details_Click() {
			String ui_FieldName = "Details";
			
			WebElement ele_CDC = findElementBy("xpath","//a[@class = 'tabHeader'][@title = 'Details']",ui_FieldName);
			click(ele_CDC,ui_FieldName);	
			
			return this;
		}
		
		@And("Edit EndDate add (.*) days")
		public CampaignsPage campaigns_CampaignsDetails_EditEndDate_Click(int num) {
			String ui_FieldName = "Edit End Date";	
			
			WebElement ele_EditEndDate = findElementBy("xpath","//button[@title='Edit End Date']",ui_FieldName);
			click(ele_EditEndDate, ui_FieldName);
			
			ui_FieldName = "End Date";	
			
			WebElement ele_EndDatePicker = findElementBy("xpath","(//a[@class='datePicker-openIcon display'])[2]",ui_FieldName);
			click(ele_EndDatePicker, ui_FieldName);
			
			ui_FieldName = "End Date > Calender ";	
			
			DateFormat caldateFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date rawexpDate = new Date(new Date().getTime() + (num*24*60*60*1000));
			String calexpDate = caldateFormat.format(rawexpDate);
			
			String dateXPath = "//td[@data-datevalue='"+calexpDate+"']";
			
			WebElement ele_EdtEndDatePicker = findElementBy("xpath",dateXPath, ui_FieldName);
			click(ele_EdtEndDatePicker, ui_FieldName);
			
			return this;
		}
		
		@And("Edit Revenue as (.*)")
		public CampaignsPage campaigns_CampaignsDetails_EditRevenue_Type(String expRev) {
			String ui_FieldName = "Edit Revenue";	
			
			WebElement ele_EditEndDate = findElementBy("xpath","//span[text()='Expected Revenue in Campaign']/parent::label/following-sibling::input",ui_FieldName);
		
			ele_EditEndDate.sendKeys(Keys.CONTROL, Keys.chord("a"));
			ele_EditEndDate.sendKeys(Keys.BACK_SPACE);

			type(ele_EditEndDate, expRev, ui_FieldName);
			
			return this;
			
		}
		
		@And("Edit Budget as (.*)")
		public CampaignsPage campaigns_CampaignsDetails_EditBudget_Type(String bgtCost){
			String ui_FieldName = "Edit Budget Cost";	
			
			WebElement ui_EditBudgetCost = findElementBy("xpath","//span[text()='Budgeted Cost in Campaign']/parent::label/following-sibling::input",ui_FieldName);
			
			ui_EditBudgetCost.sendKeys(Keys.CONTROL, Keys.chord("a"));
			ui_EditBudgetCost.sendKeys(Keys.BACK_SPACE);
			
			type(ui_EditBudgetCost, bgtCost, ui_FieldName);
			
			return this;
			
		} 
		
		@When("Click EditSave")		
		public FinalTestcaseValidationsLightning campaigns_CampaignsDetails_EditSave_Click(){
			String ui_FieldName = "Save";	
			
			WebElement ui_EditBudgetCost = findElementBy("xpath","//button[@title='Save']",ui_FieldName);
			click(ui_EditBudgetCost,ui_FieldName);
			
			return new FinalTestcaseValidationsLightning(driver);
		}
}
			

	
	

	

	

