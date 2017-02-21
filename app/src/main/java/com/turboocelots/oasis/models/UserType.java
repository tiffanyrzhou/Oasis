package com.turboocelots.oasis.models;

import java.io.Serializable;

/**
 * Created by Tiffany on 2/20/17.
 * Enum Class of different User Types
 */

public enum UserType implements Serializable {
    Reporter ("Reporter"),
    Worker ("Worker"),
    Manager("Manager"),
    Administrator ("Administrator");

    private final String userTypeName;

    /**
     * Default constructer for enum type
     * @param userTypeName
     */
    UserType(String userTypeName){
        this.userTypeName = userTypeName;
    }

    /**
     *  Returns a String representation of the UserType
     * @return the UserTypeName
     */
    private String getUserTypeName(){
        return userTypeName;
    }
}
