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

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(final String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getSharepointUrl() {
        return sharepointUrl;
    }

    public void setSharepointUrl(final String sharepointUrl) {
        this.sharepointUrl = sharepointUrl;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(final Date closingDate) {
        this.closingDate = closingDate;
    }

    public int getNumberOfOpenPositions() {
        return numberOfOpenPositions;
    }

    public void setNumberOfOpenPositions(final int numberOfOpenPositions) {
        this.numberOfOpenPositions = numberOfOpenPositions;
    }
}
