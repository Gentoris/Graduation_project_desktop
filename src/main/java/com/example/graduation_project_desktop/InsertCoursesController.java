package com.example.graduation_project_desktop;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class InsertCoursesController extends Controller{
    @javafx.fxml.FXML
    private TextField nameInput;
    @javafx.fxml.FXML
    private TextField descriptionInput;
    @javafx.fxml.FXML
    private TextField cphotoInput;
    @javafx.fxml.FXML
    private TextField subjectInput;
    @javafx.fxml.FXML
    private TextField topicInput;
    @javafx.fxml.FXML
    private TextField dlInput;

    @FXML
    private Button insertButton;

    @FXML
    public void InsertCourseClick(ActionEvent actionEvent) {
        String name = this.nameInput.getText();
        String description = this.descriptionInput.getText();
        String cphoto = this.cphotoInput.getText();
        String subject = this.subjectInput.getText();
        String topic = this.topicInput.getText();

        if (name.isEmpty()) {
            warning("Név megadása kötelező");
            return;
        }
        if (description.isEmpty()) {
            warning("Leírás megadása kötelező");
            return;
        }

        if (subject.isEmpty()) {
            warning("Tantárgy megadása kötelező");
            return;
        }
        if (topic.isEmpty()) {
            warning("Témakör megadása kötelező");
            return;
        }
        Course course = new Course(0, name, description, cphoto, subject, topic);
        Gson gson = new Gson();
        String json = gson.toJson(course);
        try {
            Response response = RequestHandler.post(App.Insert_Course_URL, json);
            if(response.getResponseCode() == 201) {
                nameInput.setText("");
                descriptionInput.setText("");
                cphotoInput.setText("");
                subjectInput.setText("");
                topicInput.setText("");
            }else{
                error("Hiba történt a Hozzáadás során", response.getContent());
            }
        } catch (IOException e) {
            error("Nem sikerült a szerverhez csatlakozni");
        }
    }
}
