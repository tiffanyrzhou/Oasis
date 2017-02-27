package com.turboocelots.oasis.models;

import java.io.Serializable;

/**
 * Created by Shane on 2/23/17.
 */

public enum TypeOfWater implements Serializable {
    BOTTLED ("Bottled"),
    WELL ("Well"),
    STREAM ("Stream"),
    LAKE ("Lake"),
    SPRING ("Spring"),
    OTHER ("Other");

    private final String waterType;

    /**
     * Default constructor for TypeOfWater
     * @param waterType
     */
    TypeOfWater(String waterType) { this.waterType = waterType; }

    /**
     * Returns a String representation of waterType
     * @return waterType
     */
    public String getWaterType() { return waterType; }
}
