package org.kainos.ea.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicantRequest {
    private String email;
    private String jobRoleName;
    private String etag;

    @JsonCreator
    public ApplicantRequest(
            @JsonProperty("email") final String email,
            @JsonProperty("jobRoleName") final String jobRoleName,
            @JsonProperty("etag") final String etag
    ) {
        this.email = email;
        this.jobRoleName = jobRoleName;
        this.etag = etag;
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
}
