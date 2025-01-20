package com.bridgelabz.book_store.dto;

import lombok.Getter;

@Getter
public class BookRequestDTO {

    private String bookName;
    private String author;
    private String bookDescription;
    private String logo;
    private double price;
    private long quantity;

    public BookRequestDTO(String bookName, String author, String bookDescription, String logo, double price, long quantity) {
        this.bookName = bookName;
        this.author = author;
        this.bookDescription = bookDescription;
        this.logo = logo;
        this.price = price;
        this.quantity = quantity;
    }

    public BookRequestDTO() {
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
