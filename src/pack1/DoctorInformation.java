package pack1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class DoctorInformation extends JFrame {
    private JComboBox<String> cityComboBox;

    public DoctorInformation() {
        setTitle("Doctor Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblDoctorName = new JLabel("Doctor Name:");
        JTextField txtDoctorName = new JTextField();

        JLabel lblSurname = new JLabel("Surname:");
        JTextField txtSurname = new JTextField();

        JLabel lblDepartment = new JLabel("Department:");
        JTextField txtDepartment = new JTextField();

        cityComboBox = new JComboBox<>(new HospitalSelection().getCityOptions());

        JButton btnBack = new JButton("Back");
        JButton btnSave = new JButton("Save");
        JButton btnDelete = new JButton("Delete");

        panel.add(lblDoctorName);
        panel.add(txtDoctorName);
        panel.add(lblSurname);
        panel.add(txtSurname);
        panel.add(lblDepartment);
        panel.add(txtDepartment);
        panel.add(new JLabel("City:"));
        panel.add(cityComboBox);
        panel.add(btnBack);
        panel.add(btnSave);
        panel.add(btnDelete);

        add(panel);
        pack();
        setLocationRelativeTo(null);

        btnBack.addActionListener(e -> {
            dispose();
            new MainFrame().setVisible(true);
        });

        btnSave.addActionListener(e -> {
            String doctorName = txtDoctorName.getText();
            String surname = txtSurname.getText();
            String department = txtDepartment.getText();
            String selectedCity = (String) cityComboBox.getSelectedItem();

            
            try {
                FileWriter writer = new FileWriter("doctor_records.txt", true);
                writer.write("Doctor Name: " + doctorName + "\n");
                writer.write("Surname: " + surname + "\n");
                writer.write("Department: " + department + "\n");
                writer.write("City: " + selectedCity + "\n");
                writer.write("------------------------\n");
                writer.close();
                JOptionPane.showMessageDialog(this, "Information saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnDelete.addActionListener(e -> {
            txtDoctorName.setText("");
            txtSurname.setText("");
            txtDepartment.setText("");
            cityComboBox.setSelectedIndex(0);
        });
    }
}
