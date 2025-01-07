package org.kainos.ea.models;

public class Band {
    private int bandId;
    private String bandName;

    public Band(final int bandId, final String bandName) {
        this.bandId = bandId;
        this.bandName = bandName;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(final int bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(final String bandName) {
        this.bandName = bandName;
    }
}
