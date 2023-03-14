package com.example.graduation_project_desktop;

import com.google.gson.annotations.Expose;

public class Course {

    private int id;
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String cphoto;
    @Expose
    private String subject;
    @Expose
    private String topic;

    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    private String email;
    @Expose
    private String profile_pic;


    public Course(int id, String name, String description, String cphoto, String subject, String topic) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cphoto = cphoto;
        this.subject = subject;
        this.topic = topic;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profile_pic = profile_pic;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCphoto() { return cphoto; }

    public String getSubject() {
        return subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCphoto(String cphoto) {this.cphoto = cphoto;}

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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





