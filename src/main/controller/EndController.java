package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.Values;
import main.classes.ChoiceBox;
import main.classes.Saving;

import java.net.URL;
import java.util.ResourceBundle;

public class EndController{

    @FXML
    private void restart(ActionEvent event) throws Exception{

        Saving.delete();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../layout/index.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("./main/style/style.css");

        stage.setScene(scene);
    }

}
