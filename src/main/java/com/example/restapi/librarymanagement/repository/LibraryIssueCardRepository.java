package com.example.restapi.librarymanagement.repository;

import com.example.restapi.librarymanagement.model.LibraryIssueCard;
import com.example.restapi.librarymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryIssueCardRepository extends JpaRepository<LibraryIssueCard,Long> {

    List<LibraryIssueCard> findByUserUserId(Long userid);
    Optional<LibraryIssueCard> findByUser(User user);
}
