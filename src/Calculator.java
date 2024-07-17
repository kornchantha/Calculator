import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JTextField inputField1;
    private JTextField inputField2;
    private JTextField resultField;
    private String operator = "";

    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel label1 = new JLabel("First Number:");
        inputPanel.add(label1, gbc);
        gbc.gridx++;
        inputField1 = new JTextField(10);
        inputPanel.add(inputField1, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel label2 = new JLabel("Second Number:");
        inputPanel.add(label2, gbc);
        gbc.gridx++;
        inputField2 = new JTextField(10);
        inputPanel.add(inputField2, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel label3 = new JLabel("Result:");
        inputPanel.add(label3, gbc);
        gbc.gridx++;
        resultField = new JTextField(10);
        resultField.setEditable(false);
        inputPanel.add(resultField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1; // Reset grid width to default
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] buttonLabels = {"+", "-", "*", "/", "=", "Clear"}; // Added "=" and "Clear" buttons
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            inputPanel.add(button, gbc);
            gbc.gridx++;
            if (gbc.gridx == 2) { // Reset grid position if column exceeds 2
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }

        add(inputPanel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "+":
            case "-":
            case "*":
            case "/":
                operator = command;
                break;
            case "=":
                calculateResult();
                break;
            case "Clear":
                clearFields();
                break;
        }
    }

    private void calculateResult() {
        try {
            double num1 = Double.parseDouble(inputField1.getText());
            double num2 = Double.parseDouble(inputField2.getText());
            double result = 0;
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(this, "Cannot divide by zero!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    break;
            }
            resultField.setText(String.valueOf(result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        inputField1.setText("");
        inputField2.setText("");
        resultField.setText("");
    }


}
