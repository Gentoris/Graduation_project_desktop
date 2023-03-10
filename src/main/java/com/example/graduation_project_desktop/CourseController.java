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
    private TableView<Course> userTable;
    @FXML
    private TableColumn<Course, String> nameCol;
    @FXML
    private TableColumn<Course, String> pwCol;
    @FXML
    private TableColumn<Course, String> emailCol;
    @FXML
    private TableColumn<Course, String> pictureCol;
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
        nevCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        photoCol.setCellValueFactory(new PropertyValueFactory<>("cphoto"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        topicCol.setCellValueFactory(new PropertyValueFactory<>("topic"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        pwCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        pictureCol.setCellValueFactory(new PropertyValueFactory<>("profile_pic"));

        Platform.runLater(() -> {
            try {
                loadCoursesFromServer();
            } catch (IOException e) {
                error("Hiba t??rt??nt az adatok lek??r??se sor??n", e.getMessage());

                Platform.exit();
            }
            try {
                loadUsersFromServer();
            } catch (IOException e) {
                error("Hiba t??rt??nt az adatok lek??r??se sor??n", e.getMessage());

                Platform.exit();
            }
        });
    }

    private void loadCoursesFromServer() throws IOException {
        Response response = RequestHandler.get(App.Course_URL);
        String content = response.getContent();
        Gson converter = new Gson();
        Course[] course_db = converter.fromJson(content, Course[].class);
        courseTable.getItems().clear();
        for (Course course : course_db) {
            courseTable.getItems().add(course);
        }
    }

    private void loadUsersFromServer() throws IOException {
        Response response = RequestHandler.get(App.User_URL);
        String content = response.getContent();
        Gson converter = new Gson();
        Course[] course_db = converter.fromJson(content, Course[].class);
        userTable.getItems().clear();
        for (Course course : course_db) {
            userTable.getItems().add(course);
        }
    }

    @FXML
    public void insertCourseClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("insert-courses-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            stage.setTitle("??j kurzus");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                try {
                    loadCoursesFromServer();
                } catch (IOException e) {
                    error("Nem siker??lt kapcsol??dni a szerverhez");
                }
            });
            stage.show();
        } catch (IOException e) {
            error("Hiba t??rt??nt az ??rlap bet??lt??se sor??n", e.getMessage());
        }
    }

    @FXML
    public void updateCourseClick(ActionEvent actionEvent) {
        Course SelectedCourse =  courseTable.getSelectionModel().getSelectedItem();
        if (SelectedCourse == null) {
            warning("T??rl??shez el??bb v??lasszon ki egy elemet!");
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("update-courses-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            UpdateCoursesController controller = fxmlLoader.getController();
            controller.setCourse(SelectedCourse);
            stage.setTitle("Update "+ SelectedCourse.getName());
            stage.setScene(scene);
            stage.setOnHidden(event -> {
                try {
                    loadCoursesFromServer();
                } catch (IOException e) {
                    error("Nem siker??lt kapcsol??dni a szerverhez");
                }
            });
            stage.show();
        } catch (IOException e) {
            error("Hiba t??rt??nt a bet??lt??s sor??n", e.getMessage());
        }
    }

    @FXML
    public void deleteCourseClick(ActionEvent actionEvent) {
        Course SelectedCourse =  courseTable.getSelectionModel().getSelectedItem();
        if (SelectedCourse == null) {
            warning("A t??rl??shez el??bb v??lasszon ki egy elemet!");
            return;
        }
        Optional<ButtonType> optionalButtonType =
                alert(Alert.AlertType.CONFIRMATION, "Biztos?",
                        "Biztos, hogy t??r??lni szeretn?? ezt a kurzust?: "
                                + SelectedCourse.getName(),
                        "");
        if (optionalButtonType.isPresent() &&
                optionalButtonType.get().equals(ButtonType.OK)
        ) {
            String url = App.Course_URL + "/" + SelectedCourse.getId();
            try {
                RequestHandler.delete(url);
                loadCoursesFromServer();
            } catch (IOException e) {
                error("Nem siker??lt kapcsol??dni a szerverhez");
            }
        }
    }


}

