package swing;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecipeApp extends JFrame {

    private DefaultListModel<String> recipeListModel;
    private JList<String> recipeList;

    public RecipeApp() {

        setTitle("Our App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        recipeListModel = new DefaultListModel<>();
        recipeList = new JList<>(recipeListModel);
        JScrollPane listScrollPane = new JScrollPane(recipeList);
        JButton loadButton = new JButton("Φόρτωση Συνταγών");
        JButton viewButton = new JButton("Προβολή Συνταγής");
        JButton shoppingListButton = new JButton("Λίστα Αγορών");
        JButton executeButton = new JButton("Εκτέλεση Συνταγής");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(shoppingListButton);
        buttonPanel.add(executeButton);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        //listeners
        loadButton.addActionListener(e -> loadRecipesFromResources());
        viewButton.addActionListener(e -> viewRecipe());
        shoppingListButton.addActionListener(e -> generateShoppingList());
        executeButton.addActionListener(e -> executeRecipe());
    }

    private void loadRecipesFromResources() {
        recipeListModel.clear();
        List<String> recipes = getRecipeFiles();
        if (recipes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Δεν βρέθηκαν συνταγές!");
        } else {
            recipes.forEach(recipeListModel::addElement);
        }
    }

    private List<String> getRecipeFiles() {
        List<String> fileNames = new ArrayList<>();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("recipes/list.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            String line;
            while ((line = reader.readLine()) != null) {
                fileNames.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Σφάλμα κατά τη φόρτωση της λίστας συνταγών.");
        }
        return fileNames;
    }

    private void viewRecipe() {
        String selectedRecipe = recipeList.getSelectedValue();
        if (selectedRecipe != null) {
            String content = readRecipeContent(selectedRecipe);
            JTextArea textArea = new JTextArea(content);
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 400));

            JOptionPane.showMessageDialog(this, scrollPane, "Προβολή Συνταγής", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Επιλέξτε μια συνταγή!");
        }
    }

    private String readRecipeContent(String fileName) {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("recipes/" + fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Σφάλμα κατά τη φόρτωση της συνταγής.";
        }
    }

    private void generateShoppingList() {
        List<String> selectedRecipes = recipeList.getSelectedValuesList();
        if (!selectedRecipes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Λίστα Αγορών για: " + selectedRecipes);
            // λίστα αγορών
        } else {
            JOptionPane.showMessageDialog(this, "Επιλέξτε τουλάχιστον μία συνταγή!");
        }
    }

    private void executeRecipe() {
        String selectedRecipe = recipeList.getSelectedValue();
        if (selectedRecipe != null) {
            JOptionPane.showMessageDialog(this, "Εκτέλεση συνταγής: " + selectedRecipe);
            //αντίστροφη μέτρηση
        } else {
            JOptionPane.showMessageDialog(this, "Επιλέξτε μια συνταγή!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecipeApp app = new RecipeApp();
            app.setVisible(true);
        });
    }
}
