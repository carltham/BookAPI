/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookapi.repository;

import com.mycompany.dto.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author carl
 */
@Component
public class FakeDB {

    private Map<Long, Book> booksMap = new HashMap<>();
    private Long lastId = 0l;

    public List<Book> findAll() {
        return booksMap.values().stream()
                .collect(Collectors.toList());
    }

    Book save(Book newBook) {
        if (newBook.getId() == null) {
            newBook.setId(lastId++);
        }
        booksMap.put(newBook.getId(), newBook);
        return newBook;
    }

    void deleteById(Long id) {
        booksMap.remove(id);
    }

    public Book findById(Long id) {
        Book result = booksMap.get(id);

        System.out.println("FakeDB::findById-booksMap=" + booksMap);
        return result;
    }

    public synchronized void load(List<Book> newBooksList) {
        newBooksList.forEach((book) -> {
            if (book.getId() == null) {
                book.setId(lastId++);
            }
        });
        booksMap = newBooksList.stream().collect(Collectors.toMap(Book::getId, Function.identity()));
        lastId = 0l;
    }

}
