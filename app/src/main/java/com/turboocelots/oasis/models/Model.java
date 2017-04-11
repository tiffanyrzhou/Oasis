package com.turboocelots.oasis.models;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Singleton design pattern to allow access to corresponding
 * Data repositories from each Activity
 */
final public class Model {
    /* Singleton instance */
    private static final Model _instance = new Model();

    /**
     * Returns a singleton instance of the Model
     * @return the singleton instance of the Model
     */
    public static Model getInstance() {return _instance;}

    // Holds a list of all Reporters
    private final List<User> _users;
    private final List<Report> _reports;

    /**
     * Resets the model instance, clearing all Lists
     */
    public void clear() {
        _users.clear();
        _reports.clear();
    }


    /**
     * Makes a new model
     */
    private Model() {
        _users = new ArrayList<>();
        _reports = new ArrayList<>();
    }

    /**
     * Gets the Reporters
     * @return a List of Reporters in the app
     */
    public Collection<User> getUsers() {
        return _users;
    }

    /**
     * Gets the waterSourceReports
      * @return a List of waterSourceReports in the app
     */
    public static List<Report> getReports(){
        return _instance._reports;
    }


    /**
     * Gets just the Water Quality Reports
     * @return the water quality reports
     */
    private List<WaterQualityReport> getWaterQualityReports() {
        List<WaterQualityReport> selectedReports = new ArrayList<>();
        for (Report r: _reports) {
            if (r instanceof WaterQualityReport) {
                selectedReports.add((WaterQualityReport) r);
            }
        }
        return selectedReports;
    }

    public List<WaterQualityReport> generateSelectedReports(int year, double longitude, double lat){
        List<WaterQualityReport> selectedReports = new ArrayList<>();
        for (WaterQualityReport r: getWaterQualityReports()) {
                    if (r.getYear() == year && (Math.abs(r.getReportLat() - lat) < 0.01) &&
                            (Math.abs(r.getReportLong() - longitude) < 0.01)) {
                        selectedReports.add(r);
            }
        }
        return selectedReports;
    }

    /**
     * gets the current user via username
     * @param username the username to compare all users to
     * @return the correct user if found, null otherwise
     */
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
     * @param user the new user to be added
     * @return whether or not the user is successfully added
     */
    public boolean addUser(User user) {
        if(user == null) {
            return false;
        }
        for (User u : _users) {
            if (u.equals(user)) {
                return false;
            }
        }
        _users.add(user);
        return true;
    }

    /**
     * Adds a waterSourceReport to the app
     * @param report the waterSourceReport to be added
     * @return true if successful, false otherwise
     */
    public boolean addReport(Report report) {
        if (report == null) return false;
        if (!report.isValid())  {
            return false;
        }
        if (!_reports.contains(report)) {
            _reports.add(report);
            return true;
        }
        return false;
    }
}
