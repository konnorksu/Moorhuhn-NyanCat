package com.example.nyanmoorhuhn01;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

import java.io.File;

public class Menu extends Group {
    private Scene scene;
    private Text text;
    private Text text1;
    public MediaPlayer mediaPlayer;
    public String path = "src/main/resources/space.mp3";
    public Media media;
    public MediaPlayer mediaPlayer1;
    public MediaView mediaView;
    public Group root;
    public Menu(Group root, Scene scene) {
        this.root = root;
        this.scene = scene;
        ImageView background = new ImageView(new Image("background.png", 1920,
                1200, false, false));

        background.setFitHeight(scene.getHeight());
        background.setFitWidth(scene.getWidth());

        root.getChildren().addAll(background);

        media = new Media(new File(path).toURI().toString());
        mediaPlayer1 = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer1);
        root.getChildren().add(mediaView);
        mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer1.play();

        this.text = new Text("RMB - shoot   \n \nLMB - reload");
        text.getStyleClass().add("menu-Text");

        this.text1 = new Text("  Play  ");
        text1.getStyleClass().add("menu-Text");

        this.text.setLayoutX(scene.getWidth() / 2-200);
        this.text.setLayoutY(scene.getHeight() / 2 - 100);
        this.text1.setLayoutX(scene.getWidth() / 2-200);
        this.text1.setLayoutY(scene.getHeight() / 2 + 50);

        root.getChildren().add(this.text);
        root.getChildren().add(this.text1);

        text1.setOnMouseClicked(event -> {
            mediaPlayer1.stop();
            getChildren().remove(this);
            getChildren().add(new Game(root, scene));
        });
    }

}
