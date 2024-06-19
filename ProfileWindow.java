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
        FOOD_NUTRITION_MAP.put("Apple", new FoodNutrition(52, 0.3));
        FOOD_NUTRITION_MAP.put("Banana", new FoodNutrition(89, 1.1));
        FOOD_NUTRITION_MAP.put("Chicken", new FoodNutrition(239, 27.3));
        FOOD_NUTRITION_MAP.put("Rice", new FoodNutrition(130, 2.7));
        FOOD_NUTRITION_MAP.put("Almonds", new FoodNutrition(579, 21.2));
        FOOD_NUTRITION_MAP.put("Beef", new FoodNutrition(250, 26.1));
        FOOD_NUTRITION_MAP.put("Broccoli", new FoodNutrition(34, 2.8));
        FOOD_NUTRITION_MAP.put("Carrot", new FoodNutrition(41, 0.9));
        FOOD_NUTRITION_MAP.put("Cheese", new FoodNutrition(402, 25.0));
        FOOD_NUTRITION_MAP.put("Egg", new FoodNutrition(155, 13.0));
        FOOD_NUTRITION_MAP.put("Fish", new FoodNutrition(206, 22.1));
        FOOD_NUTRITION_MAP.put("Grapes", new FoodNutrition(69, 0.7));
        FOOD_NUTRITION_MAP.put("Milk", new FoodNutrition(42, 3.4));
        FOOD_NUTRITION_MAP.put("Orange", new FoodNutrition(47, 0.9));
        FOOD_NUTRITION_MAP.put("Peanut Butter", new FoodNutrition(588, 25.1));
        FOOD_NUTRITION_MAP.put("Potato", new FoodNutrition(77, 2.0));
        FOOD_NUTRITION_MAP.put("Spinach", new FoodNutrition(23, 2.9));
        FOOD_NUTRITION_MAP.put("Tomato", new FoodNutrition(18, 0.9));
        FOOD_NUTRITION_MAP.put("Yogurt", new FoodNutrition(59, 10.0));
        FOOD_NUTRITION_MAP.put("Avocado", new FoodNutrition(160, 2.0));
        FOOD_NUTRITION_MAP.put("Blueberries", new FoodNutrition(57, 0.7));
        FOOD_NUTRITION_MAP.put("Bread", new FoodNutrition(265, 9.0));
        FOOD_NUTRITION_MAP.put("Butter", new FoodNutrition(717, 0.9));
        FOOD_NUTRITION_MAP.put("Cashews", new FoodNutrition(553, 18.2));
        FOOD_NUTRITION_MAP.put("Celery", new FoodNutrition(16, 0.7));
        FOOD_NUTRITION_MAP.put("Cherries", new FoodNutrition(50, 1.0));
        FOOD_NUTRITION_MAP.put("Corn", new FoodNutrition(86, 3.2));
        FOOD_NUTRITION_MAP.put("Cucumber", new FoodNutrition(16, 0.7));
        FOOD_NUTRITION_MAP.put("Dates", new FoodNutrition(282, 2.5));
        FOOD_NUTRITION_MAP.put("Garlic", new FoodNutrition(149, 6.4));
        FOOD_NUTRITION_MAP.put("Honey", new FoodNutrition(304, 0.3));
        FOOD_NUTRITION_MAP.put("Kale", new FoodNutrition(49, 4.3));
        FOOD_NUTRITION_MAP.put("Lettuce", new FoodNutrition(15, 1.4));
        FOOD_NUTRITION_MAP.put("Mango", new FoodNutrition(60, 0.8));
        FOOD_NUTRITION_MAP.put("Mushrooms", new FoodNutrition(22, 3.1));
        FOOD_NUTRITION_MAP.put("Oats", new FoodNutrition(389, 16.9));
        FOOD_NUTRITION_MAP.put("Olives", new FoodNutrition(115, 0.8));
        FOOD_NUTRITION_MAP.put("Onion", new FoodNutrition(40, 1.1));
        FOOD_NUTRITION_MAP.put("Peas", new FoodNutrition(81, 5.4));
        FOOD_NUTRITION_MAP.put("Pineapple", new FoodNutrition(50, 0.5));
        FOOD_NUTRITION_MAP.put("Pumpkin", new FoodNutrition(26, 1.0));
        FOOD_NUTRITION_MAP.put("Raspberries", new FoodNutrition(52, 1.2));
        FOOD_NUTRITION_MAP.put("Strawberries", new FoodNutrition(32, 0.7));
        FOOD_NUTRITION_MAP.put("Sweet Potato", new FoodNutrition(86, 1.6));
        FOOD_NUTRITION_MAP.put("Turkey", new FoodNutrition(189, 28.0));
        FOOD_NUTRITION_MAP.put("Walnuts", new FoodNutrition(654, 15.2));
        FOOD_NUTRITION_MAP.put("Watermelon", new FoodNutrition(30, 0.6));
        FOOD_NUTRITION_MAP.put("Zucchini", new FoodNutrition(17, 1.2));
        FOOD_NUTRITION_MAP.put("Aloo Gobi", new FoodNutrition(65, 2.5));
        FOOD_NUTRITION_MAP.put("Baingan Bharta", new FoodNutrition(102, 3.5));
        FOOD_NUTRITION_MAP.put("Bhindi Masala", new FoodNutrition(80, 2.1));
        FOOD_NUTRITION_MAP.put("Butter Chicken", new FoodNutrition(240, 12.0));
        FOOD_NUTRITION_MAP.put("Chana Masala", new FoodNutrition(180, 6.0));
        FOOD_NUTRITION_MAP.put("Dal Makhani", new FoodNutrition(174, 7.6));
        FOOD_NUTRITION_MAP.put("Fish Curry", new FoodNutrition(140, 15.0));
        FOOD_NUTRITION_MAP.put("Kadai Paneer", new FoodNutrition(240, 10.0));
        FOOD_NUTRITION_MAP.put("Malai Kofta", new FoodNutrition(220, 7.0));
        FOOD_NUTRITION_MAP.put("Mutter Paneer", new FoodNutrition(150, 6.0));
        FOOD_NUTRITION_MAP.put("Palak Paneer", new FoodNutrition(150, 8.0));
        FOOD_NUTRITION_MAP.put("Rajma", new FoodNutrition(140, 7.0));
        FOOD_NUTRITION_MAP.put("Tandoori Chicken", new FoodNutrition(150, 16.0));
        FOOD_NUTRITION_MAP.put("Biryani", new FoodNutrition(200, 6.0));
        FOOD_NUTRITION_MAP.put("Roti", new FoodNutrition(110, 3.0));
        FOOD_NUTRITION_MAP.put("Naan", new FoodNutrition(270, 9.0));
        FOOD_NUTRITION_MAP.put("Puri", new FoodNutrition(300, 6.0));
        FOOD_NUTRITION_MAP.put("Samosa", new FoodNutrition(260, 6.0));
        FOOD_NUTRITION_MAP.put("Vada Pav", new FoodNutrition(290, 7.0));
        FOOD_NUTRITION_MAP.put("Dosa", new FoodNutrition(170, 3.9));
        FOOD_NUTRITION_MAP.put("Idli", new FoodNutrition(58, 1.6));
        FOOD_NUTRITION_MAP.put("Upma", new FoodNutrition(200, 5.0));
        FOOD_NUTRITION_MAP.put("Poha", new FoodNutrition(130, 2.0));
        FOOD_NUTRITION_MAP.put("Pav Bhaji", new FoodNutrition(250, 4.5));
        FOOD_NUTRITION_MAP.put("Pongal", new FoodNutrition(205, 4.4));
        FOOD_NUTRITION_MAP.put("Rasam", new FoodNutrition(40, 2.0));
        FOOD_NUTRITION_MAP.put("Sambar", new FoodNutrition(150, 4.0));
        FOOD_NUTRITION_MAP.put("Chole Bhature", new FoodNutrition(450, 10.0));
        FOOD_NUTRITION_MAP.put("Paneer Tikka", new FoodNutrition(260, 17.0));
        FOOD_NUTRITION_MAP.put("Aloo Paratha", new FoodNutrition(220, 5.0));
        FOOD_NUTRITION_MAP.put("Gulab Jamun", new FoodNutrition(175, 2.5));
        FOOD_NUTRITION_MAP.put("Jalebi", new FoodNutrition(150, 1.0));
        FOOD_NUTRITION_MAP.put("Kheer", new FoodNutrition(120, 3.0));
        FOOD_NUTRITION_MAP.put("Lassi", new FoodNutrition(150, 5.0));
        FOOD_NUTRITION_MAP.put("Mango Lassi", new FoodNutrition(160, 4.0));
        FOOD_NUTRITION_MAP.put("Masala Chai", new FoodNutrition(60, 1.0));
        FOOD_NUTRITION_MAP.put("Mysore Pak", new FoodNutrition(560, 6.0));
        FOOD_NUTRITION_MAP.put("Paneer Butter Masala", new FoodNutrition(200, 8.0));
        FOOD_NUTRITION_MAP.put("Pulao", new FoodNutrition(160, 3.0));
        FOOD_NUTRITION_MAP.put("Raita", new FoodNutrition(60, 2.0));
        FOOD_NUTRITION_MAP.put("Saag", new FoodNutrition(100, 4.0));
        FOOD_NUTRITION_MAP.put("Sev Puri", new FoodNutrition(250, 5.0));
        FOOD_NUTRITION_MAP.put("Shahi Paneer", new FoodNutrition(300, 10.0));
        FOOD_NUTRITION_MAP.put("Veg Korma", new FoodNutrition(150, 4.0));
        FOOD_NUTRITION_MAP.put("Veg Pakora", new FoodNutrition(250, 5.0));
        FOOD_NUTRITION_MAP.put("Masoor Dal", new FoodNutrition(116, 9.0));
        FOOD_NUTRITION_MAP.put("Tamarind Rice", new FoodNutrition(165, 3.0));
        FOOD_NUTRITION_MAP.put("Medu Vada", new FoodNutrition(250, 8.0));
        FOOD_NUTRITION_MAP.put("Sabudana Khichdi", new FoodNutrition(200, 2.0));

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
