package com.turboocelots.oasis.models;

import java.io.Serializable;

/**
 * Enum that represents the metrics the PPM represents
 * Currently includes Viruses and Contaminants
 */

public enum PPMType implements Serializable {
    VIRUS ("Viruses"),
    CONTAM ("Contaminants");

    /**
     * Default constructor for PPMType
     * @param ppmType the string representation of ppmType
     */
    PPMType(String ppmType) { }
}
