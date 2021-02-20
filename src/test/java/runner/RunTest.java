package runner;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/java/features", 
					glue = {"lib.pom.lightning", "stepdefinition"},
					dryRun = true,
					monochrome = true,
					plugin = { "pretty", "html:target/htmlreports"} )

@Test
public class RunTest extends AbstractTestNGCucumberTests { 
	public RunTest() {
	}
}
