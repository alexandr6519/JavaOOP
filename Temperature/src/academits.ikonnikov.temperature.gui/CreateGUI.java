package ru.academits.ikonnikov.temperature.gui;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.showOptionDialog;

public class CreateGUI extends JFrame {
    private JLabel label1 = new JLabel("Value of temperature");
    private JLabel label2 = new JLabel("Initial scale of temperature");
    private JLabel label3 = new JLabel("The scale to convert");
    public JTextField field1 = new JTextField(10);
    private JTextField field2 = new JTextField(12);
    public JTextField field3 = new JTextField(12);
    public JButton buttonInput = new JButton("Enter");
    private JButton buttonConvert = new JButton("Convert");
    private JFrame frame = new JFrame("Temperature converter");
    private JPanel panel = new JPanel();

    public void createPanelInput() {
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
        gbl.setConstraints(label2, c);
        panel.add(label2);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(40, 10, 0, 0);
        gbl.setConstraints(field2, c);
        panel.add(field2);
        field2.setEditable(false);

        c.gridwidth = 1;
        c.insets = new Insets(40, 10, 0, 10);
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

    public int toExit() {
        return JOptionPane.showConfirmDialog(frame, "Are you sure to exit?", "Opportunity to exit", JOptionPane.YES_NO_OPTION);
    }

    public int chooseInitialScale() {
        Object[] options = {"Celsius", "Fahrenheit", "Kelvin"};
        return showOptionDialog(frame, "Choose the initial scale of temperature", "Options of initial scale", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    public int chooseScaleToConvert(String scale) {
        switch (scale) {
            case "Celsius":
                field2.setText("Celsius");
                Object[] options1 = {"Fahrenheit", "Kelvin"};
                return showOptionDialog(frame, "Choose the scale to convert", "Options of scale to convert", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options1, options1[0]);
            case "Fahrenheit":
                field2.setText("Fahrenheit");
                Object[] options2 = {"Celsius", "Kelvin"};
                return showOptionDialog(frame, "Choose the scale to convert", "Options of scale to convert", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
            case "Kelvin":
                field2.setText("Kelvin");
                Object[] options3 = {"Celsius", "Fahrenheit"};
                return showOptionDialog(frame, "Choose the scale to convert", "Options of scale to convert", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]);
        }
        return -1;
    }

    public void outputMessage(String messageOutput) {
        buttonConvert.addActionListener(e2 ->
                JOptionPane.showMessageDialog(frame, messageOutput)
        );
    }

    public void showError(String messageError) {
        JOptionPane.showMessageDialog(panel, messageError);
    }
}
