package com.example.restapi.librarymanagement.exception;

import com.example.restapi.librarymanagement.exception.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ApiExceptionResponse>> notValid(MethodArgumentNotValidException ex) {


        List<ApiExceptionResponse> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ApiExceptionResponse("BAD_REQUEST", error.getDefaultMessage(), LocalDateTime.now())
                ).collect(Collectors.toList());


        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> userNotFound(UserNotFoundException ex) {
        ApiExceptionResponse response = new ApiExceptionResponse();
        response.setErrror("NOT_FOUND");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> bookNotFound(BookNotFoundException ex)
    {
        ApiExceptionResponse response=new ApiExceptionResponse("NOT_FOUND",ex.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IssueCardNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> issueCardNotFound(IssueCardNotFoundException ex)
    {
        ApiExceptionResponse response=new ApiExceptionResponse("NOT_FOUND",ex.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookLimitException.class)
    public ResponseEntity<ApiExceptionResponse> bookLimitException(BookLimitException ex)
    {
        ApiExceptionResponse response=new ApiExceptionResponse("BOOK_LIMIT",ex.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotAvailbleException.class)
    public ResponseEntity<ApiExceptionResponse> bookNotAvailble(BookNotAvailbleException ex)
    {
        ApiExceptionResponse response=new ApiExceptionResponse("NOT_AVAILABLE",ex.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookWithAuthorNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> authorNotFound(BookWithAuthorNotFoundException ex)
    {
        ApiExceptionResponse response=new ApiExceptionResponse("NOT_FOUND",ex.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BookwithNameNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> bookNotFound(BookwithNameNotFoundException ex)
    {
        ApiExceptionResponse response=new ApiExceptionResponse("NOT_FOUND",ex.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(IssueCardOfUserNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> noIssueCard(IssueCardOfUserNotFoundException ex)
    {
        ApiExceptionResponse response=new ApiExceptionResponse("NOT_FOUND",ex.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<ApiExceptionResponse> BadRequest(HttpClientErrorException.BadRequest ex)
    {
        ApiExceptionResponse response=new ApiExceptionResponse("BAD_REQUEST",ex.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

}
