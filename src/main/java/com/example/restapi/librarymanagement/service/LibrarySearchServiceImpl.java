package com.example.restapi.librarymanagement.service;

import com.example.restapi.librarymanagement.exception.exceptions.BookNotAvailbleException;
import com.example.restapi.librarymanagement.model.Book;
import com.example.restapi.librarymanagement.model.BookRequest;
import com.example.restapi.librarymanagement.model.BookStatus;
import com.example.restapi.librarymanagement.model.User;
import com.example.restapi.librarymanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibrarySearchServiceImpl implements LibrarySearchService {


    private final Logger LOGGER = LoggerFactory.getLogger(LibrarySearchServiceImpl.class);
    @Autowired
    BookService bookService;

    @Autowired
    UserRepository userRepository;

    /**
     *  this method is used to find the available books based on the user requests
     *  and return a book per request
     *  if all books are reserved
     *  @throws  BookNotAvailbleException
     * @param bookRequests
     * @return List<Book>
     */
    @Override
    public  List<Book> findBookByRequest(List<BookRequest> bookRequests) {

        LOGGER.info("finding available books for bookrequest ");
        List<Book> searchresult=new ArrayList<>();
        for(BookRequest bookRequest:bookRequests)
        {
            if(bookRequest.getBookId()!=null)
            {
                Book book=bookService.getBookById(bookRequest.getBookId()).get();
                if(book.getBookStatus()==BookStatus.AVAILABLE)
                    searchresult.add(bookService.getBookById(bookRequest.getBookId()).get());
                else throw new BookNotAvailbleException(book.getBookId());
            }
            else if(bookRequest.getBookIsbn()!=null)
            {
                Book bookByIsbn=
                        bookService.getBookByIsbn(bookRequest.getBookIsbn()).stream()
                                .filter(book -> book.getBookStatus()==BookStatus.AVAILABLE).findAny()
                                .orElseThrow(()->new BookNotAvailbleException());

                searchresult.add(bookByIsbn);
            }

            else if(bookRequest.getAuthorName()!=null) {
                Book bookBynameAndAuthor=bookService.getBookByBooknameAndAuthorname(bookRequest.getBookName(),bookRequest.getAuthorName())
                        .stream()
                        .filter(book -> book.getBookStatus()==BookStatus.AVAILABLE)
                        .findAny()
                        .orElseThrow(()->new BookNotAvailbleException());
                searchresult.add(bookBynameAndAuthor);
            }
            else{
                Book bookByname=bookService.getBookByBookName(bookRequest.getBookName())
                        .stream()
                        .filter(book -> book.getBookStatus()==BookStatus.AVAILABLE)
                        .findAny()
                        .orElseThrow(()->new BookNotAvailbleException());
                searchresult.add(bookByname);
            }

        }


        return searchresult;
    }

    @Override
    public List<User> findUsersHoldingABook(String bookisbn) {
        return userRepository.findUsersByBook(bookisbn);
    }
}
