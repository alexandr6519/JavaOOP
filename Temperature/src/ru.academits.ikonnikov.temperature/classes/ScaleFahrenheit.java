package ru.academits.ikonnikov.temperature.classes;

public class ScaleFahrenheit implements Scales {
    @Override
    public String getScaleName() {
        return "Fahrenheit";
    }

    @Override
    public double convertFromCelsius(double inputTemperature) {
        return inputTemperature * 9 / 5 + 32;
    }

    @Override
    public double convertToCelsius(double inputTemperature) {
        return (inputTemperature - 32) * 5 / 9;
    }

    @Override
    public boolean isInputIncorrect(double inputTemperature) {
        return inputTemperature < -459.67;
    }
}
