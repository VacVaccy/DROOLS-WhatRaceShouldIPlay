package com.sample;

import javax.swing.*;
import java.awt.*;

public class App {

    public App() {

    }

    public static String askQuestion(String question, String... options) {
        if (options == null || options.length == 0) {
            return "No options provided";
        }

        // Pytanie z możliwością zawijania tekstu
        JLabel questionLabel = new JLabel("<html><body style='width: 400px; text-align: center;'>" + question + "</body></html>");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 15, 0)); // góra, lewo, dół, prawo

        // Panel z układem BoxLayout (układ pionowy)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Przyciski jeden pod drugim
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 25, 40)); // góra, lewo, dół, prawo

        // Tworzenie dialogu
        JButton[] buttons = new JButton[options.length];
        JDialog dialog = new JDialog((Frame) null, "Question", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(questionLabel, BorderLayout.NORTH);
        dialog.add(buttonPanel, BorderLayout.CENTER);
        dialog.setSize(600, 400);

        final String[] selectedOption = {null};

        // Tworzenie przycisków
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            buttons[i] = new JButton("<html><body style='width: 400px;'>" + option + "</body></html>");
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT); // Wyrównanie przycisku do środka
//            buttons[i].setHorizontalAlignment(SwingConstants.CENTER); // Wyrównanie tekstu w poziomie
//            buttons[i].setVerticalAlignment(SwingConstants.CENTER);   // Wyrównanie tekstu w pionie
            buttons[i].setPreferredSize(new Dimension(200, 50)); // Szerokość przycisku
            buttons[i].setFocusable(false);

            
            buttons[i].addActionListener(e -> {
                selectedOption[0] = option;
                dialog.dispose();
            });
            buttonPanel.add(Box.createVerticalStrut(20)); // Odstęp między przyciskami
            buttonPanel.add(buttons[i]);
        }

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return selectedOption[0] != null ? selectedOption[0] : "User interrupts";
    }

    public static void finish(String name) {
        JLabel resultLabel = new JLabel("<html><body style='width: 400px; padding: 20px 0;'>Your race: " + name + "</body></html>");
        JOptionPane.showMessageDialog(
            null,
            resultLabel,
            "Result",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
