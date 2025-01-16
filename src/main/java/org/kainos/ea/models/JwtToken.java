package org.kainos.ea.models;

import javax.security.auth.Subject;
import java.security.Principal;

public class JwtToken implements Principal {
    UserRole userRole;
    User user;

    public JwtToken(final UserRole userRole, final User user) {
        setUserRole(userRole);
        setUser(user);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(final Subject subject) {
        return Principal.super.implies(subject);
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(final UserRole userRole) {
        this.userRole = userRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
