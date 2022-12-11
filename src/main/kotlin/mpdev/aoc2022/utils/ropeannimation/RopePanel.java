package mpdev.aoc2022.utils.ropeannimation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RopePanel extends JPanel implements KeyListener, ActionListener {
    public RopeManager ropeManager;
    private Timer timer;
    private final int DELAY = 150;

    public RopePanel() {
        this.setOpaque(true);
        this.setBackground(new Color(51, 51, 51));
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        ropeManager = new RopeManager(this);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ropeManager.renderRope(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ropeManager.gameLoop();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> ropeManager.moveSnakeUp();
            case KeyEvent.VK_DOWN -> ropeManager.moveSnakeDown();
            case KeyEvent.VK_RIGHT -> ropeManager.moveSnakeRight();
            case KeyEvent.VK_LEFT -> ropeManager.moveSnakeLeft();
            case KeyEvent.VK_ESCAPE -> System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
