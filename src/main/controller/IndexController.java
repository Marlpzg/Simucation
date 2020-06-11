package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class IndexController {

    @FXML
    private void nextScene(ActionEvent event) throws Exception{

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../layout/intro.fxml"));
        Scene scene = new Scene(root, screenSize.getWidth()/1.8, screenSize.getHeight()/1.7);
        scene.getStylesheets().add("./main/style/style.css");

        stage.setScene(scene);
    }

}
