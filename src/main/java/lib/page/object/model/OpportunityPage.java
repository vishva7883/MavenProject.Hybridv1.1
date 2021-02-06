//<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >

package lib.page.object.model;

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

import lib.dataProviders.ReadExcel;
import lib.utility.ProjectSpecificMethods;

public class OpportunityPage extends ProjectSpecificMethods {

//Campaign List view

			public OpportunityPage(RemoteWebDriver driver) {
				this.driver = driver;
			}

			public OpportunityPage opportunity_DeletefromList_Click(String oppName) {			
				String ui_FieldName = "Table > Opportunity Name";
				List<WebElement> opportunityName = findElementsBy("xpath","//table[@data-aura-class='uiVirtualDataTable']//following-sibling::tbody/tr/th/span/a",ui_FieldName);
				List<WebElement> opportunityAction = findElementsBy("xpath","//table[@data-aura-class='uiVirtualDataTable']//following-sibling::tbody/tr/td[8]/span/div",ui_FieldName);
				
				int x=-1;
				for (WebElement cn : opportunityName) {
					x++;
					String acc = getText(cn, ui_FieldName);		
					if (acc.contentEquals(oppName))
					{
						WebElement oA = opportunityAction.get(x);
						click(oA,"Opportunity Action Dropdown");
					
						WebElement opportunityDelete = findElementBy("xpath","//a[@title='Delete']","Choose Delete Action");
						click(opportunityDelete, "Delete Action");
						
						WebElement opportunityConfirmDelete = findElementBy("xpath","//button[@title='Delete']","Confirm Delete Action");
						click(opportunityConfirmDelete,"Delete Confirmation");
						
						WebElement opportunitySuccessfulDelete = findElementBy("xpath","//div[@data-key='success']","Delete Success message");
						if (opportunitySuccessfulDelete.isDisplayed())
						{
							if (opportunitySuccessfulDelete.isEnabled()){
									WebElement opportunityDeleteMsg = findElementBy("xpath","//div[@data-key='success']/div//span","Success message");
									String deleteConfirmationMessage = getText(opportunityDeleteMsg,"Success flash message");
									System.out.println("Deletion Message : "+deleteConfirmationMessage);
								}
						}
						break;
					}
				}
				return this;
			}
		
}
			

	
	

	

	

