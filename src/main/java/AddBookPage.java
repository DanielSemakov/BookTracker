/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Danny Semakov
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddBookPage extends JFrame {
    private JTextField isbnField, nameField, authorField, genreField, publisherField, ratingField;
    private JButton submitButton, cancelButton;
    private BookManager bookManager;
    private BookDashboard bookDashboard;  

    public AddBookPage(BookManager bookManager, BookDashboard bookDashboard) {
        this.bookManager = bookManager;
        this.bookDashboard = bookDashboard;

        setTitle("Add New Book");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        isbnField = new JTextField(20);
        nameField = new JTextField(20);
        authorField = new JTextField(20);
        genreField = new JTextField(20);
        publisherField = new JTextField(20);
        ratingField = new JTextField(5);

        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        //Using GridLayout to format the UI
        setLayout(new GridLayout(8, 2, 10, 10)); 

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
        add(new JLabel("Rating (1-5):"));
        add(ratingField);
        add(submitButton);
        add(cancelButton);

        submitButton.addActionListener((ActionEvent e) -> {
            handleSubmit();
        });

        cancelButton.addActionListener((ActionEvent e) -> {
            handleCancel();
        });
    }

    public void handleSubmit() {
        String isbn = isbnField.getText();
        String name = nameField.getText();
        String author = authorField.getText();
        String genre = genreField.getText();
        String publisher = publisherField.getText();
        int rating = Integer.parseInt(ratingField.getText());

        Book newBook = new Book(isbn, name, author, genre, publisher, rating);
        bookManager.addBook(newBook);

        JOptionPane.showMessageDialog(this, "Book added successfully!");

        bookDashboard.setVisible(true);
        this.dispose(); 
    }

    public void handleCancel() {
        bookDashboard.setVisible(true);
        this.dispose(); 
    }
}

