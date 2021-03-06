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
public class BookListResponse {

    private List<Resource<Book>> books = new ArrayList<>();

    public BookListResponse() {
    }

    public BookListResponse(List<Resource<Book>> books) {
        this.books = books;
    }

    public List<Resource<Book>> getBooks() {
        return books;
    }

    public void setBooks(List<Resource<Book>> books) {
        this.books = books;
    }
}
