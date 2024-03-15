package pack1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HospitalSelection extends JFrame {
    public HospitalSelection() {
        setTitle("Hospital Selection");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BorderLayout());

        JComboBox<String> cityComboBox = new JComboBox<>(getCityOptions());

        JTextArea hospitalList = new JTextArea(10, 30);
        hospitalList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(hospitalList);

        JButton showHospitalsButton = new JButton("Show Hospitals");
        JButton backButton = new JButton("Back");

        JPanel cityPanel = new JPanel();
        cityPanel.add(new JLabel("Select City: "));
        cityPanel.add(cityComboBox);
        cityPanel.add(showHospitalsButton);
        cityPanel.add(backButton);

        panel.add(cityPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        showHospitalsButton.addActionListener(e -> {
            hospitalList.setText(""); // Clear previous list

            String selectedCity = (String) cityComboBox.getSelectedItem();
            String[] hospitals = getHospitalOptions(selectedCity);

            if (hospitals != null) {
                for (String hospital : hospitals) {
                    hospitalList.append(hospital + "\n");
                }
            } else {
                hospitalList.setText("No hospitals found for the selected city!");
            }
        });

        backButton.addActionListener(e -> {
            dispose();
            new MainFrame().setVisible(true);
        });

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HospitalSelection frame = new HospitalSelection();
            frame.setVisible(true);
        });
    }

    public String[] getHospitalOptions(String selectedCity) {
        Map<String, String[]> hospitals = new HashMap<>();
        hospitals.put("Istanbul", new String[]{"Istanbul Hospital", "Umraniye Hospital", "Uskudar Hospital","Kadıköy Hospital", "Florya Hospital"});
        hospitals.put("Ankara", new String[]{"Ankara Hospital", "Kızılay Hospital", "Çankaya Hospital", "Keçiören Hospital","Sincan Hospital", "Çubuk Hospital"});
        hospitals.put("Izmir", new String[]{"Izmir Hospital", "Konak Hospital","Bergama Hospital", "Çeşme Hospital"});
        hospitals.put("Bursa", new String[]{"Bursa Hospital", "Nilufer Hospital", "Iznik Hospital"});
        hospitals.put("Antalya", new String[]{"Antalya Hospital", "Alanya Hospital","Aksu Hospital", "Manavgat Hospital","Kemer Hospital"});
        hospitals.put("Manisa", new String[]{"Manisa Hospital", "Akhisar Hospital"});

        return hospitals.getOrDefault(selectedCity, new String[]{"No hospitals found for the selected city!"});
    }

    public String[] getCityOptions() {
        return new String[]{"Istanbul", "Ankara", "Izmir","Bursa", "Antalya", "Manisa"};
    }
}
