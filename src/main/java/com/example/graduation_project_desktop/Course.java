package com.example.graduation_project_desktop;

public class Course {
    private String courseName;
    private String courseDescription;
    private String coverPhoto;
    private String subject;
    private String topic;

    public Course(String courseName, String courseDescription, String coverPhoto, String subject, String topic) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.coverPhoto = coverPhoto;
        this.subject = subject;
        this.topic = topic;
    }

    @Override
    public String toString() {
        return " " + courseName + "\n" +
                " " + courseDescription + "\n" +
                " " + coverPhoto + "\n" +
                " " + subject + "\n" +
                " " + topic;
    }
}

