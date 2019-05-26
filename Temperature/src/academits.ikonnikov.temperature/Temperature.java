package ru.academits.ikonnikov.temperature;

import javax.swing.*;

public class Temperature {
    /*private static boolean isNumeric(String stringNumber) {
        try {
            Double.parseDouble(stringNumber);
            return true;
        } catch ( NumberFormatException e ) {
            return false;
        }
    }*/

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
