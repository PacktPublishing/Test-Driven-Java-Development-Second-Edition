package com.packtpublishing.tddjava.ch02friendships.bdd.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class WebSteps {
    @Given("^I go to Wikipedia homepage$")
    @org.jbehave.core.annotations.Given("I go to Wikipedia homepage")
    public void goToWikiPage() {
        open("http://en.wikipedia.org/wiki/Main_Page");
    }

    @When("^I enter the value (.*) on a field named (.*)$")
    @org.jbehave.core.annotations.When("I enter the value $value on a field named $fieldName")
    public void enterValueOnFieldByName(String value, String fieldName){
        $(By.name(fieldName)).setValue(value);
    }

    @When("^I click the button (.*)$")
    @org.jbehave.core.annotations.When("I click the button $buttonName")
    public void clickButonByName(String buttonName){
        $(By.name(buttonName)).click();
    }

    @Then("^the page title contains (.*)$")
    @org.jbehave.core.annotations.Then("the page title contains $title")
    public void pageTitleIs(String title) {
        assertThat(title(), containsString(title));
    }

}
