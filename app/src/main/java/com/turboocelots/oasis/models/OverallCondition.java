package com.turboocelots.oasis.models;

import java.io.Serializable;

/**
 * Enum that represents the Overall Condition of the water
 * Three categories: Safe, Treatable, Unsafe
 */

public enum OverallCondition implements Serializable {
    Safe ("Safe"),
    Treatable ("Treatable"),
    Unsafe ("Unsafe");


    private final String name;

    /**
     * default constructor
     * @param name the name of the condition
     */
    OverallCondition(String name) {
        this.name = name;
    }
}
