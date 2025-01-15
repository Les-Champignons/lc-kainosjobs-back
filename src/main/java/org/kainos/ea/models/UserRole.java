package org.kainos.ea.models;

import java.util.Map;

public class UserRole {
    public static final String USER = "User";
    public static final String ADMIN = "Admin";
    private int roleId;

    private static final Map<Integer, String> ROLES_MAP = Map.of(
            0, USER,
            1, ADMIN
    );

    public UserRole(final int roleId) {
        setRoleId(roleId);
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(final int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        if (!ROLES_MAP.containsKey(roleId)) {
            return "Guest";
        }
        return ROLES_MAP.get(getRoleId());
    }
}
