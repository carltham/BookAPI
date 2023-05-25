/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.bookapi.pub;

import com.mycompany.bookapi.dto.Book;
import com.mycompany.bookapi.dto.BookDTO;
import com.mycompany.bookapi.repository.FakeDB;
import com.mycompany.bookapi.test.TestBooks;
import com.mycompany.bookapi.test.TestConstants;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author carl
 */
@SpringBootTest
public class BookAPITest {

    SpringExtension extension;

    @Autowired
    private BookAPI instance;

    private Book newBook;

    @BeforeEach
    public void setup() {

        newBook = new BookDTO("tttt");

        List<Book> loadedBooks = TestBooks.createList(TestConstants._TEST_TITLE, 5);
        FakeDB.load(loadedBooks);
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(instance).isNotNull();
    }

    @Test
    public void testAll() {

        List<Book> result = instance.list();
        List<Book> expResult = FakeDB.findAll();
        assertThat(result).isEqualTo(expResult);
    }

    @Test
    public void testNewBook() {

        Book result = instance.create((BookDTO) newBook);
        assertThat(result).isEqualTo(newBook);
    }

    @Test
    public void testOne() {

        Book expResult = FakeDB.findById(TestConstants._TEST_ID2);
        Book result = instance.read(TestConstants._TEST_ID2);
        assertThat(result).isEqualTo(expResult);

    }

    @Test
    public void testReplaceBook() {

        Book result = instance.update((BookDTO) newBook, TestConstants._TEST_ID2);
        assertThat(result.getId()).isEqualTo(TestConstants._TEST_ID2);
        assertThat(result.getTitle()).isEqualTo(newBook.getTitle());
    }

    @Test
    public void testDeleteBook() {

        Book expResult = FakeDB.findById(TestConstants._TEST_ID2);
        assertThat(expResult).isNotNull();
        instance.delete(TestConstants._TEST_ID2);
        expResult = FakeDB.findById(TestConstants._TEST_ID2);
        assertThat(expResult).isNull();

    }

}
