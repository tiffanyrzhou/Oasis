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
    protected String _title;
    protected String _phone;
    protected UserType _userType;

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
        User r = (User) o;
        return (r.getUsername().equals(_username));
    }


}