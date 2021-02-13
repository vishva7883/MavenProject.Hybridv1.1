package lib.utility;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PropertiesConfig extends GenericWrapper {	

	public String excelFileName;
	public static Properties prop;

	public static String propertyFilePath = "./PropertiesFile/Configuration.properties"; //propertyFilePath : This is just a String variable which holds the information of the config file path.
	
	public PropertiesConfig(){
		FileInputStream fis;
		try {
			fis = new FileInputStream(propertyFilePath);
												//new FileReader(propertyFilePath) : Creates a new FileReader, given the name of the file to read from.
												//new BufferedReader(new FileReader(propertyFilePath)) : Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
				prop = new Properties();		//new Properties() : The Properties class represents a persistent set of properties. The Properties can be saved to a stream or loaded from a stream. Each key and its corresponding value in the property list is a string.
				try {
						prop.load(fis);			//properties.load(reader) : Reads a property list (key and element pairs) from the input character stream in a simple line-oriented format.
						fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		} 
	}

	public static String getBrowser() {
		String brw = prop.getProperty("browser");
		if(brw != null) return brw;
		else throw new RuntimeException("brw not specified in the Configuration.properties file.");
	}
	
	public static String getApplicationUrl() {
		String url = prop.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	
	public String getApplicationUid() {
		String uid = prop.getProperty("uid");
		if(uid != null) return uid;
		else throw new RuntimeException("User Id not specified in the Configuration.properties file.");
	}

	public String getApplicationPwd() {
		String pwd = prop.getProperty("pwd");
		if(pwd != null) return pwd;
		else throw new RuntimeException("Password not specified in the Configuration.properties file.");
	}

	public String getUniqueCName() {
		String cname = prop.getProperty("cname");
		if(cname != null)
		{	
			Date retrieveDate = new Date(new Date().getTime());
			String formDate = retrieveDate.toString();
			String[] d=formDate.split(" ", 6);

			String cName = cname+d[5]+d[1]+d[2]+d[3].replaceAll("[:]*","");
			return cName;
		}
		else throw new RuntimeException("Campaign Name not specified in the Configuration.properties file.");
	}
			
	public long getImplicitlyWait() { 
		String implicitlyWait = prop.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file."); 
	}
	
	public long getUploadFilePath() { 
		String fileUploadPath = prop.getProperty("fileUploadPath");
		if(fileUploadPath != null) return Long.parseLong(fileUploadPath);
		else throw new RuntimeException("File Upload Path not specified in the Configuration.properties file."); 
	}
 
	public long getUploadFileName() { 
		String fileName = prop.getProperty("fileName");
		if(fileName != null) return Long.parseLong(fileName);
		else throw new RuntimeException("File Name not specified in the Configuration.properties file."); 
	}
 
	
}
