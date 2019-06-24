package ru.academits.ikonnikov.temperature;

import ru.academits.ikonnikov.temperature.classes.*;
import ru.academits.ikonnikov.temperature.gui.View;

public class Temperature {
    public static void main(String[] args) {
        Scales[] scales = new Scales[3];
        scales[0] = new ScaleCelsius();
        scales[1] = new ScaleFahrenheit();
        scales[2] = new ScaleKelvin();
        View view = new View(scales);
        view.run();
        view.setVisible(true);
    }
}
