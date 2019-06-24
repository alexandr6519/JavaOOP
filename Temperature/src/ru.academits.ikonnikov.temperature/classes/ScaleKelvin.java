package ru.academits.ikonnikov.temperature.classes;

public class ScaleKelvin implements Scales {
    @Override
    public String getScaleName() {
        return "Kelvin";
    }

    @Override
    public double convertFromCelsius(double inputTemperature) {
        return inputTemperature + 273.15;
    }

    @Override
    public double convertToCelsius(double inputTemperature) {
        return inputTemperature - 273.15;
    }

    @Override
    public boolean isInputIncorrect(double inputTemperature) {
        return inputTemperature < 0;
    }
}
