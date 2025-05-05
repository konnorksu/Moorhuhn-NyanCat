package com.example.nyanmoorhuhn01;


import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Cats extends ImageView {
    Timeline t;
    Timeline t1;
    Timeline t2;
    double speed;
    private int points;
    private double yy;
    private Image[] sprites;
    private int currentSprite;
    private double width;
    private double height;
    private int direction = 0;
    int direction1 = 0;
    private double w;
    private int death = 0; // 0-live 1-die
    private String name;
    public Cats(String name, int spritesCount, double speed, int points,
                double xx, double yy, double w, double h) {
        this.speed = speed;
        this.points = points;
        this.yy = yy;
        this.w = w;
        this.name = name;
        if(speed<4){
            width = w/3;
            height = h/3;
            this.points = 25;
        } else if(speed<10) {
            width = w/2;
            height = h/2;
            this.points = 10;
        } else {
            width = w;
            height = h;
            this.points = 5;
        }
        sprites = new Image[spritesCount];
        for(int i = 0; i<spritesCount; i++) {
            sprites[i] = new Image(name+i+".png", width, height, false, true);
        }
        if (Math.random()<0.5) {
            direction = 1; // 0 - left-right, 1 - right-left
            xx = 1500;
        }
        if(direction == 1){
            direction1 = 1;
            setImage(sprites[4]);
//            System.out.println("lavo");
            setLayoutX(xx);
            setLayoutY(yy);
        } else {
            direction1 = 0;
            setImage(sprites[0]);
            setLayoutX(xx);
            setLayoutY(yy);
        }
        t = new Timeline(new KeyFrame(Duration.millis(150), e -> {
            nextSprite();
        }));
        this.t1 = new Timeline(new KeyFrame(Duration.millis(45), ex -> {
            move();
        }));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        t1.setCycleCount(Timeline.INDEFINITE);
        t1.play();
    }

    protected void nextSprite() {
        if (direction == 1) {
            currentSprite = 4+((currentSprite+1)%4);
            setImage(sprites[currentSprite]);
        } else {
            currentSprite = (currentSprite+1)%4;
            setImage(sprites[currentSprite]);
        }
    }


    public void move() {
        if(direction1 == 1){
            setLayoutX(getLayoutX() - speed);
        } else {
            setLayoutX(getLayoutX() + speed);
        }
    }
    public void BoomDraw() {
//        score.add(points);
        if((direction == 1)&& (this.name.equals("nyan"))){
            this.setImage(new Image("boom1.png", width, height, false, false));
        } else if ((direction == 0)&& (this.name.equals("nyan"))){
            this.setImage(new Image("boom0.png", width, height, false, false));
        } else if ((direction == 1)&& (this.name.equals("tac"))){
            this.setImage(new Image("tac4.png", width, height, false, false));
            this.points = -50;
        } else {
            this.setImage(new Image("tac0.png", width, height, false, false));
            this.points = -50;
        }
        setLayoutX(getLayoutX());
        t2 = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            if(width == w/3){
                setLayoutX(getLayoutX());
                setLayoutY(getLayoutY() + 10);
                if (this.getLayoutY() > 890.0) {
                    t2.stop();
                    this.death = 1;
                }
            } else if(width == w/2){
                setLayoutX(getLayoutX());
                setLayoutY(getLayoutY() + 15);
                if (this.getLayoutY() > 890.0) {
                    t2.stop();
                    this.death = 1;
                }
            } else {
                setLayoutX(getLayoutX());
                setLayoutY(getLayoutY() + 20);
                if (this.getLayoutY() > 890.0) {
                    t2.stop();
                    this.death = 1;
                }
            }
        }));
        t2.setCycleCount(Timeline.INDEFINITE);
        t2.play();
    }

    public void Boom(){
        t.stop();
        this.t.stop();
        this.t1.stop();
        this.BoomDraw();
    }

    public int getDeath(){
        return this.death;
    }

    public int getPoints(){
        return this.points;
    }

}

