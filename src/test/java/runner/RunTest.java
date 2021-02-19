package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/java/features", 
					glue = "lib.pom.classic", 
					dryRun = false,
					monochrome = true,
					plugin = { "pretty", "html:target/htmlreports"} )

public class RunTest extends AbstractTestNGCucumberTests { 
	public RunTest() {
	}
}
