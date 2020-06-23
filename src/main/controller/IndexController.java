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

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    @FXML
    private Button btnCont;

    @FXML
    private Button btnReset;

    @FXML
    private void nextScene(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../layout/intro.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("./main/style/style.css");

        Saving.save("1");

        stage.setScene(scene);
    }

    @FXML
    private void continueCourse(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../layout/"+Saving.getProgress()+".fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("./main/style/style.css");


        stage.setScene(scene);
    }

    @FXML
    private void resetCourse(ActionEvent event) throws Exception{

        boolean del = ChoiceBox.display("Confirmación", "¿Estás seguro que quieres borrar tu progreso?", "Sí, Bórralo", "Mejor no");
        if(del) {
            Saving.delete();

            btnCont.setDisable(true);
            btnReset.setDisable(true);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Saving.getProgress() != null){
            btnCont.setDisable(false);
            btnReset.setDisable(false);
        }else{
            btnCont.setDisable(true);
            btnReset.setDisable(true);
        }

    }
}
