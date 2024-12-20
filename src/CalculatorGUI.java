import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField display; // Text field to display numbers and results
    private String operator; // Stores the selected operator
    private double num1, num2, result; // Variables for calculations

    public CalculatorGUI() {
        // Frame settings
        setTitle("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 4, 10, 10));

        // Buttons array
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        // Add buttons to panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this); // Add event listener for button clicks
            buttonsPanel.add(button);
        }

        add(buttonsPanel, BorderLayout.CENTER); // Add panel to frame

        // Make the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Get the button's label

        // If a number button is clicked
        if (command.matches("\\d")) {
            display.setText(display.getText() + command);
        }
        // If an operator button is clicked
        else if (command.matches("[+\\-*/]")) {
            operator = command;
            num1 = Double.parseDouble(display.getText());
            display.setText(""); // Clear display for next input
        }
        // If the equals button is clicked
        else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+" -> result = num1 + num2;
                case "-" -> result = num1 - num2;
                case "*" -> result = num1 * num2;
                case "/" -> result = (num2 != 0) ? num1 / num2 : Double.NaN; // Handle division by zero
            }
            display.setText(String.valueOf(result)); // Show the result
        }
        // If the clear button is clicked
        else if (command.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0; // Reset all values
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI());
    }

}
