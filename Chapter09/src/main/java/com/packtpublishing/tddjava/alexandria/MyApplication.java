package com.packtpublishing.tddjava.alexandria;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class MyApplication extends ResourceConfig {

    public MyApplication() {
        this(new BooksEndpoint(new BooksRepository()));
    }

    public MyApplication(BooksEndpoint booksEndpoint) {
        register(booksEndpoint);
        register(RequestContextFilter.class);
        register(JacksonJaxbJsonProvider.class);
        register(CustomExceptionMapper.class);
    }

}
