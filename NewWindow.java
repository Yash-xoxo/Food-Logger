import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class NewWindow extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JTextField heightField;
    private JTextField weightField;
    private JTextField genderField;
    private MainWindow mainWindow;
    private String[] userData;

    public NewWindow(MainWindow mainWindow, String[] userData) {
        this.mainWindow = mainWindow;
        this.userData = userData;

        // Set up the new window
        setTitle(userData == null ? "Add New User" : "Edit User");
        setSize(1000,900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create panels for layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));

        // Add input fields
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField(20);
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField(20);
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Height (cm):"));
        heightField = new JTextField(20);
        inputPanel.add(heightField);

        inputPanel.add(new JLabel("Weight (kg):"));
        weightField = new JTextField(20);
        inputPanel.add(weightField);

        inputPanel.add(new JLabel("Gender:"));
        genderField = new JTextField(20);
        inputPanel.add(genderField);

        // Pre-fill fields if editing
        if (userData != null) {
            nameField.setText(userData[0]);
            ageField.setText(userData[1]);
            heightField.setText(userData[2]);
            weightField.setText(userData[3]);
            genderField.setText(userData[4]);
        }

        JButton saveButton = new JButton(userData == null ? "Add User" : "Save Changes");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String age = ageField.getText();
                String height = heightField.getText();
                String weight = weightField.getText();
                String gender = genderField.getText();

                if (name.isEmpty() || age.isEmpty() || height.isEmpty() || weight.isEmpty() || gender.isEmpty()) {
                    JOptionPane.showMessageDialog(NewWindow.this, "Please fill all fields.");
                } else {
                    saveUser(name, age, height, weight, gender);
                }
            }
        });

        // Create a close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        // Add buttons to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(closeButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Set the layout for the main panel
        setContentPane(mainPanel);
    }

    private void saveUser(String name, String age, String height, String weight, String gender) {
        try (FileWriter writer = new FileWriter("users.txt", true)) {
            writer.write(name + ";" + age + ";" + height + ";" + weight + ";" + gender + "\n");
            JOptionPane.showMessageDialog(this, "User saved successfully!");
            mainWindow.loadUsers(); // Refresh the display in main window
            dispose(); // Close the window
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving user: " + e.getMessage());
        }
    }
}
