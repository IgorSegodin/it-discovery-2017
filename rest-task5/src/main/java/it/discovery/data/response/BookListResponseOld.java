package it.discovery.data.response;

import it.discovery.data.model.Book;
import org.springframework.hateoas.Resource;

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
public class BookListResponseOld {

    private List<Book> books = new ArrayList<>();

    public BookListResponseOld() {
    }

    public BookListResponseOld(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
