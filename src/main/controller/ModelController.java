package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Values;
import main.classes.Saving;


public class ModelController{


    @FXML
    private void prevScene(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/layout/intro.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("/main/style/style.css");

        stage.setScene(scene);
    }

    @FXML
    private void nextScene(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/layout/condIntro.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("/main/style/style.css");

        Saving.save("3");

        stage.setScene(scene);
    }

}
