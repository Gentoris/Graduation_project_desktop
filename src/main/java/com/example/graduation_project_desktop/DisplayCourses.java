package com.example.graduation_project_desktop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DisplayCourses {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:3000/courses"); // Backend URL
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString()); // Megjelenites
        } catch (Exception e) {
            System.out.println("Váratlan hiba történt: " + e.getMessage());
        }
    }
}