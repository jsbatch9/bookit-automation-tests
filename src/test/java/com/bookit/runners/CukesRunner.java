package com.bookit.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class) 
@CucumberOptions(
		tags= {"@login1, @login2", "not @login3"},
		features = "src/test/resources/features",
		glue = "com/bookit/step_definitions",
		dryRun = false
		)
public class CukesRunner {

}
