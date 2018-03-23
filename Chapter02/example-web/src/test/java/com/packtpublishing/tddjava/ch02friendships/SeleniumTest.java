package com.packtpublishing.tddjava.ch02friendships;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

public class SeleniumTest {
    @Test
    public void wikipediaSearchFeature() throws InterruptedException {
        // Declaring the web driver used for web browsing
        WebDriver driver = new FirefoxDriver();

        // Opening Wikipedia page
        driver.get("http://en.wikipedia.org/wiki/Main_Page");

        // Searching TDD
        WebElement query = driver.findElement(By.name("search"));
        query.sendKeys("Test-driven development");

        // Clicking search button
        WebElement goButton = driver.findElement(By.name("go"));
        goButton.click();

        // Checks
        assertThat(driver.getTitle(), startsWith("Test-driven development"));

        driver.quit();
    }
}
