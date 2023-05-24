/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookapi;

import com.mycompany.dto.Book;
import com.mycompany.dto.BookDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carl
 */
class TestBooks {

    static List<Book> createList(String _BOOK_TITLE, int amountOfBooks) {
        List<Book> booksList = new ArrayList<>();
        for (int id = 0; id < amountOfBooks; id++) {
            Book testBook = new BookDTO(_BOOK_TITLE + "-" + id);
            booksList.add(testBook);
        }
        return booksList;
    }

}
