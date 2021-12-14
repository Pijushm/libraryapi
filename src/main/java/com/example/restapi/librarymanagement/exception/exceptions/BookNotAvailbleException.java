package com.example.restapi.librarymanagement.exception.exceptions;

import com.example.restapi.librarymanagement.config.Translator;

public class BookNotAvailbleException extends RuntimeException {

    public BookNotAvailbleException(Long bookId) {
        super(Translator.toLocale("book.notavailble") + ", id:" + bookId);
    }

    public BookNotAvailbleException() {
        super(Translator.toLocale("book.nobookavailabe"));
    }
}
