package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AccountController {

    @FXML
    Button confirmPasswordB;
    @FXML
    Button confirmSecurityB;
    @FXML
    Button cancelB;
    @FXML
    Button deleteAccountB;
    @FXML
    TextField oldpass;
    @FXML
    TextField newpass;
    @FXML
    TextField cityname;



    @FXML
    public void cancelButton() {
        try {
            Parent root = (Parent) FXMLLoader.load(Main.class.getResource("courseViewer.fxml"));
            Stage stage = (Stage)this.cancelB.getScene().getWindow();
            stage.setTitle("Course Viewer");
            stage.setScene(new Scene(root, 1024, 768));
            stage.setResizable(false);
            stage.show();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }


}
