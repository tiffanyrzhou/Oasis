package com.turboocelots.oasis.models;

import com.turboocelots.oasis.models.constants.UserTitle;
import com.turboocelots.oasis.models.constants.UserType;

/**
 * Created by mlin on 4/21/17.
 */

public class UserFactory {
    public static User createUser(UserType userType, String userName, String password) {
        User newUser;
        if (userType == UserType.Administrator) {
            newUser = new Administrator(userName, password);
        } else if (userType == UserType.Manager) {
            newUser = new Manager(userName, password);
        } else if (userType == UserType.Worker) {
            newUser = new Worker(userName, password);
        } else {
            newUser = new Reporter(userName, password);
        }
        newUser.setTitle(UserTitle.NA);
        return newUser;
    }
}
