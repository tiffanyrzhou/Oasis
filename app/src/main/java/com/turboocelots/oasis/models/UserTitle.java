package com.turboocelots.oasis.models;

import java.io.Serializable;

/**
 * Created by slyons094 on 2/21/2017.
 * Enum class of different Titles options
 */

public enum UserTitle implements Serializable {
    Mr ("Mr."),
    Ms ("Ms."),
    Mrs ("Mrs."),
    Dr ("Dr.");

    private final String userTitleName;

    /**
     * Default constructor for UserTitle
     * @param userTitleName
     */
    UserTitle(String userTitleName) { this.userTitleName = userTitleName;}

    /**
     * Returns string represenation of userTitleName
     * @return userTitleName
     */
    public String getUserTitleName() {return userTitleName; }
}
