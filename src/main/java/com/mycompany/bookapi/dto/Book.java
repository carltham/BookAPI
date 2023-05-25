package com.mycompany.bookapi.dto;

/**
 *
 * @author carl
 */
public interface Book {

    public String getTitle();

    public void setTitle(String title);

    public void setId(Long id);

    public Long getId();

}
