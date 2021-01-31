
package test.Campaign_S3_TestNGPOMTransition;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import library.data.reader.ReadExcel;
import library.page.object.model.LoginPage;
import library.utility.Utility;


public class S2052AttachDocument extends Utility {

	public S2052AttachDocument(RemoteWebDriver driver) {
		this.driver = driver; 
	}


	@Test(dataProvider = "CreateCampaign")
	public void mainflow(
			String url,
			String uid,
			String pwd,
			String browser,
			String cn, 
			String fn, 
			String ln, 
			String compo       ) throws AWTException  
	{
		cls = "S2052AttachDocument.class";	
		String campaign_Name = fn + ln + "_"+cn;
		String fileName = "AutoFileUploadTest.pdf";
		String filePath = "C:\\Users\\VISHNU S\\"+fileName;
		
		
		new LoginPage(driver)
		.browser_Selection(browser, url)
		.login_UserName_Type(uid)										// Enter SF login username
		.login_Password_Type(pwd)										// Enter SF login password
		.login_Submit_Click()											// Click login button
		.verifyTitle("Home | Salesforce")	
		.clickToggle()												//2. Click on the toggle menu button from the left corner
		.viewAll()
		.appLauncher_Sales_Click()										// Click on Sales link
		.campaigns_Click()											//4. Click on the Campaigns tab 
		.campaigns_PickCampaignfromList_Click(campaign_Name)		//5. Click the Bootcamp link
		.campaigns_Scroll()
		.campaigns_Related_Attachments_Upload_Click(filePath)	//6. Upload file			
		.campaigns_Related_Attachments_CompleteFileUpload()
		.S2052AttachDocument_end_validation(fileName);				//7. Verify the upload
		

	}

	
	@DataProvider(name = "CreateCampaign")
	public Object[][] getdata() throws IOException
	{
		ReadExcel rexl = new ReadExcel();
		Object[][] data = rexl.readdata("CreateCampaign");
		return data;
		
	}

}















