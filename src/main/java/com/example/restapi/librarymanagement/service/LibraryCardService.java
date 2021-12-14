package com.example.restapi.librarymanagement.service;

import com.example.restapi.librarymanagement.model.Book;
import com.example.restapi.librarymanagement.model.LibraryIssueCard;

import java.util.List;
import java.util.Optional;

public interface LibraryCardService {

    List<LibraryIssueCard> getIssueCards();
    List<LibraryIssueCard> getIssueCardsByUser(Long userid);
    Optional<LibraryIssueCard> getIssueCardByid(Long cardId);
    LibraryIssueCard    saveIssueCard(LibraryIssueCard libraryIssueCard,Long userid);
    void returnIssueCardBooks(LibraryIssueCard libraryIssueCard, Long userid);
}
