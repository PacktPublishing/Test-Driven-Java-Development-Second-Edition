package com.packtpublishing.tddjava.alexandria;

import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("books")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class BooksEndpoint {

    private BooksRepository books;

    private UserRepository users = new UserRepository();

    public BooksEndpoint(BooksRepository books) {
        this.books = books;
    }

    @GET
    @Path("")
    public Response getAllBooks(@QueryParam("title") String title, @QueryParam("author") String author, @QueryParam("id") String id, @QueryParam("state") String state) {
        Books list = books.list();
        if (null != id) {
            return Response.accepted(list.filterById(id).filterOutCensored()).build();
        }
        if (null != author) {
            list = list.filterByAuthor(author);
        }
        if (null != title) {
            list = list.filterByTitle(title);
        }
        if (null != state) {
            list = list.filterByState(state);
        }
        return Response.accepted(list.filterOutCensored()).build();
    }

    @POST
    @Path("{id}/rent/{user}")
    public Response rentBook(@PathParam("id") String id, @PathParam("user") String userId) {
        final Books matchingBooks = books.list().filterById(id);
        if (null == id || null == userId || matchingBooks.isEmpty() || users.findById(userId).isEmpty() || matchingBooks.first().getStatus() != States.AVAILABLE) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Could not process this request ").build();
        }
        matchingBooks.first().rent();

        return Response.accepted(matchingBooks.first()).build();
    }

    @POST
    @Path("{id}/return")
    public Response returnBook(@PathParam("id") String id) {
        Books list = books.list();
        final Books matchingBooks = list.filterById(id);
        if (null == id || matchingBooks.isEmpty() || matchingBooks.first().getStatus() != States.RENTED) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Could not process this request").build();
        }
        matchingBooks.first().returnBook();

        return Response.accepted(matchingBooks.first()).build();
    }

    @POST
    @Path("{id}/censor")
    public Response censorBook(@PathParam("id") String id) {
        final Books matchingBooks = books.list().filterById(id);
        if (null == id || matchingBooks.isEmpty() || matchingBooks.first().getStatus() == States.CENSORED) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Could not process this request").build();
        }
        matchingBooks.first().censor();

        return Response.accepted(matchingBooks.first()).build();
    }

    @POST
    @Path("{id}/uncensor")
    public Response uncensorBook(@PathParam("id") String id) {
        final Books matchingBooks = books.list().filterById(id);
        if (null == id || matchingBooks.isEmpty() || matchingBooks.first().getStatus() != States.CENSORED) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Could not process this request").build();
        }
        matchingBooks.first().uncensor();

        return Response.accepted(matchingBooks.first()).build();
    }

    @POST
    @Path("{id}/prepare")
    public Response initialPreparation(@PathParam("id") String id) {
        final Books matchingBooks = books.list().filterById(id);
        if (null == id || matchingBooks.isEmpty() || matchingBooks.first().getStatus() != States.BOUGHT) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Could not process this request").build();
        }
        matchingBooks.first().prepare();

        return Response.accepted(matchingBooks.first()).build();
    }


    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("")
    public Response addNewBook(
            @FormParam("title") String title,
            @FormParam("author") String author
    ) {
        books.add(new Book(title, author, States.BOUGHT));
        return Response.accepted(books.list().size()).build();
    }

}
