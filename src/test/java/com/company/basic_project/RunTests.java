package com.company.basic_project;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features="src/test/resources/features/",
        plugin = {"json:target/cucumber-reports/Cucumber.json", "junit:target/cucumber-reports/Cucumber.xml"}
)
public class RunTests {

}
