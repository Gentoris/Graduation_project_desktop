package com.example.graduation_project_desktop;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class InsertUsersController extends Controller{
    @javafx.fxml.FXML
    private TextField usernameInput;
    @javafx.fxml.FXML
    private TextField passwordInput;
    @javafx.fxml.FXML
    private TextField emailInput;
    @javafx.fxml.FXML
    private TextField profileInput;

    @FXML
    private Button InsertUserButton;

    @FXML
    public void InsertUserClick(ActionEvent actionEvent) {
        String username = this.usernameInput.getText();
        String password = this.passwordInput.getText();
        String email = this.emailInput.getText();
        String profile = this.profileInput.getText();


        if (username.isEmpty()) {
            warning("Felhasználó név kötelező");
            return;
        }
        if (password.isEmpty()) {
            warning("Jelszó kötelező");
            return;
        }
        if (email.isEmpty()) {
            warning("Jelszó kötelező");
            return;
        }


        User user = new User(0, username, password, email, profile);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        try {
            Response response = RequestHandler.post(App.Insert_User_URL, json);
            if(response.getResponseCode() == 201) {
                usernameInput.setText("");
                passwordInput.setText("");
                emailInput.setText("");
                profileInput.setText("");
            }else{
                error("Hiba történt a Hozzáadás során", response.getContent());
            }
        } catch (IOException e) {
            error("Nem sikerült a szerverhez csatlakozni");
        }
    }
}
