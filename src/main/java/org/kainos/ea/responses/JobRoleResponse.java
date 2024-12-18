package org.kainos.ea.responses;

import java.util.Date;

public class JobRoleResponse {
    private int jobRoleId;
    private String roleName;
    private String location;
    private Date closingDate;
    private String capabilityName;
    private String bandName;

    public JobRoleResponse(int jobRoleId, String roleName, String location,
                           Date closingDate, String capabilityName,
                           String bandName) {
        this.jobRoleId = jobRoleId;
        this.roleName = roleName;
        this.location = location;
        this.closingDate = closingDate;
        this.capabilityName = capabilityName;
        this.bandName = bandName;
    }

    public int getJobRoleId() {
        return jobRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getLocation() {
        return location;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public String getBandName() {
        return bandName;
    }
}
