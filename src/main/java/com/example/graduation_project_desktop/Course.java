package com.example.graduation_project_desktop;

import com.google.gson.annotations.Expose;

public class Course {

    private int id;
    @Expose
    private String course_Name;
    @Expose
    private String course_Description;
    @Expose
    private String cover_Photo;
    @Expose
    private String subject;
    @Expose
    private String topic;

    public Course(int id, String courseName, String courseDescription, String coverPhoto, String subject, String topic) {
        this.id = id;
        this.course_Name = courseName;
        this.course_Description = courseDescription;
        this.cover_Photo = coverPhoto;
        this.subject = subject;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return course_Name;
    }

    public String getCourseDescription() {
        return course_Description;
    }

    public String getCoverPhoto() {
        return cover_Photo;
    }

    public String getSubject() {
        return subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourseName(String courseName) {
        this.course_Name = courseName;
    }

    public void setCourseDescription(String courseDescription) {
        this.course_Description = courseDescription;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.cover_Photo = coverPhoto;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}





