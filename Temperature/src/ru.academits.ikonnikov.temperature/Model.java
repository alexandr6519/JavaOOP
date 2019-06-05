package ru.academits.ikonnikov.temperature;

public class Model {
    private double outputTemperature;

    public double getOutputTemperature() {
        return outputTemperature;
    }

    private void setOutputTemperature(double outputTemperature) {
        this.outputTemperature = outputTemperature;
    }

    public boolean wasConvertTemperature(double inputTemperature, int scaleInitial, int scaleToConvert) {
        switch (scaleInitial) {
            case 0:
                if (inputTemperature < -273.15) {
                    return false;
                }

                if (scaleToConvert == 1) {
                    setOutputTemperature(inputTemperature * 9 / 5 + 32);
                } else if (scaleToConvert == 2) {
                    setOutputTemperature(inputTemperature + 273.15);
                } else if (scaleToConvert == 0) {
                    setOutputTemperature(inputTemperature);
                }
                return true;
            case 1:
                if (inputTemperature < -459.67) {
                    return false;
                }

                if (scaleToConvert == 0) {
                    setOutputTemperature((inputTemperature - 32) * 5 / 9);
                } else if (scaleToConvert == 2) {
                    setOutputTemperature((inputTemperature - 32) * 5 / 9 + 273.15);
                } else if (scaleToConvert == 1) {
                    setOutputTemperature(inputTemperature);
                }
                return true;
            case 2:
                if (inputTemperature < 0) {
                    return false;
                }

                if (scaleToConvert == 0) {
                    setOutputTemperature(inputTemperature - 273.15);
                } else if (scaleToConvert == 1) {
                    setOutputTemperature((inputTemperature - 273.15) * 9 / 5 + 32);
                } else if (scaleToConvert == 2) {
                    setOutputTemperature(inputTemperature);
                }
                return true;
            default:
                return false;
        }
    }


}
