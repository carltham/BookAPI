/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.bookapi;

import com.mycompany.bookapi.repository.FakeDB;
import com.mycompany.dto.Book;
import com.mycompany.dto.BookDTO;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author carl
 */
@SpringBootTest
public class BookAPITest {

    static String _BOOK_TITLE = "Title";

    @Autowired
    private BookAPI instance;
    @Autowired
    private FakeDB fakeDB;

    private Long _BOOK_ID1 = 1l;
    private Long _BOOK_ID2 = 2l;
    private Book newBook;

    @BeforeEach
    public void setup() {

        newBook = new BookDTO("tttt");

        List<Book> loadedBooks = TestBooks.createList(_BOOK_TITLE, 5);
        fakeDB.load(loadedBooks);
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(instance).isNotNull();
    }

    @Test
    public void testAll() {

        List<Book> result = instance.all();
        List<Book> expResult = fakeDB.findAll();
        assertEquals(expResult, result);
    }

    @Test
    public void testNewBook() {

        Book expResult = newBook;
        Book result = instance.newBook(newBook);
        assertEquals(expResult, result);
    }

    @Test
    public void testOne() {

        Long id = _BOOK_ID1;

        Book expResult = fakeDB.findById(id);
        Book result = instance.one(id);
        assertEquals(expResult, result);

    }

    @Test
    public void testReplaceBook() {
        System.out.println("replaceBook");
        Long id = _BOOK_ID2;

        Book expResult = newBook;
        Book result = instance.replaceBook(newBook, id);
        assertEquals(id, result.getId());
        assertEquals(result.getTitle(), newBook.getTitle());

    }

    @Test
    public void testDeleteBook() {
        System.out.println("deleteBook");
        Long id = null;

        instance.deleteBook(id);

    }

}
