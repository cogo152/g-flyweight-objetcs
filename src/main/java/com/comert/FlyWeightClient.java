package com.comert;

import com.comert.ball.BallFactory;

public class FlyWeightClient {

    public static void main(String[] args) {
        App game = new App();
        game.startGame();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.stopGame();
        try {
            game.joinGameThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // although total drawn ball size is too many, total object size is not
        game.printDrawnBall();
        BallFactory.printBallSatetes();

    }

}
