import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ProfileWindow extends JFrame {
    private JLabel nameLabel;
    private JTextField foodTypeField;
    private JTextField amountField;
    private JTextArea foodLogArea;
    private JLabel totalCaloriesLabel;
    private JLabel totalProteinLabel;

    private static final Map<String, FoodNutrition> FOOD_NUTRITION_MAP = new HashMap<>();

    static {
        // Initialize the nutrition data for different foods (per 100 grams)
        FOOD_NUTRITION_MAP.put("Apple", new FoodNutrition(52, 0.3));
        FOOD_NUTRITION_MAP.put("Banana", new FoodNutrition(89, 1.1));
        FOOD_NUTRITION_MAP.put("Chicken", new FoodNutrition(239, 27.3));
        FOOD_NUTRITION_MAP.put("Rice", new FoodNutrition(130, 2.7));
        // Add more food items as needed
    }

    public ProfileWindow(String[] userData) {
        // Set up the profile window
        setTitle("Profile: " + userData[0]); // First element is the name
        setSize(1000,900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create panels for layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel infoPanel = new JPanel(new GridLayout(userData.length + 2, 1));

        // Display user details
        nameLabel = new JLabel("User Profile: " + userData[0]);
        infoPanel.add(nameLabel);

        for (int i = 1; i < userData.length; i++) {
            infoPanel.add(new JLabel(userData[i]));
        }

        // Food entry fields
        JLabel foodLabel = new JLabel("Enter Food Type:");
        foodTypeField = new JTextField(20);

        JLabel amountLabel = new JLabel("Enter Amount (grams):");
        amountField = new JTextField(10);

        JButton saveFoodButton = new JButton("Save Food Entry");
        saveFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String foodType = foodTypeField.getText();
                String amountText = amountField.getText();
                if (foodType.isEmpty() || amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(ProfileWindow.this, "Please enter both food type and amount.");
                } else {
                    try {
                        int amount = Integer.parseInt(amountText);
                        saveFoodEntry(userData[0], foodType, amount);
                        updateFoodLog(userData[0]);
                        calculateTotalNutrition(userData[0]);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(ProfileWindow.this, "Amount must be a number.");
                    }
                }
            }
        });

        // Food log area
        foodLogArea = new JTextArea(10, 30);
        foodLogArea.setEditable(false);
        JScrollPane foodLogScrollPane = new JScrollPane(foodLogArea);

        // Total calories and protein labels
        totalCaloriesLabel = new JLabel("Total Calories: 0");
        totalProteinLabel = new JLabel("Total Protein: 0");

        // Add components to panels
        infoPanel.add(foodLabel);
        infoPanel.add(foodTypeField);
        infoPanel.add(amountLabel);
        infoPanel.add(amountField);
        infoPanel.add(saveFoodButton);
        infoPanel.add(foodLogScrollPane);
        infoPanel.add(totalCaloriesLabel);
        infoPanel.add(totalProteinLabel);

        mainPanel.add(infoPanel, BorderLayout.CENTER);

        // Create a close button to close the profile window
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Set the layout for the main panel
        setContentPane(mainPanel);

        // Load and display food log
        updateFoodLog(userData[0]);
        calculateTotalNutrition(userData[0]);
    }

    private void saveFoodEntry(String userName, String foodType, int amount) {
        try (FileWriter writer = new FileWriter(userName + "_food.txt", true)) {
            writer.write(foodType + ";" + amount + "\n");
            JOptionPane.showMessageDialog(this, "Food entry saved successfully!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving food entry: " + ex.getMessage());
        }
    }

    private void updateFoodLog(String userName) {
        foodLogArea.setText("");
        try (BufferedReader reader = new BufferedReader(new FileReader(userName + "_food.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                foodLogArea.append(line + "\n");
            }
        } catch (IOException e) {
            foodLogArea.setText("No food entries found.");
        }
    }

    private void calculateTotalNutrition(String userName) {
        int totalCalories = 0;
        double totalProtein = 0.0;

        try (BufferedReader reader = new BufferedReader(new FileReader(userName + "_food.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String foodType = parts[0];
                int amount = Integer.parseInt(parts[1]);

                FoodNutrition nutrition = FOOD_NUTRITION_MAP.get(foodType);
                if (nutrition != null) {
                    totalCalories += nutrition.calories * amount / 100;
                    totalProtein += nutrition.protein * amount / 100;
                }
            }
        } catch (IOException e) {
            // Handle error
        }

        totalCaloriesLabel.setText("Total Calories: " + totalCalories);
        totalProteinLabel.setText("Total Protein: " + totalProtein);
    }

    // Inner class to represent nutrition information
    static class FoodNutrition {
        int calories;
        double protein;

        FoodNutrition(int calories, double protein) {
            this.calories = calories;
            this.protein = protein;
        }
    }
}
