package com.turboocelots.oasis.models;

import com.turboocelots.oasis.models.Manager;

/**
 * Created by Shane on 2/20/17.
 */

public class Administrator extends User {

    /**
     * Creates an instance of the Administrator class.
     * @param username the username of the Administrator
     * @param password the password of the Administrator
     * @param email the email of the Administrator
     * @param home the home address of the Administrator
     * @param title the title of the Administrator
     * @param phone the phone number of the Administrator
     */
    public Administrator(String username, String name, String password,
                         String email, String home, String title, String phone) {

        _username = username;
        _name = name;
        _password = password;
        _email = email;
        _home = home;
        _title = title;
        _phone = phone;
    }

    public UserType getUserType(){
        return UserType.Administrator;
    }
}
