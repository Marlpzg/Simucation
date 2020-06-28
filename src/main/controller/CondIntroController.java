package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Values;
import main.classes.AlertBox;
import main.classes.Saving;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CondIntroController implements Initializable {

    @FXML
    private TextField txtDir;

    @FXML
    private TextField txtNeu;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnVerif;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String checkpoint = Saving.load();
        if (checkpoint != null){
            if(Integer.parseInt(checkpoint) > 3) {
                btnNext.setDisable(false);
            }
        }
    }

    @FXML
    private void prevScene(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/layout/model.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("/main/style/style.css");

        stage.setScene(scene);
    }

    @FXML
    private void nextScene(ActionEvent event) throws Exception{

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Saving.save("4");
        Parent root = FXMLLoader.load(getClass().getResource("/main/layout/mefSteps.fxml"));
        Scene scene = new Scene(root, Values.getWidth(), Values.getHeight());
        scene.getStylesheets().add("/main/style/style.css");
        stage.setScene(scene);
    }

    @FXML
    private void checkAns(ActionEvent event) throws Exception{

        ArrayList<Integer> neumann = new ArrayList<>();
        ArrayList<Integer> dirichlet = new ArrayList<>();
        neumann.add(2);
        neumann.add(3);
        neumann.add(6);
        neumann.add(7);
        dirichlet.add(5);
        dirichlet.add(6);
        dirichlet.add(7);
        dirichlet.add(8);

        String[] neuTxt = txtNeu.getText().split(",");
        String[] dirTxt = txtDir.getText().split(",");

        if(neuTxt.length == neumann.size()){
            for (int i = 0; i<4; i++){
                try{
                    if(neumann.contains(Integer.parseInt(neuTxt[i].trim()))){
                        neumann.remove(neumann.indexOf(Integer.parseInt(neuTxt[i].trim())));
                    }
                }catch(Exception e){
                    AlertBox.display("Error", "Solo pueden ingresarse números separados por comas (,).", "Aceptar");
                    txtNeu.requestFocus();
                    break;
                }
            }
            if(neumann.size() == 0){

                if(dirTxt.length == dirichlet.size()){
                    for (int i = 0; i<4; i++){
                        try{
                            if(dirichlet.contains(Integer.parseInt(dirTxt[i].trim()))){
                                dirichlet.remove(dirichlet.indexOf(Integer.parseInt(dirTxt[i].trim())));
                            }
                        }catch(Exception e){
                            AlertBox.display("Error", "Solo pueden ingresarse números separados por comas (,).", "Aceptar");
                            txtDir.requestFocus();
                            break;
                        }
                    }
                    if(dirichlet.size() == 0){

                        AlertBox.display("Éxito", "¡Muy bien! Has respondido correctamente.", "Continuar");
                        txtNeu.setDisable(true);
                        txtDir.setDisable(true);
                        btnVerif.setDisable(true);
                        btnNext.setDisable(false);
                        btnNext.requestFocus();

                    }else{
                        AlertBox.display("Error", "Los nodos de la condición de Dirichlet no son correctos.", "Aceptar");
                        txtDir.requestFocus();
                    }
                }else{
                    AlertBox.display("Error", "La cantidad de nodos con condición de Dirichlet es exactamente 4.", "Aceptar");
                    txtDir.requestFocus();
                }

            }else{
                AlertBox.display("Error", "Los nodos de la condición de Neumann no son correctos.", "Aceptar");
                txtNeu.requestFocus();
            }
        }else{
            AlertBox.display("Error", "La cantidad de nodos con condición de Neumann es exactamente 4.", "Aceptar");
            txtNeu.requestFocus();
        }

    }

}
