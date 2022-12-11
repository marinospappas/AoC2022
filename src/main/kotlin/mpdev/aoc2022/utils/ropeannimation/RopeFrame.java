package mpdev.aoc2022.utils.ropeannimation;


import javax.swing.*;
import java.awt.*;

public class RopeFrame extends JFrame {
    private RopePanel ropePanel;
    private JLabel scoreLabel;

    public RopeFrame() throws HeadlessException, InterruptedException {
        this.setTitle("Rope Movement");
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

        ropePanel = new RopePanel();
        ropePanel.setBounds(20, 20, 760, 750);
        this.add(ropePanel);

        this.setVisible(true);
        while(ropePanel.ropeManager.isRunning())
            Thread.sleep(100);
    }
}

