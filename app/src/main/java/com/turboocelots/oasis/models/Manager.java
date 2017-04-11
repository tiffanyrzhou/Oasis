package com.turboocelots.oasis.models;

import com.turboocelots.oasis.models.Worker;


/**
 * A manager user
 * Managers can do anything a Worker can do.
 * Can view historical reports and trends of water purity.
 * Can delete reports they deem inaccurate
 */

public class Manager extends Worker {

    /**
     * Two parameter constructor representing bare minimum
     * @param username the username of the new Manager
     * @param password the password of the new Manager
     */
    public Manager(String username, String password) {
        super(username, password);
    }
    /**
     * Creates an instance of the Manager class
     * @param username the username of the manager
     * @param password the password of the manager
     * @param name the name of the manager
     * @param email the email of the manager
     * @param home the home address of the manager
     * @param title the title of the manager
     * @param phone the phone number of the manager
     */
    public Manager(String username, String password, String name, String email,
                   String home, UserTitle title, String phone){
        super(username, password, name, email, home, title, phone);
    }

    /**
     * Returns the type of user
     * @return the UserType Enum for Manager
     */
    @Override
    public UserType getUserType(){
        return UserType.Manager;
    }
}
