package com.danielsemakov.booktracker;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Danny Semakov
 */

import com.danielsemakov.booktracker.BookDashboard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {
    private UserManager userManager;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private JPanel panel;

    /**
     * Sets up the login page UI and user manager.
     */
    public LoginPage(UserManager userManager) {
        this.userManager = userManager;

        //Create window
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        
        //Using GBC
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        //Create the JLabels
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        //Organize all the elements on the page
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(registerButton, gbc);

        add(panel);

        //Add action listeners
        loginButton.addActionListener((ActionEvent e) -> {
            handleLogin();
        });

        registerButton.addActionListener((ActionEvent e) -> {
            handleRegister();
        });
    }

    /**
     * Checks if the login info is correct and opens the dashboard.
     */
    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (userManager.login(username, password)) {
            BookDashboard dashboard = new BookDashboard(userManager);
            dashboard.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }

    /**
     * Registers a new user and logs them in.
     */
    private void handleRegister() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        userManager.addUser(username, password);
        JOptionPane.showMessageDialog(this, "User registered successfully");

        if (userManager.login(username, password)) {
            BookDashboard dashboard = new BookDashboard(userManager);
            dashboard.setVisible(true);
            dispose();
        }
    }
}