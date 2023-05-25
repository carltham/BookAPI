package com.mycompany.bookapi.repository;

import com.mycompany.bookapi.dto.BookDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author carl
 */
public class FakeDB {

    private static Map<Long, BookDTO> booksMap = new HashMap<>();
    private static Long lastId = 0l;

    private FakeDB() {
    }

    public static List<BookDTO> findAll() {
        return booksMap.values().stream()
                .collect(Collectors.toList());
    }

    public static BookDTO save(BookDTO newBook) {
        if (newBook.getId() == null) {
            newBook.setId(lastId++);
        }
        booksMap.put(newBook.getId(), newBook);
        return newBook;
    }

    public static void deleteById(Long id) {
        booksMap.remove(id);
    }

    public static BookDTO findById(Long id) {
        BookDTO result = booksMap.get(id);

        System.out.println("FakeDB::findById-booksMap=" + booksMap);
        return result;
    }

    public static void load(List<BookDTO> newBooksList) {

        lastId = 0l;
        newBooksList.forEach((book) -> {
            if (book.getId() == null) {
                book.setId(lastId++);
            }
        });
        booksMap = newBooksList.stream().collect(Collectors.toMap(BookDTO::getId, Function.identity()));
    }

}
