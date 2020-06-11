package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class IntroController implements Initializable {

    @FXML
    private StackPane visor;

    private int xVal;
    private int yVal;

    public void renderModel(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ImageView img = new ImageView(new Image("/main/resources/domain/x"+xVal+"y"+yVal+".png"));
        img.setFitWidth(screenSize.getWidth()/(1.8*2));
        img.setFitHeight(screenSize.getHeight()/(4));
        visor.getChildren().add(img);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xVal = 1;
        yVal = 3;
        renderModel();
    }

    @FXML
    private void prevScene(ActionEvent event) throws Exception{

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../layout/index.fxml"));
        Scene scene = new Scene(root, screenSize.getWidth()/1.8, screenSize.getHeight()/1.7);
        scene.getStylesheets().add("./main/style/style.css");

        stage.setScene(scene);
    }

    @FXML
    private void nextScene(ActionEvent event) throws Exception{

        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        //Parent root = FXMLLoader.load(getClass().getResource("../layout/intro.fxml"));
        //Scene scene = new Scene(root, screenSize.getWidth()/1.8, screenSize.getHeight()/1.7);
        //scene.getStylesheets().add("./main/style/style.css");

        //stage.setScene(scene);
    }

    @FXML
    private void rotateUp(ActionEvent event) throws Exception{

        if (yVal<4)
            yVal++;
        try {
            renderModel();
        }catch (Exception e){
            yVal--;
            renderModel();
        }

    }

    @FXML
    private void rotateDown(ActionEvent event) throws Exception{

        if (yVal>0)
            yVal--;
        try {
            renderModel();
        }catch (Exception e){
            yVal++;
            renderModel();
        }

    }

    @FXML
    private void rotateRight(ActionEvent event) throws Exception{

        if (yVal == 0 || yVal == 4)
            xVal += 2;
        else
            xVal++;
        if(xVal > 7)
            xVal=0;
        renderModel();

    }

    @FXML
    private void rotateLeft(ActionEvent event) throws Exception{

        if (yVal == 0 || yVal == 4)
            xVal -= 2;
        else
            xVal--;
        if (xVal < 0)
            xVal = 7;
        renderModel();

    }

    @FXML
    private void resetImage(ActionEvent event) throws Exception{

        xVal = 1;
        yVal = 3;
        renderModel();

    }

}
