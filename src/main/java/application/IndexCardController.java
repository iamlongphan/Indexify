package application;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class IndexCardController implements Initializable {
    @FXML
    Rectangle card;

    @FXML
    ImageView editButton;

    @FXML
    CheckBox checkBox;

    @FXML
    Button createCard;

    @FXML
    Button nextButton;

    @FXML
    Button previousButton;

    @FXML
    Label text;

    @FXML
    TabPane courseTab;

    @FXML
    Text cardCount;

    @FXML
    Group scene;

    @FXML
    TextField counterBox;

    Set set;

    String currentUser;
    String currentCourse;



    @FXML
    public void editButtonClicked(Event e) {

        Stage stage = new Stage();
        stage.setTitle("Edit Card");

        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 300);

        Label termLabel = new Label("Term: ");
        TextField termField = new TextField(set.getCard().getFront());

        Label definitionLabel = new Label("Definition: ");
        TextField definitionField = new TextField(set.getCard().getBack());

        Button submit = new Button("Submit");
        submit.setStyle("-fx-text-fill: white; -fx-background-color: #a6051a");

        submit.setOnAction((actionEvent) -> {
            if(!(termField.getText().isEmpty() || definitionField.getText().isEmpty())){
                //boolean selected = !checkBox.isSelected();
                set.updateCard(termField.getText(), definitionField.getText(), checkBox.isSelected());
                stage.close();
            }
        });

        HBox termBox = new HBox();
        termLabel.setPadding(new Insets(5, 20, 0, 0));
        termBox.getChildren().addAll(termLabel, termField);
        termBox.setPadding(new Insets(20, 0, 20, 30));

        HBox defBox = new HBox();
        definitionLabel.setPadding(new Insets(5, 20, 0, 0));
        defBox.getChildren().addAll(definitionLabel, definitionField);
        defBox.setPadding(new Insets(20, 0, 20, 30));

        HBox submitBox = new HBox();
        submitBox.getChildren().addAll(submit);
        submitBox.setPadding(new Insets(20, 0, 20, 30));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(termBox, defBox, submitBox);

        root.getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void createButtonClicked(Event e){

        Stage stage = new Stage();
        stage.setTitle("Create Card");

        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 300);

        Label termLabel = new Label("Term: ");
        TextField termField = new TextField();

        Label definitionLabel = new Label("Definition: ");
        TextField definitionField = new TextField();

        Button submit = new Button("Create Card");
        submit.setStyle("-fx-text-fill: white; -fx-background-color: #a6051a");

        submit.setOnAction((actionEvent) -> {
            if(!(termField.getText().isEmpty() || definitionField.getText().isEmpty())){
                set.addCard(termField.getText(), definitionField.getText(), false);
                //This is where to write data down to the file TODO TESTING PHASE
                File courseRead = new File("userData/" + currentUser + "/" + currentUser+currentCourse+".txt");
                try {
                    BufferedWriter courseFileWriter = new BufferedWriter(new FileWriter(courseRead,true));
                    courseFileWriter.write(termField.getText()+","+definitionField.getText()+","+"false");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                stage.close();
            }
        });

        HBox termBox = new HBox();
        termLabel.setPadding(new Insets(5, 20, 0, 0));
        termBox.getChildren().addAll(termLabel, termField);
        termBox.setPadding(new Insets(20, 0, 20, 30));

        HBox defBox = new HBox();
        definitionLabel.setPadding(new Insets(5, 20, 0, 0));
        defBox.getChildren().addAll(definitionLabel, definitionField);
        defBox.setPadding(new Insets(20, 0, 20, 30));

        HBox submitBox = new HBox();
        submitBox.getChildren().addAll(submit);
        submitBox.setPadding(new Insets(20, 0, 20, 30));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(termBox, defBox, submitBox);

        root.getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteButtonClicked(Event e) {
        set.deleteCard(set.getCard());
    }

    @FXML
    public void flip(){
        set.flipCurrentCard();
    }

    @FXML
    public void learned(){
        IndexCard card = set.getCard();
        card.setLearned(checkBox.isSelected());
    }

    @FXML
    public void nextCard(){
        set.nextCard();
    }

    @FXML
    public void previousCard(){
        set.previousCard();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        File currentUserName = new File("currentUser.txt");
        File currentCourseName = new File("currentCourseSelected.txt");


        //Scanner creator to read currentUser (Helps with accessing the file)
        Scanner reader = null;
        try {
            reader = new Scanner(currentUserName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Scanner creator for reading the current Course name.
        Scanner readCourseName = null;
        try{
            readCourseName = new Scanner(currentCourseName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // currentUser reads current username,  currentCourse reads the current course that is open.
        currentUser = reader.nextLine();
        currentCourse = readCourseName.nextLine();
        reader.close();
        readCourseName.close();

        File theExistentCourse = new File("userData/"+currentUser+"/"+currentUser+currentCourse+".txt");

        //Making the Writer // is actually a reader TODO
        BufferedWriter ExistentCourse = null;
        try {
            //Should become reader TODO
            ExistentCourse = new BufferedWriter(new FileWriter(theExistentCourse));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        set = new Set(currentCourse, text, checkBox, counterBox);
        try {
            if(theExistentCourse.exists())
            {
                //While loop reading through the corresponding course file, where it will add all existing index cards. TODO
                ExistentCourse.write(currentCourse + "," + " text, checkBox, counterBox");
                ExistentCourse.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }
    //NEED TO EDIT REMOVELINE FROM FILE TO CHANGE THE COURSES FILE.
    public void removeLineFromFile(String courseToRemove) {

        try {

            File userReader = new File("currentUser.txt");
            Scanner readUser = new Scanner(userReader);
            currentUser = readUser.nextLine();
            readUser.close();


            File inFile = new File("users.txt");
            BufferedReader br = new BufferedReader(new FileReader(inFile));

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            //BufferedReader br = new BufferedReader(new FileReader());
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;
            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {

                if (line.trim().indexOf(courseToRemove) == -1) {

                    pw.println(line);
                    pw.flush();
                }


            }
            pw.close();
            br.close();

            //Delete the original file
            inFile.delete();
            //Rename the new file to the original file
            tempFile.renameTo(inFile);


        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

