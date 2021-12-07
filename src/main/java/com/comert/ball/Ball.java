package com.comert.ball;

import com.comert.Drawable;
import com.comert.Movable;

import java.awt.*;

public final class Ball implements Cloneable, Drawable, Movable {

    private static final int BALL_SPEED = 50;
    private static final int BALL_ALIVE_BOUNDARY = 1000; // after boundry violating in game frame, ball state is false means dead
    private static final int BALL_SIZE_CHANGE_BOUNDARY = 500; // after boundry violating in game frame, ball state is false means dead

    // Dışa bağımlı değişkenler
    private boolean alive; // if the ball is alive on game frame before BALL_ALIVE_BOUNDARY
    private int x, y;
    private int size;
    private boolean growthUp; // if the ball is small on game frame before BALL_SIZE_CHANGE_BOUNDARY

    public Ball() {
        this.x = 200;
        this.y = 200;
        this.size = 50;
        this.alive = false;
        this.growthUp = false;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.fillOval(x, y, size, size);
    }

    @Override
    public void move() {
        y += BALL_SPEED;
        if (!growthUp && y >= BALL_SIZE_CHANGE_BOUNDARY) { // grow up
            multiplySize();
            growthUp = true;
        }
        if (y >= BALL_ALIVE_BOUNDARY) {
            alive = false; // die
        }
    }

    private void multiplySize() {
        size *= 2;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(final boolean alive) {
        this.alive = alive;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setGrowthUp(boolean growthUp) {
        this.growthUp = growthUp;
    }

    @Override
    protected Ball clone() {
        Ball ball = null;
        try {
            ball = (Ball) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return ball;
    }
}
