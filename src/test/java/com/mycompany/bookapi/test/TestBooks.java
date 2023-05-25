/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookapi.test;

import com.mycompany.bookapi.dto.Book;
import com.mycompany.bookapi.dto.BookDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carl
 */
public class TestBooks {

    public static List<Book> createList(String _BOOK_TITLE, int amountOfBooks) {
        List<Book> booksList = new ArrayList<>();
        for (int id = 0; id < amountOfBooks; id++) {
            Book testBook = new BookDTO(_BOOK_TITLE + "-" + id);
            booksList.add(testBook);
        }
        return booksList;
    }

}
