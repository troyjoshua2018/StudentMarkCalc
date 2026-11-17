
import javax.swing.*;
import java.awt.*;

public class StudentMarkCalc extends JFrame {

    // Declaration
    private JTextField txtStudentNumber;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtEmail;
    private JTextField txtTest1;
    private JTextField txtTest2;
    private JTextField txtAssignment;
    private JTextField txtExam;
    private JTextField txtOverallMark;
    private JTextField txtFinalMark;

    public StudentMarkCalc() {

        setTitle("Student Marks Calculator");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        );

        JLabel heading = new JLabel(
                "STUDENT MARKS CALCULATOR",
                SwingConstants.CENTER
        );

        heading.setFont(new Font("Arial", Font.BOLD, 18));

        mainPanel.add(heading, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(
                new GridLayout(10, 2, 8, 10)
        );

        formPanel.setBorder(
                BorderFactory.createTitledBorder("Student Information")
        );

        // Initialise text fields
        txtStudentNumber = new JTextField();
        txtName = new JTextField();
        txtSurname = new JTextField();
        txtEmail = new JTextField();
        txtTest1 = new JTextField();
        txtTest2 = new JTextField();
        txtAssignment = new JTextField();
        txtExam = new JTextField();
        txtOverallMark = new JTextField();
        txtFinalMark = new JTextField();

        // Make calculated fields read-only
        txtOverallMark.setEditable(false);
        txtFinalMark.setEditable(false);

        // Add labels and fields
        addFormRow(formPanel, "Student Number:", txtStudentNumber);
        addFormRow(formPanel, "Name:", txtName);
        addFormRow(formPanel, "Surname:", txtSurname);
        addFormRow(formPanel, "Email:", txtEmail);
        addFormRow(formPanel, "Test 1 Mark:", txtTest1);
        addFormRow(formPanel, "Test 2 Mark:", txtTest2);
        addFormRow(formPanel, "Assignment Mark:", txtAssignment);
        addFormRow(formPanel, "Exam Mark:", txtExam);
        addFormRow(formPanel, "Overall Mark:", txtOverallMark);
        addFormRow(formPanel, "Final Mark:", txtFinalMark);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Buttons
        JButton btnCalculate = new JButton("Calculate");
        JButton btnClear = new JButton("Clear");
        JButton btnExit = new JButton("Exit");

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 5)
        );

        buttonPanel.add(btnCalculate);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExit);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Button event handlers
        btnCalculate.addActionListener(e -> calculateMarks());
        btnClear.addActionListener(e -> clearFields());
        btnExit.addActionListener(e -> System.exit(0));

        add(mainPanel);

        setVisible(true);
    }

    private void addFormRow(
            JPanel panel, String label, JTextField textField) {

        panel.add(new JLabel(label));
        panel.add(textField);
    }

    private void calculateMarks() {

        try {

            // Validate student details
            if (txtStudentNumber.getText().trim().isEmpty()
                    || txtName.getText().trim().isEmpty()
                    || txtSurname.getText().trim().isEmpty()
                    || txtEmail.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter all student details.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // Validate email
            String email = txtEmail.getText().trim();

            if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid email address.",
                        "Invalid Email",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // Read assessment marks
            double test1 = readMark(txtTest1);
            double test2 = readMark(txtTest2);
            double assignment = readMark(txtAssignment);
            double exam = readMark(txtExam);

            // Calculate overall and final marks
            double overallMark = (test1 + test2 + assignment) / 3;

            double finalMark = (overallMark * 0.4) + (exam * 0.6);

            // Display calculated marks
            txtOverallMark.setText(
                    String.format("%.2f", overallMark)
            );

            txtFinalMark.setText(
                    String.format("%.2f", finalMark)
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Marks calculated successfully.",
                    "Calculation Complete",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric marks between 0 and 100 for all assessments.",
                    "Invalid Marks",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private double readMark(JTextField field) {

        String value = field.getText().trim();

        if (value.isEmpty()) {
            throw new NumberFormatException("A mark is required.");
        }

        double mark = Double.parseDouble(value);

        if (!Double.isFinite(mark) || mark < 0 || mark > 100) {

            throw new NumberFormatException(
                    "Marks must be between 0 and 100."
            );
        }

        return mark;
    }

    // Method to clear fields
    private void clearFields() {

        txtStudentNumber.setText("");
        txtName.setText("");
        txtSurname.setText("");
        txtEmail.setText("");
        txtTest1.setText("");
        txtTest2.setText("");
        txtAssignment.setText("");
        txtExam.setText("");
        txtOverallMark.setText("");
        txtFinalMark.setText("");
    }

    // Main method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new StudentMarkCalc());
    }
}
