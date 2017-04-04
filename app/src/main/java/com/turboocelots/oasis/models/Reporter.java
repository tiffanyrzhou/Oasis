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
        this(username, password, "", "", "", UserTitle.NA, "", UserType.Reporter);
    }

    /**
     * Creates an instance of the report class.
     * @param name the name of the Reporter
     * @param username the username of the Reporter
     * @param password the password of the Reporter
     * @param email the email of the Reporter
     * @param home the home address of the Reporter
     * @param title the title of the Reporter
     * @param phone the phone number of the Reporter
     * @param usertype the usertype of the Reporter
     */
    public Reporter(String username, String password, String name, String email, String home, UserTitle title, String phone, UserType usertype) {
        _name = name;
        _username = username;
        _password = password;
        _email = email;
        _home = home;
        _title = title;
        _phone = phone;
        _userType = UserType.Reporter;
    }

    /**
     * gets the current user type
     * @return the UserType Enum for Reporter
     */
    public UserType getUserType(){
        return UserType.Reporter;
    }

    /**
     * Checks if two Reporters are equal (share the same username)
     * @param o the other object to check equality
     * @return whether or not two reporters are equal
     */
    public boolean equals(Object o) {
        if(o instanceof Reporter) {
            Reporter r = (Reporter) o;
            return (r.getUsername().equals(_username));
        } else {
            return false;
        }
    }
}
