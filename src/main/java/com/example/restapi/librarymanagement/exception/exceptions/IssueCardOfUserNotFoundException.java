package com.example.restapi.librarymanagement.exception.exceptions;

import com.example.restapi.librarymanagement.config.Translator;

public class IssueCardOfUserNotFoundException  extends RuntimeException{

    public IssueCardOfUserNotFoundException(Long userid)
    {
        super(Translator.toLocale("issuecard.notfound")+",  userid: "+userid);
    }
}
