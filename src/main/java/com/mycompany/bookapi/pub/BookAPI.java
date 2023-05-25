package com.mycompany.bookapi.pub;

import com.mycompany.bookapi.dto.BookDTO;
import java.util.List;

/**
 *
 * @author carl
 */
public interface BookAPI {

    public List<BookDTO> list();

    public BookDTO create(BookDTO newBook);

    public BookDTO read(Long id);

    public BookDTO update(BookDTO newBook, Long id);

    public void delete(Long id);

}
