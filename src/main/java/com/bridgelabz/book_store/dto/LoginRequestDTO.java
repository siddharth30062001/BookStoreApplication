package com.bridgelabz.book_store.dto;


import jakarta.persistence.Id;
import lombok.Generated;
import lombok.Getter;

public class LoginRequestDTO {

    private String emailId;
    private String password;


    public LoginRequestDTO(String emailId, String password) {
        this.emailId = emailId;
        this.password= password;


    }

    public LoginRequestDTO() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
