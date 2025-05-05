package com.example.nyanmoorhuhn01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root, 1500, 870);
        stage.setTitle("Nyan!!!");

        Menu menu = new Menu(root, scene);
        root.getChildren().add(menu);
        scene.getStylesheets().add("style.css");
//        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}