package com.turboocelots.oasis.models;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by mlin on 2/12/17.
 *
 * Singleton design pattern to allow access to Model from each controller
 */

public class Model {
    /* Singleton instance */
    public static final Model _instance = new Model();
    public static Model getInstance() {return _instance;}

    // Holds a list of all Reporters

    private List<User> _users;

    /**
     * Makes a new model
     */
    public Model() {
        _users = new ArrayList<User>();
    }

    /**
     * Gets the Reporters
     * @return a List of Reporters in the app
     */
    public List<User> getUsers() {
        return _users;
    }

    public User getUser(String username) {
        for (User user : _users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Adds a Reporter to the app. Checks if Reporter is already entered
     */
    public boolean addUser(User user) {
        for (User u : _users) {
            if (u.equals(user)) {
                return false;
            }
        }
        _users.add(user);
        return true;
    }

}
