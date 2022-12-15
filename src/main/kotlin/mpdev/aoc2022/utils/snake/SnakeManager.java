package mpdev.aoc2022.utils.snake;

import java.awt.*;

public class SnakeManager {
    private Color snakeColor;
    private Color snakeColorHead;
    private Color snakeColorTail;
    private Snake snake;
    private SnakePanel snakePanel;
    private boolean running;

    public SnakeManager(SnakePanel snakePanel) {
        this.snakePanel = snakePanel;
        snakeColor = Color.GREEN;
        snakeColorHead = Color.RED;
        snakeColorTail = Color.BLUE;
        snake = new Snake();
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
        tile = snake.getBody().getLast();
        graphics2D.fillRect(tile.x, tile.y, tile.width, tile.height);
        graphics2D.setColor(snakeColor);
        for (int i = 1; i < snake.getBody().size()-1; ++i) {
            tile = snake.getBody().get(i);
            graphics2D.fillRect(tile.x, tile.y, tile.width, tile.height);
        }
        graphics2D.setColor(snakeColorHead);
        tile = snake.getBody().get(0);
        graphics2D.fillRect(tile.x, tile.y, tile.width, tile.height);
    }

    public void update() {
        snake.move();
    }

    public boolean isRunning() {
        return running;
    }

    public void draw() {
        snakePanel.repaint();
    }

    public void moveSnakeUp() {
        snake.setUpDirection();
    }

    public void moveSnakeDown() {
        snake.setDownDirection();
    }

    public void moveSnakeRight() {
        snake.setRightDirection();
    }

    public void moveSnakeLeft() {
        snake.setLeftDirection();
    }
}

