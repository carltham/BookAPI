/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.bookapi;

import com.mycompany.dto.Book;
import java.util.List;

/**
 *
 * @author carl
 */
interface BookAPI {

    public List<Book> all();

    public Book newBook(Book newBook);

    public Book one(Long id);

    public Book replaceBook(Book newBook, Long id);

    public void deleteBook(Long id);
    
}
