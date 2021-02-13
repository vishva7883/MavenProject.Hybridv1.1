package lib.reports;

import java.io.IOException;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public abstract class Reports {

	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	public static ExtentTest testSuite, test;

	//@BeforeSuite
	// Level 1. Report File
	public void startReport() {
		spark = new ExtentSparkReporter("./sparkReports/result.html"); // For Creating the file alone and its Read Only mode.
		extent = new ExtentReports(); // To make file readable and to inject content in to the above created file, have to create object for ExtentReporter.
		extent.attachReporter(spark);
								// spark.setAppendExisting(true); 
								// spark.loadXMLConfig("./src/main/resources/extent-config.xml");
	}

	//@BeforeClass
	// Level 2. Test case details such as Test Name, Author.
	public void startTestCase(String testCaseName, String testDescription) {
	test = extent.createTest(testCaseName, testDescription);
	test.assignAuthor("Vishnu S");
	test.assignCategory("Smoke");
	test.assignDevice("Mac Pro");
	}

	//@BeforeMethod
	// Level 3. Test step details
	public void startTestStep(String Status, String Desc) {
			
	}

//	//@AfterSuite
	// Level 4. Close Extent.
	public void endResult() {
		extent.flush();
	}

	
	
	
	public abstract String takeSnap();
	public void reportSteps(String desc,String status) throws IOException {
		
		Media img = null;

		//long snapNumber = 1000000L;
		Date rawexpDate = new Date(new Date().getTime());
		String dt = rawexpDate.toString();
		String[] splt = dt.split(" ");
		String snapNumber = splt[5]+splt[1]+splt[2]+splt[3].replaceAll(":", "");
		
		snapNumber = takeSnap();
	
		img = MediaEntityBuilder.createScreenCaptureFromPath("./../reports/images/"+snapNumber+".png").build();		

		switch (status.toUpperCase())
		{
			case "PASS":{
				test.pass(desc, img);
				break;
			}	
			case "FAIL":{
				test.fail(desc, img);
				break;
			}
			case "INFO":{
				test.info(desc, img);
				break;
			}
			case "WARNING":{
				test.warning(desc, img);
				break;
			}
		}

	}



}
