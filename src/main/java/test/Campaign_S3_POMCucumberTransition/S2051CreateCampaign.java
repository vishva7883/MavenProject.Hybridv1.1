//<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >

package test.Campaign_S3_POMCucumberTransition;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import lib.dataProviders.ReadExcel;
import lib.pom.lightning.*;
import lib.utility.ProjectSpecificMethods;

public class S2051CreateCampaign extends ProjectSpecificMethods {


	@Test(dataProvider = "CreateCampaign")
	public void mainflow(
			String url,
			String uid,
			String pwd,
			String browser,
			String cn, 
			String fn, 
			String ln, 
			String compo       ) throws AWTException, IOException  
	{
		cls = "S2051CreateCampaign.class";	
		String campaign_Name = fn + ln + "_"+cn;	
		
		new LoginPage(driver)
		.login_UserName_Type(uid)										// Enter SF login username
		.login_Password_Type(pwd)										// Enter SF login password
		.login_Submit_Click()											// Click login button
		.verifyTitle("Home | Salesforce")	
		.clickToggle()												//2. Click on the toggle menu button from the left corner
		.viewAll()
		.appLauncher_Sales_Click()										// Click on Sales link
		.campaigns_Click()											//4. Click on the Campaigns tab 
		.campaigns_New_Click()										//5. Click on the New button
		.campaigns_NewCampaigns_CampaignName_Type(campaign_Name)	//6. Enter Campaign name as 'Bootcamp', Get the text and Store it 
		.campaigns_NewCampaigns_StartDate_Click(1)					//7. Choose Start date as Tomorrow  (Note : 0 for today, 1 for tomorrow and so on) 
		.campaigns_NewCampaigns_EndDate_Click(2)					//8. End date as Tomorrow + 1  (Note : 0 for today, 1 for tomorrow and so on)
		.campaigns_NewCampaigns_Save_Click()
		.s2051createcampaign_end_validation(campaign_Name);			//9. click Save and Verify the newly created Campaign
		
	}

	@DataProvider(name = "CreateCampaign")
	public Object[][] getdata() throws IOException
	{
		ReadExcel rexl = new ReadExcel();
		Object[][] data = rexl.readdata("CreateCampaign");
		return data;
		
	}

}

