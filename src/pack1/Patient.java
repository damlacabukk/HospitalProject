package pack1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Patient extends JFrame {
    private JComboBox<String> cmbDoctorName;
    private JComboBox<String> hospitalComboBox;
    private Map<String, String[]> doctorsByDepartment;

    public Patient() {
        setTitle("Patient Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField();

        JLabel lblSurname = new JLabel("Surname:");
        JTextField txtSurname = new JTextField();

        JLabel lblDepartment = new JLabel("Department:");
        String[] departments = {"Oral and Dental Diseases", "Biochemistry", "Nutrition and Diet", "Dermatology", "Pediatric Surgery"};
        JComboBox<String> cmbDepartment = new JComboBox<>(departments);

        JLabel lblDoctorName = new JLabel("Doctor Name:");
        cmbDoctorName = new JComboBox<>();

        JLabel lblHospital = new JLabel("Hospital:");
        hospitalComboBox = new JComboBox<>();

        String[] hospitals = new HospitalSelection().getHospitalOptions("Istanbul");
        for (String hospital : hospitals) {
            hospitalComboBox.addItem(hospital);
        }

        JLabel lblAppointmentTime = new JLabel("Appointment Time:");
        String[] appointmentTimes = {"9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00"};
        JComboBox<String> cmbAppointmentTime = new JComboBox<>(appointmentTimes);

        JLabel lblAppointmentDate = new JLabel("Appointment Date:");
        String[] appointmentDates = {"2024-01-02", "2024-01-03", "2024-01-04", "2024-01-05", "2024-01-06"};
        JComboBox<String> cmbAppointmentDate = new JComboBox<>(appointmentDates);

        JLabel lblPhone = new JLabel("Phone:");
        JTextField txtPhone = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JButton btnBack = new JButton("Back");
        JButton btnSave = new JButton("Save");
        JButton btnDelete = new JButton("Delete");
        JButton btnViewRecords = new JButton("View Records");

        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblSurname);
        panel.add(txtSurname);
        panel.add(lblDepartment);
        panel.add(cmbDepartment);
        panel.add(lblDoctorName);
        panel.add(cmbDoctorName);
        panel.add(lblHospital);
        panel.add(hospitalComboBox);
        panel.add(lblAppointmentTime);
        panel.add(cmbAppointmentTime);
        panel.add(lblAppointmentDate);
        panel.add(cmbAppointmentDate);
        panel.add(lblPhone);
        panel.add(txtPhone);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(btnBack);
        panel.add(btnSave);
        panel.add(btnDelete);
        panel.add(btnViewRecords);

        add(panel);
        pack();
        setLocationRelativeTo(null);

        btnBack.addActionListener(e -> {
            dispose();
            new MainFrame().setVisible(true);
        });

        btnSave.addActionListener(e -> {
            String name = txtName.getText();
            String surname = txtSurname.getText();
            String department = (String) cmbDepartment.getSelectedItem();
            String doctor = (String) cmbDoctorName.getSelectedItem();
            String appointmentTime = (String) cmbAppointmentTime.getSelectedItem();
            String appointmentDate = (String) cmbAppointmentDate.getSelectedItem();
            String phone = txtPhone.getText();
            String email = txtEmail.getText();

            try {
                FileWriter writer = new FileWriter("patient_records.txt", true);
                writer.write("Name: " + name + "\n");
                writer.write("Surname: " + surname + "\n");
                writer.write("Department: " + department + "\n");
                writer.write("Doctor: " + doctor + "\n");
                writer.write("Appointment Time: " + appointmentTime + "\n");
                writer.write("Appointment Date: " + appointmentDate + "\n");
                writer.write("Phone: " + phone + "\n");
                writer.write("Email: " + email + "\n");
                writer.write("------------------------\n");
                writer.close();
                JOptionPane.showMessageDialog(this, "Information saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnDelete.addActionListener(e -> {
            txtName.setText("");
            txtSurname.setText("");
            cmbDepartment.setSelectedIndex(0);
            cmbDoctorName.setSelectedIndex(0);
            cmbAppointmentTime.setSelectedIndex(0);
            cmbAppointmentDate.setSelectedIndex(0);
            txtPhone.setText("");
            txtEmail.setText("");
        });

        btnViewRecords.addActionListener(e -> {
            try {
                Desktop.getDesktop().open(new java.io.File("patient_records.txt"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        doctorsByDepartment = new HashMap<>();
        doctorsByDepartment.put("Oral and Dental Diseases", new String[]{"Dr. Duncan", "Dr. Francis"});
        doctorsByDepartment.put("Biochemistry", new String[]{"Dr. Felix", "Dr. Drew"});
        doctorsByDepartment.put("Nutrition and Diet", new String[]{"Dr. Smith", "Dr. Johnson"});
        doctorsByDepartment.put("Dermatology", new String[]{"Dr. Parker", "Dr. Turner"});
        doctorsByDepartment.put("Pediatric Surgery", new String[]{"Dr. Brown", "Dr. Miller"});

        cmbDepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDepartment = (String) cmbDepartment.getSelectedItem();
                String[] doctors = doctorsByDepartment.get(selectedDepartment);
                cmbDoctorName.removeAllItems();

                if (doctors != null) {
                    for (String doctor : doctors) {
                        cmbDoctorName.addItem(doctor);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Patient frame = new Patient();
            frame.setVisible(true);
        });
    }
}
