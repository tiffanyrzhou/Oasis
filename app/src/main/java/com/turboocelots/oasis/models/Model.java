package com.turboocelots.oasis.models;
import java.util.ArrayList;
import java.util.Calendar;
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
    private List<Report> _reports;
    private List<WaterQualityReport> selectedReport;


    /**
     * Makes a new model
     */
    public Model() {
        _users = new ArrayList<User>();
        _reports = new ArrayList<Report>();
        selectedReport = new ArrayList<WaterQualityReport>();
    }

    /**
     * Gets the Reporters
     * @return a List of Reporters in the app
     */
    public List<User> getUsers() {
        return _users;
    }

    /**
     * Gets the waterSourceReports
      * @return a List of waterSourceReports in the app
     */
    public  List<Report> getReports(){
        return _reports;
    }

    public void generate_reports_Selected(int year, double longtitude, double lat){
        for (Report r: _reports) {
            if(r.getDate().get(Calendar.YEAR) == year
                    && r.getReportLat()== lat && r.getReportLong() == longtitude && r instanceof WaterQualityReport){
                    selectedReport.add((WaterQualityReport) r);
            }
        }
    }

    public List<WaterQualityReport> get_reports_Selected(){
        return selectedReport;
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
     */
    public void addReport(Report report) {

        _reports.add(report);

    }


}
