package it.discovery.controller;

import it.discovery.data.response.BookListResponse;
import it.discovery.data.model.Book;
import it.discovery.data.response.BookListResponseOld;
import it.discovery.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Book getBook(@PathVariable Integer id) {
        return bookRepository.findById(id);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public int addBook(@RequestBody Book book) {
        return bookRepository.save(book).getId();
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Book putBook(@PathVariable Integer id, @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookRepository.delete(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BookListResponseOld listBook() {
        return new BookListResponseOld(bookRepository.findAll());
    }

}
