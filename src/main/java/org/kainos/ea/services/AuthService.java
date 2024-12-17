package org.kainos.ea.services;

import io.jsonwebtoken.Jwts;
import org.kainos.ea.daos.AuthDao;
import org.kainos.ea.exceptions.Entity;
import org.kainos.ea.exceptions.InvalidException;
import org.kainos.ea.models.User;
import org.kainos.ea.requests.LoginRequest;

import java.security.Key;
import java.sql.SQLException;
import java.util.Date;

public class AuthService {
    private final AuthDao authDao;
    private final Key key;

    static final int EXPIRATION_TIME = 8 * 60 * 60 * 1000;

    public AuthService(final AuthDao authDao, final Key key) {
        this.authDao = authDao;
        this.key = key;
    }

    public String login(final LoginRequest loginRequest)
            throws SQLException, InvalidException {
        User user = authDao.getUser(loginRequest);

        if (user == null) {
            throw new InvalidException(Entity.USER, "Does not exist");
        }

        return generateJwtToken(user);
    }

    private String generateJwtToken(final User user) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(
                        new Date(System.currentTimeMillis() + EXPIRATION_TIME)
                )
                .claim("Role", user.getRoleId())
                .claim("User", user)
                .subject(user.getEmail())
                .issuer("KainosJobs")
                .signWith(key)
                .compact();
    }
}
