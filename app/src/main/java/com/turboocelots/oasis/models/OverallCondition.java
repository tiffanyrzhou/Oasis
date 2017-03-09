package com.turboocelots.oasis.models;

import java.io.Serializable;

/**
 * Created by Tiffany on 3/7/17.
 */

public enum OverallCondition implements Serializable {
    Safe ("Safe"),
    Treatable ("Treatable"),
    Unsafe ("Safe");


    private final String name;

    /**
     * default constructor
     * @param name
     */
    OverallCondition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
