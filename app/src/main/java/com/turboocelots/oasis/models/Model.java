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

    private List<Reporter> _reporters;

    /**
     * Makes a new model
     */
    public Model() {
        _reporters = new ArrayList<Reporter>();
    }

    /**
     * Gets the Reporters
     * @return a List of Reporters in the app
     */
    public List<Reporter> getReporters() {
        return _reporters;
    }

    /**
     * Adds a Reporter to the app. Checks if Reporter is already entered
     */
    public boolean addReporter(Reporter reporter) {
        for (Reporter r : _reporters) {
            if (r.equals(reporter)) {
                return false;
            }
        }
        _reporters.add(reporter);
        return true;
    }

}
