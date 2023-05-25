/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookapi.pub;

import com.mycompany.bookapi.dto.Book;
import com.mycompany.bookapi.dto.BookDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author carl
 */
public class TestHttpClient {

    public static String _API_URL;
    static TestRestTemplate restTemplate;

    public static ResponseEntity<List<BookDTO>> list(String endPoint) throws URISyntaxException {

        URI BASE_URL = new URI(_API_URL + endPoint);
        ResponseEntity<List<BookDTO>> responseEntity
                = restTemplate.exchange(
                        BASE_URL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<BookDTO>>() {
                });
        return responseEntity;
    }

    static ResponseEntity<BookDTO> exchange(String endPoint, HttpMethod method) throws URISyntaxException {
        return exchange(endPoint, method, null);
    }

    static ResponseEntity<BookDTO> exchange(String endPoint, HttpMethod method, Book book) throws URISyntaxException {

        URI BASE_URL = new URI(_API_URL + endPoint);
        RequestEntity<Book> entity = RequestEntity.method(method, BASE_URL).body(book);
        ResponseEntity<BookDTO> responseEntity
                = restTemplate.exchange(
                        BASE_URL,
                        method,
                        entity,
                        new ParameterizedTypeReference<BookDTO>() {
                });
        return responseEntity;
    }
}