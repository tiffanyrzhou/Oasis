package com.turboocelots.oasis.models;

/**
 * Created by Shane on 2/20/17.
 */

public class Worker extends Reporter {

    /**
     * Creates an instance of the Worker class.
     * @param username the username of the Worker
     * @param password the password of the Worker
     * @param email the email of the Worker
     * @param home the home address of the Worker
     * @param title the title of the Worker
     * @param phone the phone number of the Worker
     */
    public Worker(String username, String name, String password, String email,
                  String home, String title, String phone) {
                    super(username, name, password, email, home, title, phone);
    }

    public UserType getUserType(){
        return UserType.Worker;
    }
}
