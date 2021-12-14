package com.example.restapi.librarymanagement.controller;

import com.example.restapi.librarymanagement.exception.exceptions.BookNotFoundException;
import com.example.restapi.librarymanagement.model.Book;
import com.example.restapi.librarymanagement.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

      private final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

      private BookService bookService;

      public BookController(BookService bookService)
      {
          this.bookService=bookService;
      }

      //TODO add pagination and sorting
      @GetMapping(value = "/v1/books",produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<List<Book>> AllBooks(@RequestParam(required=false) String bookname,@RequestParam(required=false) String authorname)
      {     LOGGER.info("Retrieving all books");

        List<Book> books=new ArrayList<>();
            if(bookname!=null&&authorname!=null)
                books=bookService.getBookByBooknameAndAuthorname(bookname,authorname);
            else if(bookname!=null)
                books=bookService.getBookByBookName(bookname);
            else if(authorname!=null)
                books=bookService.getBookByAuthor(authorname);
            else books= bookService.getAllBooks();

            return new ResponseEntity<>(books, HttpStatus.OK);
      }
      /** Retrieving book with book id ,
         @throws BookNotFoundException **/
      @GetMapping(value = "/v1/books/{bookid}",produces =MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<Book>  bookById(@PathVariable Long bookid)
      {
            LOGGER.info("Retrieving book with bookid "+bookid);
            Optional<Book> optBook=bookService.getBookById(bookid);
            if(optBook.isPresent())
            {
                  return new ResponseEntity<>(optBook.get(),HttpStatus.OK);
            }
            else throw new BookNotFoundException(bookid);

      }

       /** Retrieving books with book isbn,
        *  @throws BookNotFoundException **/
      @GetMapping(value = "/v1/books/isbn/{bookisbn}",produces =MediaType.APPLICATION_JSON_VALUE)
       public ResponseEntity<List<Book>>  bookByIsbn(@PathVariable String bookisbn)
      {
          LOGGER.info("Retrieving book with isbn number "+bookisbn);
          List<Book> books=bookService.getBookByIsbn(bookisbn);
          if(books.isEmpty())
          {
              throw new BookNotFoundException(bookisbn);
          }
          return new ResponseEntity<>(books,HttpStatus.OK);
      }


      @PostMapping(value = "/v1/books",consumes = MediaType.APPLICATION_JSON_VALUE)
      @ResponseStatus(HttpStatus.CREATED)
      public Book addBook(@RequestBody @Valid Book book)
      {
           LOGGER.info("Adding a new book ");
           book.setCreationDate(LocalDate.now());
           return bookService.saveBook(book);
      }

      @PutMapping(value = "/v1/books/{bookId}",consumes = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable Long bookId)
      {
          LOGGER.info("Updating book with id "+bookId);
          Book updatedBook=bookService.updateBook(book,bookId);

          return new ResponseEntity<>(updatedBook, HttpStatus.OK);

      }


    @DeleteMapping(value = "/v1/books/{bookid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookCopy(@PathVariable Long bookid) {

        LOGGER.info("Deleting book with id "+bookid);
        Book bookCopy = bookService.getBookById(bookid).orElseThrow(() -> new BookNotFoundException(bookid));
        bookService.deleteBook(bookCopy.getBookId());
    }

}
