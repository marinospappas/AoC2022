package mpdev.aoc2022.utils.snake;


import javax.swing.*;
import java.awt.*;

public class SnakeFrame extends JFrame {
    private SnakePanel snakePanel;
    private JLabel scoreLabel;

    public SnakeFrame() throws HeadlessException, InterruptedException {
        this.setTitle("Snake");
        this.setBounds(100, 100, 800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        scoreLabel = new JLabel("Aoc 2022 Day 9");
        scoreLabel.setBounds(0, 0, 200, 20);
        scoreLabel.setForeground(Color.WHITE);
        getContentPane().add(scoreLabel);

        snakePanel = new SnakePanel();
        snakePanel.setBounds(20, 20, 760, 750);
        this.add(snakePanel);

        this.setVisible(true);
        while(snakePanel.snakeManager.isRunning())
            Thread.sleep(100);
    }
}

