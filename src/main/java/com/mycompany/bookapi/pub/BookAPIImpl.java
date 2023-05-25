package com.mycompany.bookapi.pub;

import com.mycompany.bookapi.dto.Book;
import com.mycompany.bookapi.dto.BookDTO;
import com.mycompany.bookapi.repository.BookRepository;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author carl
 */
@RestController
public class BookAPIImpl implements BookAPI {

    private final BookRepository repository;

    BookAPIImpl(BookRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping(path = "/books", produces = "application/json")
    public List<Book> list() {
        List<Book> books = repository.findAll();
        System.out.println("books=" + books);
        return books;
    }
    // end::get-aggregate-root[]

    @PostMapping(path = "/books", consumes = "application/json", produces = "application/json")
    public Book create(@RequestBody BookDTO newBook) {
        return repository.save(newBook);
    }

    // Single item
    @GetMapping(path = "/books/{id}", produces = "application/json")
    public Book read(@PathVariable Long id) {

        Book book = repository.findById(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        return book;
    }

    @PutMapping(path = "/books/{id}", produces = "application/json")
    public BookDTO update(@RequestBody BookDTO newBook, @PathVariable Long id) {

        Book book = repository.findById(id);
        if (book != null) {
            book.setTitle(newBook.getTitle());
            return (BookDTO) repository.save(book);

        } else {
            newBook.setId(id);
            return (BookDTO) repository.save(newBook);

        }
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
