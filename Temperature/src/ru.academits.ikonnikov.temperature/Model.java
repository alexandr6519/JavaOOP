package ru.academits.ikonnikov.temperature;

public class Model {
    public double outputTemperature(double inputTemperature, int scaleInitial, int scaleToConvert) {
        switch (scaleInitial) {
            case 0:
                if (scaleToConvert == 0) {
                    return inputTemperature * 9 / 5 + 32;
                } else if (scaleToConvert == 1) {
                    return inputTemperature + 273.15;
                }
            case 1:
                if (scaleToConvert == 0) {
                    return (inputTemperature - 32) * 5 / 9;
                } else if (scaleToConvert == 1) {
                    return (inputTemperature - 32) * 5 / 9 + 273.15;
                }
            case 2:
                if (scaleToConvert == 0) {
                    return inputTemperature - 273.15;
                } else if (scaleToConvert == 1) {
                    return (inputTemperature - 273.15) * 9 / 5 + 32;
                }
            default:
                return 0;
        }
    }
}
