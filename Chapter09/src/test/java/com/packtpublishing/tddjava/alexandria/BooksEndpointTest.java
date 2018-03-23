package com.packtpublishing.tddjava.alexandria;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.packtpublishing.tddjava.alexandria.MyApplication;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;


public class BooksEndpointTest {

    public static final URI FULL_PATH = URI.create("http://localhost:8080/alexandria");
    public static final String AUTHOR_BOOK_1 = "Viktor Farcic and Alex Garcia";
    public static final String TITLE_BOOK_1 = "TDD in Java";
    private HttpServer server;
    private final Map<String, String> TDD_IN_JAVA;

    public BooksEndpointTest() {
        TDD_IN_JAVA = getBookProperties(TITLE_BOOK_1, AUTHOR_BOOK_1);
        RestAssured.baseURI = FULL_PATH.toString();
    }

    private Map<String, String> getBookProperties(String title, String author) {
        Map<String, String> bookProperties = new HashMap<>();
        bookProperties.put("title", title);
        bookProperties.put("author", author);
        return bookProperties;
    }

    @Before
    public void setUp() throws IOException {
        ResourceConfig resourceConfig = new MyApplication();
        server = GrizzlyHttpServerFactory.createHttpServer(FULL_PATH, resourceConfig);
        server.start();
    }

    @After
    public void tearDown(){
        server.shutdownNow();
    }


    @Test
    public void add_one_book() throws IOException {
        final Response books1 = addBook(TDD_IN_JAVA);
        assertBooksSize(books1, is("1"));
    }

    @Test
    public void add_a_second_book() throws IOException {
        addBook(TDD_IN_JAVA);

        final Response book2 = addBook(TDD_IN_JAVA);
        assertBooksSize(book2, is("2"));
    }

    @Test
    public void get_book_details_by_id() throws IOException {
        addBook(TDD_IN_JAVA);

        final Response book2 = getBook(withId(0));
        checkBookSetDetails(book2, 0, hasStatus(1));
    }

    @Test
    public void get_several_books_in_a_row() throws IOException {
        addBook(TDD_IN_JAVA);
        addBook(TDD_IN_JAVA);
        addBook(TDD_IN_JAVA);
        addBook(TDD_IN_JAVA);

        checkThatBook(withId(0), hasStatus(1));
        checkThatBook(withId(1), hasStatus(1));
        checkThatBook(withId(2), hasStatus(1));
        checkThatBook(withId(3), hasStatus(1));
    }

    @Test
    public void censor_a_book() throws IOException {
        addBook(TDD_IN_JAVA);

        final Response response = censorBook(withId(0));
        checkBookDetails(response, 0, hasStatus(4));
    }

    @Test
    public void cannot_retrieve_a_censored_book() throws IOException {
        addBook(TDD_IN_JAVA);

        censorBook(withId(0));

        final Response book = getBook(withId(0));
        book.prettyPrint();
        book.then().body("empty", is(true));
    }

    private Map<String, ?> withId(int id) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("id", id);
        return properties;
    }

    private void checkBookDetails(Response response, int id, Matcher<Integer> statusMatcher) {
        response.prettyPrint();
        response.then()
                .body("author", is(AUTHOR_BOOK_1))
                .body("title", is(TITLE_BOOK_1))
                .body("id", is(id))
                .body("status", statusMatcher);
    }

    private Response censorBook(Map<String, ?> bookProperties) {
        return RestAssured.given().log().path().contentType(ContentType.URLENC).post("books/{id}/censor", bookProperties.get("id"));
    }

    private Matcher<Integer> hasStatus(int status) {
        return is(status);
    }


    private void checkThatBook(Map<String, ?> bookProperties, Matcher<Integer> status) {
        final Response book2 = getBook(bookProperties);
        checkBookSetDetails(book2, (Integer) bookProperties.get("id"), status);
    }

    private void checkBookSetDetails(Response response, int id, Matcher<Integer> status) {
        response.prettyPrint();
        response.then()
                .body("empty", is(false))
                .body("result[0].author", is(AUTHOR_BOOK_1))
                .body("result[0].title", is(TITLE_BOOK_1))
                .body("result[0].id", is(id))
                .body("result[0].status", status);
    }

    private Response getBook(Map<String, ?> bookProperties) {
        return RestAssured.given().log().path().contentType(ContentType.URLENC).accept(ContentType.JSON).parameters(bookProperties).get("books");
    }

    private void assertBooksSize(Response response, Matcher<String> matcher) {
        response.then().body(matcher);
    }

    private Response addBook(Map<String, ?> bookProperties) {
        return RestAssured.given().log().path().contentType(ContentType.URLENC).parameters(bookProperties).post("books");
    }
}
