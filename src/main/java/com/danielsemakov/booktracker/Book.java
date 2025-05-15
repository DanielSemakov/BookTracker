package com.danielsemakov.booktracker;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Danny Semakov
 */
public class Book {
    private final String isbn;
    private String name;
    private String author;
    private String genre;
    private String publisher;
    private int rating;

    public Book(String isbn, String name, String author, String genre, String publisher, int rating) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.rating = rating;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}