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
    public Worker(String username, String password, String name, String email,
                  String home, UserTitle title, String phone, UserType type) {
                    super(username, password, name, email, home, title, phone, type);
    }

    /**
     * gets current user type
     * @return UserType enum Worker
     */
    public UserType getUserType(){
        return UserType.Worker;
    }
}
