/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookapi;

/**
 *
 * @author carl
 */
class BookNotFoundException extends RuntimeException {

    BookNotFoundException(Long id) {
        super("Could not find book " + id);
    }
}
