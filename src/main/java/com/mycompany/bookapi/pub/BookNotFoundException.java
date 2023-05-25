package com.mycompany.bookapi.pub;

/**
 *
 * @author carl
 */
class BookNotFoundException extends RuntimeException {

    BookNotFoundException(Long id) {
        super("Could not find book " + id);
    }
}
