package ru.academits.ikonnikov.temperature;

import javax.swing.*;

public class Temperature {  
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(
                Model::run);
    }
}
