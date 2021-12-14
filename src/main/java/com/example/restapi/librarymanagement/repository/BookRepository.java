package com.example.restapi.librarymanagement.repository;


import com.example.restapi.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

   List<Book> findByBookIsbn(String bookIsbn);
   List<Book> findByLibraryIssueCardIssueCardId(Long issuecard);
   List<Book> findByBookName(String bookname);
   List<Book> findByBookAuthor(String authorname);
   List<Book> findByBookNameAndAndBookAuthor(String bookname,String authorName);
}
