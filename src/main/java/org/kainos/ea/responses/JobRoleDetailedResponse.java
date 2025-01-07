package org.kainos.ea.responses;

import org.kainos.ea.models.JobRoleDetailedParameters;

public class JobRoleDetailedResponse {
    private int jobRoleId;
    private String statusName;
    private String bandName;
    private String capabilityName;
    private JobRoleDetailedParameters jobRoleDetailedParameters;

    public JobRoleDetailedResponse(
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

    public JobRoleDetailedParameters getJobRoleDetailedParameters() {
        return jobRoleDetailedParameters;
    }

    public void setJobRoleDetailedParameters(
            final JobRoleDetailedParameters jobRoleDetailedParameters) {
        this.jobRoleDetailedParameters = jobRoleDetailedParameters;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(final String capabilityName) {
        this.capabilityName = capabilityName;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(final String bandName) {
        this.bandName = bandName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(final String statusName) {
        this.statusName = statusName;
    }

    public int getJobRoleId() {
        return jobRoleId;
    }

    public void setJobRoleId(final int jobRoleId) {
        this.jobRoleId = jobRoleId;
    }
}
