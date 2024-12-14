package com.sample;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;

public class App {

    public App() {
		
    }

    public static String askQuestion(String question, String... options) {
        if (options == null || options.length == 0) {
            return "No options provided";
        }

        JLabel questionLabel = new JLabel("<html><body style='width: 400px;'>" + question + "</body></html>");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1)); 

        JButton[] buttons = new JButton[options.length];
        JDialog dialog = new JDialog((Frame) null, "Question", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(questionLabel, BorderLayout.NORTH);
        dialog.add(buttonPanel, BorderLayout.CENTER);
        dialog.setSize(600, 400);

        final String[] selectedOption = {null};

        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            buttons[i] = new JButton("<html><body style='width: 400px;'>" + option + "</body></html>");
            buttons[i].addActionListener(e -> {
                selectedOption[0] = option;
                dialog.dispose();
            });
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
