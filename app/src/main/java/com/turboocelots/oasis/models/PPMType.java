package com.turboocelots.oasis.models;

import java.io.Serializable;

/**
 * Created by jacobspeed on 3/27/17.
 */

public enum PPMType implements Serializable {
    VIRUS ("Viruses"),
    CONTAM ("Contaminants");

    private final String ppmType;

    /**
     * Default constructor for PPMType
     * @param ppmType
     */
    PPMType(String ppmType) { this.ppmType = ppmType; }

    /**
     * Returns a String representation of ppmType
     * @return ppmType
     */
    public String getPPMType() { return ppmType; }
}
