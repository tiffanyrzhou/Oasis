package com.turboocelots.oasis.models;

/**
 * Abstract class that describes a generic User
 */

public abstract class User {
    private String _username;
    private String _password;
    private String _name;
    private String _email;
    private String _home;
    private UserTitle _title;
    private String _phone;

    /**
     * Two parameter constructor representing bare minimum of USer
     * @param username the username of the User
     * @param password the password of the User
     */

    User (String username, String password) {
        this(username, password, "", "", "", UserTitle.NA, "");
    }

    User (String username, String password, String name,
                 String email, String home, UserTitle title, String phone) {
        _name = name;
        _username = username;
        _password = password;
        _email = email;
        _home = home;
        _title = title;
        _phone = phone;
    }

    /* Getters and setters */

    /**
     * gets the username
     * @return the current user's username
     */
    public String getUsername() {
        return _username;
    }

    /**
     * changed the username
     * @param username the new username
     */
    public void setUsername(String username) { _username = username; }

    /**
     * gets the password
     * @return the current user's password
     */
    public String getPassword() {
        return _password;
    }

    /**
     * changes the password
     * @param password the new password
     */
    public void setPassword(String password) { _password = password; }

    /**
     * gets the name
     * @return the current user's name
     */
    public String getName() {return _name;}

    /**
     * changes the name
     * @param name the new name
     */
    public void setName(String name) {_name = name;}

    /**
     * gets the email address
     * @return the current user's email address
     */
    public String getEmail() { return _email; }

    /**
     * changes the email address
     * @param email the new email address
     */
    public void setEmail(String email) { _email = email; }

    /**
     * gets the home address
     * @return the current user's home address
     */
    public String getHome() { return _home; }

    /**
     * Sets the home address
     * @param home the new Home Address
     */
    public void setHome(String home) { _home = home; }

    /**
     * gets the title
     * @return the current user's title
     */
    public UserTitle getTitle() { return _title; }

    /**
     * change the title
     * @param title the new user's title
     */
    public void setTitle(UserTitle title) { _title = title; }

    /**
     * gets the phone number
     * @return the current user's phone number
     */
    public String getPhone() { return _phone; }

    /**
     * change phone number
     * @param phone the new phone number
     */
    public void setPhone(String phone) { _phone = phone; }

    /**
     * gets the current user type
     * @return the Enum type for the user
     */
    public abstract UserType getUserType();


    /**
     * Permission for being able to submit Quality Reports
     * Currently held by Workers
     */

    public boolean canSubmitQualityReport() {
        return false;
    }

    /**
     * Permission for being able to submit Water Source Reports
     * Currently held by Reporters
     */
    public boolean canSubmitWaterSourceReport() {
        return false;
    }

    /**
     * Checks equality of users by username
     * @param o the object to compare user to
     * @return whether or not the users are equal
     */
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        User r = (User) o;
        return (r.getUsername().equals(_username));
    }
}