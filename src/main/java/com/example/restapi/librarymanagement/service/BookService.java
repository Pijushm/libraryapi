package com.example.restapi.librarymanagement.service;

import com.example.restapi.librarymanagement.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> getBookById(Long bookId);
    List<Book> getBookByIsbn(String bookIsbn);
    List<Book> getBookByIssueCard(Long issuecard);
    List<Book>  getBookByAuthor(String authorname);
    List<Book>  getBookByBookName(String bookname);
    List<Book>  getBookByBooknameAndAuthorname(String bookname,String authorname);
    List<Book> getAllBooks();
    Book saveBook(Book book);
    Book updateBook(Book book, Long bookId);
    void deleteBook(Long bookId);
}
