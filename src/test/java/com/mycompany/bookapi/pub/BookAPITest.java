package com.mycompany.bookapi.pub;

import com.mycompany.bookapi.dto.Book;
import com.mycompany.bookapi.dto.BookDTO;
import com.mycompany.bookapi.repository.FakeDB;
import com.mycompany.bookapi.test.TestBooks;
import com.mycompany.bookapi.test.TestConstants;
import java.net.URISyntaxException;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
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
    public void testCreate() {

//WHEN
//THEN
        Book result = instance.create((BookDTO) newBook);
//VERIFY
        assertThat(result).isEqualTo(newBook);
    }

    @Test
    public void testList() {

//WHEN
        List<Book> expResult = FakeDB.findAll();
//THEN
        List<Book> result = instance.list();
//VERIFY
        assertThat(result).isEqualTo(expResult);
    }

    @Test
    public void testRead_OK() {

//WHEN
        Book expResult = FakeDB.findById(TestConstants._TEST_ID2);
//THEN
        Book result = instance.read(TestConstants._TEST_ID2);
//VERIFY
        assertThat(result).isEqualTo(expResult);

    }

    @Test()
    public void testRead_FAIL() throws URISyntaxException {

//WHEN
        Book expResult = FakeDB.findById(TestConstants._TEST_ID9);
        try {
//THEN
            Book book = instance.read(TestConstants._TEST_ID9);

//VERIFY
            assertThat(book).isNotNull();
            fail("Should newer have come this far...");
            assertThat(book.getId()).isEqualTo(expResult.getId());
            assertThat(book.getTitle()).isEqualTo(expResult.getTitle());
        } catch (BookNotFoundException e) {
            System.out.println("Error = " + e.getClass());
        }

    }

    @Test
    public void testUpdate() {

//WHEN
//THEN
        Book result = instance.update((BookDTO) newBook, TestConstants._TEST_ID2);
//VERIFY
        assertThat(result.getId()).isEqualTo(TestConstants._TEST_ID2);
        assertThat(result.getTitle()).isEqualTo(newBook.getTitle());
    }

    @Test
    public void testDelete() {

//WHEN
        Book expResult = FakeDB.findById(TestConstants._TEST_ID2);
        assertThat(expResult).isNotNull();
//THEN
        instance.delete(TestConstants._TEST_ID2);
        expResult = FakeDB.findById(TestConstants._TEST_ID2);
//VERIFY
        assertThat(expResult).isNull();

    }

}
