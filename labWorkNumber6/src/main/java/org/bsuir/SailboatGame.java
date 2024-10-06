package org.bsuir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.imageio.ImageIO;

public class SailboatGame extends JPanel implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int SAILBOAT_WIDTH = 50;
    private static final int SAILBOAT_HEIGHT = 50;
    private static final int TORPEDO_WIDTH = 10;
    private static final int TORPEDO_HEIGHT = 10;

    private JFrame frame;
    private JButton torpedoButton;
    private JButton startButton;
    private JButton stopButton;
    private Sailboat sailboat;
    private Torpedo torpedo;
    private Random random;
    private AtomicBoolean isRunning;
    private BufferedImage sailboatImage;
    private BufferedImage torpedoImage;

    public SailboatGame() {
        random = new Random();
        sailboat = new Sailboat();
        torpedo = new Torpedo();
        isRunning = new AtomicBoolean(false);

        try {
            sailboatImage = ImageIO.read(new File("labWorkNumber6/src/main/java/org/bsuir/ship.jpg"));
            torpedoImage = ImageIO.read(new File("labWorkNumber6/src/main/java/org/bsuir/torpedo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame = new JFrame("Sailboat Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(this);

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);

        torpedoButton = new JButton("Launch Torpedo");
        torpedoButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(torpedoButton);

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (sailboat.running.get() || !torpedo.running.get()) {
            sailboat.draw(g);
        }
        torpedo.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            isRunning.set(true);
            sailboat.start();
        } else if (e.getSource() == stopButton) {
            sailboat.stopSailboat();
        } else if (e.getSource() == torpedoButton) {
            if (!torpedo.running.get()) {
                torpedo.start();
            }
        }
    }

    private class Sailboat {
        private int x;
        private int y;
        private int speed;
        private AtomicBoolean running;
        private Thread thread;

        public Sailboat() {
            x = 0;
            y = HEIGHT / 2 - SAILBOAT_HEIGHT / 2;
            speed = random.nextInt(5) + 1;
            running = new AtomicBoolean(false);
        }

        public void start() {
            if (!running.get()) {
                running.set(true);
                thread = new Thread(() -> {
                    while (running.get()) {
                        x += speed;
                        if (x > WIDTH) {
                            x = 0;
                            speed = random.nextInt(5) + 1;
                        }
                        repaint();
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        }

        public void stopSailboat() {
            running.set(false);
        }

        public void draw(Graphics g) {
            g.drawImage(sailboatImage, x, y, null);
        }
    }

    private class Torpedo extends Thread {
        private int x;
        private int y;
        private int speed;
        private AtomicBoolean running;

        public Torpedo() {
            x = WIDTH / 2 - TORPEDO_WIDTH / 2;
            y = HEIGHT - TORPEDO_HEIGHT - 20;
            speed = 5;
            running = new AtomicBoolean(false);
        }

        public void run() {
            running.set(true);
            while (running.get()) {
                y -= speed;
                if (y < 0) {
                    y = HEIGHT - TORPEDO_HEIGHT - 20;
                }
                if (checkCollision()) {
                    sailboat.stopSailboat();
                    sailboat.running.set(false);
                    sailboat.x = -100;
                    sailboat.y = -100;
                    System.out.println("Torpedo hit the sailboat!");
                    running.set(false);
                }
                if (sailboat.running.get() || !running.get()) {
                    repaint();
                }
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void draw(Graphics g) {
            g.drawImage(torpedoImage, x, y, null);
        }

        private boolean checkCollision() {
            return x + TORPEDO_WIDTH > sailboat.x && x < sailboat.x + SAILBOAT_WIDTH && y + TORPEDO_HEIGHT > sailboat.y && y < sailboat.y + SAILBOAT_HEIGHT;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SailboatGame::new);
    }
}