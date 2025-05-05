package com.example.nyanmoorhuhn01;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Donuts extends Group {
    private final int countDonuts = 8;
    private int widthBar = 576;
    private int heightBar = 74;
    private int widthDonuts = 64;
    private int heightDonuts = 64;
    private int donuts = 8;
    private boolean done = false;
    private ImageView donutsPng[] = new ImageView[donuts];
    public Donuts() {
        for (int i = 0; i < countDonuts; i++) {
            donutsPng[i] = new ImageView(new Image("donut.png", widthDonuts, heightDonuts, false, false));
            donutsPng[i].setLayoutX(i * widthBar / countDonuts + 8);
            donutsPng[i].setLayoutY(heightBar - 8);
            getChildren().add(donutsPng[i]);
        }
    }
        public void deliteDonut(){
            if (donuts >= 1) {
                donuts--;
                getChildren().remove(donutsPng[donuts]);
                donutCheck();
            } else {
                this.done = true;
                donutCheck();
            }
        }

        public boolean donutCheck(){
            if (donuts <= 0) {
                this.done = true;
            }
            return done;
        }

        public void addDonuts(){
            if (this.done) {
                for (int i = 0; i < countDonuts; i++) {
                    donutsPng[i].setLayoutX(i*widthBar/countDonuts+8);
                    donutsPng[i].setLayoutY(heightBar-8);
                    getChildren().add(donutsPng[i]);
                    this.done = false;
                }
                donuts = countDonuts;
            }
        }


}
