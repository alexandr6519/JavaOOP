package ru.academits.ikonnikov.temperature;

import ru.academits.ikonnikov.temperature.classes.MyListener;

import javax.swing.*;

public class Temperature {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                MyListener::run);
    }
}
