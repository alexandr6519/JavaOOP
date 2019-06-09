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

                switch (scaleToConvert) {
                    case 0:
                        setOutputTemperature(inputTemperature);
                        break;
                    case 1:
                        setOutputTemperature(inputTemperature * 9 / 5 + 32);
                        break;
                    case 2:
                        setOutputTemperature(inputTemperature + 273.15);
                        break;
                }
                return true;
            case 1:
                if (inputTemperature < -459.67) {
                    return false;
                }

                switch (scaleToConvert) {
                    case 0:
                        setOutputTemperature((inputTemperature - 32) * 5 / 9);
                        break;
                    case 1:
                        setOutputTemperature(inputTemperature);
                        break;
                    case 2:
                        setOutputTemperature((inputTemperature - 32) * 5 / 9 + 273.15);
                        break;
                }
                return true;
            case 2:
                if (inputTemperature < 0) {
                    return false;
                }

                switch (scaleToConvert) {
                    case 0:
                        setOutputTemperature(inputTemperature - 273.15);
                        break;
                    case 1:
                        setOutputTemperature((inputTemperature - 273.15) * 9 / 5 + 32);
                        break;
                    case 2:
                        setOutputTemperature(inputTemperature);
                        break;
                }
                return true;
            default:
                return false;
        }
    }
}
