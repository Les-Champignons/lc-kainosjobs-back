package org.kainos.ea.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicantStatusRequest {
    private String status;

    @JsonCreator
    public ApplicantStatusRequest(
            @JsonProperty("status") final String status
    ) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}

