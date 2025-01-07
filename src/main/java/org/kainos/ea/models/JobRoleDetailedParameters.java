package org.kainos.ea.models;

import java.util.Date;

public class JobRoleDetailedParameters {
    private String description;
    private String responsibilities;
    private String sharepointUrl;
    private String roleName;
    private String location;
    private Date closingDate;
    private int numberOfOpenPositions;

    public JobRoleDetailedParameters(
            final String description,
            final String responsibilities,
            final String sharepointUrl,
            final String roleName,
            final String location,
            final Date closingDate,
            final int numberOfOpenPositions
    ) {
        this.description = description;
        this.responsibilities = responsibilities;
        this.sharepointUrl = sharepointUrl;
        this.roleName = roleName;
        this.location = location;
        this.closingDate = closingDate;
        this.numberOfOpenPositions = numberOfOpenPositions;
    }
}
