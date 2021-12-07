package com.comert.ball;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class BallFactory {

    private static Ball prototype = new Ball();
    private static List<Ball> pool = new ArrayList<>();

    static { // pool size initialization with 5 element
        for (int i = 0; i < 5; i++) {
            pool.add(prototype.clone());
        }
    }

    public static Ball getBall(final int x, final int y) {
        final Optional<Ball> ball = pool.stream()
                .filter(deadBall -> !deadBall.isAlive())
                .findFirst(); // return dead ball

        if (ball.isPresent()) {
            final Ball ballAvailable = ball.get();
            ballAvailable.setX(x);
            ballAvailable.setY(y);
            ballAvailable.setSize(50);
            ballAvailable.setAlive(true);
            ballAvailable.setGrowthUp(false);
            return ballAvailable;
        } else {
            final Ball ballNew = prototype.clone();
            ballNew.setX(x);
            ballNew.setY(y);
            ballNew.setAlive(true);
            pool.add(ballNew);
            return ballNew;
        }
    }

    public static void printBallSatetes() {
        final int totalBalls = pool.size();
        int liveBalls;
        int deadBalls;

        liveBalls = (int) pool.stream().filter(Ball::isAlive).count();
        deadBalls = (int) pool.stream().filter(ball -> !ball.isAlive()).count();

        System.out.println("Total Ball Size: " + totalBalls);
        System.out.println("Live Ball Size: " + liveBalls);
        System.out.println("Dead Ball Size: " + deadBalls);

    }

}
