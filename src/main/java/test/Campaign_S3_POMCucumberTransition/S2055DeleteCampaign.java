
package test.Campaign_S3_POMCucumberTransition;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import lib.dataProviders.ReadExcel;
import lib.pom.lightning.CampaignsPage;
import lib.pom.lightning.LoginPage;
import lib.utility.ProjectSpecificMethods;

public class S2055DeleteCampaign extends ProjectSpecificMethods {


	//@Test(dataProvider = "CreateCampaign")
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
		cls = "S2055DeleteCampaign.class";	
		String c_Name = "Salesforce Automation by Palanimohan";
		
		new LoginPage(driver)
		.login_UserName_Type(uid)										// Enter SF login username
		.login_Password_Type(pwd)										// Enter SF login password
		.login_Submit_Click()											// Click login button
		.verifyTitle("Home | Salesforce")	
		.clickToggle()												//2. Click on the toggle menu button from the left corner
		.viewAll()
		.appLauncher_Sales_Click()										// Click on Sales link
		.campaigns_Click()										//4. Click on the Campaigns tab 
		.search_Campaign(c_Name)
		.campaignActionDropdown()
		.campaignDeleteAction()
		.campaignConfirmDelete()
		.S2055DeleteCampaign_end_validation(c_Name);
		
		}

	
	
	@DataProvider(name = "CreateCampaign")
	public Object[][] getdata() throws IOException
	{
		ReadExcel rexl = new ReadExcel();
		Object[][] data = rexl.readdata("CreateCampaign");
		return data;
		
	}
	
	
	
}

