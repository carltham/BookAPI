/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.bookapi.repository;

import com.mycompany.dto.Book;
import java.util.List;

/**
 *
 * @author carl
 */
public interface BookRepository {

    public List<Book> findAll();

    public Book save(Book newBook);

    public Book findById(Long id);

    public void deleteById(Long id);

}
