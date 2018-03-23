package com.packtpublishing.tddjava.ch02friendships.bdd.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:bdd/cucumber/features"},
                    format = {"pretty", "html:build/reports/cucumber-report"},
                    glue = {"com.packtpublishing.tddjava.ch02friendships.bdd.steps"})
public class CucumberTest {

}
