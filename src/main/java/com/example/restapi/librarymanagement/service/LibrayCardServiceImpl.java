package com.example.restapi.librarymanagement.service;

import com.example.restapi.librarymanagement.exception.exceptions.BookNotFoundException;
import com.example.restapi.librarymanagement.utils.LibraryUtils;
import com.example.restapi.librarymanagement.exception.exceptions.BookLimitException;
import com.example.restapi.librarymanagement.exception.exceptions.UserNotFoundException;
import com.example.restapi.librarymanagement.model.*;
import com.example.restapi.librarymanagement.repository.LibraryIssueCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibrayCardServiceImpl implements LibraryCardService {

    private final Logger LOGGER = LoggerFactory.getLogger(LibrayCardServiceImpl.class);
    @Autowired
    LibraryIssueCardRepository cardRepository;
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @Autowired
    LibrarySearchService librarySearchService;

    @Override
    public List<LibraryIssueCard> getIssueCards() {

        LOGGER.info(" Retrieving all issuecards from database ");
        return cardRepository.findAll();
    }

    @Override
    public List<LibraryIssueCard> getIssueCardsByUser(Long userid) {

        LOGGER.info(" Retrieving all issuecards from database of userid "+ userid);
        return cardRepository.findByUserUserId(userid);
    }

    @Override
    public Optional<LibraryIssueCard> getIssueCardByid(Long cardId) {
        LOGGER.info(" Retrieving issuecard of cardid "+ cardId);
        return cardRepository.findById(cardId);
    }

    /**
     * to issue a new issue card for a user
     * @throws BookLimitException if user has crossed maximum limit
     */

    @Override
    public LibraryIssueCard saveIssueCard(LibraryIssueCard libraryIssueCard,Long userid) {

        LOGGER.info("persisting new issuecard for user "+ userid);
        User user = userService.getUserById(userid).
                orElseThrow(() -> new UserNotFoundException(userid));



        libraryIssueCard.setUser(user);

        if (user.getIssuedBooks().size() + libraryIssueCard.getBookRequests().size() > LibraryUtils.MAX_BOOKS_ISSUED)
            throw new BookLimitException();

        List<Book> requestedBooks =librarySearchService.findBookByRequest(libraryIssueCard.getBookRequests());
        libraryIssueCard.setIssueDate(LocalDate.now());
        libraryIssueCard.setDueDate(LocalDate.now().plusDays(LibraryUtils.MAX_BOOK_DUE_DAYS));
        cardRepository.save(libraryIssueCard);
        for (Book book : requestedBooks) {

                 book.setBookStatus(BookStatus.RESERVED);
                 book.setLibraryIssueCard(libraryIssueCard);
                 bookService.updateBook(book,book.getBookId());

        }

        return libraryIssueCard;


    }


    @Override
    public void returnIssueCardBooks(LibraryIssueCard libraryIssueCard, Long userid) {

        User user = userService.getUserById(userid).
                orElseThrow(() -> new UserNotFoundException(userid));


        //to keep retrieved book from database
        List<Book> issuecardBooks=bookService.getBookByIssueCard(libraryIssueCard.getIssueCardId());

        libraryIssueCard.setReturnDate(LocalDate.now());
        for(Book book:issuecardBooks)
        {
            book.setBookStatus(BookStatus.AVAILABLE);
            book.setLibraryIssueCard(null);
            bookService.saveBook(book);
        }
        cardRepository.save(libraryIssueCard);
    }


}
