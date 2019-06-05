package ru.academits.ikonnikov.temperature;

public class Model {
    private double outputTemperature;

    public double getOutputTemperature() {
        return outputTemperature;
    }

    private void setOutputTemperature(double outputTemperature) {
        this.outputTemperature = outputTemperature;
    }

    public boolean wasConvertTemperature(double inputTemperature, String scaleInitial, String scaleToConvert) {
        switch (scaleInitial) {
            case "Celsius":
                if (inputTemperature < -273.15) {
                    return false;
                }

                switch (scaleToConvert) {
                    case "Fahrenheit":
                        setOutputTemperature(inputTemperature * 9 / 5 + 32);
                        break;
                    case "Kelvin":
                        setOutputTemperature(inputTemperature + 273.15);
                        break;
                    case "Celsius":
                        setOutputTemperature(inputTemperature);
                        break;
                }
                return true;
            case "Fahrenheit":
                if (inputTemperature < -459.67) {
                    return false;
                }

                switch (scaleToConvert) {
                    case "Celsius":
                        setOutputTemperature((inputTemperature - 32) * 5 / 9);
                        break;
                    case "Kelvin":
                        setOutputTemperature((inputTemperature - 32) * 5 / 9 + 273.15);
                        break;
                    case "Fahrenheit":
                        setOutputTemperature(inputTemperature);
                        break;
                }
                return true;
            case "Kelvin":
                if (inputTemperature < 0) {
                    return false;
                }

                switch (scaleToConvert) {
                    case "Celsius":
                        setOutputTemperature(inputTemperature - 273.15);
                        break;
                    case "Fahrenheit":
                        setOutputTemperature((inputTemperature - 273.15) * 9 / 5 + 32);
                        break;
                    case "Kelvin":
                        setOutputTemperature(inputTemperature);
                        break;
                }
                return true;
            default:
                return false;
        }
    }
}
