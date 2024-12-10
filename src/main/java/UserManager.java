/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Danny Semakov
 */
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    //Store user info via a map of username to password
    private Map<String, String> users;  
    
    //Associate a user with their book collection by storing a map of username
    //to BookManager
    private Map<String, BookManager> userBookManagers;  
 
    private String currentUser;

    public UserManager() {
        users = new HashMap<>();
        userBookManagers = new HashMap<>();
    }

    public void addUser(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, password);
            
            // New user gets an empty book collection
            userBookManagers.put(username, new BookManager());  
        }
    }

    public boolean login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            currentUser = username;
            return true;
        }
        return false;
    }

    public BookManager getBookManagerForCurrentUser() {
        return userBookManagers.get(currentUser);
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void logout() {
        currentUser = null;
    }
}