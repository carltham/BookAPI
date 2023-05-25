package com.mycompany.bookapi.pub;

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

    @GetMapping(path = "/books", produces = "application/json")
    public List<BookDTO> list() {
        List<BookDTO> books = repository.findAll();
        System.out.println("books=" + books);
        return books;
    }

    @PostMapping(path = "/books", consumes = "application/json", produces = "application/json")
    public BookDTO create(@RequestBody BookDTO newBook) {
        return repository.save(newBook);
    }

    @GetMapping(path = "/books/{id}", produces = "application/json")
    public BookDTO read(@PathVariable Long id) {

        BookDTO book = repository.findById(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        return book;
    }

    @PutMapping(path = "/books/{id}", produces = "application/json")
    public BookDTO update(@RequestBody BookDTO newBook, @PathVariable Long id) {

        BookDTO book = repository.findById(id);
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
