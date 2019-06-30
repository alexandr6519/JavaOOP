package ru.academits.ikonnikov.temperature.classes;

public interface Scales {
    String getScaleName();

    double convertFromCelsius(double inputTemperature);

    double convertToCelsius(double inputTemperature);

    boolean isInputIncorrect(double inputTemperature);
}
