package com.mycompany.bookapi.repository;

import com.mycompany.bookapi.dto.Book;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author carl
 */
@Component
public class BookRepositoryImpl implements BookRepository {

    public BookRepositoryImpl() {
    }

    @Override
    public List<Book> findAll() {
        return FakeDB.findAll();
    }

    @Override
    public Book save(Book newBook) {
        return FakeDB.save(newBook);
    }

    @Override
    public void deleteById(Long id) {
        FakeDB.deleteById(id);
    }

    @Override
    public Book findById(Long id) {
        return FakeDB.findById(id);
    }

}
