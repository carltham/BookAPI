package com.mycompany.bookapi.repository;

import com.mycompany.bookapi.dto.BookDTO;
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
    public List<BookDTO> findAll() {
        return FakeDB.findAll();
    }

    @Override
    public BookDTO save(BookDTO newBook) {
        return FakeDB.save(newBook);
    }

    @Override
    public void deleteById(Long id) {
        FakeDB.deleteById(id);
    }

    @Override
    public BookDTO findById(Long id) {
        return FakeDB.findById(id);
    }

}
