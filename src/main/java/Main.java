/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author Danny Semakov
 */
public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        new LoginPage(userManager).setVisible(true);
    }
}
