package com.turboocelots.oasis.models;

/**
 * The Administrator class.
 * Special account for maintenance of system.
 * Can Delete any accounts, ban a user, and unblock an account
 * that has been locked.
 * Can view security logs
 */

public class Administrator extends User {

    /**
     * Two parameter constructor representing bare minimum
     * @param username the username of the new Administrator
     * @param password the password of the new Administrator
     */
    public Administrator(String username, String password) {
        super(username, password);
    }

    /**
     * Creates an instance of the Administrator class.
     * @param username the username of the Administrator
     * @param password the password of the Administrator
     * @param email the email of the Administrator
     * @param home the home address of the Administrator
     * @param title the title of the Administrator
     * @param phone the phone number of the Administrator
     */
    public Administrator(String username, String password, String name,
                         String email, String home, UserTitle title, String phone) {
        super(username, name, password, email, home, title, phone);
    }

    public UserType getUserType(){
        return UserType.Administrator;
    }
}
