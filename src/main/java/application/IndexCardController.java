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

import java.net.URL;
import java.util.ResourceBundle;

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
    public void createButtonClicked(Event e) {

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
        set = new Set("course", text, checkBox, counterBox);
    }
}
