package com.example.restapi.librarymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    @NotBlank(message = "User name should not be empty")
    @Size(min = 2,message = "Name should have atleast 2 characters")
    String fullName;
    @Email(message = "Email address not valid")
    String emailId;
    @NotNull
    LocalDate dateOfJoining;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<LibraryIssueCard> issuedBooks;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<LibraryIssueCard> getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(List<LibraryIssueCard> issuedBooks) {
        this.issuedBooks = issuedBooks;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
