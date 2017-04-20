package com.turboocelots.oasis.models;

import java.io.Serializable;
/**
 * Represents a single Reporter in model
 *
 * Created by mlin on 2/12/17.
 */

public class Reporter extends User implements Serializable {

    /**
     * Two parameter constructor representing basic user
     * @param username username of the new Reporter
     * @param password password of the new Reporter
     */
    public Reporter(String username, String password) {
        super(username, password);
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
     */
    public Reporter(String username, String password, String name,
                    String email, String home, UserTitle title, String phone) {
        super(username, password, name, email, home, title, phone);
    }

    @Override
    public boolean canSubmitWaterSourceReport() {
        return false;
    }

    /**
     * gets the current user type
     * @return the UserType Enum for Reporter
     */
    @Override
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
            return (r.getUsername().equals(this.getUsername()));
        } else {
            return false;
        }
    }
}
