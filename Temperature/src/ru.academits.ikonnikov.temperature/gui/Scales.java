package ru.academits.ikonnikov.temperature.gui;

class Scales {
    private String[] scales;

    Scales() {
        this.scales = new String[]{"Celsius", "Fahrenheit", "Kelvin"};
    }

    int getScaleIndex(String scale) {
        for (int i = 0; i < scales.length; i++) {
            if (scale.equals(scales[i])) {
                return i;
            }
        }
        return -1;
    }

    String[] getScales() {
        return scales;
    }
}
