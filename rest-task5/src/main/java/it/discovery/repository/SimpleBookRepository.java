package it.discovery.repository;

import it.discovery.data.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SimpleBookRepository implements BookRepository {

	private CounterService counterService;
	private GaugeService gaugeService;

	private final Map<Integer, Book> books = new ConcurrentHashMap<>();

	private int counter = 0;

	@Autowired
	public SimpleBookRepository(CounterService counterService, GaugeService gaugeService) {
		this.counterService = counterService;
		this.gaugeService = gaugeService;
	}

	@Override
	public Book findById(int id) {
		return books.get(id);
	}

	@Override
	public List<Book> findAll() {
		return new ArrayList<>(books.values());
	}

	@Override
	public Book save(Book book) {
		if (book.getId() == 0) {
			book.setId(nextId());
		}
        books.put(book.getId(), book);
        System.out.println("*** Book with id=" + book.getId() + " was updated");
		counterService.increment("bookCount");
        return book;
	}

	@Override
	public boolean delete(int id) {
        Book removed = books.remove(id);
        if (removed != null) {
            System.out.println("*** Book with id=" + id + " was deleted");
			counterService.decrement("bookCount");
        }
        return true;
	}

	protected synchronized int nextId() {
		counter++;
		gaugeService.submit("lastGeneratedBookId", counter);
	    return counter;
    }

}
