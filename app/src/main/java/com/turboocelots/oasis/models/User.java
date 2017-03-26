package com.turboocelots.oasis.models;

/**
 * Created by caylavinzons on 2/22/17.
 */

public abstract class User {
    protected String _username;
    protected String _password;
    protected String _name;
    protected String _email;
    protected String _home;
    protected UserTitle _title;
    protected String _phone;
    protected UserType _userType;
    protected Boolean _isBanned;
    protected Boolean _isDeleted;

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
    public UserType getUserType(){
        return UserType.Reporter;
    }

    /**
     * gets whether or not the user account is banned
     * @return the boolean for whether or not the account is banned
     */
    public Boolean getIsBanned() { return _isBanned; }

    /**
     * changes the ban status of the user
     * @param isBanned
     */
    public void setIsBanned(Boolean isBanned) { _isBanned = isBanned; }

    /**
     * gets whether or not the account should be deleted
     * @return the boolean for whether or not the account should be deleted
     */
    public Boolean getIsDeleted() { return _isDeleted; }

    /**
     * changes the deletion status of the user
     * @param isDeleted
     */
    public void setIsDeleted(Boolean isDeleted) { _isDeleted = isDeleted; }

    /**
     * Checks equality of users by username
     * @param o the object to compare user to
     * @return whether or not the users are equal
     */
    public boolean equals(Object o) {
        User r = (User) o;
        return (r.getUsername().equals(_username));
    }


}