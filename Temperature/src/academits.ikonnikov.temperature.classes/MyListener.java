package ru.academits.ikonnikov.temperature.classes;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;

public class MyListener extends JFrame {
    private static boolean isNumeric(String stringNumber) {
        try {
            Double.parseDouble(stringNumber);
            return true;
        } catch ( NumberFormatException e ) {
            return false;
        }
    }

    public static void run() {
        JFrame frame = new JFrame("Temperature converter");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int) (screenSize.width * 0.4), (int) (screenSize.height * 0.4));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        JPanel panel = new JPanel();
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
        JLabel label1 = new JLabel("Enter value of temperature");
        gbl.setConstraints(label1, c);
        panel.add(label1);

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
        JTextField field1 = new JTextField(10);
        gbl.setConstraints(field1, c);
        panel.add(field1);

        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(40, 10, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        JButton buttonEnter = new JButton("OK");
        gbl.setConstraints(buttonEnter, c);
        panel.add(buttonEnter);

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
        JLabel label2 = new JLabel("Initial scale of temperature");
        gbl.setConstraints(label2, c);
        panel.add(label2);

        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(40, 10, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        JTextField field2 = new JTextField(15);
        gbl.setConstraints(field2, c);
        panel.add(field2);
        field2.setEditable(false);

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
        JLabel label3 = new JLabel("The scale to convert");
        gbl.setConstraints(label3, c);
        panel.add(label3);

        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(40, 10, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        JTextField field3 = new JTextField(15);
        gbl.setConstraints(field3, c);
        panel.add(field3);
        field3.setEditable(false);

        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(40, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        JButton buttonConvert = new JButton("Convert");
        gbl.setConstraints(buttonConvert, c);
        panel.add(buttonConvert);

        buttonEnter.addActionListener(e1 -> {
            String text = field1.getText();

            if (text.length() > 0 && isNumeric(text)) {
                double inputTemperature = Double.parseDouble(text);
                String messageInput = String.format("You enter number %.2f", inputTemperature);
                JOptionPane.showMessageDialog(panel, messageInput);

                Object[] options = {"Celsius", "Fahrenheit", "Kelvin"};
                String stringOptionScale = (String) showInputDialog(frame, "Choose the initial scale of temperature", "Initial scale", PLAIN_MESSAGE, null, options, options[0]);

                double outputTemperature = 0;
                String messageConvert = "";
                String messageError = "The value of temperature of this scale isn't correct!";

                switch (stringOptionScale) {
                    case "Celsius":
                        if (inputTemperature >= -273.15) {
                            field2.setText("Celsius");
                            Object[] optionsNoCelsius = {"Fahrenheit", "Kelvin"};
                            String sCelsius = (String) showInputDialog(frame, "Choose the scale to convert the temperature", "Scale to convert", PLAIN_MESSAGE, null, optionsNoCelsius, optionsNoCelsius[0]);

                            if (sCelsius.equals("Fahrenheit")) {
                                field3.setText("Fahrenheit");
                                outputTemperature = inputTemperature * 9 / 5 + 32;
                                messageConvert = "from Celsius to Fahrenheit";

                            } else if (sCelsius.equals("Kelvin")) {
                                field3.setText("Kelvin");
                                outputTemperature = inputTemperature + 273.15;
                                messageConvert = "from Celsius to Kelvin";
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, messageError);
                            return;
                        }
                        break;
                    case "Fahrenheit":
                        if (inputTemperature >= -459.67) {
                            field2.setText("Fahrenheit");
                            Object[] optionsNoFahrenheit = {"Celsius", "Kelvin"};
                            String sFahrenheit = (String) showInputDialog(frame, "Choose the scale to convert the temperature", "Scale to convert", PLAIN_MESSAGE, null, optionsNoFahrenheit, optionsNoFahrenheit[0]);

                            if (sFahrenheit.equals("Celsius")) {
                                field3.setText("Celsius");
                                outputTemperature = (inputTemperature - 32) * 5 / 9;
                                messageConvert = "from Fahrenheit to Celsius";
                            } else if (sFahrenheit.equals("Kelvin")) {
                                field3.setText("Kelvin");
                                outputTemperature = (inputTemperature - 32) * 5 / 9 + 273.15;
                                messageConvert = "from Fahrenheit to Kelvin";
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, messageError);
                            return;
                        }
                        break;
                    case "Kelvin":
                        if (inputTemperature >= 0) {
                            field2.setText("Kelvin");
                            Object[] optionsNoKelvin = {"Celsius", "Fahrenheit"};
                            String sKelvin = (String) showInputDialog(frame, "Choose the scale to convert the temperature", "Scale to convert", PLAIN_MESSAGE, null, optionsNoKelvin, optionsNoKelvin[0]);

                            if (sKelvin.equals("Celsius")) {
                                field3.setText("Celsius");
                                outputTemperature = inputTemperature - 273.15;
                                messageConvert = "from Kelvin to Celsius";
                            } else if (sKelvin.equals("Fahrenheit")) {
                                field3.setText("Fahrenheit");
                                outputTemperature = (inputTemperature - 273.15) * 9 / 5 + 32;
                                messageConvert = "from Kelvin to Fahrenheit";
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, messageError);
                            return;
                        }
                        break;
                }

                String messageOutput = (String.format("The result of conversion value (%.2f) %s is : (%.2f) ", inputTemperature, messageConvert, outputTemperature));

                buttonConvert.addActionListener(e2 ->
                        JOptionPane.showMessageDialog(frame, messageOutput)
                );
            } else {
                JOptionPane.showMessageDialog(frame, "You entered not number!");
            }
        });
    }
}
