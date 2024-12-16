package com.bridgelabz.book_store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;


public class UserRequestDTO {

    public UserRequestDTO(String firstName, String lastName, LocalDate dob, LocalDate updatedDate, String password, String emailId, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.updatedDate = updatedDate;
        this.password = password;
        this.emailId = emailId;
        this.role = role;
    }


    @NotBlank
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private LocalDate updatedDate;
    private String password;
    @Email
    private String emailId;
    private String role;


    public UserRequestDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
