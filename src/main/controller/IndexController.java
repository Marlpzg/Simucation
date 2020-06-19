package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Values;

import java.awt.*;

public class IndexController {

    @FXML
    private void nextScene(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../layout/intro.fxml"));
        Scene scene = new Scene(root, Values.WIDTH, Values.HEIGHT);
        scene.getStylesheets().add("./main/style/style.css");

        stage.setScene(scene);
    }

}
