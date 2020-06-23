package main.classes;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChoiceBox {

    static boolean retVal;

    public static Boolean display(String title, String message, String yes, String no) {
        retVal = false;
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.getIcons().add(new Image("./main/resources/circuit.png"));

        Label label = new Label();
        label.setStyle("-fx-font-size: 14pt;");
        label.setMaxWidth(300);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setText(message);
        label.setPadding(new Insets(0,0,15,0));
        Button closeButton = new Button(no);
        closeButton.setOnAction(e -> {
            retVal = false;
            window.close();
        });
        Button acceptButton = new Button(yes);
        acceptButton.setOnAction(e -> {
            retVal = true;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton, acceptButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30,30,30,30));

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("./main/style/style.css");
        window.setScene(scene);
        window.showAndWait();
        return retVal;
    }

}