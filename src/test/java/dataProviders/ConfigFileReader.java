package dataProviders;
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
 
public class ConfigFileReader {
	private Properties properties;
	private final String propertyFilePath = "Configuration\\Configuration.properties"; //propertyFilePath : This is just a String variable which holds the information of the config file path.
	
	public ConfigFileReader(){
		BufferedReader reader;
		try {
				reader = new BufferedReader(new FileReader(propertyFilePath)); 
												//new FileReader(propertyFilePath) : Creates a new FileReader, given the name of the file to read from.
												//new BufferedReader(new FileReader(propertyFilePath)) : Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
				properties = new Properties();	//new Properties() : The Properties class represents a persistent set of properties. The Properties can be saved to a stream or loaded from a stream. Each key and its corresponding value in the property list is a string.
				try {
						properties.load(reader);//properties.load(reader) : Reads a property list (key and element pairs) from the input character stream in a simple line-oriented format.
						reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		} 
	}
 
	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	
	public String getApplicationUid() {
		String uid = properties.getProperty("uid");
		if(uid != null) return uid;
		else throw new RuntimeException("User Id not specified in the Configuration.properties file.");
	}

	public String getApplicationPwd() {
		String pwd = properties.getProperty("pwd");
		if(pwd != null) return pwd;
		else throw new RuntimeException("Password not specified in the Configuration.properties file.");
	}

	public String getUniqueCName() {
		String cname = properties.getProperty("cname");
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
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file."); 
	}
	
	public long getUploadFilePath() { 
		String fileUploadPath = properties.getProperty("fileUploadPath");
		if(fileUploadPath != null) return Long.parseLong(fileUploadPath);
		else throw new RuntimeException("File Upload Path not specified in the Configuration.properties file."); 
	}
 
	public long getUploadFileName() { 
		String fileName = properties.getProperty("fileName");
		if(fileName != null) return Long.parseLong(fileName);
		else throw new RuntimeException("File Name not specified in the Configuration.properties file."); 
	}
 
}