package com.turboocelots.oasis.models;

import com.turboocelots.oasis.models.constants.UserTitle;
import com.turboocelots.oasis.models.constants.UserType;

/**
 * Represents a Worker
 * Workers can create reports on water purity levels
 */

public class Worker extends Reporter {
    /**
     * Two parameter constructor representing bare minimum Worker
     * @param username the username of the new Worker
     * @param password the password of the new Worker
     */
    public Worker(String username, String password) {
        super(username, password);
    }

    /**
     * Creates an instance of the Worker class.
     * @param username the username of the Worker
     * @param password the password of the Worker
     * @param name the name of the Worker
     * @param email the email of the Worker
     * @param home the home address of the Worker
     * @param title the title of the Worker
     * @param phone the phone number of the Worker
     */
    public Worker(String username, String password, String name, String email,
                  String home, UserTitle title, String phone) {
                    super(username, password, name, email, home, title, phone);
    }

    /**
     * gets current user type
     * @return UserType enum Worker
     */
    @Override
    public UserType getUserType(){
        return UserType.Worker;
    }

    @Override
    public boolean canSubmitQualityReport() {
        return true;
    }
}
