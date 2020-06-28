package main.classes;


import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {

    public static void display(String title, String message, String btntext) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.getIcons().add(new Image("/main/resources/circuit.png"));

        Label label = new Label();
        label.setStyle("-fx-font-size: 14pt;");
        label.setMaxWidth(300);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setText(message);
        label.setPadding(new Insets(0,0,15,0));
        Button closeButton = new Button(btntext);
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30,30,30,30));

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("/main/style/style.css");
        window.setScene(scene);
        window.showAndWait();
    }

}