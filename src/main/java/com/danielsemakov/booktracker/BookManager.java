package com.danielsemakov.booktracker;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Danny Semakov
 */

import com.danielsemakov.booktracker.Book;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    final List<Book> books;


    public BookManager() {
        books = new ArrayList<>();
    }


    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Tries to update an existing book based on its ISBN. Returns true if the book is found 
     * and updated and returns false otherwise.
     */
    public boolean updateBook(String isbn, Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getIsbn().equals(isbn)) {
                book.setName(updatedBook.getName());
                book.setAuthor(updatedBook.getAuthor());
                book.setGenre(updatedBook.getGenre());
                book.setPublisher(updatedBook.getPublisher());
                book.setRating(updatedBook.getRating());
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a book based on its ISBN. If the book is found, it gets removed from the list.
     */
    public void removeBook(String isbn) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(isbn)) {
                books.remove(i);
                return;
            }
        }
    }

    /**
     * Gets a book by its ISBN. Returns the book if found, or null if not.
     */
    public Book getBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        
        return null;
    }

    /**
     * Returns all books in the collection.
     */
    public List<Book> getAllBooks() {
        return books;
    }

    /**
     * Finds and returns the book with the highest rating.
     * @return Book book with highest rating
     */
    public Book getFavoriteBook() {
        if (books.isEmpty()) {
            return null;
        }

        Book favoriteBook = books.get(0);
        for (Book book : books) {
            if (book.getRating() > favoriteBook.getRating()) {
                favoriteBook = book;
            }
        }
        return favoriteBook;
    }

    /**
     * Returns the author with the highest rating.
     */
    public String getFavoriteAuthor() {
        if (books.isEmpty()) {
            return "";
        }

        String favoriteAuthor = books.get(0).getAuthor();
        for (Book book : books) {
            if (book.getAuthor().compareTo(favoriteAuthor) < 0) {
                favoriteAuthor = book.getAuthor();
            }
        }
        return favoriteAuthor;
    }

    /**
     * Returns the genre that has the highest rating.
     */
    public String getFavoriteGenre() {
        if (books.isEmpty()) {
            return "";
        }

        String favoriteGenre = books.get(0).getGenre();
        for (Book book : books) {
            if (book.getGenre().compareTo(favoriteGenre) < 0) {
                favoriteGenre = book.getGenre();
            }
        }
        return favoriteGenre;
    }
}