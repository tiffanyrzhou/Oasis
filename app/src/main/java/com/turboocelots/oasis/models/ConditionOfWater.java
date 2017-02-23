package com.turboocelots.oasis.models;

import java.io.Serializable;

/**
 * Created by jacobspeed on 2/23/17.
 * Enum class of different water conditions
 */

public enum ConditionOfWater implements Serializable{
    WA ("Waste"),
    TRC ("Treatable-Clear"),
    TRM ("Treatable-Muddy"),
    PO ("Potable");

    private final String waterCondition;

    /**
     * Default constructor for ConditionOfWater
     * @param waterCondition
     */

    ConditionOfWater(String waterCondition) {this.waterCondition = waterCondition;}

    /**
     * Returns string representation of waterCondition
     * @return waterCondition
     */

    public String getWaterCondition() {return waterCondition;}
}