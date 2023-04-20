package com.example.graduation_project_desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static final String Course_URL = "http://localhost:3000/course/list";
    public static final String User_URL = "http://localhost:3000/user/list";
    public static final String Insert_Course_URL = "http://localhost:3000/course/create";
    public static final String Insert_User_URL = "http://localhost:3000/user/register";
    public static final String Update_Course_URL = "http://localhost:3000/course/update";
    public static final String Delete_Course_Url = "http://localhost:3000/course/delete";
    public static final String Delete_User_Url = "http://localhost:3000/user/delete";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
