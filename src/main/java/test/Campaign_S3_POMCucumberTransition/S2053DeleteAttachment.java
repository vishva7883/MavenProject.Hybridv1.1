
package test.Campaign_S3_POMCucumberTransition;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import lib.dataProviders.ReadExcel;
import lib.pom.lightning.LoginPage;
import lib.utility.ProjectSpecificMethods;



public class S2053DeleteAttachment extends ProjectSpecificMethods {

	//@Test(dataProvider = "CreateCampaign", dependsOnMethods = "test.Campaign_S3_TestNGPOMTransition.S2052AttachDocument")
	public void mainflow(
			String url,
			String uid,
			String pwd,
			String browser,
			String cn, 
			String fn, 
			String ln, 
			String compo       ) throws AWTException, IOException, InterruptedException  
	{
		cls = "S2053DeleteAttachment.class";	
		String campaign_Name = fn + ln + "_"+cn;
		String fileName = "AutoFileUploadTest";
		
		
		new LoginPage(driver)
		.login_UserName_Type(uid)										// Enter SF login username
		.login_Password_Type(pwd)										// Enter SF login password
		.login_Submit_Click()											// Click login button
		.verifyTitle("Home | Salesforce")	
		.clickToggle()												//2. Click on the toggle menu button from the left corner
		.viewAll()
		.appLauncher_Sales_Click()										// Click on Sales link
		.campaigns_Click()											//4. Click on the Campaigns tab 
		.campaigns_PickCampaignfromList_Click(campaign_Name)		//5. Click the Bootcamp link	
		.campaigns_Related_Attachments_ViewAll_Click()				//6. Click on View All link in the Attachments section
		.campaigns_Related_Attachments_Table_Actions()
		.campaigns_Related_Attachments_TableActions_Delete()
		.campaigns_Related_Attachments_Confirm_Delete()
		.S2053DeleteAttachment_end_validation();

	}
	
	@DataProvider(name = "CreateCampaign")
	public Object[][] getdata() throws IOException
	{
		ReadExcel rexl = new ReadExcel();
		Object[][] data = rexl.readdata("CreateCampaign");
		return data;
		
	}

}

