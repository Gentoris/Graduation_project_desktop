package com.example.graduation_project_desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**Ez a fájl felelős a program futtatásáért. Ez a fő fájl, amely futtatja az alkalmazást!*/
public class App extends Application {
    public static final String Course_URL = "http://localhost:3000/course/list";
    public static final String User_URL = "http://localhost:3000/user/list";
    public static final String Insert_Course_URL = "http://localhost:3000/course/create";
    public static final String Insert_User_URL = "http://localhost:3000/user/register";
    public static final String Update_Course_URL = "http://localhost:3000/course/update";
    public static final String Delete_Course_Url = "http://localhost:3000/course/delete";
    public static final String Delete_User_Url = "http://localhost:3000/user/delete";
    /**Ezek az URL-ek a backend-ről kerülnek lekérésre a RequestHandler fájl segítségével. Ezek az URL linkek felelősek
    azért, hogy a grafikus felületen megjelenő gombok végrehajtsák az alapvető funkciókat(Insert, Update, Delete), illetve
    hogy a táblázatban megjelenítsük a kurzusok illetve felhasználók adatait.*/

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("administrating.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Course Administrator");
        stage.setScene(scene);
        stage.show();
    }
    /**A fenti sorok azért felelősek, hogy megjelenítsék az alkalmazás grafikus felületét, amelynek részleteit az
    administrating.fxml-ből kap meg! Emellett a felület alap méretét, illetve címét adtam itt meg.*/

    public static void main(String[] args) {
        launch();
    }
}
