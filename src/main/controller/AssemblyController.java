package main.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class AssemblyController implements Initializable {

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
            if(Integer.parseInt(checkpoint) > 22) {
                checkpoint = "22";
                btnNext.setDisable(false);
            }
            maxStep = Integer.parseInt(checkpoint)-19;
            currStep = maxStep;
        }

        ArrayList<MefPart> prep = new ArrayList<>();
        ArrayList<MefPart> assemblyX = new ArrayList<>();
        ArrayList<MefPart> assemblyM = new ArrayList<>();
        ArrayList<MefPart> assemblyb = new ArrayList<>();

        //19
        prep.add(new MefPart("Para proceder con el ensamblaje hay que recordar que inicialmente se propuso una malla con n = 8 nodos, localmente es sistema es el siguiente:","assemblyPrep1"));
        prep.add(new MefPart("Con el fin de mejorar la legibilidad se introduciran los signos dentro de las matrices, por lo que ahora debes tener en cuenta que todas las posiciones de L y K están siendo multiplicadas por (-1):","assemblyPrep2"));
        prep.add(new MefPart("Otro aspecto a considerar es que en cada nodo global contiene 2 incógnitas, por lo que si n = 8 nodos el sistema global tendría las siguientes dimensiones:","assemblyPrep3"));
        prep.add(new MefPart("Al momento de añadir el SEL local de cada elemento tendrá que asimilarse una fórmula de colocación ya que se está trabajando con dos incógnitas, las posiciones reales en la matriz vendrán dadas por las siguientes fórmulas:","assemblyPrep4"));
        prep.add(new MefPart("En estas fórmulas debes saber que i representa el nodo global al que corresponde el nodo local que estamos evaluando, lo verás de mejor manera en la siguiente sección.","assemblyPrep4"));
        steps.add(new MefStep("Antes del Proceso de Ensamblaje", prep));

        //20
        assemblyX.add(new MefPart("Para ensamblaje de X debes considerar que en cada nodo existe una A y una B. Tomando en cuenta las fórmulas de la sección anterior y sabiendo que existen ocho nodos en total, se tendrán ocho espacios para A y ocho espacios para B.",null, "Si A_1 se encuentra en el nodo global 1, esta se posiciona en la " +
                "celda 1 del vector, mientras que B_1 se coloca en la celda 9 del vector. Continuando A_2 se encuentra en el nodo global 2, por lo que B_2 se coloca en la celda 10 y así sucesivamente. En la siguiente sección podrás visualizar todo el proceso."));
        assemblyX.add(new MefPart("El ensamblaje completo es de la siguiente forma:","assemblyX"));
        steps.add(new MefStep("Ensamblaje del Vector X", assemblyX));

        //21
        assemblyM.add(new MefPart("Ahora te presentaremos una animación del proceso de ensamblaje de la Matriz M para el Elemento 1. En general el proceso es el mismo para todos los elementos, por lo que solo mostraremos ese elemento. Recuerda que para pasar de nodos locales a globales utilizaremos la fórmula:","assemblyPrep4"));
        assemblyM.add(new MefPart("El ensamblaje del Elemento 1 es el siguiente:","assemblyM"));
        assemblyM.add(new MefPart("Se repite el proceso con cada uno de los elementos, tomando en cuenta que si ya existe contenido en alguna celda simplemente se sumaría la nueva posición al resto del contenido de la celda; por ejemplo, si existiera un valor X que debe agregarse a M(1,1) se tendría lo siguiente","assemblyM1"));
        assemblyM.add(new MefPart("En este elemento el proceso resultó ser bastante ordenado, pero existen casos como el elemento 2 en que no se sigue ningún orden particular:","assemblyM2"));
        assemblyM.add(new MefPart("En estos casos el ensamblaje será bastante desordenado, pero seguirá conservando la misma lógica que el elemento 1 así que no te preocupes.",null));
        steps.add(new MefStep("Ensamblaje de la Matriz M", assemblyM));

        //22
        assemblyb.add(new MefPart("Ahora para finalizar haremos el proceso de ensamblaje del Vector b para el Elemento 2. De igual forma el proceso es el mismo para todos los elementos, por lo que solo mostraremos este ejemplo. Recuerda que seguiremos haciendo uso de las fórmulas:","assemblyPrep4"));
        assemblyb.add(new MefPart("De igual forma que en la matriz M se enumeran las posiciones del vector y luego se reemplazan por los nodos globales siguiendo las fórmulas ya establecidas para A y B. Recuerda que el superíndice indica qué elemento se está trabajando. Luego se procede a ensamblar.",null));
        assemblyb.add(new MefPart("El ensamblaje del Elemento 2 es el siguiente:","assemblyb"));
        assemblyb.add(new MefPart("Considera que al igual que en la matriz M, a medida se ensamblan más elementos se suman a las casillas ocupadas.",null, "¡Muy Bien! ya hemos concluido el proceso de ensamblaje, ahora solo nos falta aplciar las condiciones de contorno y podremos ejecutar nuestra simulación."));
        steps.add(new MefStep("Ensamblaje del Vector b", assemblyb));

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

            if (imgName.equals("assemblyX") || imgName.equals("assemblyM") || imgName.equals("assemblyb")){

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
                String saveVal = (19+maxStep)+"";
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
        Parent root = FXMLLoader.load(getClass().getResource("/main/layout/defSteps.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("/main/style/style.css");

        stage.setScene(scene);
    }

    @FXML
    private void nextScene(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Saving.save("23");
        Parent root = FXMLLoader.load(getClass().getResource("/main/layout/conditions.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("/main/style/style.css");
        stage.setScene(scene);

    }

}
