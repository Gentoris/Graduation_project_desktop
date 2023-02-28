package com.example.graduation_project_desktop;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class UpdateCoursesController extends Controller{
    @FXML
    private TextField nevInput;
    @FXML
    private TextField descInput;
    @FXML
    private TextField photoInput;
    @FXML
    private TextField subjectInput;
    @FXML
    private TextField topicInput;
    @FXML
    private Button updateButton;
    private Course course;

    public void setCourse(Course course) {
        this.course = course;
        this.nevInput.setText(course.getCourseName());
        this.descInput.setText(course.getCourseDescription());
        this.photoInput.setText(course.getCoverPhoto());
        this.subjectInput.setText(course.getSubject());
        this.topicInput.setText(course.getTopic());


    }

    @FXML
    private void UpdateClick(ActionEvent actionEvent) {
        String nev = this.nevInput.getText();
        String description = this.descInput.getText();
        String photo = this.photoInput.getText();
        String tant = this.subjectInput.getText();
        String tema = this.topicInput.getText();

        this.course.setCourseName(nev);
        this.course.setCourseDescription(description);
        this.course.setCoverPhoto(photo);
        this.course.setSubject(tant);
        this.course.setTopic(tema);
        Gson gson = new Gson();
        String json = gson.toJson(this.course);
        try {
            String url = App.BASE_URL + "/" + this.course.getId();
            Response response = RequestHandler.put(url, json);
            if(response.getResponseCode() == 200) {
                Stage stage = (Stage) this.updateButton.getScene().getWindow();
                stage.close();
            }else{
                error("Hiba történt a módosítás során", response.getContent());
            }
        } catch (IOException e) {
            error("Nem sikerült a szerverhez csatlakozni");
        }


    }
}


