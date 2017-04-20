package com.turboocelots.oasis.models;

import java.io.Serializable;

/**
 * Created by jacobspeed on 2/23/17.
 * Enum class of different water conditions
 */

public enum ConditionOfWater implements Serializable{
    WASTE ("Waste"),
    CLEAR ("Treatable-Clear"),
    MUDDY ("Treatable-Muddy"),
    POTABLE ("Potable");
    /**
     * Default constructor for ConditionOfWater
     * @param waterCondition the condition of the water
     */

    ConditionOfWater(String waterCondition) {}
}
