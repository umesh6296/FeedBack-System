package com.example.feedback;

public class UserEntity {
    String email,name,password,role,mobile;

    public UserEntity(String email, String name, String password, String role,String mobile) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.mobile=mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
