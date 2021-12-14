package com.example.restapi.librarymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bookId;
    @NotBlank(message = "Book isbn is needed")
    String bookIsbn;
    @NotBlank(message = "Book name is needed")
    String bookName;
    @NotBlank(message = "Author name is needed")
    String bookAuthor;
    @Enumerated(EnumType.STRING)
    // @ValidateBookFormat
            BookFormat bookFormat;
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    @Column(columnDefinition = "varchar(255) default 'AVAILABLE'")
    BookStatus bookStatus = BookStatus.AVAILABLE;
    LocalDate publishDate;
    LocalDate creationDate;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "issuecard_id")
    LibraryIssueCard libraryIssueCard;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public BookFormat getBookFormat() {
        return bookFormat;
    }

    public void setBookFormat(BookFormat bookFormat) {
        this.bookFormat = bookFormat;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public LibraryIssueCard getLibraryIssueCard() {
        return libraryIssueCard;
    }

    public void setLibraryIssueCard(LibraryIssueCard libraryIssueCard) {
        this.libraryIssueCard = libraryIssueCard;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


}
