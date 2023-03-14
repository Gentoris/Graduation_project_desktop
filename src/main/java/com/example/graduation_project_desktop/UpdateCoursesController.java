package com.example.graduation_project_desktop;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class UpdateCoursesController extends Controller{
    @FXML
    private TextField nameInput;
    @FXML
    private TextField descriptionInput;
    @FXML
    private TextField cphotoInput;
    @FXML
    private TextField subjectInput;
    @FXML
    private TextField topicInput;
    @FXML
    private Button updateButton;
    private Course course;

    public void setCourse(Course course) {
        this.course = course;
        this.nameInput.setText(course.getName());
        this.descriptionInput.setText(course.getDescription());
        this.cphotoInput.setText(course.getCphoto());
        this.subjectInput.setText(course.getSubject());
        this.topicInput.setText(course.getTopic());


    }

    @FXML
    private void UpdateClick(ActionEvent actionEvent) {
        String name = this.nameInput.getText();
        String description = this.descriptionInput.getText();
        String cphoto = this.cphotoInput.getText();
        String tant = this.subjectInput.getText();
        String tema = this.topicInput.getText();

        this.course.setName(name);
        this.course.setDescription(description);
        this.course.setCphoto(cphoto);
        this.course.setSubject(tant);
        this.course.setTopic(tema);
        Gson gson = new Gson();
        String json = gson.toJson(this.course);
        try {
            String url = App.Course_URL + "/" + this.course.getId();
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


