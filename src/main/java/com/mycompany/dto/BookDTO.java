package com.mycompany.dto;

/**
 *
 * @author carl
 */
public class BookDTO implements Book {

    private String title;
    private Long id;

    public BookDTO(String newTitle) {
        title = newTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long newId) {
        id = newId;
    }

}
