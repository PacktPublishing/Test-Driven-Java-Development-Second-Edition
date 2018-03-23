package com.packtpublishing.tddjava.alexandria;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Book {

    private final String title;
    private final String author;
    @XmlTransient
    private ArrayList<States> status;
    private int id;

    public Book(String title, String author, States status) {
        this.title = title;
        this.author = author;
        this.status = new ArrayList<>();
        this.status.add(status);
    }

    public String getTitle() {
        return title;
    }


    public States getStatus() {
        return status.get(status.size() - 1);
    }

    public String getAuthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @XmlElement(name = "status")
    public int getStatusAsInteger(){
        return getStatus().getValue();
    }

    public void rent() {
        status.add(States.RENTED);
    }

    public void returnBook() {
        status.add(States.AVAILABLE);
    }

    public void censor() {
        status.add(States.CENSORED);
    }

    public void uncensor() {
        status.add(States.AVAILABLE);
    }

    public void prepare() {
        status.add(States.AVAILABLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (!author.equals(book.author)) return false;
        if (!status.equals(book.status)) return false;
        if (!title.equals(book.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + id;
        return result;
    }

    public List<States> anyState() {
        return status;
    }
}
