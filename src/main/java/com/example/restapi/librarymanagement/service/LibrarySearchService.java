package com.example.restapi.librarymanagement.service;

import com.example.restapi.librarymanagement.model.Book;
import com.example.restapi.librarymanagement.model.BookRequest;
import com.example.restapi.librarymanagement.model.User;

import java.util.List;

public interface LibrarySearchService {


    List<Book> findBookByRequest(List<BookRequest> bookRequests);
    List<User> findUsersHoldingABook(String bookisbn);
}
