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
import javafx.stage.Stage;
import main.Values;
import main.classes.MefPart;
import main.classes.MefStep;
import main.classes.Saving;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DefStepsController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        steps = new ArrayList<>();
        currStep = 0;
        currPart = 0;
        maxStep = 0;

        String checkpoint = Saving.load();
        if (checkpoint != null){
            if(Integer.parseInt(checkpoint) > 17) {
                checkpoint = "17";
                btnNext.setDisable(false);
            }
            maxStep = Integer.parseInt(checkpoint)-10;
            currStep = maxStep;
        }

        ArrayList<MefPart> interp = new ArrayList<>();
        ArrayList<MefPart> chain = new ArrayList<>();
        ArrayList<MefPart> intro = new ArrayList<>();
        ArrayList<MefPart> vf = new ArrayList<>();
        ArrayList<MefPart> ve = new ArrayList<>();
        ArrayList<MefPart> mK = new ArrayList<>();
        ArrayList<MefPart> mL = new ArrayList<>();
        ArrayList<MefPart> mC = new ArrayList<>();
        ArrayList<MefPart> mH = new ArrayList<>();

        interp.add(new MefPart("Para la definición de los siguientes componentes de las matrices será necesario interpolar los valores de x,y,z en términos de ε,η,ψ. Haciendo uso de las funciones de forma originales se obtiene la siguiente interpolación:","varInterp"));
        interp.add(new MefPart("Simplificaremos un poco el contenido de las interpolaciones definiendo una nueva notación e implementándola:","varInterpSimpl"));
        steps.add(new MefStep("Interpolación de variables", interp));

        chain.add(new MefPart("También se deberemos hacer uso de la regla de la cadena para los cálculos de gradientes ya que las funciones de forma dependen de variables diferentes a las espaciales. La regla de la cadena generalizada para una matriz A cualquiera es la siguiente:","chainRule"));
        steps.add(new MefStep("Regla de la cadena", chain));

        intro.add(new MefPart("Ahora sí podemos proceder a la resolución de cada matriz del sistema, nosotros te guiaremos paso por paso pero omitiremos algunas operaciones como integrales y derivadas (ya que resultan en un proceso muy engorroso), pero te invitamos a que intentes resolverlas por tu cuenta para verificar los resultados. Así que ¡En marcha!",null));
        steps.add(new MefStep("Definición de componentes", intro));

        vf.add(new MefPart("Daremos inicio con el vector f ya que resulta particularmente sencillo:","vf1"));
        vf.add(new MefPart("Antes de resolver esas integrales debes considerar que al trabajar en el espacio dV = dz dy dx, sin embargo la integral debe estar expresada en el espacio isoparamétrico (ε,η,ψ). Haciendo uso del concepto de Jacobiano es posible modificar los diferenciales:","vf2"));
        vf.add(new MefPart("El jacobiano se define de la siguiente forma (y si te fijas detenidamente todos sus componentes son constantes):","vf3"));
        vf.add(new MefPart("Y ahora resolvemos todas las integrales dentro del vector extrayendo el Jacobiano ya que es constante:","vf4"));
        vf.add(new MefPart("Y el resultado será la definición de nuestro vector f:","vf5"));
        steps.add(new MefStep("Componentes del vector f", vf));

        ve.add(new MefPart("El vector e no difiere del vector f, por lo cual la respuesta será prácticamente la misma:","ve1"));
        ve.add(new MefPart("Por lo tanto nuestro vector e tendrá la siguiente forma:","ve2"));
        steps.add(new MefStep("Componentes del vector e", ve));

        //.add(new MefPart("",null));
        //steps.add(new MefStep("", ));

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


        lblStepName.setText(steps.get(currStep).getName());
        lblDesc.setText(steps.get(currStep).getParts().get(currPart).getDescrption());


        renderImage();

    }

    public void renderImage(){

        if (steps.get(currStep).getParts().get(currPart).getImg() != null){
            ImageView img = new ImageView(new Image("/main/resources/process/"+steps.get(currStep).getParts().get(currPart).getImg()+".png"));
            spImgCont.getChildren().clear();
            spImgCont.getChildren().add(img);
        }else{
            spImgCont.getChildren().clear();
        }

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
                String saveVal = (10+maxStep)+"";
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

        if (currStep == steps.size()-1 && currPart == steps.get(currStep).getParts().size()-1)
            btnNext.setDisable(false);


        setupStage();

    }

    @FXML
    private void prevScene(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../layout/mefSteps.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("./main/style/style.css");

        stage.setScene(scene);
    }

    @FXML
    private void nextScene(ActionEvent event) throws Exception{

        //Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        //Saving.save("10");
        //Parent root = FXMLLoader.load(getClass().getResource("../layout/intro.fxml"));
        //Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        //scene.getStylesheets().add("./main/style/style.css");
        //stage.setScene(scene);
    }

}
