package com.reactive.app.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("book_details")
public class Book {

    @Id
    private int bookId;

    private String name;

    @Column("book_desc")
    private String descriotion;

    private String publisher;

    @Column("author")
    private String auther;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriotion() {
        return descriotion;
    }

    public void setDescriotion(String descriotion) {
        this.descriotion = descriotion;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }


    public Book(int bookId, String name, String descriotion, String publisher, String auther) {
        this.bookId = bookId;
        this.name = name;
        this.descriotion = descriotion;
        this.publisher = publisher;
        this.auther = auther;
    }


}
