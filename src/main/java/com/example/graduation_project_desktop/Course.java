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
    private String deadline;
    @Expose
    private String details;
    @Expose
    private String file_url;


    public Course(int id, String name, String description, String cphoto, String subject, String topic, String deadline, String details, String file_url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cphoto = cphoto;
        this.subject = subject;
        this.topic = topic;
        this.deadline = deadline;
        this.details = details;
        this.file_url = file_url;
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

    public String getDeadline() {
        return deadline;
    }

    public String getDetails() {
        return details;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
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

}





