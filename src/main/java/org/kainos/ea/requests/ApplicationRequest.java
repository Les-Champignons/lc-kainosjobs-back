package org.kainos.ea.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationRequest {
    private String email;
    private String roleName;
    private String etag;
    private String status;

    @JsonCreator
    public ApplicationRequest(
            @JsonProperty("email") final String email,
            @JsonProperty("roleName") final String roleName,
            @JsonProperty("etag") final String etag,
            @JsonProperty("status") final String status
    ) {
        this.email = email;
        this.roleName = roleName;
        this.etag = etag;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
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
