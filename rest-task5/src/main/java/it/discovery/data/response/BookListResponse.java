package it.discovery.data.response;

import it.discovery.data.model.Book;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author isegodin
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BookListResponse {

    private List<Book> books = new ArrayList<>();

    public BookListResponse() {
    }

    public BookListResponse(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
