package com.mycompany.bookapi.test;

import com.mycompany.bookapi.dto.BookDTO;
import com.mycompany.bookapi.dto.BookDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carl
 */
public class TestBooks {

    public static List<BookDTO> createList(String _BOOK_TITLE, int amountOfBooks) {
        List<BookDTO> booksList = new ArrayList<>();
        for (int id = 0; id < amountOfBooks; id++) {
            BookDTO testBook = new BookDTO(_BOOK_TITLE + "-" + id);
            booksList.add(testBook);
        }
        return booksList;
    }

}
