package mpdev.aoc2022.utils.ropeannimation;

import java.awt.*;
import java.util.LinkedList;

public class Rope {
    private final int BODY_WIDTH = 20;
    private final int SNAKE_SPEED = BODY_WIDTH;
    private final int startX = 200;
    private final int startY = 100;

    private LinkedList<Rectangle> body;
    private Directions direction;

    public Rope() {
        direction = Directions.RIGHT;
        body = new LinkedList<>();
        body.add(new Rectangle(startX, startY+3*BODY_WIDTH, BODY_WIDTH, BODY_WIDTH));
        body.add(new Rectangle(startX-BODY_WIDTH, startY+2*BODY_WIDTH, BODY_WIDTH, BODY_WIDTH));
        body.add(new Rectangle(startX-2*BODY_WIDTH, startY+BODY_WIDTH, BODY_WIDTH, BODY_WIDTH));
        body.add(new Rectangle(startX-3*BODY_WIDTH, startY, BODY_WIDTH, BODY_WIDTH));
        body.add(new Rectangle(startX-4*BODY_WIDTH, startY, BODY_WIDTH, BODY_WIDTH));
        body.add(new Rectangle(startX-5*BODY_WIDTH, startY, BODY_WIDTH, BODY_WIDTH));
        body.add(new Rectangle(startX-6*BODY_WIDTH, startY, BODY_WIDTH, BODY_WIDTH));
        body.add(new Rectangle(startX-7*BODY_WIDTH, startY, BODY_WIDTH, BODY_WIDTH));
        body.add(new Rectangle(startX-8*BODY_WIDTH, startY, BODY_WIDTH, BODY_WIDTH));
        body.add(new Rectangle(startX-9*BODY_WIDTH, startY, BODY_WIDTH, BODY_WIDTH));
    }

    public LinkedList<Rectangle> getBody() {
        return body;
    }

    public void setUpDirection() {
        if (direction != Directions.UP)
            direction = Directions.UP;
    }

    public void setDownDirection() {
        if (direction != Directions.DOWN)
            direction = Directions.DOWN;
    }

    public void setRightDirection() {
        if (direction != Directions.RIGHT)
            direction = Directions.RIGHT;
    }

    public void setLeftDirection() {
        if (direction != Directions.LEFT)
            direction = Directions.LEFT;
    }

    public void move() {
        Rectangle newHead = body.removeLast();
        newHead.x = body.peek().x;
        newHead.y = body.peek().y;
        switch (direction) {
            case UP -> newHead.y -= SNAKE_SPEED;
            case DOWN -> newHead.y += SNAKE_SPEED;
            case RIGHT -> newHead.x += SNAKE_SPEED;
            case LEFT -> newHead.x -= SNAKE_SPEED;
        }
        body.addFirst(newHead);
    }
}