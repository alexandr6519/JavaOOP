package ru.academits.ikonnikov.temperature.gui;

import ru.academits.ikonnikov.temperature.Model;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.showOptionDialog;

public class View {
    private JTextField field1 = new JTextField(10);
    private JTextField field2 = new JTextField(12);
    private JTextField field3 = new JTextField(12);
    private JButton buttonInput = new JButton("Enter");
    private JButton buttonConvert = new JButton("Convert");
    private JFrame frame = new JFrame("Temperature converter");
    private JPanel panel = new JPanel();
    private double inputTemperature;
    private Model model;

    public View() {
        model = new Model();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setVisible(true);
        frame.setResizable(false);

        frame.add(panel);
        GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(40, 10, 0, 10);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        JLabel label1 = new JLabel("Value of temperature");
        gbl.setConstraints(label1, c);
        panel.add(label1);

        gbl.setConstraints(field1, c);
        panel.add(field1);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(40, 10, 0, 0);
        gbl.setConstraints(buttonInput, c);
        panel.add(buttonInput);

        c.gridwidth = 1;
        c.insets = new Insets(40, 10, 0, 10);
        JLabel label2 = new JLabel("Initial scale of temperature");
        gbl.setConstraints(label2, c);
        panel.add(label2);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(40, 10, 0, 0);
        gbl.setConstraints(field2, c);
        panel.add(field2);
        field2.setEditable(false);

        c.gridwidth = 1;
        c.insets = new Insets(40, 10, 0, 10);
        JLabel label3 = new JLabel("The scale to convert");
        gbl.setConstraints(label3, c);
        panel.add(label3);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(40, 10, 0, 0);
        gbl.setConstraints(field3, c);
        panel.add(field3);
        field3.setEditable(false);

        c.insets = new Insets(40, 0, 0, 0);

        gbl.setConstraints(buttonConvert, c);
        panel.add(buttonConvert);
    }

    public void setVisible(boolean bool) {
        frame.setVisible(bool);
    }


    private int chooseInitialScale() {
        Object[] options = {"Celsius", "Fahrenheit", "Kelvin"};
        return showOptionDialog(frame, "Choose the initial scale of temperature", "Options of initial scale", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    private int chooseScaleToConvert(String scale) {
        String title = "Options of scale to convert";
        String messageDialog = "Choose the scale to convert";

        switch (scale) {
            case "Celsius":
                field2.setText("Celsius");
                Object[] options1 = {"Fahrenheit", "Kelvin"};
                return showOptionDialog(frame, messageDialog, title, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options1, options1[0]);
            case "Fahrenheit":
                field2.setText("Fahrenheit");
                Object[] options2 = {"Celsius", "Kelvin"};
                return showOptionDialog(frame, messageDialog, title, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
            case "Kelvin":
                field2.setText("Kelvin");
                Object[] options3 = {"Celsius", "Fahrenheit"};
                return showOptionDialog(frame, messageDialog, title, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]);
        }
        return -1;
    }

    private void displayError(String messageError) {
        JOptionPane.showMessageDialog(panel, messageError);
    }

    private int toExit() {
        return JOptionPane.showConfirmDialog(frame, "Are you sure to exit?", "Opportunity to exit", JOptionPane.YES_NO_OPTION);
    }

    private int chanceToExit(String scale) {
        int scaleToConvert = -1;

        if (scale.equals("initial")) {
            scaleToConvert = chooseInitialScale();
        } else {
            scaleToConvert = chooseScaleToConvert(scale);
        }

        while (scaleToConvert == -1) {
            int resultDialog = toExit();

            if (resultDialog == 0) {
                System.exit(0);
            } else if (scale.equals("initial")) {
                scaleToConvert = chooseInitialScale();
            } else {
                scaleToConvert = chooseScaleToConvert(scale);
            }
        }
        return scaleToConvert;
    }

    public void run() {
        buttonInput.addActionListener(e -> {
            String text = field1.getText();

            if (!isNumeric(text)) {
                field1.setText("");
                displayError("You need to enter number!");
            } else {
                inputTemperature = Double.parseDouble(text);
                int initialScale = chanceToExit("initial");

                String messageConvert = "";
                String messageError = "The value of temperature of this scale isn't correct!";
                int scaleToConvert = -1;

                switch (initialScale) {
                    case 0:
                        if (inputTemperature >= -273.15) {
                            scaleToConvert = chanceToExit("Celsius");

                            if (scaleToConvert == 0) {
                                field3.setText("Fahrenheit");
                                messageConvert = "from Celsius to Fahrenheit";
                            } else if (scaleToConvert == 1) {
                                field3.setText("Kelvin");
                                messageConvert = "from Celsius to Kelvin";
                            }
                        } else {
                            displayError(messageError);
                            return;
                        }
                        break;
                    case 1:
                        if (inputTemperature >= -459.67) {
                            scaleToConvert = chanceToExit("Fahrenheit");

                            if (scaleToConvert == 0) {
                                field3.setText("Celsius");
                                messageConvert = "from Fahrenheit to Celsius";
                            } else if (scaleToConvert == 1) {
                                field3.setText("Kelvin");
                                messageConvert = "from Fahrenheit to Kelvin";
                            }
                        } else {
                            displayError(messageError);
                            return;
                        }
                        break;
                    case 2:
                        if (inputTemperature >= 0) {
                            scaleToConvert = chanceToExit("Kelvin");

                            if (scaleToConvert == 0) {
                                field3.setText("Celsius");
                                messageConvert = "from Kelvin to Celsius";
                            } else if (scaleToConvert == 1) {
                                field3.setText("Fahrenheit");
                                messageConvert = "from Kelvin to Fahrenheit";
                            }
                        } else {
                            displayError(messageError);
                            return;
                        }
                        break;
                }
                buttonInput.setEnabled(false);
                double outputTemperature = model.outputTemperature(inputTemperature, initialScale, scaleToConvert);
                String messageOutput = (String.format("The result of conversion value (%.2f) %s is : (%.2f) ", inputTemperature, messageConvert, outputTemperature));
                displayOutputMessage(messageOutput);
            }
        });
    }

    private void displayOutputMessage(String messageOutput) {
        buttonConvert.addActionListener(e2 -> {
            buttonInput.setEnabled(true);
            JOptionPane.showMessageDialog(frame, messageOutput);
        });
    }

    private static boolean isNumeric(String stringNumber) {
        try {
            Double.parseDouble(stringNumber);
            return true;
        } catch ( NumberFormatException e ) {
            return false;
        }
    }
}
