package com.sample;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class App {

    public App() {

    }

    public static String askQuestion(String question, String... options) {
        if (options == null || options.length == 0) {
            return "No options provided";
        }
        // custom kolory
        Color dark_brown = new Color(52,21,3);
        Color fire_orange = new Color(252,139,50);
        Color fire_yellow = new Color(252,157,20);
        Color fire_yellow2 = new Color(250,216,100);
        Color fire_yellow3 = new Color(250,231,148);
        
        // tło
        BackgroundPanel backgroundPanel = new BackgroundPanel("/images/tav2_3.jpg");

        // label z pytaniem
        JLabel questionLabel = new JLabel("<html><body text-align: center;'>" + question + "</body></html>");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 15, 0)); // góra, lewo, dół, prawo
        questionLabel.setFont(new Font("Perpetua", Font.BOLD, 22));
        //questionLabel.setForeground(Color.WHITE);
        questionLabel.setForeground(fire_yellow2);

        // konfiguracja BoxLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // przyciski jeden pod drugim
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 25, 40)); // góra, lewo, dół, prawo
        buttonPanel.setOpaque(false); // żeby panel nie zasłaniał tła

        // dialog
        JButton[] buttons = new JButton[options.length];
        JDialog dialog = new JDialog((Frame) null, "Question", true);
        dialog.setContentPane(backgroundPanel); // panel z tłem jako zawartość okna
        dialog.setLayout(new BorderLayout());
        dialog.add(questionLabel, BorderLayout.NORTH);
        dialog.add(buttonPanel, BorderLayout.CENTER);
        dialog.setSize(600, 400);

        final String[] selectedOption = {null};

        // fabryka przycisków :)
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            buttons[i] = new JButton("<html><body>" + option + "</body></html>");
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
//            buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
//            buttons[i].setVerticalAlignment(SwingConstants.CENTER);
            buttons[i].setPreferredSize(new Dimension(200, 50));
            buttons[i].setFocusable(false);
            
            // kolor tła przycisku
            buttons[i].setBackground(dark_brown);
            // kolor tekstu na przycisku
            buttons[i].setForeground(fire_yellow2);
            // font
            buttons[i].setFont(new Font("Perpetua", Font.PLAIN, 18));
            
            buttons[i].addActionListener(e -> {
                selectedOption[0] = option;
                dialog.dispose();
            });
            buttonPanel.add(Box.createVerticalStrut(20)); // odstępy między przyciskami
            buttonPanel.add(buttons[i]);
        }

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return selectedOption[0] != null ? selectedOption[0] : "User interrupts";
    }

    public static void finish(String name) {
        // custom kolory
        Color dark_brown = new Color(52, 21, 3);
        Color fire_orange = new Color(252, 139, 50);
        Color fire_yellow2 = new Color(250, 216, 100);
        
        // tło
        BackgroundPanel backgroundPanel = new BackgroundPanel("/images/tav2_4.jpg");
        
        // label z wynikiem
        JLabel resultLabel = new JLabel("<html><body>You should play as: " + name + "!</body></html>");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("Perpetua", Font.BOLD, 24));
        resultLabel.setForeground(fire_yellow2); 
        
        resultLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        // okno
        JDialog resultDialog = new JDialog((Frame) null, "Result", true);
        resultDialog.setContentPane(backgroundPanel); // panel + tło
        resultDialog.setLayout(new BorderLayout());
        resultDialog.add(resultLabel, BorderLayout.CENTER);
        resultDialog.setSize(600, 400);
        resultDialog.setLocationRelativeTo(null);
        resultDialog.setVisible(true);
    }
    
 // tło panelu
    static class BackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
            	backgroundImage = ImageIO.read(getClass().getResource(imagePath));
            } catch (IOException e) {
                System.err.println("Could not load image: " + e.getMessage());
            }
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // rysuj w tle
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
