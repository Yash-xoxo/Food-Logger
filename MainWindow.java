import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
    private JList<String> userList;
    private DefaultListModel<String> listModel;
    private List<String[]> userDataList;

    public MainWindow() {
        // Set up the main window
        setTitle("Main Home Window");
        setSize(1000, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize user data list
        userDataList = new ArrayList<>();

        // Create a list model and JList to display user names
        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        loadUsers();

        // Create buttons for various actions
        JButton openProfileButton = new JButton("Open Profile");
        JButton addUserButton = new JButton("Add User");
        JButton editUserButton = new JButton("Edit User");
        JButton deleteUserButton = new JButton("Delete User");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to buttons
        openProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = userList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String[] userData = userDataList.get(selectedIndex);
                    ProfileWindow profileWindow = new ProfileWindow(userData);
                    profileWindow.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainWindow.this, "Please select a user.");
                }
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewWindow newUserWindow = new NewWindow(MainWindow.this, null);
                newUserWindow.setVisible(true);
            }
        });

        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = userList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String[] userData = userDataList.get(selectedIndex);
                    NewWindow editUserWindow = new NewWindow(MainWindow.this, userData);
                    editUserWindow.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainWindow.this, "Please select a user to edit.");
                }
            }
        });

        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = userList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String[] userData = userDataList.get(selectedIndex);
                    deleteUser(userData[0]); // Delete user by name
                    loadUsers(); // Refresh the display
                } else {
                    JOptionPane.showMessageDialog(MainWindow.this, "Please select a user to delete.");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Create panels for better layout control
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openProfileButton);
        buttonPanel.add(addUserButton);
        buttonPanel.add(editUserButton);
        buttonPanel.add(deleteUserButton);
        buttonPanel.add(exitButton);

        // Add components to the main window
        setLayout(new BorderLayout());
        add(new JScrollPane(userList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void loadUsers() {
        listModel.clear();
        userDataList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(";");
                listModel.addElement(userData[0]); // Add only the name to the list
                userDataList.add(userData); // Store all data for each user
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading users: " + e.getMessage());
        }
    }

    private void deleteUser(String userName) {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(";");
                if (!userData[0].equals(userName)) {
                    lines.add(line);
                }
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
            for (String newline : lines) {
                writer.write(newline + "\n");
            }
            writer.close();

            JOptionPane.showMessageDialog(this, "User deleted successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error deleting user: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
}

