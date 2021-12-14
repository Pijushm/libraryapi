package com.example.restapi.librarymanagement.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibraryIssueCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long issueCardId;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "libraryIssueCard")
    List<Book> bookCopies;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id")
    User user;
    LocalDate issueDate;
    LocalDate dueDate;
    LocalDate returnDate;
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<BookRequest> bookRequests;

    public Long getIssueCardId() {
        return issueCardId;
    }

    public void setIssueCardId(Long issueCardId) {
        this.issueCardId = issueCardId;
    }

    public List<Book> getBookCopies() {
        return bookCopies;
    }

    public void setBookCopies(List<Book> bookCopies) {
        this.bookCopies = bookCopies;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void addBooks(Book book)
    {
        bookCopies.add(book);
    }


    public List<BookRequest> getBookRequests() {
        return bookRequests;
    }

    public void setBookRequests(List<BookRequest> bookRequests) {
        this.bookRequests = bookRequests;
    }
}
