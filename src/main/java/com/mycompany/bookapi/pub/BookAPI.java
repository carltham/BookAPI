package com.mycompany.bookapi.pub;

import com.mycompany.bookapi.dto.Book;
import com.mycompany.bookapi.dto.BookDTO;
import java.util.List;

/**
 *
 * @author carl
 */
public interface BookAPI {

    public List<Book> list();

    public Book create(BookDTO newBook);

    public Book read(Long id);

    public BookDTO update(BookDTO newBook, Long id);

    public void delete(Long id);

}
