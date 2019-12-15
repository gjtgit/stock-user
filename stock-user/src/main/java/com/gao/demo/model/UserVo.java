package com.gao.demo.model;

public class UserVo {
    private Long id;
    private String username;
    private String password;
    private String userType;
    private String email;
    private String mobile;
    private int confirmed;
    
    public UserVo() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public int getConfirmed() {
        return confirmed;
    }
    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }
    
}
