package com.bridgelabz.book_store.dto;

import com.bridgelabz.book_store.model.Book;
import com.bridgelabz.book_store.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {


    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
