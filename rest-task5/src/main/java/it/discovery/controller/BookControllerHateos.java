package it.discovery.controller;

import it.discovery.data.ServiceLayerException;
import it.discovery.data.model.Book;
import it.discovery.data.response.BookListResponse;
import it.discovery.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/hateos/book")
public class BookControllerHateos {

    private final BookRepository bookRepository;

    @Autowired
    public BookControllerHateos(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BookListResponse listBook() {
        return new BookListResponse(bookRepository.findAll().stream().map(this::prepareResource).collect(Collectors.toList()));
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Resource<Book> getBook(@PathVariable Integer id) {
        return prepareResource(bookRepository.findById(id));
    }

    @PutMapping(path = "/{id}/rent")
    public ResponseEntity rentBook(@PathVariable Integer id) {
        Book book = bookRepository.findById(id);
        if (book.isOnRent()) {
            throw new ServiceLayerException("Book is already rented.", HttpStatus.CONFLICT.value());
        }
        book.setOnRent(true);

        bookRepository.save(book);
        return new ResponseEntity(HttpStatus.OK);
    }

    protected Resource<Book> prepareResource(Book book) {
        Resource<Book> resource = new Resource<>(book);
        resource.add(
                ControllerLinkBuilder.linkTo(
                        ControllerLinkBuilder.methodOn(BookControllerHateos.class).getBook(book.getId())
                ).withSelfRel()
        );
        if (!book.isOnRent()) {
            resource.add(
                    ControllerLinkBuilder.linkTo(
                            ControllerLinkBuilder.methodOn(BookControllerHateos.class).rentBook(book.getId())
                    ).withRel("edit")
            );
        }
        return resource;
    }

}
