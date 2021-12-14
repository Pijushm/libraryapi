package com.example.restapi.librarymanagement.controller;


import com.example.restapi.librarymanagement.config.Translator;
import com.example.restapi.librarymanagement.exception.exceptions.BookNotFoundException;
import com.example.restapi.librarymanagement.exception.exceptions.IssueCardNotFoundException;
import com.example.restapi.librarymanagement.exception.exceptions.IssueCardOfUserNotFoundException;
import com.example.restapi.librarymanagement.model.Book;
import com.example.restapi.librarymanagement.model.LibraryIssueCard;
import com.example.restapi.librarymanagement.model.User;
import com.example.restapi.librarymanagement.service.BookService;
import com.example.restapi.librarymanagement.service.LibraryCardService;
import com.example.restapi.librarymanagement.service.LibrarySearchService;
import com.example.restapi.librarymanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LibraryIssueCardController {


    @Autowired
    LibraryCardService libraryCardService;
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @Autowired
    LibrarySearchService librarySearchService;

    private final Logger LOGGER = LoggerFactory.getLogger(LibraryIssueCardController.class);


    @GetMapping(value = "/v1/issuecards", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LibraryIssueCard>> AllIssueCards() {
        LOGGER.info("Retriving all issuecard");
        List<LibraryIssueCard> issueCards = libraryCardService.getIssueCards();
        return new ResponseEntity<>(issueCards, HttpStatus.OK);
    }

    /**
     * Retrieving issucard with cardid,
     *
     * @throws IssueCardNotFoundException
     */
    @GetMapping(value = "/v1/issuecards/{issuecardid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LibraryIssueCard> IssueCardByid(@PathVariable Long issuecardid) {

        LOGGER.info("Retriving all issuecard with cardid " + issuecardid);
        Optional<LibraryIssueCard> optIssueCard = libraryCardService.getIssueCardByid(issuecardid);
        if (optIssueCard.isPresent()) {
            return new ResponseEntity<>(optIssueCard.get(), HttpStatus.OK);
        } else throw new IssueCardNotFoundException(issuecardid);
    }

    /**Retriving all books froma  cardid
     */
    @GetMapping(value = "/v1/issuecards/{issuecardid}/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> booksOfIssueCard(@PathVariable Long issuecardid) {
        LOGGER.info("Retriving all books from cardid " + issuecardid);
        return new ResponseEntity<>(bookService.getBookByIssueCard(issuecardid), HttpStatus.OK);
    }

    /**
     * Retrieving  all issuecard for a particular user
     *
     * @throws IssueCardOfUserNotFoundException
     **/
    @GetMapping(value = "/v1/users/{userid}/issuecards/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LibraryIssueCard>> IssueCardByUser(@PathVariable Long userid) {
        LOGGER.info("Retriving all issucards of userid " + userid);
        List<LibraryIssueCard> issueCards = libraryCardService.getIssueCardsByUser(userid);
        if (issueCards.isEmpty()) {
            throw new IssueCardOfUserNotFoundException(userid);

        }
        return new ResponseEntity<>(issueCards, HttpStatus.OK);
    }

    /**
     * Retrieving  all books issued to a particular user
     **/
    @GetMapping(value = "/v1/users/{userid}/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> BooksIssuetoUser(@PathVariable Long userid) {
        LOGGER.info("Retriving all books issued to a user " + userid);
        List<LibraryIssueCard> issueCards = libraryCardService.getIssueCardsByUser(userid);
        List<Book> booksissued = new ArrayList<>();

        for (LibraryIssueCard card : issueCards) {
            booksissued.addAll(bookService.getBookByIssueCard(card.getIssueCardId()));
        }
        return new ResponseEntity<>(booksissued, HttpStatus.OK);
    }


    /**
     * Retrieving  all users holding particular book
     **/
    @GetMapping(value = "/v1/books/{bookisbn}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> UsersHoldingBooks(@PathVariable String bookisbn) {
        LOGGER.info("Retriving all users for a particular book" + bookisbn);

        List<Book> books = bookService.getBookByIsbn(bookisbn);
        if (books.isEmpty()) {
            throw new BookNotFoundException(bookisbn);
        }

        List<User> users=librarySearchService.findUsersHoldingABook(bookisbn);
        //TODO throw exception is no user holding
        if(users.isEmpty())
            new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    /**
     * creating a new  issuecard for a  user
     */
    @PostMapping(value = "/v1/users/{userid}/issuecards", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LibraryIssueCard issueBooks(@RequestBody LibraryIssueCard libraryIssueCard, @PathVariable Long userid) {

        LOGGER.info("Issuing new book to user " + userid);
        return libraryCardService.saveIssueCard(libraryIssueCard, userid);
    }

    /**
     * user submitting books for a particular issuecard
     */
    @PutMapping(value = "/v1/users/{userid}/submission/issuecards/{issuecard}")
    public ResponseEntity<String> returnBooks(@PathVariable Long userid,
                                              @PathVariable Long issuecard) {

        LOGGER.info("user submitting books " + userid);
        //retrive exist card from database
        LibraryIssueCard issueCard = libraryCardService.getIssueCardByid(issuecard).orElseThrow(() -> new IssueCardNotFoundException(issuecard));

        libraryCardService.returnIssueCardBooks(issueCard, userid);
        return new ResponseEntity<>(Translator.toLocale("book.submitted"), HttpStatus.OK);
    }

    //TODO rewnew books of issuecards
}
