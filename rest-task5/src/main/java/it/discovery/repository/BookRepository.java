package it.discovery.repository;

import java.util.List;

import it.discovery.data.model.Book;

public interface BookRepository {
	Book findById(int id);
	
	List<Book> findAll();
	
	Book save(Book book);
	
	boolean delete(int id);

}
