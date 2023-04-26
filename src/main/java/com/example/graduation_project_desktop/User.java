package com.example.graduation_project_desktop;

import com.google.gson.annotations.Expose;

/**Ez az osztály felelős a felhasználók adatainak tárolásáért.*/
public class User {

    private int id;

    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    private String email;
    @Expose
    private String profile_pic;


    public User(int id, String username, String password, String email, String profile_pic) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profile_pic = profile_pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
