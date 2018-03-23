package com.packtpublishing.tddjava.ch02friendships;

import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

public class SelenideTest {
    @Test
    public void wikipediaSearchFeature() throws InterruptedException {
        // Opening Wikipedia page
        open("http://en.wikipedia.org/wiki/Main_Page");

        // Searching TDD
        $(By.name("search")).setValue("Test-driven development");

        // Clicking search button
        $(By.name("go")).click();

        // Checks
        assertThat(title(), startsWith("Test-driven development"));
    }
}
