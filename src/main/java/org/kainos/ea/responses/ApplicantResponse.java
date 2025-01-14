package org.kainos.ea.responses;

public class ApplicantResponse {
    private String email;
    private String jobRoleName;
    private String etag;
    private String status;

    public ApplicantResponse(
            final String email,
            final String jobRoleName,
            final String etag,
            final String status
    ) {
        this.email = email;
        this.jobRoleName = jobRoleName;
        this.etag = etag;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getJobRoleName() {
        return jobRoleName;
    }

    public void setJobRoleName(final String jobRoleName) {
        this.jobRoleName = jobRoleName;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(final String etag) {
        this.etag = etag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}

