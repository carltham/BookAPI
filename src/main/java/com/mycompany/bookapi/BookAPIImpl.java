/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookapi;

import com.mycompany.bookapi.repository.BookRepository;
import com.mycompany.dto.Book;
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
    @GetMapping("/books")
    public List<Book> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/books")
    public Book newBook(@RequestBody Book newBook) {
        return repository.save(newBook);
    }

    // Single item
    @GetMapping("/books/{id}")
    public Book one(@PathVariable Long id) {

        Book book = repository.findById(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        return book;
    }

    @PutMapping("/books/{id}")
    public Book replaceBook(@RequestBody Book newBook, @PathVariable Long id) {

        Book book = repository.findById(id);
        if (book != null) {
            book.setTitle(newBook.getTitle());
            return repository.save(book);

        } else {
            newBook.setId(id);
            return repository.save(newBook);

        }
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
