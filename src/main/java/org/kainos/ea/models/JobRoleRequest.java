package org.kainos.ea.models;
import java.sql.Date;

public class JobRoleRequest {
    private int jobRoleId;
    private String roleName;
    private String location;
    private Date closingDate;
    private String capabilityName;
    private String bandName;


    public JobRoleRequest(int jobRoleId, String roleName, String location,
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

    public void setJobRoleId(int jobRoleId) {
        this.jobRoleId = jobRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(String capabilityName) {
        this.capabilityName = capabilityName;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }
}
