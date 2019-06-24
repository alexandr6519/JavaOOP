package ru.academits.ikonnikov.temperature.classes;

public class ScaleCelsius implements Scales {
    @Override
    public String getScaleName() {
        return "Celsius";
    }

    @Override
    public double convertFromCelsius(double inputTemperature) {
        return inputTemperature;
    }

    @Override
    public double convertToCelsius(double inputTemperature) {
        return inputTemperature;
    }

    @Override
    public boolean isInputIncorrect(double inputTemperature) {
        return inputTemperature < -273.15;
    }
}
