import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TotalNutritionWindow extends JFrame {
    private JTextArea totalNutritionArea;

    public TotalNutritionWindow() {
        // Set up the window
        setTitle("Total Nutrition Summary");
        setSize(1000,900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        totalNutritionArea = new JTextArea(10, 30);
        totalNutritionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(totalNutritionArea);

        // Add components to the window
        add(scrollPane, BorderLayout.CENTER);

        // Calculate and display total nutrition
        calculateAndDisplayTotalNutrition();
    }

    private void calculateAndDisplayTotalNutrition() {
        totalNutritionArea.setText("");

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(";");
                String userName = userData[0];

                int totalCalories = 0;
                double totalProtein = 0.0;

                try (BufferedReader foodReader = new BufferedReader(new FileReader(userName + "_food.txt"))) {
                    String foodLine;
                    while ((foodLine = foodReader.readLine()) != null) {
                        String[] parts = foodLine.split(";");
                        String foodType = parts[0];
                        int amount = Integer.parseInt(parts[1]);

                        ProfileWindow.FoodNutrition nutrition = ProfileWindow.FOOD_NUTRITION_MAP.get(foodType);
                        if (nutrition != null) {
                            totalCalories += nutrition.calories * amount / 100;
                            totalProtein += nutrition.protein * amount / 100;
                        }
                    }
                } catch (IOException e) {
                    // Handle error
                }

                totalNutritionArea.append("User: " + userName + "\n");
                totalNutritionArea.append("Total Calories: " + totalCalories + "\n");
                totalNutritionArea.append("Total Protein: " + totalProtein + "\n");
                totalNutritionArea.append("---------------------------\n");
            }
        } catch (IOException e) {
            totalNutritionArea.setText("Error calculating total nutrition: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TotalNutritionWindow window = new TotalNutritionWindow();
                window.setVisible(true);
            }
        });
    }
}
