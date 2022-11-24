package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

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

    public boolean validatespass(String oldpassw) throws FileNotFoundException {
        File database = new File("users.TXT");
        Scanner readDatabase = new Scanner(database);

        while(readDatabase.hasNextLine())
        {
            String[] details = readDatabase.nextLine().split(",");
            String realOldPass = details[1];
            if (realOldPass.equals(oldpassw))
            {
                readDatabase.close();
                return true;
            }

        }
        readDatabase.close();
        return false;

    }

    public void changeSecurityAnswer(){
        String newAnswer = cityname.getText();

        //TODO Change the Answer in File

    }


    public void changePass() throws FileNotFoundException {
        //read from file the new pass
        String oldPassword = oldpass.getText();
        String newPassword = newpass.getText();

        if (validatespass(oldPassword) ){


            //TODO replace the old password with the new one

            System.out.println("Change password");
        }
        else{
            System.out.println("Password is not Correct");
        }


    }


    public void deleteAccount(){
        //TODO Delete account form File
    }



}
