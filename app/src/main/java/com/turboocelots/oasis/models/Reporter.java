package com.turboocelots.oasis.models;

import java.io.Serializable;
/**
 * Represents a single Reporter in model
 *
 * Created by mlin on 2/12/17.
 */

public class Reporter extends User implements Serializable {

    /**
     * Legacy constructor for Reporter class
     * Fills in extra parameters with empty strings
     * @param username the username of the Reporter
     * @param password the password of the Reporter
     */
    public Reporter(String username, String password) {
        this(username, "", password, "", "", "", "");
    }
    /**
     * Creates an instance of the report class.
     * @param username the username of the Reporter
     * @param name the name of the Reporter
     * @param password the password of the Reporter
     * @param email the email of the Reporter
     * @param home the home address of the Reporter
     * @param title the title of the Reporter
     * @param phone the phone number of the Reporter
     */
    public Reporter(String username, String name, String password, String email, String home, String title, String phone) {
        _name = name;
        _username = username;
        _password = password;
        _email = email;
        _home = home;
        _title = title;
        _phone = phone;
    }
}
