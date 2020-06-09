package main.controller;

import com.proudapes.jlatexmathfx.Control.LateXMathControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private VBox layBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String latex = "\\left\\{\\begin{array}{c}\n" +
                "x \\nabla(A)+y^{2} \\nabla(\\nabla(B))=10.9 \\\\\n" +
                "(x-y) \\nabla(\\nabla(A))+(x+y) \\nabla(B)=40.8\n" +
                "\\end{array}\\right.";

        LateXMathControl lc=new LateXMathControl(latex);
        StackPane pane=new StackPane();
        pane.getChildren().add(lc);
        layBox.getChildren().add(pane);

    }
}
