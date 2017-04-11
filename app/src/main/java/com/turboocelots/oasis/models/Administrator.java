package com.turboocelots.oasis.models;

/**
 * Created by Shane on 2/20/17.
 */

public class Administrator extends User {

    /**
     * Legacy constructor for Reporter class
     * Fills in extra parameters with empty strings
     * @param username the username of the Reporter
     * @param password the password of the Reporter
     */
    public Administrator(String username, String password) {
        this("", username, password, "", "", UserTitle.NA, "");
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
        _name = name;
        _username = username;
        _password = password;
        _email = email;
        _home = home;
        _title = title;
        _phone = phone;
        _userType = UserType.Administrator;
    }

    public UserType getUserType(){
        return UserType.Administrator;
    }
}
