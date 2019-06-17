package ru.academits.ikonnikov.temperature;

import ru.academits.ikonnikov.temperature.gui.Scales;
import ru.academits.ikonnikov.temperature.gui.View;

public class Temperature {
    public static void main(String[] args) {
        Scales scales = new Scales();
        View view = new View(scales);
        view.run();
        view.setVisible(true);
    }
}
