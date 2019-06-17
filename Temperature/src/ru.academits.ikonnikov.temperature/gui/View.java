package ru.academits.ikonnikov.temperature.gui;

import ru.academits.ikonnikov.temperature.Model;

import javax.swing.*;
import java.awt.*;

public class View {
    private JTextField field1 = new JTextField(10);
    private JTextField field2 = new JTextField(10);
    private JButton buttonConvert = new JButton("OK");
    private JFrame frame = new JFrame(" Temperature converter");
    private JPanel panel = new JPanel();
    private double inputTemperature;
    private ButtonGroup buttonGroupScales;
    private ButtonGroup buttonGroupScalesToConvert;
    private Model model;
    private Scales scales;

    public View(Scales scales) {
        this.scales = scales;
        model = new Model(scales);
        String[] scalesSet = scales.getScales();
        int scalesCount = scalesSet.length;
        frame.setSize(700, 350);
        frame.setLocation(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        c.insets = new Insets(10, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        JLabel label1 = new JLabel("Enter value of temperature");
        gbl.setConstraints(label1, c);
        panel.add(label1);

        c.gridwidth = GridBagConstraints.REMAINDER;
        gbl.setConstraints(field1, c);
        field1.setEditable(true);
        panel.add(field1);

        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 0, 10);
        JLabel label2 = new JLabel("Please, choose initial scale");
        gbl.setConstraints(label2, c);
        panel.add(label2);

        buttonGroupScales = new ButtonGroup();
        JRadioButton[] radioButtons = new JRadioButton[scalesCount];

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

        for (int i = 0; i < scalesSet.length; i++) {
            radioButtons[i].setActionCommand(scalesSet[i]);
            buttonsToConvert[i].setActionCommand(scalesSet[i]);
        }

        c.gridwidth = 1;
        JLabel label5 = new JLabel("The result of conversion is : ");
        gbl.setConstraints(label5, c);
        panel.add(label5);

        c.gridwidth = GridBagConstraints.REMAINDER;
        gbl.setConstraints(field2, c);
        field2.setEditable(false);

        panel.add(field2);
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

                    String stringInitialScale = buttonGroupScales.getSelection().getActionCommand();
                    String stringScaleToConvert = buttonGroupScalesToConvert.getSelection().getActionCommand();

                    if (!model.wasConvertTemperature(inputTemperature, scales.getScaleIndex(stringInitialScale), scales.getScaleIndex(stringScaleToConvert))) {
                        field1.setText("");
                        field2.setText("");
                        displayError("The value of temperature of this scale isn't correct!");
                    } else {
                        double outputTemperature = model.getOutputTemperature();
                        field2.setText(String.format("%.2f", outputTemperature));
                    }
                }
            });
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
