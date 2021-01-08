package jmlRunner;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import util.WebController;
import cucumber.api.*;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "html:target/cucumber-reports/cucumber-html-report",
		"json:target/cucumber-reports/cucumber.json", "pretty:target/cucumber-reports/cucumber-pretty.txt",
		"usage:target/cucumber-reports/cucumber-usage.json", "junit:target/cucumber-reports/cucumber-results.xml",
		"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-extent-reports/report.html" }, 
		features = {"src/main/resources/features" },
		glue = { "pageObjects","stepDefinitions", "util","jmlRunner" },
		tags = {"@test"},
		dryRun = false, 
		snippets = SnippetType.CAMELCASE)

public class CucumberRunner_ui {

	public static void tearDown() {
		WebController.closeDeviceDriver();
	}

}