package com.example.graduation_project_desktop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DisplayCourses {
    public static void main(String[] args) {
        List<Course> courses = getCoursesFromBackend("http://localhost:3000/courses");

        for (Course course : courses) {
            System.out.println(course.toString());
        }
    }

    private static List<Course> getCoursesFromBackend(String urlStr) {
        List<Course> courses = new ArrayList<>();

        try {
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String[] lines = response.toString().split("\n");
                for (String line : lines) {
                    String[] fields = line.split(",");
                    if (fields.length >= 5) {
                        Course course = new Course(fields[0], fields[1], fields[2], fields[3], fields[4]);
                        courses.add(course);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Hibakeletkezett az adatok lekérdezésekor: " + e.getMessage());
        }

        return courses;
    }
}