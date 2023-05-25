package com.mycompany.bookapi.repository;

import com.mycompany.bookapi.dto.BookDTO;
import java.util.List;

/**
 *
 * @author carl
 */
public interface BookRepository {

    public List<BookDTO> findAll();

    public BookDTO save(BookDTO newBook);

    public BookDTO findById(Long id);

    public void deleteById(Long id);

}
