package mpdev.aoc2022.utils.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;

public class SnakePanel extends JPanel implements KeyListener, ActionListener {
    public SnakeManager snakeManager;
    private Timer timer;
    private final int DELAY = 2000;

    public SnakePanel() {
        this.setOpaque(true);
        this.setBackground(new Color(51, 51, 51));
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        snakeManager = new SnakeManager(this);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println(LocalTime.now() + " paintComponent: calling animationManager.renderObject");
        super.paintComponent(g);
        snakeManager.renderRope(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snakeManager.gameLoop();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> snakeManager.moveSnakeUp();
            case KeyEvent.VK_DOWN -> snakeManager.moveSnakeDown();
            case KeyEvent.VK_RIGHT -> snakeManager.moveSnakeRight();
            case KeyEvent.VK_LEFT -> snakeManager.moveSnakeLeft();
            case KeyEvent.VK_ESCAPE -> System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
