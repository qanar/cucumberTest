package com.example.cucumberDemo.cucumber;

import com.example.cucumberDemo.CucumberDemoApplication;
import com.example.cucumberDemo.model.Email;
import com.example.cucumberDemo.service.EmailService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = CucumberDemoApplication.class)
@ContextConfiguration(classes = CucumberDemoApplication.class, loader = SpringBootContextLoader.class)
public class EmailSteps extends RestBaseSteps {

    @Autowired
    private EmailService emailService;

    @Given("there are no emails")
    public void thereAreNoEmails() {
        emailService.removeAll();
    }

    @Given("^(.*) is saved$")
    public void emailIsSaved(String value) {
        post("", "/email/{value}", value);
    }

    @When("^the user requests a list of all emails$")
    public void theUserRequestsAListOfAllEmails() {
        get("/email");
    }

    @Then("^the response status is (\\d+)$")
    public void theResponseStatusIs(int status) {
        assertThat(ScenarioWorld.getInstance().getResponse().statusCode(), equalTo(status));
    }

    @Then("^the response contains (\\d+) (?:email|emails)$")
    public void theResponseContainsEmails(int size) {
        if (size > 0) {
            final List<Email> emails = ScenarioWorld.getInstance().getResponse().getBody().jsonPath().get();
            assertThat(emails, hasSize(size));
        } else {
            assertThat(ScenarioWorld.getInstance().getResponse().getBody().print(), anyOf(isEmptyString(), equalTo("[]")));
        }
    }

    @Then("^the response contains the text (.*)$")
    public void theResponseContains(String value) {
        assertThat(ScenarioWorld.getInstance().getResponse().getBody().print(), containsString(value));
    }
}
