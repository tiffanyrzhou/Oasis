package com.turboocelots.oasis.models.constants;

import java.io.Serializable;

/**
 * Enum that represents the water type.
 * Currently includes Bottled, Well, Stream, Lake, Spring, Other
 */

public enum TypeOfWater implements Serializable {
    BOTTLED ("Bottled"),
    WELL ("Well"),
    STREAM ("Stream"),
    LAKE ("Lake"),
    SPRING ("Spring"),
    OTHER ("Other");
    /**
     * Default constructor for TypeOfWater
     * @param waterType the String representation of the waterType
     */
    TypeOfWater(String waterType) {}
}
