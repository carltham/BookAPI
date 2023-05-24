/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookapi.repository;

import com.mycompany.dto.Book;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author carl
 */
@Component
public class BookRepositoryImpl implements BookRepository {

    private FakeDB fakeDB;

    public BookRepositoryImpl(FakeDB fakeDB) {
        this.fakeDB = fakeDB;
    }

    @Override
    public List<Book> findAll() {
        return fakeDB.findAll();
    }

    @Override
    public Book save(Book newBook) {
        return fakeDB.save(newBook);
    }

    @Override
    public void deleteById(Long id) {
        fakeDB.deleteById(id);
    }

    @Override
    public Book findById(Long id) {
        return fakeDB.findById(id);
    }

}
