package com.turboocelots.oasis.models;

import java.io.Serializable;

/**
 * Created by Tiffany on 2/20/17.
 */

public enum UserType implements Serializable {
    Reporter ("Reporter"),
    Worker ("Worker"),
    Manager("Manager"),
    Administrator ("Administrator");


    private final String userTypeName;

    UserType(String userTypeName){
        this.userTypeName = userTypeName;
    }

    private String getUserTypeName(){
        return userTypeName;
    }
}
