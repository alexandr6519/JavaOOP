package ru.academits.ikonnikov.temperature.gui;

class Scales {
    private int scaleInitial;
    private int scaleToConvert;
    private String[] scales;

    Scales() {
        this.scales = new String[]{"Celsius", "Fahrenheit", "Kelvin"};
    }

    String[] getScales() {
        return scales;
    }
    int getScaleInitial() {
        return scaleInitial;
    }

    void setScaleInitial(int scaleInitial) {
        this.scaleInitial = scaleInitial;
    }

    int getScaleToConvert() {
        return scaleToConvert;
    }

    void setScaleToConvert(int scaleToConvert) {
        this.scaleToConvert = scaleToConvert;
    }
}
