package com.example.restapi.librarymanagement.repository;

import com.example.restapi.librarymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT U FROM Book B JOIN LibraryIssueCard C ON B.libraryIssueCard.issueCardId=C.issueCardId JOIN User U ON U.userId=C.user.userId " +
            "WHERE B.bookIsbn =?1" )
    List<User> findUsersByBook(String bookisbn);
}
