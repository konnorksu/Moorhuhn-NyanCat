package com.example.nyanmoorhuhn01;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Game extends Group {
    private Stage stage;
    private Scene scene;
    private Donuts donuts;
    public Group root;
    public String path = "src/main/resources/nyan.mp3";
    public String path2 = "src/main/resources/pong.mp3";
    public Media media;
    public Media media2;
    public MediaPlayer mediaPlayer;
    public MediaPlayer mediaPlayer2;
    public MediaView mediaView;
    public MediaView mediaView2;
    public Text scorebar = new Text();
    public int score = 0;
    public Text timebar = new Text();
    public int time;
    private int mintime = 0;
    private Timeline Сountdown;
    private ArrayList<Cats> cats = new ArrayList<>();
    private ArrayList<Cats> tacs = new ArrayList<>();
    private TimerForUpdate timer = new TimerForUpdate(this);

    public Game(Group root, Scene scene) {
        this.root = root;
        this.scene = scene;
        this.time = 60;
        ImageView background = new ImageView(new Image("background.png", 1920,
                1200, false, false));

        background.setFitHeight(scene.getHeight());
        background.setFitWidth(scene.getWidth());

        root.getChildren().addAll(background);

        donuts = new Donuts();
        donuts.setLayoutX(scene.getWidth() - 590);
        donuts.setLayoutY(scene.getHeight() - 74 * 2);
        root.getChildren().addAll(donuts);

        media = new Media(new File(path).toURI().toString());
        media2 = new Media(new File(path2).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer2 = new MediaPlayer(media2);
        mediaView = new MediaView(mediaPlayer);
        mediaView2 = new MediaView(mediaPlayer2);
        root.getChildren().add(mediaView);
        root.getChildren().add(mediaView2);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        timer.start();
        mediaPlayer.play();
        this.Сountdown = new Timeline(new KeyFrame(Duration.seconds(1.0), (ex) -> {
            --this.time;
            timebar.setText(String.format("Time:%2d:%02d", this.time / 60, this.time % 60));
        }), new KeyFrame(Duration.seconds(1.0), (ex) -> {
        }));
        timebar.setLayoutX(100);
        timebar.setLayoutY(30);
        this.Сountdown.setCycleCount(time);
        this.Сountdown.play();
        this.Сountdown.setOnFinished((a) -> {
            this.Сountdown.stop();
            timer.stop();
            mediaPlayer.stop();
            getChildren().add(new Over(root, scene, score));
        });

        scorebar.setText("Score: " + score);
        scorebar.setLayoutX(100);
        scorebar.setLayoutY(60);

        root.getChildren().addAll(timebar, scorebar);
    }

    public void obnovit(long dTime) {
        if (Math.random() < 0.01) {
            Cats cat = new Cats("nyan", 8, Math.random() * 20 + 2, 300,
                    0, Math.random() * 650 + 50, 154, 74);
            root.getChildren().add(cat);
            cats.add(cat);
        } else if (Math.random() < 0.001) {
            Cats tac = new Cats("tac", 8, Math.random() * 20, -50,
                    0, Math.random() * 650 + 50, 154, 74);
            root.getChildren().add(tac);
            tacs.add(tac);
        }

        Iterator<Cats> it = this.cats.iterator();
        while (it.hasNext()) {
            Cats cat = it.next();
            cat.setOnMouseClicked((event) -> {
//                it.remove();
                if (event.getButton() == MouseButton.PRIMARY) {
                    if(donuts.donutCheck() != true) {
                        cat.Boom();
                        score = score + cat.getPoints();
                        this.scorebar.setText("Score: " + this.score);
                    }
                }
            });
            if (cat.getDeath() == 1) {
                this.getChildren().remove(cat);
                it.remove();
            }
            if (cat.getLayoutX() > scene.getWidth() + 100) {
                it.remove();
//                cat.delete();
            }
        }
        Iterator<Cats> it1 = this.tacs.iterator();
        while (it1.hasNext()) {
            Cats tac = it1.next();
            tac.setOnMouseClicked((event) -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if (donuts.donutCheck() != true) {
                        tac.Boom();
                        score = score + tac.getPoints();
                        this.scorebar.setText("Score: " + this.score);
                    }
                }
            });
            if (tac.getDeath() == 1) {
                this.getChildren().remove(tac);
                it1.remove();
            }
            if (tac.getLayoutX() > scene.getWidth() + 100) {
                it1.remove();
//                cat.delete();
            }
        }
        scene.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.SECONDARY) {
                donuts.addDonuts();
            } else {
                if (donuts.donutCheck() != true){
                    mediaPlayer2.play();
                    mediaPlayer2.seek(Duration.seconds(0.0));
                }
                donuts.deliteDonut();

            }
        });

    }

    public int getScore() {
        return score;
    }
}
