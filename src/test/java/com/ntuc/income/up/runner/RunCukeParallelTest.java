package com.ntuc.income.up.runner;

import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
		glue = "com.ntuc.income.up.steps",
		tags = "@PU-3",
		plugin = "json:target/jsons/report2.json"
//		dryRun = false,
//		strict = true,
//		monochrome = false
)

public class RunCukeParallelTest {

	@AfterClass
	public static void teardown() {
//		Reporter.loadXMLConfig(new File("src/test/java/extent-config.xml"));
//		Reporter.setSystemInfo("user", System.getProperty("user.name"));
//		Reporter.setSystemInfo("os", "Windows");
//		Reporter.setTestRunnerOutput("Sample test runner output message");
	}

	@BeforeClass
	public static void setup() {
//		ExtentProperties extentProperties = ExtentProperties.INSTANCE;
//		extentProperties.setReportPath("output/myreport.html");
	}
}
