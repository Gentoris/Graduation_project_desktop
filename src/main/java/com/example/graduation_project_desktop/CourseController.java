package com.example.graduation_project_desktop;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CourseController extends Controller{
    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Course> courseTable;
    @FXML
    private TableColumn<Course, Integer> idCol;
    @FXML
    private TableColumn<Course, String> nevCol;
    @FXML
    private TableColumn<Course, String> descCol;
    @FXML
    private TableColumn<Course, String> photoCol;
    @FXML
    private TableColumn<Course, String> subjectCol;
    @FXML
    private TableColumn<Course, String> topicCol;

    @FXML
    private void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nevCol.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("course_description"));
        photoCol.setCellValueFactory(new PropertyValueFactory<>("cover_photo"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        topicCol.setCellValueFactory(new PropertyValueFactory<>("topic"));
        Platform.runLater(() -> {
            try {
                loadCoursesFromServer();
            } catch (IOException e) {
                error("Hiba történt az adatok lekérése során", e.getMessage());

                Platform.exit();
            }
        });
    }

    private void loadCoursesFromServer() throws IOException {
        Response response = RequestHandler.get(App.BASE_URL);
        String content = response.getContent();
        Gson converter = new Gson();
        Course[] course_db = converter.fromJson(content, Course[].class);
        courseTable.getItems().clear();
        for (Course course : course_db) {
            courseTable.getItems().add(course);
        }
    }

    @FXML
    public void insertClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("insert-courses-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            stage.setTitle("Új kurzus");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                try {
                    loadCoursesFromServer();
                } catch (IOException e) {
                    error("Nem sikerült kapcsolódni a szerverhez");
                }
            });
            stage.show();
        } catch (IOException e) {
            error("Hiba történt az űrlap betöltése során", e.getMessage());
        }
    }

    @FXML
    public void updateClick(ActionEvent actionEvent) {
        Course SelectedCourse =  courseTable.getSelectionModel().getSelectedItem();
        if (SelectedCourse == null) {
            warning("Törléshez előbb válasszon ki egy elemet!");
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("update-courses-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            UpdateCoursesController controller = fxmlLoader.getController();
            controller.setCourse(SelectedCourse);
            stage.setTitle("Update "+ SelectedCourse.getCourseName());
            stage.setScene(scene);
            stage.setOnHidden(event -> {
                try {
                    loadCoursesFromServer();
                } catch (IOException e) {
                    error("Nem sikerült kapcsolódni a szerverhez");
                }
            });
            stage.show();
        } catch (IOException e) {
            error("Hiba történt a betöltés során", e.getMessage());
        }
    }

    @FXML
    public void deleteClick(ActionEvent actionEvent) {
        Course SelectedCourse =  courseTable.getSelectionModel().getSelectedItem();
        if (SelectedCourse == null) {
            warning("A törléshez előbb válasszon ki egy elemet!");
            return;
        }
        Optional<ButtonType> optionalButtonType =
                alert(Alert.AlertType.CONFIRMATION, "Biztos?",
                        "Biztos, hogy törölni szeretné ezt a kurzust?: "
                                + SelectedCourse.getCourseName() +  " description: " + SelectedCourse.getCourseDescription(),
                        "");
        if (optionalButtonType.isPresent() &&
                optionalButtonType.get().equals(ButtonType.OK)
        ) {
            String url = App.BASE_URL + "/" + SelectedCourse.getId();
            try {
                RequestHandler.delete(url);
                loadCoursesFromServer();
            } catch (IOException e) {
                error("Nem sikerült kapcsolódni a szerverhez");
            }
        }
    }
}

