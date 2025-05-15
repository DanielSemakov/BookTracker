package com.danielsemakov.booktracker;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Danny Semakov
 */

import com.danielsemakov.booktracker.BookManager;
import com.danielsemakov.booktracker.BookDashboard;
import com.danielsemakov.booktracker.Book;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UpdateBookPage extends JFrame {
    private BookManager bookManager;
    private JTextField isbnField, nameField, authorField, genreField, publisherField;
    private JTextField ratingField;
    private JButton saveButton, cancelButton;
    private Book bookToUpdate;
    private BookDashboard bookDashboard;

    public UpdateBookPage(BookManager bookManager, JFrame parent, Book bookToUpdate) {
        this.bookManager = bookManager;
        this.bookToUpdate = bookToUpdate;
        this.bookDashboard = (BookDashboard) parent;

        //Create window
        setTitle("Update Book");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        //Create fields
        isbnField = new JTextField(bookToUpdate.getIsbn());
        isbnField.setEditable(false);

        nameField = new JTextField(bookToUpdate.getName());
        authorField = new JTextField(bookToUpdate.getAuthor());
        genreField = new JTextField(bookToUpdate.getGenre());
        publisherField = new JTextField(bookToUpdate.getPublisher());

        ratingField = new JTextField(String.valueOf(bookToUpdate.getRating()));

        //Create buttons
        saveButton = new JButton("Save Changes");
        cancelButton = new JButton("Cancel");

        //Add labels and fields to window
        add(new JLabel("ISBN:"));
        add(isbnField);
        
        add(new JLabel("Book Name:"));
        add(nameField);
        
        add(new JLabel("Author:"));
        add(authorField);
        
        add(new JLabel("Genre:"));
        add(genreField);
        
        add(new JLabel("Publisher:"));
        add(publisherField);
        
        add(new JLabel("Rating:"));
        add(ratingField);

        add(saveButton);
        add(cancelButton);

        //Add action listeners
        saveButton.addActionListener((ActionEvent e) -> {
            handleSave();
        });

        cancelButton.addActionListener((ActionEvent e) -> {
            handleCancel(parent);
        });
    }

    private void handleSave() {
        try {
            bookToUpdate.setName(nameField.getText());
            bookToUpdate.setAuthor(authorField.getText());
            bookToUpdate.setGenre(genreField.getText());
            bookToUpdate.setPublisher(publisherField.getText());
            bookToUpdate.setRating(Integer.parseInt(ratingField.getText()));

            JOptionPane.showMessageDialog(this, "Book details updated successfully!");

            bookDashboard.setVisible(true);
            this.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid rating! Please enter a number between 1 and 5.");
        }
    }

    private void handleCancel(JFrame parent) {
        this.dispose();
        parent.setVisible(true);
    }
}