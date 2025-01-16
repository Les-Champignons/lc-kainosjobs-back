package org.kainos.ea.models;

public class Applicant {
    private int applicantId;
    private String email;
    private String jobRoleName;
    private String etag;
    private String status;

    public Applicant(
            final int applicantId,
            final String email,
            final String jobRoleName,
            final String etag,
            final String status
    ) {
        this.applicantId = applicantId;
        this.email = email;
        this.jobRoleName = jobRoleName;
        this.etag = etag;
        this.status = status;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(final int applicantId) {
        this.applicantId = applicantId;
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
