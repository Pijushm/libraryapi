package com.example.restapi.librarymanagement.exception.exceptions;

import com.example.restapi.librarymanagement.config.Translator;

public class BookwithNameNotFoundException extends RuntimeException {

    public BookwithNameNotFoundException(String bookname)
    {
        super(Translator.toLocale("book.notfound")+", bookname: "+bookname);
    }

    public BookwithNameNotFoundException(String bookname,String authorname)
    {
        super(Translator.toLocale("book.notfound")+", bookname: "+bookname+" and author name: "+authorname);
    }


}
