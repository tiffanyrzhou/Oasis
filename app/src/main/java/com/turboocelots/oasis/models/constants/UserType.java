package com.turboocelots.oasis.models.constants;

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


    /**
     * Default constructor for enum type
     */
    UserType(String userTypeName){}
}
