package com.mycompany.bookapi.pub;

import com.mycompany.bookapi.Application;
import com.mycompany.bookapi.dto.Book;
import com.mycompany.bookapi.dto.BookDTO;
import com.mycompany.bookapi.repository.FakeDB;
import com.mycompany.bookapi.test.TestBooks;
import com.mycompany.bookapi.test.TestConstants;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author carl
 */
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookAPIOverHttpTest {

    @Autowired
    private BookAPI instance;
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Book newBook;

    @BeforeEach
    public void setup() {

        TestHttpClient._API_URL = "http://localhost:" + port + "/api/";
        TestHttpClient.restTemplate = restTemplate;

        newBook = new BookDTO("tttt");

        List<Book> loadedBooks = TestBooks.createList(TestConstants._TEST_TITLE, 5);
        FakeDB.load(loadedBooks);
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(instance).isNotNull();
    }

    @Test
    public void testCreate() throws URISyntaxException {
//WHEN
        int nrOfBooksAtThebeginning = FakeDB.findAll().size();
        List<Book> allBooks = FakeDB.findAll();
        assertThat(allBooks.size()).isEqualTo(nrOfBooksAtThebeginning);
//THEN
        ResponseEntity<BookDTO> responseEntity = TestHttpClient.exchange("/books", HttpMethod.POST, newBook);
        BookDTO createdBook = responseEntity.getBody();
//VERIFY
        allBooks = FakeDB.findAll();
        assertThat(createdBook.getId()).isNotNull();
        assertThat(allBooks.size()).isEqualTo(nrOfBooksAtThebeginning + 1);
    }

    @Test
    public void testList() throws MalformedURLException, URISyntaxException {
//WHEN
        List<Book> expectedBooks = FakeDB.findAll();
//THEN
        ResponseEntity<List<BookDTO>> responseEntity = TestHttpClient.list("books");
        List<BookDTO> books = responseEntity.getBody();
        assertThat(books).size().isEqualTo(expectedBooks.size());

        Map<Long, Book> booksMap = books.stream()
                .collect(Collectors.toMap(Book::getId, Function.identity()));
//VERIFY
        expectedBooks.forEach((book) -> {
            Book foundBook = booksMap.get(book.getId());
            assertThat(book.getId()).isEqualTo(foundBook.getId());
            assertThat(book.getTitle()).isEqualTo(foundBook.getTitle());
        });
    }

    @Test
    public void testRead_OK() throws URISyntaxException {

//WHEN
        Book expResult = FakeDB.findById(TestConstants._TEST_ID2);
//THEN
        ResponseEntity<BookDTO> responseEntity = TestHttpClient.exchange("/books/" + TestConstants._TEST_ID2, HttpMethod.GET);
        BookDTO book = responseEntity.getBody();
//VERIFY
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(book.getId()).isEqualTo(expResult.getId());
        assertThat(book.getTitle()).isEqualTo(expResult.getTitle());

    }

    @Test()
    public void testRead_FAIL() throws URISyntaxException {

//WHEN
        Book expResult = FakeDB.findById(TestConstants._TEST_ID9);
//THEN
        ResponseEntity<BookDTO> responseEntity = TestHttpClient.exchange("/books/" + TestConstants._TEST_ID9, HttpMethod.GET);
        BookDTO book = responseEntity.getBody();
//VERIFY
        try {
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            fail("Should newer have come this far...");
            assertThat(book.getId()).isEqualTo(expResult.getId());
            assertThat(book.getTitle()).isEqualTo(expResult.getTitle());
        } catch (AssertionFailedError e) {
            System.out.println("Error = " + e.getClass());
        }

    }

    @Test
    public void testUpdate() throws URISyntaxException {

//WHEN
        int nrOfBooksAtThebeginning = FakeDB.findAll().size();

//THEN
        ResponseEntity<BookDTO> responseEntity = TestHttpClient.exchange("/books/" + TestConstants._TEST_ID2, HttpMethod.PUT, (BookDTO) newBook);
        BookDTO book = responseEntity.getBody();

//VERIFY
        List<Book> allBooks = FakeDB.findAll();
        assertThat(book.getId()).isEqualTo(TestConstants._TEST_ID2);
        assertThat(book.getTitle()).isEqualTo(newBook.getTitle());
        assertThat(allBooks.size()).isEqualTo(nrOfBooksAtThebeginning);
    }

    @Test
    public void testDelete() throws URISyntaxException {

//WHEN
        int nrOfBooksAtThebeginning = FakeDB.findAll().size();
        Book expResult = FakeDB.findById(TestConstants._TEST_ID2);
        assertThat(expResult).isNotNull();

//THEN
        ResponseEntity<BookDTO> responseEntity = TestHttpClient.exchange("/books/" + TestConstants._TEST_ID2, HttpMethod.DELETE);
        BookDTO book = responseEntity.getBody();

//VERIFY
        List<Book> allBooks = FakeDB.findAll();
        expResult = FakeDB.findById(TestConstants._TEST_ID2);
        assertThat(expResult).isNull();
        assertThat(allBooks.size()).isEqualTo(nrOfBooksAtThebeginning - 1);

    }

}
