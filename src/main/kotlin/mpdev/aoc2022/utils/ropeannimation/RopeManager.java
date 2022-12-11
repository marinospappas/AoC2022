package mpdev.aoc2022.utils.ropeannimation;

import java.awt.*;

public class RopeManager {
    private Color snakeColor;
    private Color snakeColorHead;
    private Color snakeColorTail;
    private Rope rope;
    private RopePanel ropePanel;
    private boolean running;

    public RopeManager(RopePanel ropePanel) {
        this.ropePanel = ropePanel;
        snakeColor = Color.GREEN;
        snakeColorHead = Color.RED;
        snakeColorTail = Color.BLUE;
        rope = new Rope();
        running = true;
    }

    public void gameLoop() {
        update();
        draw();
    }

    public void renderRope(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        Rectangle tile;
        graphics2D.setColor(snakeColorTail);
        tile = rope.getBody().getLast();
        graphics2D.fillRect(tile.x, tile.y, tile.width, tile.height);
        graphics2D.setColor(snakeColor);
        for (int i = 1; i < rope.getBody().size()-1; ++i) {
            tile = rope.getBody().get(i);
            graphics2D.fillRect(tile.x, tile.y, tile.width, tile.height);
        }
        graphics2D.setColor(snakeColorHead);
        tile = rope.getBody().get(0);
        graphics2D.fillRect(tile.x, tile.y, tile.width, tile.height);
    }

    public void update() {
        rope.move();
    }

    public boolean isRunning() {
        return running;
    }

    public void draw() {
        ropePanel.repaint();
    }

    public void moveSnakeUp() {
        rope.setUpDirection();
    }

    public void moveSnakeDown() {
        rope.setDownDirection();
    }

    public void moveSnakeRight() {
        rope.setRightDirection();
    }

    public void moveSnakeLeft() {
        rope.setLeftDirection();
    }
}

