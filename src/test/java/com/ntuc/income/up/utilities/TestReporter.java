package com.ntuc.income.up.utilities;

import com.rajatthareja.reportbuilder.Color;
import com.rajatthareja.reportbuilder.ReportBuilder;
import net.masterthought.cucumber.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestReporter {
    public static void main(String... args) {
        TestReporter reports = new TestReporter();
        reports.generateCucumberHtmlReports();
        reports.generateCucumberReports();
    }

    public void generateCucumberHtmlReports() {
        Configuration config = new Configuration(new File("target/"), "up");
        net.masterthought.cucumber.ReportBuilder reportBuilder;
        reportBuilder = new net.masterthought.cucumber.ReportBuilder(getJsonPaths(), config);
        reportBuilder.generateReports();
    }

    public void generateCucumberReports() {
        String browser = System.getProperty("Browser");
        ReportBuilder reportBuilder = new ReportBuilder();
        reportBuilder.setReportDirectory("target/cucumber-reports/");
        reportBuilder.setReportFileName("up-automation-report");
        reportBuilder.setReportTitle("up-automation-report");

        reportBuilder.setReportColor(Color.BROWN);
        reportBuilder.enableVoiceControl();
        reportBuilder.setAdditionalInfo("Browser", browser);

        try {
            List<Object> cucumberJsonReports = new ArrayList<>();
            List<File> files = FileUtils.getAllFiles("target/jsons/");
            files.forEach(file -> cucumberJsonReports.add(file));
            reportBuilder.build(cucumberJsonReports);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getJsonPaths() {
        Collection<File> jsonFiles = org.apache.commons.io.FileUtils.listFiles(new File("target/jsons/"), new String[]{"json"}, true);
        List<String> jsonPaths = new ArrayList(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        return jsonPaths;
    }

}
