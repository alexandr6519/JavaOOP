package ru.academits.ikonnikov.temperature;

import ru.academits.ikonnikov.temperature.gui.Scales;

public class Model {
    private double outputTemperature;
    private Scales scales;

    public Model(Scales scales) {
        this.scales = scales;
    }

    public double getOutputTemperature() {
        return outputTemperature;
    }

    private void setOutputTemperature(double outputTemperature) {
        this.outputTemperature = outputTemperature;
    }

    public boolean wasConvertTemperature(double inputTemperature, int scaleInitial, int scaleToConvert) {
        if (scales.isConvertTemperature(inputTemperature, scaleInitial, scaleToConvert)) {
            setOutputTemperature(scales.getOutputTemperature());
            return true;
        } else {
            return false;
        }
    }
}
