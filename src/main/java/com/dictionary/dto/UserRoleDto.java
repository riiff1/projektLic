package com.dictionary.dto;

import java.io.Serializable;

public class UserRoleDto implements Serializable {
    private static final long serialVersionUID = 1281328099718572512L;
    private long userRoldeId;
    private long userId;
    private String role;

    public long getUserRoldeId() {
        return userRoldeId;
    }

    public void setUserRoldeId(long userRoldeId) {
        this.userRoldeId = userRoldeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
