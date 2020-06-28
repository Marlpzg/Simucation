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
            if(Integer.parseInt(checkpoint) > 18) {
                checkpoint = "18";
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

        //10
        interp.add(new MefPart("Para la definición de los siguientes componentes de las matrices será necesario interpolar los valores de x,y,z en términos de ε,η,ψ. Haciendo uso de las funciones de forma originales se obtiene la siguiente interpolación:","varInterp"));
        interp.add(new MefPart("Simplificaremos un poco el contenido de las interpolaciones definiendo una nueva notación e implementándola:","varInterpSimpl"));
        steps.add(new MefStep("Interpolación de variables", interp));

        //11
        chain.add(new MefPart("También se deberemos hacer uso de la regla de la cadena para los cálculos de gradientes ya que las funciones de forma dependen de variables diferentes a las espaciales. La regla de la cadena generalizada para una matriz A cualquiera es la siguiente:","chainRule"));
        steps.add(new MefStep("Regla de la cadena", chain));

        //12
        intro.add(new MefPart("Ahora sí podemos proceder a la resolución de cada matriz del sistema, nosotros te guiaremos paso por paso pero omitiremos algunas operaciones como integrales y derivadas (ya que resultan en un proceso muy engorroso), pero te invitamos a que intentes resolverlas por tu cuenta para verificar los resultados. Así que ¡En marcha!",null));
        steps.add(new MefStep("Definición de componentes", intro));

        //13
        vf.add(new MefPart("Daremos inicio con el vector f ya que resulta particularmente sencillo:","vf1"));
        vf.add(new MefPart("Antes de resolver esas integrales debes considerar que al trabajar en el espacio dV = dz dy dx, sin embargo la integral debe estar expresada en el espacio isoparamétrico (ε,η,ψ). Haciendo uso del concepto de Jacobiano es posible modificar los diferenciales:","vf2"));
        vf.add(new MefPart("El jacobiano se define de la siguiente forma (y si te fijas detenidamente todos sus componentes son constantes):","vf3"));
        vf.add(new MefPart("Y ahora resolvemos todas las integrales dentro del vector extrayendo el Jacobiano ya que es constante:","vf4"));
        vf.add(new MefPart("Y el resultado será la definición de nuestro vector f:","vf5"));
        steps.add(new MefStep("Componentes del vector f", vf));

        //14
        ve.add(new MefPart("El vector e no difiere del vector f, por lo cual la respuesta será prácticamente la misma:","ve1"));
        ve.add(new MefPart("Por lo tanto nuestro vector e tendrá la siguiente forma:","ve2"));
        steps.add(new MefStep("Componentes del vector e", ve));

        //15
        mK.add(new MefPart("Ahora resolveremos para la matriz K, en este caso se presentan algunas complicaciones como la operación de ∇, que debe operarse antes de realizar las integrales y siguiendo la regla de la cadena:","mk1"));
        mK.add(new MefPart("∇εN se convertirá en nuestra matriz β:","mk2"));
        mK.add(new MefPart("También recordemos que el valor de ∇εX ya ha sido encontrado antes:","mk3"));
        mK.add(new MefPart("Con esto en mente podemos encontrar la inversa de esa matriz, al determinante de ∇εX lo llamaremos \"Det\" para ocuparlo más adelante:","mk4"));
        mK.add(new MefPart("Ahora será necesario encontrar la matriz Adjunta de ∇εX a la cual llamaremos matriz α, se definirá también una nueva notación para simplificar contenido:","mk5"));
        mK.add(new MefPart("Con esto ya podemos encontrar ∇N juntando todas las partes:","mk6"));
        mK.add(new MefPart("Antes de poder resolver la integral todavía nos queda resolver otra operación, para este paso definiremos una nueva matriz de manera que haya mejor legibilidad e introduciremos ∇ en la transpuesta:","mk7"));
        mK.add(new MefPart("Ahora deberemos auxiliarnos de nuevo por la regla de la cadena, y podremos notar que ya hemos resuelto una parte:","mk8"));
        mK.add(new MefPart("Procedemos a resolver el resto para poder completar la regla de la cadena:","mk9"));
        mK.add(new MefPart("Luego de esto podemos transponer la matriz:","mk10"));
        mK.add(new MefPart("Finalmente podemos proceder a resolver para la matriz K:","mk11"));
        mK.add(new MefPart("Extraemos de la integral todo lo que permanezca constante e introducimos la integral a cada una de las posiciones de la matriz:","mk12"));
        mK.add(new MefPart("Se deben resolver todas esas integrales recordando el concepto de jacobiano, las integrales tendrán la siguiente forma:","volumeIntegral"));
        mK.add(new MefPart("Al resolver todas las integrales tendremos una matriz bastante extensa:","mk13"));
        mK.add(new MefPart("A esta matriz, sin considerar la constante a la izquierda, se le denominará λ. Con esto ya tenemos definida nuestra matriz K:","mk14"));
        mK.add(new MefPart("Ufffff... vaya eso ha sido bastante trabajo ¿no? Siéntete libre de tomarte un descanso luego de dar clic en \"Siguiente Paso\" para que se guarde tu progreso. \nMientras tanto ten un chiste, te lo has ganado:",null, "\n\"¿Qué es un niño complejo?\nUn niño con la madre real y el padre imaginario.\""));
        steps.add(new MefStep("Componentes de la matriz K", mK));

        //16
        mL.add(new MefPart("¡Muy bien! ahora continuaremos con la definición de componentes de las matrices. El desarrollo de la matriz L es muy similar al de la matriz K, por lo que se simplificará mucho el trabajo, recuerda que la matriz L es la siguiente:","mL1"));
        mL.add(new MefPart("Recordemos que ya hemos definido anteriormente el resultado de ∇N:","nablaN"));
        mL.add(new MefPart("Ahora antes de resolver la integral debemos resolver otra operación, para este paso nuevamente definiremos una matriz de manera que haya mejor legibilidad e introduciremos ∇ en la transpuesta:","mL2"));
        mL.add(new MefPart("Resolveremos de nuevo la parte interna y luego transpondremos la matriz resultante. Volvemos a valernos de la regla de la cadena y aprovechamos que ésta ya ha sido resuelta parcialmente:","mL3"));
        mL.add(new MefPart("Continuando con la resulución tenemos lo siguiente:","mL4"));
        mL.add(new MefPart("Ahora transponemos el resultado para poder continuar con la resolución de la matriz L:","mL5"));
        mL.add(new MefPart("Juntamos todas las partes de la integral:","mL6"));
        mL.add(new MefPart("Extraemos de la integral todo lo que permanezca constante e introducimos la integral a cada una de las posiciones de la matriz:","mL7"));
        mL.add(new MefPart("Se deben resolver todas esas integrales recordando el concepto de jacobiano, recordar que las integrales tendrán la siguiente forma:","volumeIntegral"));
        mL.add(new MefPart("Al resolver todas las integrales tendremos una matriz un poco menos extensa que la matriz λ:","mL8"));
        mL.add(new MefPart("De igual forma que con la matriz λ, a ésta matriz sin considerar la constante a la izquierda le asignaremos el nombre de matriz μ. Con esto ya tenemos definida nuestra matriz L:","mL9"));
        steps.add(new MefStep("Componentes de la matriz L", mL));

        //17
        mC.add(new MefPart("Ahora ya podemos resolver la matriz C, de igual forma que con las matrices anteriores es necesario determinar el integrando primero. Podemos aprovechar que el proceso no varía mucho:","mC1"));
        mC.add(new MefPart("En esta ocasión no es necesario realizar procesos derivativos así que simplemente juntamos todo dentro de la integral y extraemos lo que permanezca constante:","mC2"));
        mC.add(new MefPart("El resultado de esa integral será una matriz, a la que le llamaremos matriz φ. Sin embargo tenemos un problema cuando analizamos las dimensiones de las matrices:","mC3"));
        mC.add(new MefPart("Como podrás observar ese producto no puede resolverse, eso se debe a que en este caso hay que modificar nuestros pesos de Galerkin de manera que sea posible efectuar el producto, la modificación no debe cambiar la idea general que hemos venido utilizando. Hemos definido una nueva matriz N considerando el coeficiente variable, así que adaptaremos el nuevo peso:","mC4"));
        mC.add(new MefPart("Ahora sí es posible realizar el producto por lo que resolveremos:","mC5"));
        mC.add(new MefPart("Se deben resolver todas esas integrales recordando el concepto de jacobiano, recordar que las integrales tendrán la siguiente forma:","volumeIntegral"));
        mC.add(new MefPart("Resolviendo las integrales se tiene la siguiente matriz:","mC6"));
        mC.add(new MefPart("De nuevo, sin considerar la constante de la izquierda, le asignaremos un nombre. El nombre que se había establecido es matriz φ, por lo que la matriz C será la siguiente:","mC7"));
        steps.add(new MefStep("Componentes de la matriz C", mC));

        //18
        mH.add(new MefPart("¡Ya casi hemos terminado! La matriz H es muy similar a la matriz C en su forma de resolución, por lo que seguiremos esos pasos con menos detalle:","mH1"));
        mH.add(new MefPart("Ya podemos proceder a resolver la integral de la matriz H:","mH2"));
        mH.add(new MefPart("Una vez más se debe usar el concepto de jacobiano, recordar que las integrales tendrán la siguiente forma:","volumeIntegral"));
        mH.add(new MefPart("Resolviendo las integrales tendremos como resultado una matriz muy extensa:","mH3"));
        mH.add(new MefPart("Una vez más le asignaremos un nombre a esta matriz sin considerar la constante de la izquierda. Esta matriz se denominará matriz ω. \nCon esto hemos definido completamente la matriz H:","mH4"));
        mH.add(new MefPart("¡Increíble! Estamos muy cerca de completar nuestra implementación del MEF en 3D. Este es un muy buen momento para tomarte un descanso luego de dar clic en \"Siguiente\" para que se guarde tu progreso. \nComo sabemos que te has esforzado mucho te tenemos preparada una demostración para que te relajes:","demo"));
        steps.add(new MefStep("Componentes de la matriz H", mH));

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
            ImageView img = new ImageView(new Image("/main/resources/process/"+steps.get(currStep).getParts().get(currPart).getImg()+".png"));
            spImgCont.getChildren().clear();
            spImgCont.getChildren().add(img);
        }else{
            spImgCont.getChildren().clear();
        }

        if(steps.get(currStep).getParts().get(currPart).getText2() != null){
            Label lblText = new Label(steps.get(currStep).getParts().get(currPart).getText2());
            lblText.setPrefHeight(300);
            lblText.setStyle("-fx-font-size: 18pt;");
            spImgCont.getChildren().add(lblText);
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

        setupStage();

    }

    @FXML
    private void prevScene(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/layout/mefSteps.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("/main/style/style.css");

        stage.setScene(scene);
    }

    @FXML
    private void nextScene(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Saving.save("19");
        Parent root = FXMLLoader.load(getClass().getResource("/main/layout/assembly.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("/main/style/style.css");
        stage.setScene(scene);

    }

}
