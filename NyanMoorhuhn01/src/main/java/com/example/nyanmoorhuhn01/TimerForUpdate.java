package com.example.nyanmoorhuhn01;

import javafx.animation.AnimationTimer;

public class TimerForUpdate extends AnimationTimer {
    private long lastUpdate;
    private Game game;
    private long dTime;
    public TimerForUpdate(Game game) {
        this.game = game;
    }

    @Override
    public void handle(long now) {
        game.obnovit(now - lastUpdate);
        lastUpdate = now;
    }
    @Override
    public void start() {
        lastUpdate = System.nanoTime();
        super.start();
    }
}
