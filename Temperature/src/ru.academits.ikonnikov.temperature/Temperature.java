package ru.academits.ikonnikov.temperature;

import ru.academits.ikonnikov.temperature.gui.View;

import javax.swing.*;

public class Temperature {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            View view = new View();
            view.run();
            view.setVisible(true);
        });
    }
}
