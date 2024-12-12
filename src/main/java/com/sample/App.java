package com.sample;

import javax.swing.JOptionPane;

public class App {

    public App() {
		
    }

    public static String askQuestion(String question, String... options) {
        if (options == null || options.length == 0) {
            return "No options provided";
        }

        int n = JOptionPane.showOptionDialog(
            null,
            question,
            "Question",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,    // No custom icon
            options, // Button labels
            options[0] // Default button
        );

        if (n >= 0 && n < options.length) {
            return options[n];
        } else {
            return "User interrupts";
        }
    }

    public static void finish(String name) {
        JOptionPane.showMessageDialog(
            null, 
            "The perfect game for you: " + name, 
            "Result", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
