package com.bridgelabz.book_store.dto;

import com.bridgelabz.book_store.model.Book;
import com.bridgelabz.book_store.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class CartRequestDTO {


    private Long userId;
    private Long bookModel;
    private Long quantity;


    public Long getBookModel() {
        return bookModel;
    }

    public void setBookModel(Long bookModel) {
        this.bookModel = bookModel;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


}
