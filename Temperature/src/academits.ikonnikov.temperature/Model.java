package ru.academits.ikonnikov.temperature;

import ru.academits.ikonnikov.temperature.gui.View;

import javax.swing.*;

public class Model {
    private static View view = new View();

    private static boolean isNumeric(String stringNumber) {
        try {
            Double.parseDouble(stringNumber);
            return true;
        } catch ( NumberFormatException e ) {
            return false;
        }
    }

    public static void run() {
        view.createPanelInput();
        view.buttonInput.addActionListener(e -> {
            String text = view.field1.getText();

            if (!isNumeric(text)) {
                view.field1.setText("");
                view.showError("You entered not number!");
            } else {
                double inputTemperature = Double.parseDouble(text);
                int chooseInitialScale = view.chooseInitialScale();

                while (chooseInitialScale == -1) {
                    int resultDialog = view.toExit();

                    if (resultDialog == 0) {
                        System.exit(0);
                    } else {
                        chooseInitialScale = view.chooseInitialScale();
                    }
                }

                double outputTemperature = 0;
                String messageConvert = "";
                String messageError = "The value of temperature of this scale isn't correct!";
                JTextField field3 = view.field3;

                switch (chooseInitialScale) {
                    case 0:
                        if (inputTemperature >= -273.15) {
                            int fromCelsius = view.chooseScaleToConvert("Celsius");

                            while (fromCelsius == -1) {
                                int resultDialog = view.toExit();

                                if (resultDialog == 0) {
                                    System.exit(0);
                                } else {
                                    fromCelsius = view.chooseScaleToConvert("Celsius");
                                }
                            }

                            if (fromCelsius == 0) {
                                field3.setText("Fahrenheit");
                                outputTemperature = inputTemperature * 9 / 5 + 32;
                                messageConvert = "from Celsius to Fahrenheit";
                            } else if (fromCelsius == 1) {
                                field3.setText("Kelvin");
                                outputTemperature = inputTemperature + 273.15;
                                messageConvert = "from Celsius to Kelvin";
                            }
                        } else {
                            view.showError(messageError);
                            return;
                        }
                        break;
                    case 1:
                        if (inputTemperature >= -459.67) {
                            int fromFahrenheit = view.chooseScaleToConvert("Fahrenheit");

                            while (fromFahrenheit == -1) {
                                int resultDialog = view.toExit();

                                if (resultDialog == 0) {
                                    System.exit(0);
                                } else {
                                    fromFahrenheit = view.chooseScaleToConvert("Fahrenheit");
                                }
                            }

                            if (fromFahrenheit == 0) {
                                field3.setText("Celsius");
                                outputTemperature = (inputTemperature - 32) * 5 / 9;
                                messageConvert = "from Fahrenheit to Celsius";
                            } else if (fromFahrenheit == 1) {
                                field3.setText("Kelvin");
                                outputTemperature = (inputTemperature - 32) * 5 / 9 + 273.15;
                                messageConvert = "from Fahrenheit to Kelvin";
                            }
                        } else {
                            view.showError(messageError);
                            return;
                        }
                        break;
                    case 2:
                        if (inputTemperature >= 0) {
                            int fromKelvin = view.chooseScaleToConvert("Kelvin");

                            while (fromKelvin == -1) {
                                int resultDialog = view.toExit();

                                if (resultDialog == 0) {
                                    System.exit(0);
                                } else {
                                    fromKelvin = view.chooseScaleToConvert("Kelvin");
                                }
                            }

                            if (fromKelvin == 0) {
                                field3.setText("Celsius");
                                outputTemperature = inputTemperature - 273.15;
                                messageConvert = "from Kelvin to Celsius";
                            } else if (fromKelvin == 1) {
                                field3.setText("Fahrenheit");
                                outputTemperature = (inputTemperature - 273.15) * 9 / 5 + 32;
                                messageConvert = "from Kelvin to Fahrenheit";
                            }
                        } else {
                            view.showError(messageError);
                            return;
                        }
                        break;
                }
                String messageOutput = (String.format("The result of conversion value (%.2f) %s is : (%.2f) ", inputTemperature, messageConvert, outputTemperature));
                view.outputMessage(messageOutput);
            }
        });
    }
}
