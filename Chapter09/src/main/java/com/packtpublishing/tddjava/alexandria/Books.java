package com.packtpublishing.tddjava.alexandria;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

@XmlRootElement
public class Books {

    @XmlElement(name = "result")
    private final ConcurrentLinkedQueue<Book> books;
    private AtomicInteger counter = new AtomicInteger();

    public Books() {
        books = new ConcurrentLinkedQueue<>();
    }

    private Books(ConcurrentLinkedQueue<Book> books) {
        this.books = books;
    }

    public Book first() {
        return books.peek();
    }

    public void add(Book book) {
        book.setId(counter.getAndIncrement());
        books.add(book);
    }

    public int size() {
        return books.size();
    }

    public Books filterByAuthor(String author) {
        return new Books(new ConcurrentLinkedQueue<>(books.stream().filter(x -> x.getAuthor().contains(author)).collect(toList())));
    }

    public Books filterByTitle(String title) {
        return new Books(new ConcurrentLinkedQueue<>(books.stream().filter(x -> x.getTitle().toUpperCase().contains(title.toUpperCase())).collect(toList())));
    }

    public Books filterById(String id) {
        return new Books(new ConcurrentLinkedQueue<>(books.stream().filter(x -> x.getId() == Integer.valueOf(id)).collect(toList())));
    }

    public Books filterByState(String state) {
        return new Books(new ConcurrentLinkedQueue<>(books.stream().filter(x -> x.anyState().contains(States.fromValue(Integer.valueOf(state)))).collect(toList())));
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public String getRequestTime() {
        return DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.now());
    }

    public Books filterOutCensored() {
        return new Books(new ConcurrentLinkedQueue<>(books.stream().filter(x -> x.getStatus() != States.CENSORED).collect(toList())));
    }
}
