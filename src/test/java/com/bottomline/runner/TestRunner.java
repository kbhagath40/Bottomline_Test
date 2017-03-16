package com.bottomline.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources",
		glue = {"com/bottomline/stepdefinitions"},
		plugin = {"html:target/cucumber-hrml-reports",
				  "json:target/cucumber.json"},
		tags = {"@login"})
public class TestRunner {

}
