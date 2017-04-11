package com.turboocelots.oasis.models;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by mlin on 4/11/17.
 */

/**
 * A repository containing all of the users in the program
 */
final public class UserRepository {
    // Singleton instance
    private static final Collection<User> users = new ArrayList<>();

    /**
     * Private constructor; cannot be invoked
     */
    private UserRepository() {}

    /**
     * Clears the singleton instance
     */
    public static void clear() {
        users.clear();
    }

    /**
     * Gets a user by username
     * @return the User if it exists, or null if it does not
     */
    public static User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Adds a user to the list if it does not already exist
     * @return true if successful, false otherwise
     */
    public static boolean addUser(User newUser) {
        if (users.contains(newUser)) {
            return false;
        }
        users.add(newUser);
        return true;
    }

}
