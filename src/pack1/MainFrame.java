package pack1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnPatientRegistration = new JButton("Patient Registration");
        JButton btnDoctorInformation = new JButton("Doctor Information");
        JButton btnHospitalSelection = new JButton("Hospital Selection"); 
        JButton btnExit = new JButton("Exit");

        panel.add(btnPatientRegistration);
        panel.add(btnDoctorInformation);
        panel.add(btnHospitalSelection); 
        panel.add(btnExit);

        add(panel);
        pack();
        setLocationRelativeTo(null);

        btnPatientRegistration.addActionListener(e -> {
            dispose();
            new Patient().setVisible(true);
        });

        btnDoctorInformation.addActionListener(e -> {
            dispose();
            new DoctorInformation().setVisible(true);
        });

        btnHospitalSelection.addActionListener(e -> {
            dispose();
            new HospitalSelection().setVisible(true); 
        });

        btnExit.addActionListener(e -> {
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
