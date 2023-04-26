package com.example.graduation_project_desktop;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

/**Ez az osztály felelős a kurzusok bevitelének működéséhez. Itt történnek validációk, hogy hiba üzenetet kapj pl
üres beviteli mezőkért. A validációk szinkronban vannak a backenddel, így ha itt nincs is validáció megírva, a program
 tudja a backendben megírt validációkat és ezt a program a hibaüzenetben közvetlen tudatja is a felhasználóval.*/

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
    private TextField deadlineInput;
    @javafx.fxml.FXML
    private TextField detailsInput;
    @javafx.fxml.FXML
    private TextField file_urlInput;

    @FXML
    private Button insertButton;

    /**Validálás*/
    @FXML
    public void InsertCourseClick(ActionEvent actionEvent) {
        String name = this.nameInput.getText();
        String description = this.descriptionInput.getText();
        String cphoto = this.cphotoInput.getText();
        String subject = this.subjectInput.getText();
        String topic = this.topicInput.getText();
        String deadline = this.deadlineInput.getText();
        String details = this.detailsInput.getText();
        String file_url = this.file_urlInput.getText();

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
        if (deadline.isEmpty()) {
            warning("Határidő megadása kötelező");
            return;
        }
        if (details.isEmpty()) {
            warning("Részletek megadása kötelező");
            return;
        }
        if (file_url.isEmpty()) {
            warning("Fájl link megadása kötelező");
            return;
        }
        Course course = new Course(0, name, description, cphoto, subject, topic, deadline, details, file_url);
        Gson gson = new Gson();
        String json = gson.toJson(course);
        /**Ha a bevitel sikeres, üressé teszi a mezőket további adatok betöltéséhez, és a program bezárása során
        a felhasználó által beírt adatokkal,hozzáadja az új adatokat az adatbázishoz.*/
        try {
            Response response = RequestHandler.post(App.Insert_Course_URL, json);
            if(response.getResponseCode() == 201) {
                nameInput.setText("");
                descriptionInput.setText("");
                cphotoInput.setText("");
                subjectInput.setText("");
                topicInput.setText("");
                deadlineInput.setText("");
                detailsInput.setText("");
                file_urlInput.setText("");
            }else{
                error("Hiba történt a Hozzáadás során", response.getContent());
            }
        } catch (IOException e) {
            error("Nem sikerült a szerverhez csatlakozni");
        }
    }


}
