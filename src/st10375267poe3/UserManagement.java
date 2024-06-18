package st10375267poe3;

import java.util.Scanner;

public class UserManagement {
    static Scanner loadMe = new Scanner(System.in);
    static String username;
    static String password;
    static String firstName;
    static String lastName;

    public static boolean checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            System.out.println("Username Successfully Captured!!!");
            return true;
        } else {
            System.out.println(
                    "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            return false;
        }
    }

    public static boolean checkPasswordComplexity(String password) {
        if (password.length() >= 8 && password.matches("(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).*")) {
            System.out.println("Password Successfully Captured!!!");
            return true;
        } else {
            System.out.println(
                    "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
            return false;
        }
    }

    public static String registerUser() {
        boolean validUsername = false;
        boolean validPassword = false;

        while (!validUsername) {
            System.out.println("Please enter your username:");
            username = loadMe.nextLine();
            validUsername = checkUserName(username);
        }

        while (!validPassword) {
            System.out.println("Please enter your password:");
            password = loadMe.nextLine();
            validPassword = checkPasswordComplexity(password);
        }

        System.out.println("Please enter your first name:");
        firstName = loadMe.nextLine();

        System.out.println("Please enter your last name:");
        lastName = loadMe.nextLine();
        
        System.out.println("\nUser successfully registered!!! ;)");
        return "User successfully registered.";
    }

    public static boolean loginUser() {
        boolean validLogin = false;
        
        while (!validLogin) {
            System.out.println("Please enter your username:");
            String enteredUsername = loadMe.nextLine();

            System.out.println("Please enter your password:");
            String enteredPassword = loadMe.nextLine();

            if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                System.out.println("Welcome " + firstName + " " + lastName + ", it is great to see you again.!!!");
                validLogin = true;
            } else {
                System.out.println("Username or password incorrect, please try again.");
            }
        }
        return true;
    }
}
