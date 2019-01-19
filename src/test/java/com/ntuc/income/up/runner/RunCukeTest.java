package com.ntuc.income.up.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
glue = "com/ntuc/income/up/steps",
tags = { "@PU-2" },
dryRun = false,
strict = true,
monochrome = true,
plugin = {
//		"pretty",
//		"html:target/cucumber_regression",
//		"com.cucumber.listener.ExtentCucumberFormatter:output/report_regression.html",
//		"html:target/cucumber-html-report_regression",
//		"json:target/cucumber_regression.json",
//		"pretty:target/cucumber-pretty_regression.txt",
//		"usage:target/cucumber-usage_regression.json",
//		"junit:target/cucumber-results_regression.xml"
//		"json:target/cucumber-html-reports/index.json"
		"pretty","html:target/html", "json:target/jsonReports/cucumber-json-report.json"
		})
public class RunCukeTest {

	@AfterClass
	public static void teardown() {
//		Reporter.loadXMLConfig(new File("src/test/java/extent-config.xml"));
//		Reporter.setSystemInfo("user", System.getProperty("user.name"));
//		Reporter.setSystemInfo("os", "Windows");
//		Reporter.setTestRunnerOutput("Sample test runner
//		output message");

		/*Collection<File> jsonFiles = org.apache.commons.io.FileUtils.listFiles(new File("target/cucumber-html-reports/"), new String[]{"json"}, true);
		List<String> jsonPaths = new ArrayList(jsonFiles.size());
		jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
		Configuration config = new Configuration(new File("target/cucumber-html-reports/"), "up");
		ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
		reportBuilder.generateReports();

*/

		/*File reportOutputDirectory = new File("target/cucumber-html-reports/");
		List<String> jsonFiles = new ArrayList<>();
		jsonFiles.add("target/cucumber-html-reports/cucumber-json-report.json");
//		jsonFiles.add("cucumber-report-2.json");

		String buildNumber = "1";
		String projectName = "up-automation";
		boolean runWithJenkins = false;

		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
// optional configuration - check javadoc
		configuration.setRunWithJenkins(runWithJenkins);
		configuration.setBuildNumber(buildNumber);
// addidtional metadata presented on main page
		configuration.addClassifications("Platform", "Windows");
		configuration.addClassifications("Browser", "Chrome");
		configuration.addClassifications("Branch", "release/1.0");

// optionally add metadata presented on main page via properties file
//		List<String> classificationFiles = new ArrayList<>();
//		classificationFiles.add("properties-1.properties");
//		classificationFiles.add("properties-2.properties");
//		configuration.addClassificationFiles(classificationFiles);

		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();*/

	}

	@BeforeClass
	public static void setup() {

//		ExtentProperties extentProperties = ExtentProperties.INSTANCE;
//		extentProperties.setReportPath("output/myreport.html");
	}
}
