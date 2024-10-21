package part1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import static part1.SqlScripts.*;

public class GuiInterface extends JFrame {
    private final JTextField surnameField;
    private final JTextField nameField;
    private final JTextField patronymicField;
    private final JTextField birthDateField;
    private final JTextField diagnosisField;
    private final JTextField positionField;
    private final JTextField specializationField;
    private final JTabbedPane tabbedPane;

    public GuiInterface() {
        surnameField = new JTextField(20);
        nameField = new JTextField(20);
        patronymicField = new JTextField(20);
        birthDateField = new JTextField(20);
        diagnosisField = new JTextField(20);
        positionField = new JTextField(20);
        specializationField = new JTextField(20);

        JPanel patientPanel = new JPanel();
        patientPanel.setLayout(new GridLayout(5, 2));
        patientPanel.add(new JLabel("Surname:"));
        patientPanel.add(surnameField);
        patientPanel.add(new JLabel("Name:"));
        patientPanel.add(nameField);
        patientPanel.add(new JLabel("Patronymic:"));
        patientPanel.add(patronymicField);
        patientPanel.add(new JLabel("Birth Date (YYYY-MM-DD):"));
        patientPanel.add(birthDateField);
        patientPanel.add(new JLabel("Diagnosis:"));
        patientPanel.add(diagnosisField);

        JPanel doctorPanel = new JPanel();
        doctorPanel.setLayout(new GridLayout(6, 2));
        doctorPanel.add(new JLabel("Surname:"));
        doctorPanel.add(new JTextField(20));
        doctorPanel.add(new JLabel("Name:"));
        doctorPanel.add(new JTextField(20));
        doctorPanel.add(new JLabel("Patronymic:"));
        doctorPanel.add(new JTextField(20));
        doctorPanel.add(new JLabel("Birth Date (YYYY-MM-DD):"));
        doctorPanel.add(new JTextField(20));
        doctorPanel.add(new JLabel("Position:"));
        doctorPanel.add(positionField);
        doctorPanel.add(new JLabel("Specialization:"));
        doctorPanel.add(specializationField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new AddButtonListener());

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Patients", patientPanel);
        tabbedPane.addTab("Doctors", doctorPanel);

        add(tabbedPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Hospital Database Management");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (tabbedPane.getSelectedIndex() == 0) {
                addPatient();
            } else {
                addDoctor();
            }
        }

        private void addPatient() {
            String surname = surnameField.getText();
            String name = nameField.getText();
            String patronymic = patronymicField.getText();
            String birthDate = birthDateField.getText();
            String diagnosis = diagnosisField.getText();

            if (surname.isEmpty() || name.isEmpty() || patronymic.isEmpty() || birthDate.isEmpty() || diagnosis.isEmpty()) {
                JOptionPane.showMessageDialog(GuiInterface.this, "Please fill in all fields for the patient.");
                return;
            }

            if (!isValidDate(birthDate)) {
                JOptionPane.showMessageDialog(GuiInterface.this, "Invalid date format. Please use YYYY-MM-DD.");
                return;
            }

            try (Connection connection = DBconfig.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT)) {
                preparedStatement.setString(1, surname);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, patronymic);
                preparedStatement.setDate(4, Date.valueOf(birthDate));
                preparedStatement.setString(5, diagnosis);

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(GuiInterface.this, "Patient data added successfully!");

            } catch (SQLException sqlEx) {
                if (sqlEx.getSQLState().equals("23505")) {
                    JOptionPane.showMessageDialog(GuiInterface.this, "A record with this primary key already exists for the patient.");
                } else {
                    JOptionPane.showMessageDialog(GuiInterface.this, "Database error: " + sqlEx.getMessage());
                }
            }
        }

        private void addDoctor() {
            String surname = ((JTextField) ((JPanel) tabbedPane.getComponentAt(1)).getComponent(1)).getText();
            String name = ((JTextField) ((JPanel) tabbedPane.getComponentAt(1)).getComponent(3)).getText();
            String patronymic = ((JTextField) ((JPanel) tabbedPane.getComponentAt(1)).getComponent(5)).getText();
            String birthDate = ((JTextField) ((JPanel) tabbedPane.getComponentAt(1)).getComponent(7)).getText();
            String position = positionField.getText();
            String specialization = specializationField.getText();

            if (surname.isEmpty() || name.isEmpty() || patronymic.isEmpty() || birthDate.isEmpty() || position.isEmpty() || specialization.isEmpty()) {
                JOptionPane.showMessageDialog(GuiInterface.this, "Please fill in all fields for the doctor.");
                return;
            }

            if (!isValidDate(birthDate)) {
                JOptionPane.showMessageDialog(GuiInterface.this, "Invalid date format. Please use YYYY-MM-DD.");
                return;
            }
            try (Connection connection = DBconfig.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DOCTOR)) {
                preparedStatement.setString(1, surname);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, patronymic);
                preparedStatement.setDate(4, Date.valueOf(birthDate));
                preparedStatement.setString(5, position);
                preparedStatement.setString(6, specialization);

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(GuiInterface.this, "Doctor data added successfully!");

            } catch (SQLException sqlEx) {
                if (sqlEx.getSQLState().equals("23505")) {
                    JOptionPane.showMessageDialog(GuiInterface.this, "A record with this primary key already exists for the doctor.");
                } else {
                    JOptionPane.showMessageDialog(GuiInterface.this, "Database error: " + sqlEx.getMessage());
                }
            }
        }

        private boolean isValidDate(String date) {
            try {
                java.sql.Date.valueOf(date);
                return true;
            } catch (IllegalArgumentException ex) {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        DBconfig.liquibaseStart();
        SwingUtilities.invokeLater(GuiInterface::new);
    }
}