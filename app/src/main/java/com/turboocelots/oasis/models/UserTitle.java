package com.turboocelots.oasis.models;

import java.io.Serializable;

/**
 * Created by slyons094 on 2/21/2017.
 * Enum class of different Titles options
 */

public enum UserTitle implements Serializable {
    NA ("NA"),
    Mr ("Mr."),
    Ms ("Ms."),
    Mrs ("Mrs."),
    Dr ("Dr.");

    private final String userTitleName;

    /**
     * Default constructor for UserTitle
     * @param userTitleName the String that represents userTitleName
     */
    UserTitle(String userTitleName) { this.userTitleName = userTitleName;}
}
