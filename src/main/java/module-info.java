module com.example.graduation_project_desktop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.graduation_project_desktop to javafx.fxml;
    exports com.example.graduation_project_desktop;
}