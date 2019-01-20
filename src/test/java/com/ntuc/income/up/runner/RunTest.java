package com.ntuc.income.up.runner;

import com.ntuc.income.up.utilities.TestReporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = "com.ntuc.income.up.steps",
        tags = "@PU-2",
        plugin = "json:target/jsons/report1.json"
        //dryRun = false,
        //strict = true,
        //monochrome = false,
)
public class RunTest {

    @AfterClass
    public static void teardown() {

    }

}
