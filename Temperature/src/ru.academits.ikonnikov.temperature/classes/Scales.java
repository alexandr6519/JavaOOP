package ru.academits.ikonnikov.temperature.classes;

public interface Scales {
    String[] scalesSet = new String[]{"Celsius", "Fahrenheit", "Kelvin"};

    String getScaleName();

    double convertFromCelsius(double inputTemperature);

    double convertToCelsius(double inputTemperature);

    boolean isInputIncorrect(double inputTemperature);
}
