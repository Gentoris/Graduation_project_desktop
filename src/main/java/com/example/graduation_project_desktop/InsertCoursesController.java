package com.example.graduation_project_desktop;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class InsertCoursesController extends Controller{
    @javafx.fxml.FXML
    private TextField nevInput;
    @javafx.fxml.FXML
    private TextField descInput;
    @javafx.fxml.FXML
    private TextField photoInput;
    @javafx.fxml.FXML
    private TextField subjectInput;
    @javafx.fxml.FXML
    private TextField topicInput;

    @FXML
    private Button insertButton;

    @javafx.fxml.FXML
    public void InsertClick(ActionEvent actionEvent) {
        String nev = this.nevInput.getText();
        String description = this.descInput.getText();
        String photo = this.photoInput.getText();
        String subject = this.subjectInput.getText();
        String topic = this.topicInput.getText();
        if (nev.isEmpty()) {
            warning("Név megadása kötelező");
            return;
        }
        if (description.isEmpty()) {
            warning("Leírás megadása kötelező");
            return;
        }
        if (photo.isEmpty()) {
            warning("Fotó link megadása kötelező");
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
        Course course = new Course(0, nev, description, photo, subject, topic);
        Gson gson = new Gson();
        String json = gson.toJson(course);
        try {
            Response response = RequestHandler.post(App.BASE_URL, json);
            if(response.getResponseCode() == 201) {
                nevInput.setText("");
                descInput.setText("");
                photoInput.setText("");
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
