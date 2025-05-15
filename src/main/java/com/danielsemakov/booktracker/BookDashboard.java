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
import com.danielsemakov.booktracker.AddBookPage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.List;

public class BookDashboard extends JFrame {
    private BookManager bookManager;
    private JTable bookTable;
    private UserManager userManager;

    /**
     * Initialize the BookDashboard with user details. Sets up the window, buttons, and 
     * table layout.
     */
    public BookDashboard(UserManager userManager) {
        this.userManager = userManager;
        this.bookManager = userManager.getBookManagerForCurrentUser();

        setTitle("Book Dashboard");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = createButtonPanel();
        JScrollPane tableScrollPane = createTable();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    /**
     * Creates a panel with buttons that lead to different actions. When any button is clicked,
     * it calls a specific method.
     */
    public JPanel createButtonPanel() {
        String[] buttonLabels = {"Add Book", "View All Books", "Update Book", "Delete Book", "View Stats", "Logout"};
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1));

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(createButtonListener(label)); 
            buttonPanel.add(button);
        }
        return buttonPanel;
    }

    /**
     * Creates an ActionListener for each button based on the type of action. Each 
     * button triggers a different action based on its label.
     */
    public ActionListener createButtonListener(String action) {
        return (ActionEvent e) -> {
            switch (action) {
                case "Add Book" -> handleAddBook();
                case "View All Books" -> handleViewBooks();
                case "Update Book" -> handleUpdateBook();
                case "Delete Book" -> handleDeleteBook();
                case "View Stats" -> handleViewStats();
                case "Logout" -> handleLogout();
            }
        };
    }

    /**
     * Creates a table to display book information. The table will later be filled with 
     * book data.
     */
    public JScrollPane createTable() {
        bookTable = new JTable();
        return new JScrollPane(bookTable);
    }

    /**
     * Adds a new book to the collection. Opens a new window to input details 
     * for the book.
     */
    public void handleAddBook() {
        new AddBookPage(bookManager, this).setVisible(true);
        this.setVisible(false);
    }

    /**
     * Displays the books in the collection. Gets all books and displays them in a table.
     */
    public void handleViewBooks() {
        List<Book> books = bookManager.getAllBooks();
        String[] columnNames = {"ISBN", "Book Name", "Author", "Genre", "Publisher", "Rating"};
        Object[][] data = new Object[books.size()][6];

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            data[i][0] = book.getIsbn();
            data[i][1] = book.getName();
            data[i][2] = book.getAuthor();
            data[i][3] = book.getGenre();
            data[i][4] = book.getPublisher();
            data[i][5] = book.getRating();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        bookTable.setModel(model);
    }

    /**
     * Edits a book currently in the collection. Prompts the user for the
     * book's ISBN and opens a page to update the book.
     */
    public void handleUpdateBook() {
        String isbn = JOptionPane.showInputDialog("Enter ISBN of Book to Update:");
        Book book = bookManager.getBook(isbn);

        if (book != null) {
            new UpdateBookPage(bookManager, this, book).setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Book Not Found!");
        }
    }

    /**
     * Deletes a book from the collection. Prompts the user for the book's ISBN 
     * and removes the book from the system.
     */
    public void handleDeleteBook() {
        String isbn = JOptionPane.showInputDialog("Enter ISBN of Book to Delete:");
        bookManager.removeBook(isbn);
        JOptionPane.showMessageDialog(this, "Book Deleted!");
    }

    /**
     * Display's the stats for the user's book collection. Shows the favorite book, 
     * author, and genre.
     */
    public void handleViewStats() {
        Book favoriteBook = bookManager.getFavoriteBook();
        String favoriteAuthor = bookManager.getFavoriteAuthor();
        String favoriteGenre = bookManager.getFavoriteGenre();

        String stats = "Favorite Book: " + (favoriteBook != null ? favoriteBook.getName() : "None") +
                       "\nFavorite Author: " + favoriteAuthor +
                       "\nFavorite Genre: " + favoriteGenre;

        JOptionPane.showMessageDialog(this, stats);
    }

    /**
     * Logs out of the account. Makes the BookDashboard invisible and makes the LoginPage
     * visible.
     */
    public void handleLogout() {
        this.setVisible(false);
        new LoginPage(userManager).setVisible(true);
    }
}