package com.comert;


import com.comert.ball.Ball;
import com.comert.ball.BallFactory;
import com.comert.timer.GameListener;
import com.comert.timer.GameTimer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public final class App extends Frame implements GameListener {

    private final static List<Ball> ballList = new ArrayList<>();

    private final int gameSpeed = 250;
    private final GameTimer timer = new GameTimer(this, gameSpeed);
    private int drawnBall = 0;

    public App() throws HeadlessException {
        this.setBounds(200, 200, 1000, 1000);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final int x = e.getX();
                final int y = e.getY();
                Ball requestedBall = BallFactory.getBall(x, y);
                ballList.add(requestedBall);
                drawnBall++;
            }
        });

        this.setVisible(true);
    }

    public void printDrawnBall() {
        System.out.println("Total Drawn Balls : " + drawnBall);
    }

    @Override
    public void paint(Graphics graphics) {
        ballList.forEach(ball -> {
            ball.move();
            ball.draw(graphics);
        });
    }

    @Override
    public void actionPerformed() {
        ballList.removeIf(ball -> !ball.isAlive()); // remove dead balls from ballList
        repaint();
    }

    public void startGame() {
        timer.startGame();
    }

    public void joinGameThread() throws InterruptedException {
        timer.join();
    }

    public void stopGame() {
        timer.stopGame();
        this.dispose();
    }
}
