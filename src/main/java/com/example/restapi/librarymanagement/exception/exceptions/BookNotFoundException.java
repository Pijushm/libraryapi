package com.example.restapi.librarymanagement.exception.exceptions;

import com.example.restapi.librarymanagement.config.Translator;


public class BookNotFoundException extends RuntimeException {


       public BookNotFoundException(Long bookid)
       {
           super(Translator.toLocale("book.notfound")+", id: "+bookid);
       }

       public BookNotFoundException(String bookIsbn)
       {
           super(Translator.toLocale("book.notfound")+" isbn : "+bookIsbn);
       }
}
