package org.kainos.ea.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.jsonwebtoken.Jwts;
import org.kainos.ea.models.JwtToken;
import org.kainos.ea.models.UserRole;

import javax.crypto.SecretKey;
import java.util.Optional;

public class JwtAuthenticator implements Authenticator<String, JwtToken> {
    SecretKey key;

    public JwtAuthenticator(final SecretKey key) {
        this.key = key;
    }

    @Override
    public Optional<JwtToken> authenticate(final String token) throws
            AuthenticationException {
        System.out.println("1");
        try {
            Integer roleId = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get("Role", Integer.class);

            JwtToken jwtToken = new JwtToken(new UserRole(roleId), null);

            return Optional.of(jwtToken);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}
