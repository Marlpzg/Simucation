package main;

import com.proudapes.jlatexmathfx.Control.LateXMathControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.w3c.dom.Element;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("./layout/index.fxml"));
        primaryStage.setTitle("SimuCation");
        Scene scene = new Scene(root, Values.WIDTH, Values.HEIGHT);
        primaryStage.getIcons().add(new Image("./main/resources/circuit.png"));
        scene.getStylesheets().add("./main/style/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
