package ru.academits.ikonnikov.temperature.gui;

import ru.academits.ikonnikov.temperature.Model;

import javax.swing.*;
import java.awt.*;

public class View {
    private JTextField field1 = new JTextField(10);
    private JTextField field2 = new JTextField(10);
    private JTextField field3 = new JTextField(10);
    private JTextField field4 = new JTextField(10);
    private JButton buttonConvert = new JButton("OK");
    private JFrame frame = new JFrame(" Temperature converter (Input form)");
    private JPanel panel = new JPanel();
    private double inputTemperature;
    private Model model;
    private Scales scales;
    private ButtonGroup buttonGroupScales;
    private ButtonGroup buttonGroupScalesToConvert;

    public View() {
        model = new Model();
        scales = new Scales();
        String[] scalesSet = scales.getScales();
        int scalesCount = scalesSet.length;
        frame.setSize(700, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(320, 250);
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
        c.insets = new Insets(20, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        JLabel label1 = new JLabel("Enter value of temperature");
        gbl.setConstraints(label1, c);
        panel.add(label1);

        c.gridwidth = GridBagConstraints.REMAINDER;
        gbl.setConstraints(field1, c);
        panel.add(field1);

        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 0, 10);
        JLabel label2 = new JLabel("Please, choose initial scale");
        gbl.setConstraints(label2, c);
        panel.add(label2);

        buttonGroupScales = new ButtonGroup();
        JRadioButton[] radioButtons = new JRadioButton[scalesSet.length];

        for (int i = 0; i < scalesCount - 1; i++) {
            radioButtons[i] = new JRadioButton(scalesSet[i]);
            buttonGroupScales.add(radioButtons[i]);
            gbl.setConstraints(radioButtons[i], c);
            panel.add(radioButtons[i]);
        }

        radioButtons[scalesCount - 1] = new JRadioButton(scalesSet[scalesCount - 1], true);
        buttonGroupScales.add(radioButtons[scalesCount - 1]);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gbl.setConstraints(radioButtons[scalesCount - 1], c);
        panel.add(radioButtons[scalesCount - 1]);

        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 0, 10);
        JLabel label3 = new JLabel("Choose the scale to convert");
        gbl.setConstraints(label3, c);
        panel.add(label3);

        buttonGroupScalesToConvert = new ButtonGroup();
        JRadioButton[] buttonsToConvert = new JRadioButton[scalesSet.length];

        for (int i = 0; i < scalesCount - 1; i++) {
            buttonsToConvert[i] = new JRadioButton(scalesSet[i]);
            buttonGroupScalesToConvert.add(buttonsToConvert[i]);
            gbl.setConstraints(buttonsToConvert[i], c);
            panel.add(buttonsToConvert[i]);
        }

        buttonsToConvert[scalesCount - 1] = new JRadioButton(scalesSet[scalesCount - 1], true);
        buttonGroupScalesToConvert.add(buttonsToConvert[scalesCount - 1]);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gbl.setConstraints(buttonsToConvert[scalesCount - 1], c);
        panel.add(buttonsToConvert[scalesCount - 1]);

        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 0, 0);
        JLabel label4 = new JLabel("Click button to convert ");
        gbl.setConstraints(label4, c);
        panel.add(label4);

        c.gridwidth = GridBagConstraints.REMAINDER;
        gbl.setConstraints(buttonConvert, c);
        panel.add(buttonConvert);

        //buttonConvert.addActionListener(e -> {
            for (int i = 0; i < scalesSet.length; i++) {
                radioButtons[i].setActionCommand(scalesSet[i]);
                buttonsToConvert[i].setActionCommand(scalesSet[i]);
            }
        //});
    }

    public void setVisible(boolean bool) {
        frame.setVisible(bool);
    }

    private void displayError(String messageError) {
        JOptionPane.showMessageDialog(panel, messageError);
    }

    public void run() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            buttonConvert.addActionListener(e -> {

                String text = field1.getText();

                if (!isNumeric(text)) {
                    field1.setText("");
                    displayError("You need to enter number!");
                } else {
                    inputTemperature = Double.parseDouble(text);

                    String initialScale = buttonGroupScales.getSelection().getActionCommand();
                    String scaleToConvert = buttonGroupScalesToConvert.getSelection().getActionCommand();

                    if (!model.wasConvertTemperature(inputTemperature, initialScale, scaleToConvert)) {
                        displayError("The value of temperature of this scale isn't correct!");
                    } else {
                        double outputTemperature = model.getOutputTemperature();
                        field2.setText(initialScale);
                        field3.setText(scaleToConvert);
                        displayOutputMessage(outputTemperature);
                    }
                }
            });
        });
    }

    private void displayOutputMessage(double outputTemperature) {
        frame.setVisible(false);
        JFrame frameOutput = new JFrame(" Temperature converter (Output form)");
        frameOutput.setSize(700, 300);
        frameOutput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameOutput.setLocation(420, 200);
        GridBagLayout gbl = new GridBagLayout();
        frameOutput.setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(20, 10, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        JLabel label1 = new JLabel("Value of inputTemperature:");
        gbl.setConstraints(label1, c);
        frameOutput.add(label1);

        c.gridwidth = GridBagConstraints.REMAINDER;
        gbl.setConstraints(field1, c);
        field1.setEditable(false);
        frameOutput.add(field1);

        c.gridwidth = 1;
        JLabel label2 = new JLabel("You choose initial scale : ");
        gbl.setConstraints(label2, c);
        frameOutput.add(label2);

        c.gridwidth = GridBagConstraints.REMAINDER;
        gbl.setConstraints(field2, c);
        field2.setEditable(false);
        frameOutput.add(field2);

        c.gridwidth = 1;
        JLabel label3 = new JLabel("You choose scale to convert: ");
        gbl.setConstraints(label3, c);
        frameOutput.add(label3);

        c.gridwidth = GridBagConstraints.REMAINDER;
        gbl.setConstraints(field3, c);
        field3.setEditable(false);
        frameOutput.add(field3);

        c.gridwidth = 1;
        JLabel label4 = new JLabel("The result of conversion is : ");
        gbl.setConstraints(label4, c);
        frameOutput.add(label4);

        c.gridwidth = GridBagConstraints.REMAINDER;
        gbl.setConstraints(field4, c);
        field4.setEditable(false);
        field4.setText(Double.toString(outputTemperature));
        frameOutput.add(field4);

        frameOutput.setVisible(true);
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
