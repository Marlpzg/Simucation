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
            if(Integer.parseInt(checkpoint) > 9) {
                checkpoint = "9";
                btnNext.setDisable(false);
            }
            maxStep = Integer.parseInt(checkpoint)-4;
            currStep = maxStep;
        }

        ArrayList<MefPart> step1 = new ArrayList<>();
        ArrayList<MefPart> step2 = new ArrayList<>();
        ArrayList<MefPart> step3 = new ArrayList<>();
        ArrayList<MefPart> step4 = new ArrayList<>();
        ArrayList<MefPart> step5 = new ArrayList<>();
        ArrayList<MefPart> step6 = new ArrayList<>();

        step1.add(new MefPart("Se genera una isoparametrización para poder establecer las mismas funciones de forma para cualquier elemento, utilizando el espacio (ε,η,ψ):","isoparametrization"));
        step1.add(new MefPart("En la figura anterior se han indicado los nodos locales del elemento, en cada uno de estos nodos se contiene un par de incógnitas A y B de la siguiente manera:","nodeContents"));
        step1.add(new MefPart("Ahora definitemos las funciones de forma que se utilizarán en toda nuestra simulación:","shapeFunctions"));
        steps.add(new MefStep("Paso 1 - Localización", step1));

        step2.add(new MefPart("Antes de continuar con el desarrollo interpolaremos las incógnitas para poder discretizar nuestro modelo y de esta forma poder computar todos los resultados:","interpolation"));
        steps.add(new MefStep("Paso 2 - Interpolación", step2));

        step3.add(new MefPart("Ahora sustituiremos nuestras nuevas incógnitas interpoladas dentro del modelo original, recuerda que nuestro modelo original es el siguiente:","modelSmall"));
        step3.add(new MefPart("Sustituyendo nuestras interpolaciones en el modelo original tendremos lo siguiente:","modelAprox"));
        step3.add(new MefPart("Con este nuevo modelo podemos despejar todo hacia el lado derecho de la ecuación para obtener la definición de nuestros residuales:","residuals"));
        steps.add(new MefStep("Paso 3 - Aproximación del Modelo", step3));

        step4.add(new MefPart("El método de los Residuos Ponderados establece que la integral del producto de los residuales por un \"peso\" en todo el volumen debe resultar en cero, por lo que tenemos las siguientes expresiones: ","step4Def"));
        step4.add(new MefPart("Debido a que se tiene una incógnita por nodo se deben efectuar estas integrales en los cuatro nodos del elemento, para eso definiremos un vector de pesos que contiene los pesos en cada uno de los nodos:","weights"));
        step4.add(new MefPart("Debido a que ambas ecuaciones del modelo resultan en escalares es posible asumir el mismo vector de pesos en ambas integrales; además ambas integrales son iguales a cero, por lo que pueden juntarse:","step4Impl"));
        steps.add(new MefStep("Paso 4 - Método de Residuos Ponderados", step4));

        step5.add(new MefPart("El método de Galerkin consiste en simplificar el problema asumiento que los pesos tienen el mismo valor de las funciones de forma (ya que cumplen con los mismos requisitos), por lo que se tiene lo siguiente:","galerkin"));
        step5.add(new MefPart("Ahora en base a esto se modifica la integral establecida en el paso 4, quedando de la siguiente forma:","step5Eval"));
        step5.add(new MefPart("Se distribuye el vector de pesos en cada una de las componentes del integrando, y luego se separa en varias integrales:","step5Op"));
        step5.add(new MefPart("Se sabe que los vectores de incógnitas son constantes, por lo que pueden extraerse de cualquier derivada e integral. Debes tener en cuenta que estos son vectores, por lo que deben conservar el orden de precedencia, es decir, deben mantenerse a la derecha:","step5Colors"));
        step5.add(new MefPart("Ahora asignaremos una letra a cada una de las matrices que resultarán de las integrales anteriores, cada letra conserva el color de la integral que representa para que las puedas identificar mejor. Recuerda que las letras en mayúscula representan matrices y las letras en minúscula representan vectores:","step5Letters"));
        step5.add(new MefPart("Para finalizar, separaremos nuevamente esta ecuación en dos ecuaciones. El resultado de esta separación es la \"Forma fuerte\" del sistema:","strongForm"));
        steps.add(new MefStep("Paso 5 - Método de Galerkin", step5));

        step6.add(new MefPart("Ahora nos encontramos con un problema nuevo: las matrices G y D contienen una doble derivación, sin embargo esta doble derivación contiene en su interior funciones de forma lineales (que al derivarse dos veces resultarían en cero). Para solucionar esto haremos uso de la integración por partes:","theCow"));
        step6.add(new MefPart("Iniciaremos evaluando la matriz G:","matrixG1"));
        step6.add(new MefPart("Ensamblamos el resultado de la integral con la fórmula de la Integración por Partes (de igual manera respetando el orden de precedencia original de las matrices), y además establecemos la primera parte de la solución como el vector de Neumann y la segunda parte será la matriz K:","matrixG2"));
        step6.add(new MefPart("Ahora continuaremos con la solución de la matriz D:","matrixD1"));
        step6.add(new MefPart("Igual que en la matriz G ensamblamos el resultado de la integral con la fórmula de la Integración por Partes (de igual manera respetando el orden de precedencia original de las matrices), y además establecemos la primera parte de la solución como el vector de Neumann y la segunda parte será la matriz L:","matrixD2"));
        step6.add(new MefPart("Ahora nuestro Sistema de Ecuaciones Lineales ha cambiado, esta nueva representación es conocida como la \"Forma débil\". Omitiendo temporalmente el vector de Neumann tenemos lo siguiente:","weakForm"));
        step6.add(new MefPart("Ahora representaremos el SEL en su forma matricial, esta vez sí agregaremos el vector de neumann y lo despejaremos al lado derecho:","localSystem"));
        step6.add(new MefPart("Finalmente podemos decir que nuestro sistema local es de la forma:","localSystem2"));
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
                String saveVal = (4+maxStep)+"";
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
        Parent root = FXMLLoader.load(getClass().getResource("/main/layout/condIntro.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("/main/style/style.css");

        stage.setScene(scene);
    }

    @FXML
    private void nextScene(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Saving.save("10");
        Parent root = FXMLLoader.load(getClass().getResource("/main/layout/defSteps.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("/main/style/style.css");
        stage.setScene(scene);
    }

}
