package org.kainos.ea.models;

public class JobRoleDetailedRequest {
    private int jobRoleId;
    private String statusName;
    private String bandName;
    private String capabilityName;
    private JobRoleDetailedParameters jobRoleDetailedParameters;

    public JobRoleDetailedRequest(
            final int jobRoleId,
            final String statusName,
            final String bandName,
            final String capabilityName,
            final JobRoleDetailedParameters jobRoleDetailedParameters
    ) {
        this.jobRoleId = jobRoleId;
        this.statusName = statusName;
        this.bandName = bandName;
        this.capabilityName = capabilityName;
        this.jobRoleDetailedParameters = jobRoleDetailedParameters;
    }

    public int getJobRoleId() {
        return jobRoleId;
    }

    public void setJobRoleId(final int jobRoleId) {
        this.jobRoleId = jobRoleId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(final String statusName) {
        this.statusName = statusName;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(final String bandName) {
        this.bandName = bandName;
    }

    public JobRoleDetailedParameters getJobRoleInformationParameters() {
        return jobRoleDetailedParameters;
    }

    public void setJobRoleInformationParameters(
            final JobRoleDetailedParameters jobRoleDetailedParameters
    ) {
        this.jobRoleDetailedParameters = jobRoleDetailedParameters;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(final String capabilityName) {
        this.capabilityName = capabilityName;
    }

    public JobRoleDetailedParameters getJobRoleDetailedParameters() {
        return jobRoleDetailedParameters;
    }

    public void setJobRoleDetailedParameters(
            final JobRoleDetailedParameters jobRoleDetailedParameters) {
        this.jobRoleDetailedParameters = jobRoleDetailedParameters;
    }
}
