package ru.academits.ikonnikov.temperature.gui;

public class Scales {
    private String[] scales;
    private double outputTemperature;

    public Scales() {
        this.scales = new String[]{"Celsius", "Fahrenheit", "Kelvin"};
    }

    String[] getScales() {
        return scales;
    }

    public double getOutputTemperature() {
        return outputTemperature;
    }

    int getScaleIndex(String scale) {
        for (int i = 0; i < scales.length; i++) {
            if (scale.equals(scales[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean isConvertTemperature(double inputTemperature, int scaleInitial, int scaleToConvert) {
        switch (scaleInitial) {
            case 0:
                if (inputTemperature < -273.15) {
                    return false;
                }

                switch (scaleToConvert) {
                    case 0:
                        outputTemperature = inputTemperature;
                        break;
                    case 1:
                        outputTemperature =  inputTemperature * 9 / 5 + 32;
                        break;
                    case 2:
                        outputTemperature =  inputTemperature + 273.15;
                        break;
                }
                return true;
            case 1:
                if (inputTemperature < -459.67) {
                    return false;
                }

                switch (scaleToConvert) {
                    case 0:
                        outputTemperature =  (inputTemperature - 32) * 5 / 9;
                        break;
                    case 1:
                        outputTemperature =  inputTemperature;
                        break;
                    case 2:
                        outputTemperature =  (inputTemperature - 32) * 5 / 9 + 273.15;
                        break;
                }
                return true;
            case 2:
                if (inputTemperature < 0) {
                    return false;
                }

                switch (scaleToConvert) {
                    case 0:
                        outputTemperature =  inputTemperature - 273.15;
                        break;
                    case 1:
                        outputTemperature =  (inputTemperature - 273.15) * 9 / 5 + 32;
                        break;
                    case 2:
                        outputTemperature =  inputTemperature;
                        break;
                }
                return true;
        }
        return false;
    }

}
