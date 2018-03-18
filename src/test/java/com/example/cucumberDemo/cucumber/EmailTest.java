package com.example.cucumberDemo.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","json:target/cucumber-json/brand.json"},
        features = {"src/test/resources/feature/email/addEmail.feature",
                    "src/test/resources/feature/email/emails.feature"},
        glue = {"com.example.cucumberDemo.cucumber", "com.foreach.cuke"},
        tags = {"~@Ignore"},
        strict = true
)
public class EmailTest {
}
