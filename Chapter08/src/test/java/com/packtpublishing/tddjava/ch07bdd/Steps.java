package com.packtpublishing.tddjava.ch07bdd;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.WebDriverRunner;
import org.jbehave.core.annotations.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;


public class Steps {

    private WebDriver webDriver;
    private String url = "http://localhost:9001";

    @BeforeStory
    public void beforeStory() {
        if (webDriver == null) {
            webDriver = new PhantomJSDriver();
            WebDriverRunner.setWebDriver(webDriver);
            webDriver.manage().window().setSize(new Dimension(1024, 768));
        }
    }

    @Given("user is on the books screen")
    public void givenUserIsOnTheBooksScreen() {
        open(url);
        $("#books").click();
    }

    @Then("field $elementId exists")
    public void thenFieldExists(String elementId) {
        $("#" + elementId).shouldBe(visible);
    }

    @When("user clicks the button $elementId")
    public void whenUserClicksTheButton(String elementId) {
        $("#" + elementId).click();
    }

    @When("user sets values to the book form")
    public void whenUserSetsValuesToTheBookForm() {
        $("#bookId").setValue("123");
        $("#bookTitle").setValue("BDD Assistant");
        $("#bookAuthor").setValue("Viktor Farcic");
        $("#bookDescription").setValue("Open source BDD stories editor and runner");
    }

    @Then("book is stored")
    public void thenBookIsStored() {
        $("#book123").shouldBe(present);
    }

    @When("user selects a book")
    public void whenUserSelectsABook() {
        $("#book1").click();
    }

    @Then("book form contains all data")
    public void thenBookFormContainsAllData() {
        $("#bookId").shouldHave(value("1"));
        $("#bookTitle").shouldHave(value("TDD for Java Developers"));
        $("#bookAuthor").shouldHave(value("Viktor Farcic"));
        $("#bookDescription").shouldHave(value("Cool book!"));
    }

    @When("user sets new values to the book form")
    public void whenUserSetsNewValuesToTheBookForm() {
        $("#bookTitle").setValue("TDD for Java Developers revised");
        $("#bookAuthor").setValue("Viktor Farcic and Alex Garcia");
        $("#bookDescription").setValue("Even better book!");
        $("#saveBook").click();
    }

    @Then("book is updated")
    public void thenBookIsUpdated() {
        $("#book1").shouldHave(text("TDD for Java Developers revised"));
        $("#book1").click();
        $("#bookTitle").shouldHave(value("TDD for Java Developers revised"));
        $("#bookAuthor").shouldHave(value("Viktor Farcic and Alex Garcia"));
        $("#bookDescription").shouldHave(value("Even better book!"));
    }

    @Then("book is removed")
    public void thenBookIsRemoved() {
        $("#book1").shouldNotBe(visible);
    }

}
