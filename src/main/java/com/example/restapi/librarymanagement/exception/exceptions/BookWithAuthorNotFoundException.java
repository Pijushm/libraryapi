package com.example.restapi.librarymanagement.exception.exceptions;

import com.example.restapi.librarymanagement.config.Translator;

public class BookWithAuthorNotFoundException extends RuntimeException {

    public BookWithAuthorNotFoundException(String authorname)
    {
       super( Translator.toLocale("author.notfound")+", authorname: "+authorname);
    }
}
