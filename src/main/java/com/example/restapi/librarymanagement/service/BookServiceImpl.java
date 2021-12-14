package com.example.restapi.librarymanagement.service;

import com.example.restapi.librarymanagement.exception.exceptions.BookNotFoundException;
import com.example.restapi.librarymanagement.exception.exceptions.BookWithAuthorNotFoundException;
import com.example.restapi.librarymanagement.exception.exceptions.BookwithNameNotFoundException;
import com.example.restapi.librarymanagement.exception.exceptions.IssueCardNotFoundException;
import com.example.restapi.librarymanagement.model.Book;
import com.example.restapi.librarymanagement.repository.BookRepository;
import com.example.restapi.librarymanagement.repository.LibraryIssueCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImpl implements BookService {

    private final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    BookRepository bookRepository;

    @Autowired
    LibraryIssueCardRepository cardRepository;

    public BookServiceImpl(BookRepository bookRepository)
    {
        this.bookRepository=bookRepository;
    }

    @Override
    public Optional<Book> getBookById(Long bookId) {

        LOGGER.info("Retrieving book from database with id "+bookId);
        return bookRepository.findById(bookId);
    }

    @Override
    public List<Book> getBookByIsbn(String bookIsbn) {
        LOGGER.info("Retrieving all books from database with isbn "+bookIsbn);
        return bookRepository.findByBookIsbn(bookIsbn);
    }

    @Override
    public List<Book> getBookByIssueCard(Long issuecard) {

        LOGGER.info("Retrieving all book from database with cardid "+issuecard);
         if(cardRepository.findById(issuecard).isPresent())
        return bookRepository.findByLibraryIssueCardIssueCardId(issuecard);
         else throw new IssueCardNotFoundException(issuecard);
    }

    @Override
    public List<Book> getBookByAuthor(String authorname) {

        LOGGER.info("Retrieving books from database with author  "+authorname);
        List<Book> booksByAuthor=bookRepository.findByBookAuthor(authorname);
        if(booksByAuthor.isEmpty()) throw new BookWithAuthorNotFoundException(authorname);
        return booksByAuthor;
    }
    @Override
    public List<Book> getBookByBookName(String bookname) {
        LOGGER.info("Retrieving books from database with name "+bookname);
        List<Book> booksByName=bookRepository.findByBookName(bookname);
        if(booksByName.isEmpty()) throw new BookwithNameNotFoundException(bookname);
        return booksByName;
    }

    @Override
    public List<Book> getBookByBooknameAndAuthorname(String bookname, String authorname) {
        LOGGER.info("Retrieving books from database with bookname  "+bookname+" authorname "+authorname);
        List<Book> books= bookRepository.findByBookNameAndAndBookAuthor(bookname,authorname);
        if(books.isEmpty()) throw new BookwithNameNotFoundException(bookname,authorname);
        return books;
    }

    @Override
    public List<Book> getAllBooks() {
        LOGGER.info("Retrieving all books from database");
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) {

        LOGGER.info("Persisting book ");
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book, Long bookId) {
        LOGGER.info("updating book in database with bookid "+bookId);
        Book bookCopy = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        bookCopy.setBookIsbn(book.getBookIsbn());
        bookCopy.setBookAuthor(book.getBookAuthor());
        bookCopy.setBookFormat(book.getBookFormat());
        bookCopy.setBookName(book.getBookName());
        bookCopy.setPublishDate(book.getPublishDate());
        bookCopy.setCreationDate(book.getCreationDate());

        return bookRepository.save(bookCopy);
    }


    @Override
    public void deleteBook(Long bookId) {

        LOGGER.info("deleting from database bookid  "+bookId);
        bookRepository.deleteById(bookId);
    }
}
