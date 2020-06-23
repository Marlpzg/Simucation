package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Values;
import main.classes.Saving;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;

public class IntroController implements Initializable {

    private Stage window;

    @FXML
    private StackPane visor;

    @FXML
    private Button btnMesh;

    @FXML
    private Button btnTable;

    @FXML
    private Button btnDomain;

    private int xVal;
    private int yVal;
    private String route;

    public void renderModel(){
        ImageView img = new ImageView(new Image("/main/resources/"+route+"/x"+xVal+"y"+yVal+".png"));
        img.setFitWidth(Values.getWidth()/2.1);
        img.setFitHeight(Values.getHeight()/2.35);
        visor.getChildren().clear();
        visor.getChildren().add(img);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xVal = 1;
        yVal = 3;
        route = "domain";
        renderModel();
    }

    @FXML
    private void showMesh(ActionEvent event) throws Exception{

        route="mesh";
        btnMesh.setDisable(true);
        btnDomain.setDisable(false);
        btnTable.setDisable(false);
        btnTable.requestFocus();
        renderModel();

    }

    @FXML
    private void showDomain(ActionEvent event) throws Exception{

        route="domain";
        btnMesh.setDisable(false);
        btnDomain.setDisable(true);
        btnTable.setDisable(true);
        renderModel();

    }

    @FXML
    private void showTable(ActionEvent event) throws Exception{

        window = new Stage(StageStyle.UTILITY);

        //window.initModality(Modality.APPLICATION_MODAL);
        window.setAlwaysOnTop(true);
        window.setTitle("Tabla de conectividades");
        window.setResizable(false);
        window.getIcons().add(new Image("./main/resources/circuit.png"));
        Parent root = FXMLLoader.load(getClass().getResource("../layout/table.fxml"));

        Scene scene = new Scene(root, 400, 350);

        scene.getStylesheets().add("./main/style/style.css");
        window.setScene(scene);
        window.showAndWait();

    }

    @FXML
    private void prevScene(ActionEvent event) throws Exception{

        if (window != null)
            window.close();

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../layout/index.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("./main/style/style.css");

        stage.setScene(scene);
    }

    @FXML
    private void nextScene(ActionEvent event) throws Exception{

        if (window != null)
            window.close();

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../layout/model.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("./main/style/style.css");

        Saving.save("2");

        stage.setScene(scene);


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

        if (xVal < 0){
            xVal = 7;
            if (yVal == 0 || yVal == 4)
                xVal = 6;

        }

        renderModel();

    }

    @FXML
    private void resetImage(ActionEvent event) throws Exception{

        xVal = 1;
        yVal = 3;
        renderModel();

    }

}
