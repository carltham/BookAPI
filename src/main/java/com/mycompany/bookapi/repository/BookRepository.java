package com.mycompany.bookapi.repository;

import com.mycompany.bookapi.dto.Book;
import java.util.List;

/**
 *
 * @author carl
 */
public interface BookRepository {

    public List<Book> findAll();

    public Book save(Book newBook);

    public Book findById(Long id);

    public void deleteById(Long id);

}
