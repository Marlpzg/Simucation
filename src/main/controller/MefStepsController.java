package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.Values;
import main.classes.MefPart;
import main.classes.MefStep;
import main.classes.Saving;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MefStepsController implements Initializable {

    private ArrayList<MefStep> steps;
    private int currStep;
    private int currPart;
    private int maxStep;

    @FXML
    private Label lblStepName;

    @FXML
    private Label lblDesc;

    @FXML
    private Button btnPrevStep;

    @FXML
    private Button btnNextStep;

    @FXML
    private Button btnPrevPart;

    @FXML
    private Button btnNextPart;

    @FXML
    private Button btnNext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        steps = new ArrayList<>();
        currStep = 0;
        currPart = 0;
        maxStep = 0;

        ArrayList<MefPart> step1 = new ArrayList<>();
        ArrayList<MefPart> step2 = new ArrayList<>();
        ArrayList<MefPart> step3 = new ArrayList<>();
        ArrayList<MefPart> step4 = new ArrayList<>();
        ArrayList<MefPart> step5 = new ArrayList<>();
        ArrayList<MefPart> step6 = new ArrayList<>();

        step1.add(new MefPart("1-1",""));
        step1.add(new MefPart("1-2",""));
        step1.add(new MefPart("1-3",""));
        steps.add(new MefStep("Paso 1 - Localización", step1));

        step2.add(new MefPart("2-1",""));
        step2.add(new MefPart("2-2",""));
        steps.add(new MefStep("Paso 2 - Interpolación", step2));

        step3.add(new MefPart("3-1",""));
        steps.add(new MefStep("Paso 3 - Aproximación del Modelo", step3));

        step4.add(new MefPart("4-1",""));
        step4.add(new MefPart("4-2",""));
        steps.add(new MefStep("Paso 4 - Método de Residuos Ponderados", step4));

        step5.add(new MefPart("5-1",""));
        step5.add(new MefPart("5-2",""));
        steps.add(new MefStep("Paso 5 - Método de Galerkin", step5));

        step6.add(new MefPart("6-1",""));
        step6.add(new MefPart("6-2",""));
        steps.add(new MefStep("Paso 6 - Integración por Partes", step6));

        btnNextStep.setDisable(true);
        btnPrevStep.setDisable(true);
        btnPrevPart.setDisable(true);

        setupStage();

    }

    private void setupStage(){

        if (currStep == 0)
            btnPrevStep.setDisable(true);
        else
            btnPrevStep.setDisable(false);

        if (currPart == steps.get(currStep).getParts().size()-1 || currStep < maxStep)
            btnNextStep.setDisable(false);
        else
            btnNextStep.setDisable(true);

        if (currStep == steps.size()-1)
            btnNextStep.setDisable(true);

        if (currPart < steps.get(currStep).getParts().size()-1)
            btnNextPart.setDisable(false);

        lblStepName.setText(steps.get(currStep).getName());
        lblDesc.setText(steps.get(currStep).getParts().get(currPart).getDescrption());
    }

    @FXML
    private void prevStep(ActionEvent event) throws Exception{

        if (currStep > 0) {
            currStep--;
            currPart = 0;
            btnPrevPart.setDisable(true);
        }

        setupStage();

    }

    @FXML
    private void nextStep(ActionEvent event) throws Exception{

        if (currStep < steps.size()-1) {
            if (currStep == maxStep)
                maxStep++;
            currStep++;
            currPart = 0;
            //btnNextPart.setDisable(false);
            btnPrevPart.setDisable(true);
        }

        setupStage();

    }

    @FXML
    private void prevPart(ActionEvent event) throws Exception{

        if (currPart > 0) {
            currPart--;
            if(currPart == 0)
                btnPrevPart.setDisable(true);
        }

        setupStage();

    }

    @FXML
    private void nextPart(ActionEvent event) throws Exception{

        if (currPart < steps.get(currStep).getParts().size()-1) {
            btnPrevPart.setDisable(false);
            currPart++;
            if (currPart == steps.get(currStep).getParts().size()-1)
                btnNextPart.setDisable(true);
        }

        if (currStep == steps.size()-1 && currPart == steps.get(currStep).getParts().size()-1)
            btnNext.setDisable(false);


        setupStage();

    }

    @FXML
    private void prevScene(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../layout/condIntro.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("./main/style/style.css");

        stage.setScene(scene);
    }

    @FXML
    private void nextScene(ActionEvent event) throws Exception{

        //Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        //Parent root = FXMLLoader.load(getClass().getResource("../layout/intro.fxml"));
        //Scene scene = new Scene(root, Values.WIDTH, Values.HEIGHT);
        //scene.getStylesheets().add("./main/style/style.css");

        //Saving.save("5");

        //stage.setScene(scene);
    }

}
