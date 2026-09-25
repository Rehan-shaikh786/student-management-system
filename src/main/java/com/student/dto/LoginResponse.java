package com.student.dto;

public class LoginResponse {

    private int status;
    private String message;
    private String token;
    private String email;
    private String role;

    public LoginResponse() {
    }

    public LoginResponse(
            int status,
            String message,
            String token,
            String email,
            String role) {

        this.status = status;
        this.message = message;
        this.token = token;
        this.email = email;
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}