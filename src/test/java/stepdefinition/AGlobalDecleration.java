package stepdefinition;

import java.awt.Robot;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataProviders.ConfigFileReader;

public class AGlobalDecleration {
	public static RemoteWebDriver driver;  
	public static WebDriverWait wait;
	public static JavascriptExecutor executor;
	public static Actions action;
	public static Robot robot;
	public static ConfigFileReader configFileReader;
}
