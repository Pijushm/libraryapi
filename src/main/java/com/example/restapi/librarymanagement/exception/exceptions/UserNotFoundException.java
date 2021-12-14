package com.example.restapi.librarymanagement.exception.exceptions;

import com.example.restapi.librarymanagement.config.Translator;

public class UserNotFoundException extends RuntimeException {

      public UserNotFoundException(Long userId)
      {
          super(Translator.toLocale("user.notfound")+", userid:"+ userId);
      }
}
