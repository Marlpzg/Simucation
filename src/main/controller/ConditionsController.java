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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.Values;
import main.classes.MefPart;
import main.classes.MefStep;
import main.classes.Saving;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ConditionsController implements Initializable {

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

    @FXML
    private StackPane spImgCont;

    @FXML
    private VBox vbBody;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        steps = new ArrayList<>();
        currStep = 0;
        currPart = 0;
        maxStep = 0;

        String checkpoint = Saving.load();
        if (checkpoint != null){
            if(Integer.parseInt(checkpoint) > 26) {
                checkpoint = "26";
                btnNext.setDisable(false);
            }
            maxStep = Integer.parseInt(checkpoint)-23;
            currStep = maxStep;
        }

        ArrayList<MefPart> prep = new ArrayList<>();
        ArrayList<MefPart> neu = new ArrayList<>();
        ArrayList<MefPart> dir = new ArrayList<>();
        ArrayList<MefPart> ans = new ArrayList<>();

        //23
        prep.add(new MefPart("Ahora aplicaremos las condiciones de contorno, como recordarás al inicio de esta aventura se te solicitó que determinaras sobre qué nodos se aplicarían las condiciones.",null, "Aunque está bien si no los has memorizado, aquí te refrescaremos la memoria. Hemos preparado algunas animaciones para que puedas visaulizar claramente cómo se ejecutan las condiciones que ya has determinado gráficamente antes."));
        prep.add(new MefPart("Recuerda también las fórmulas que utilizamos previamente en el ensamblaje, ya que se utilizarán para colocar las condiciones dependiendo a qué variable afecten.", "assemblyPrep4"));
        steps.add(new MefStep("Condiciones de Contorno", prep));

        //24
        neu.add(new MefPart("Primero recuerda que durante el Paso 6 definimos una componente llamada Vector de Neumann que estaba presente en nuestro sistema local, ahora ese vector de Neumann lo aplicaremos en nuestro sistema global:", "neumann1"));
        neu.add(new MefPart("Expandiremos este sistema como una matriz genérica:", "expanding"));
        neu.add(new MefPart("Ahora recuerda cuál es el valor de la condición de Neumann y sobre qué nodos debe aplicarse. Considera también que esta condición solo tiene efecto sobre la incógnita A:", "neumann2"));
        neu.add(new MefPart("Esto significa que colocaremos α solo en las posiciones 2, 3, 6 y 7:", "neumann"));
        neu.add(new MefPart("De esta manera ya hemos aplicado las condiciones de Neumann a nuestro sistema. ¡Ya solo nos faltan las condiciones de Dirichlet!", null));
        steps.add(new MefStep("Condiciones de Neumann", neu));

        //25
        dir.add(new MefPart("Ahora para aplicar las condiciones de dirichlet debemos fijarnos que en este caso solo afectan a la incógnita B. Estas condiciones son valores fijos en el problema por lo que dejan de ser incógnitas, y para aplicarlas deben modificar el Vector X.", null, "En la siguiente parte visualizarás como se aplican las condiciones de Dirichlet para este problema."));
        dir.add(new MefPart("El Vector X se modifica así:", "dirichletA"));
        dir.add(new MefPart("Ahora es necesario modificar todo el SEL, ya que hay menos incógnitas que calcular. En la siguiente parte se te presentará una animación que simula las modificaciones que deben realizarse.", null));
        dir.add(new MefPart("Presta atención al proceso:", "dirichletB"));
        dir.add(new MefPart("El resultado anterior ya tiene aplicadas todas las Condiciones de Contorno, por lo que estamos a un solo paso de finalizar nuestra implementación del MEF en 3D.", null, "¡Lo has hecho muy bien!"));
        steps.add(new MefStep("Condiciones de Dirichlet", dir));

        //26
        ans.add(new MefPart("Finalmente juntando todas las partes del sistema se modifica el SEL de manera que se determine la solución del vector X:", "solution"));
        steps.add(new MefStep("Paso Final - Resolver", ans));

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
        else
            btnNextPart.setDisable(true);

        if (currStep == steps.size()-1 && currPart == steps.get(currStep).getParts().size()-1)
            btnNext.setDisable(false);


        lblStepName.setText(steps.get(currStep).getName());
        lblDesc.setText(steps.get(currStep).getParts().get(currPart).getDescrption());


        renderImage();

    }

    public void renderImage(){

        if (steps.get(currStep).getParts().get(currPart).getImg() != null){
            String imgName = steps.get(currStep).getParts().get(currPart).getImg();

            if (imgName.equals("expanding") || imgName.equals("neumann") || imgName.equals("dirichletA") || imgName.equals("dirichletB")){

                MediaPlayer player = new MediaPlayer( new Media(getClass().getResource("/main/resources/process/"+imgName+".mp4").toExternalForm()));
                MediaView mediaView = new MediaView(player);
                player.setAutoPlay(true);
                mediaView.setFitWidth(700);
                mediaView.setFitHeight(425);
                spImgCont.getChildren().clear();
                spImgCont.getChildren().add(mediaView);

            }else {
                ImageView img = new ImageView(new Image("/main/resources/process/" + steps.get(currStep).getParts().get(currPart).getImg() + ".png"));
                spImgCont.getChildren().clear();
                spImgCont.getChildren().add(img);
            }

        }else{
            spImgCont.getChildren().clear();
        }

        if(steps.get(currStep).getParts().get(currPart).getText2() != null){
            Label lblText = new Label(steps.get(currStep).getParts().get(currPart).getText2());
            lblText.setPrefHeight(300);
            lblText.setStyle("-fx-font-size: 18pt;");
            lblText.setWrapText(true);
            lblText.setPrefWidth(650);
            lblText.setTextAlignment(TextAlignment.JUSTIFY);
            spImgCont.getChildren().add(lblText);
        }

        btnNextPart.setText("Siguiente");


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
            if (currStep == maxStep) {
                maxStep++;
                String saveVal = (23+maxStep)+"";
                Saving.save(saveVal);
            }
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

        }

        setupStage();

    }

    @FXML
    private void prevScene(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/layout/assembly.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("/main/style/style.css");

        stage.setScene(scene);
    }

    @FXML
    private void nextScene(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Saving.save("27");
        Parent root = FXMLLoader.load(getClass().getResource("/main/layout/end.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("/main/style/style.css");
        stage.setScene(scene);

    }

}
