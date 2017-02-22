package com.turboocelots.oasis.models;

import java.io.Serializable;
/**
 * Represents a single Reporter in model
 *
 * Created by mlin on 2/12/17.
 */

public class Reporter implements Serializable{
    private String _username;
    private String _password;
    private String _name;
    private String _email;
    private String _home;
    private String _title;
    private String _phone;


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

    /* Getters and setters */

    public String getUsername() {
        return _username;
    }
    public void setUsername(String username) { _username = username; }

    public String getPassword() {
        return _password;
    }
    public void setPassword(String password) { _password = password; }

    public String getName() {return _name;}
    public void setName(String name) {_name = name;}

    public String getEmail() { return _email; }
    public void setEmail(String email) { _email = email; }

    public String getHome() { return _home; }
    public void setHome(String home) { _home = home; }

    public String getTitle() { return _title; }
    public void setTitle(String title) { _title = title; }

    public String getPhone() { return _phone; }
    public void setPhone(String phone) { _phone = phone; }

    public UserType getUserType(){
        return UserType.Reporter;
    }

    public boolean equals(Object o) {
        Reporter r = (Reporter) o;
        return (r.getUsername().equals(_username));
    }
}
