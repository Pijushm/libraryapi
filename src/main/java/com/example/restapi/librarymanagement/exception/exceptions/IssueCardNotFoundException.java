package com.example.restapi.librarymanagement.exception.exceptions;

import com.example.restapi.librarymanagement.config.Translator;

public class IssueCardNotFoundException extends RuntimeException {

      public IssueCardNotFoundException(Long issueCardId)
      {
          super(Translator.toLocale("issuecard.notfound")+ ", cardid : " +issueCardId);
      }
}
