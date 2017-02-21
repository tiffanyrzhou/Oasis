package com.turboocelots.oasis.models;

/**
 * Represents a single Reporter in model
 *
 * Created by mlin on 2/12/17.
 */

public class Reporter {
    private String _username;
    private String _password;

    public Reporter(String username, String password) {
        _username = username;
        _password = password;
    }

    /* Getters and setters */

    public String getUsername() {
        return _username;
    }

    public String getPassword() {
        return _password;
    }

    public boolean equals(Object o) {
        Reporter r = (Reporter) o;
        return (r.getUsername().equals(_username));
    }
}
