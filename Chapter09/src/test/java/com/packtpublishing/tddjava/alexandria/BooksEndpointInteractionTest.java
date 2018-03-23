package com.packtpublishing.tddjava.alexandria;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class BooksEndpointInteractionTest {

    public static final URI FULL_PATH = URI.create("http://localhost:8080/alexandria");
    public static final String AUTHOR_BOOK_1 = "Viktor Farcic and Alex Garcia";
    public static final String TITLE_BOOK_1 = "TDD in Java";
    private HttpServer server;
    private final Map<String, String> TDD_IN_JAVA;
    private BooksRepository booksRepository;

    public BooksEndpointInteractionTest() {
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
        booksRepository = mock(BooksRepository.class);
        ResourceConfig resourceConfig = new MyApplication(new BooksEndpoint(booksRepository));
        server = GrizzlyHttpServerFactory.createHttpServer(FULL_PATH, resourceConfig);
        server.start();
    }

    @After
    public void tearDown(){
        server.shutdownNow();
    }


    @Test
    public void add_one_book() throws IOException {
        addBook(TDD_IN_JAVA);
        verify(booksRepository).add(new Book(TITLE_BOOK_1, AUTHOR_BOOK_1, States.fromValue(1)));
    }

    private Response addBook(Map<String, String> books) {
        return RestAssured.given().log().path().contentType(ContentType.URLENC).parameters(books).post("books");
    }
}
