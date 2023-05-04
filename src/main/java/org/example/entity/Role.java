package org.example.entity;

public enum Role {
    ADMIN("admin"),
    REGULAR_USER("regular user");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}