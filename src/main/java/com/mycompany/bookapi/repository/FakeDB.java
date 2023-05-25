/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookapi.repository;

import com.mycompany.bookapi.dto.Book;
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

    private static Map<Long, Book> booksMap = new HashMap<>();
    private static Long lastId = 0l;

    private FakeDB() {
    }

    public static List<Book> findAll() {
        return booksMap.values().stream()
                .collect(Collectors.toList());
    }

    public static Book save(Book newBook) {
        if (newBook.getId() == null) {
            newBook.setId(lastId++);
        }
        booksMap.put(newBook.getId(), newBook);
        return newBook;
    }

    public static void deleteById(Long id) {
        booksMap.remove(id);
    }

    public static Book findById(Long id) {
        Book result = booksMap.get(id);

        System.out.println("FakeDB::findById-booksMap=" + booksMap);
        return result;
    }

    public static void load(List<Book> newBooksList) {

        lastId = 0l;
        newBooksList.forEach((book) -> {
            if (book.getId() == null) {
                book.setId(lastId++);
            }
        });
        booksMap = newBooksList.stream().collect(Collectors.toMap(Book::getId, Function.identity()));
    }

}
