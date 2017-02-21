package com.turboocelots.oasis.models;

import com.turboocelots.oasis.models.Worker;

/**
 * Created by jacobspeed on 2/20/17.
 */


public class Manager extends Worker {
    /**
     * Creates an instance of the Manager class
     * @param username the username of the manager
     * @param password the password of the manager
     * @param email the email of the manager
     * @param home the home address of the manager
     * @param title the title of the manager
     * @param phone the phone number of the manager
     */
    public Manager(String username, String password, String email,
                   String home, String title, String phone){
        super(username, password, email, home, title, phone);
    }
}
