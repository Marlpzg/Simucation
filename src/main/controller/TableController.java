package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.classes.Element;

import java.net.URL;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private TableView tbTabla;

    @FXML
    private TableColumn colElement;

    @FXML
    private TableColumn colNode1;
    @FXML
    private TableColumn colNode2;
    @FXML
    private TableColumn colNode3;
    @FXML
    private TableColumn colNode4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colElement.setStyle( "-fx-alignment: CENTER;");
        colNode1.setStyle( "-fx-alignment: CENTER;");
        colNode2.setStyle( "-fx-alignment: CENTER;");
        colNode3.setStyle( "-fx-alignment: CENTER;");
        colNode4.setStyle( "-fx-alignment: CENTER;");


        colElement.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNode1.setCellValueFactory(new PropertyValueFactory<>("n1"));
        colNode2.setCellValueFactory(new PropertyValueFactory<>("n2"));
        colNode3.setCellValueFactory(new PropertyValueFactory<>("n3"));
        colNode4.setCellValueFactory(new PropertyValueFactory<>("n4"));

        tbTabla.getItems().add(new Element("1","1","2","3","6"));
        tbTabla.getItems().add(new Element("2","6","3","8","7"));
        tbTabla.getItems().add(new Element("3","3","1","8","4"));
        tbTabla.getItems().add(new Element("4","8","5","1","6"));
        tbTabla.getItems().add(new Element("5","6","3","1","8"));

    }

}
