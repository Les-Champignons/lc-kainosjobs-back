package org.kainos.ea.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static org.kainos.ea.auth.Hasher.getHash;

public class LoginRequest {
    private String email;
    private String password;

    @JsonCreator
    public LoginRequest(
            @JsonProperty("email") final String email,
            @JsonProperty("password") final String password
    ) {
        this.email = email;
        this.password = getHash(password, email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
