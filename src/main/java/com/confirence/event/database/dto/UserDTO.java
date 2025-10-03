package com.confirence.event.database.dto;



public class UserDTO {
    private String email;
    private String username;
    private String pw;

    public UserDTO() {}

    public UserDTO(String email, String username, String pw) {
        this.email = email;
        this.username = username;
        this.pw = pw;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPw() { return pw; }
    public void setPw(String pw) { this.pw = pw; }
}

