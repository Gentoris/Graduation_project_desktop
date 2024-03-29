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

/**
 *Az AdminController feladata, hogy az alkalmazás betöltse az adatokat a szerverről, melyhez társul egy metódus is.
 Ezenkívül felelős azért, hogy biztosítsa a szükséges metódusokat a gombok működéséhez.*/


public class AdminController extends Controller{
    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Course> courseTable;
    @FXML
    private TableView<User> userTable;
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
    private TableColumn<Course, String> deadlineCol;
    @FXML
    private TableColumn<Course, String> detailsCol;
    @FXML
    private TableColumn<Course, String> file_urlCol;
    @FXML
    private Button InsertUserButton;
    @FXML
    private Button deleteUserButton;
    @FXML
    private Button updateUserButton;
    @FXML
    private Tab CourseTabPage;
    @FXML
    private Tab UserTabPage;
    @FXML
    private TableColumn useridCol;
    @FXML
    private TabPane tabPane;

    /**Adatok inicializálása*/
    @FXML
    private void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nevCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        photoCol.setCellValueFactory(new PropertyValueFactory<>("cphoto"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        topicCol.setCellValueFactory(new PropertyValueFactory<>("topic"));
        deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        detailsCol.setCellValueFactory(new PropertyValueFactory<>("details"));
        file_urlCol.setCellValueFactory(new PropertyValueFactory<>("file_url"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        pwCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        pictureCol.setCellValueFactory(new PropertyValueFactory<>("profile_pic"));

        Platform.runLater(() -> {

            /**Kurzusok adatainak betöltése a szerverről*/

            try {
                System.out.println("Kurzus adatok betöltése");
                loadCoursesFromServer();
            } catch (IOException e) {
                error("Hiba történt az adatok lekérése során", e.getMessage());

                Platform.exit();
            }

            /**Felhasználok adatainak betöltése a szerverről*/

            try {
                loadUsersFromServer();
            } catch (IOException e) {
                error("Hiba történt az adatok lekérése során", e.getMessage());

                Platform.exit();
            }
        });
    }
    //
    /**Kurzusok betöltésért felelős metódus*/

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

    /**Kurzusok adatainak betöltése a szerverről*/
    private void loadUsersFromServer() throws IOException {
        Response response = RequestHandler.get(App.User_URL);
        String content = response.getContent();
        Gson converter = new Gson();
        User[] course_db = converter.fromJson(content, User[].class);
        userTable.getItems().clear();
        for (User user : course_db) {
            userTable.getItems().add(user);
        }
    }


    /**Kurzus bevitel kattintásra*/
    @FXML
    public void insertCourseClick(ActionEvent actionEvent) {
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


    /**Kurzus módosítás metódus (Hibát nem találunk benne, a módosítások végrehajtása azonban nem történik meg, vagy
     szerver hibát küld vissza. Ennek javítása folyamatban.)*/
    @FXML
    public void updateCourseClick(ActionEvent actionEvent) {
        Course SelectedCourse =  courseTable.getSelectionModel().getSelectedItem();
        if (SelectedCourse == null) {
            warning("Módosításhoz előbb válasszon ki egy elemet!");
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
                    error("Nem sikerült kapcsolódni a szerverhez");
                }
            });
            stage.show();
        } catch (IOException e) {
            error("Hiba történt a betöltés során", e.getMessage());
        }
    }


    /**Kurzus törlés kattintásra*/
    @FXML
    public void deleteCourseClick(ActionEvent actionEvent) {
        Course SelectedCourse =  courseTable.getSelectionModel().getSelectedItem();
        if (SelectedCourse == null) {
            warning("A törléshez előbb válasszon ki egy elemet!");
            return;
        }
        Optional<ButtonType> optionalButtonType =
                alert(Alert.AlertType.CONFIRMATION, "Biztos?",
                        "Biztos, hogy törölni szeretné ezt a kurzust?: "
                                + SelectedCourse.getName(),
                        "");
        if (optionalButtonType.isPresent() &&
                optionalButtonType.get().equals(ButtonType.OK)
        ) {
            String url = App.Delete_Course_Url + "/" + SelectedCourse.getId();
            try {
                RequestHandler.delete(url);
                loadCoursesFromServer();
            } catch (IOException e) {
                error("Nem sikerült kapcsolódni a szerverhez");
            }
        }
    }


    /**Felhasználó bevitel kattintásra*/
    @FXML
    public void InsertUserClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("insert-users-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            stage.setTitle("Új felhasználó");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                try {
                    loadUsersFromServer();
                } catch (IOException e) {
                    error("Nem sikerült kapcsolódni a szerverhez");
                }
            });
            stage.show();
        } catch (IOException e) {
            error("Hiba történt az űrlap betöltése során", e.getMessage());
        }
    }


    /**Felhasználó törlés kattintásra*/
    @FXML
    public void deleteUserClick(ActionEvent actionEvent) {
        User SelectedUser =  userTable.getSelectionModel().getSelectedItem();
        if (SelectedUser == null) {
            warning("A törléshez előbb válasszon ki egy elemet!");
            return;
        }
        Optional<ButtonType> optionalButtonType =
                alert(Alert.AlertType.CONFIRMATION, "Biztos?",
                        "Biztos, hogy törölni szeretné ezt a kurzust?: "
                                + SelectedUser.getUsername(),
                        "");
        if (optionalButtonType.isPresent() &&
                optionalButtonType.get().equals(ButtonType.OK)
        ) {
            String url = App.Delete_User_Url + "/" + SelectedUser.getId();
            try {
                RequestHandler.delete(url);
                loadUsersFromServer();
            } catch (IOException e) {
                error("Nem sikerült kapcsolódni a szerverhez");
            }
        }
    }


}

