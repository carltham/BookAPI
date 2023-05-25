package com.mycompany.bookapi.dto;

/**
 *
 * @author carl
 */
public class BookDTO {

    private String title;
    private Long id;

    public BookDTO() {
    }

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

    public void setId(Long newId) {
        id = newId;
    }

}
