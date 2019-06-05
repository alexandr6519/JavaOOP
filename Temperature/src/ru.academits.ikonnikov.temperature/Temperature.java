package ru.academits.ikonnikov.temperature;

import ru.academits.ikonnikov.temperature.gui.View;

public class Temperature {
    public static void main(String[] args) {
        View view = new View();
        view.run();
        view.setVisible(true);
    }
}
