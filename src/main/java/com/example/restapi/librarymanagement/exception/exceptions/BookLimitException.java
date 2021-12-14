package com.example.restapi.librarymanagement.exception.exceptions;

import com.example.restapi.librarymanagement.utils.LibraryUtils;
import com.example.restapi.librarymanagement.config.Translator;

public class BookLimitException  extends RuntimeException{

      public BookLimitException()
      {
          super(Translator.toLocale("book.limit") +", : " +LibraryUtils.MAX_BOOKS_ISSUED);
      }
}
