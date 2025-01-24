package swing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class App extends JFrame {

    private List<String> recipes = new ArrayList<>();

    public App() {
        setTitle("ChefHelperApp ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);

        JLabel welcomeMessage = new JLabel("Καλώς ήρθατε στην εφαρμογή μας!", SwingConstants.CENTER);
        welcomeMessage.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeMessage.setForeground(Color.PINK);
        JLabel teamMessage = new JLabel("Μαρία Κόλλια ~ Μαρία Τσαρούχη ~ Ιωάννα Ράντη", SwingConstants.CENTER);
        teamMessage.setFont(new Font("Arial", Font.BOLD, 14));
        teamMessage.setForeground(Color.PINK);

        JPanel messagePanel = new JPanel(new GridLayout(2, 1));
        messagePanel.setBackground(Color.BLACK);
        messagePanel.add(welcomeMessage);
        messagePanel.add(teamMessage);

        add(messagePanel, BorderLayout.NORTH);

        JPanel recipePanel = new JPanel(); //για λίστα συνταγών και διατάξεις
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
        recipePanel.setBackground(Color.BLACK);

        String[] recipes = Loader.getRecipes();

        for (String recipe : recipes) {
            String displayName = Loader.replace(recipe);
            JCheckBox recipeCheckBox = new JCheckBox(displayName);
            recipeCheckBox.setForeground(Color.WHITE);
            recipeCheckBox.setBackground(Color.BLACK);
            recipeCheckBox.setFocusPainted(false);
            recipeCheckBox.addActionListener(e -> {
                recipeSelection(recipe, recipeCheckBox.isSelected());
                updateButtons();
            });
            recipePanel.add(recipeCheckBox);
        }

        add(new JScrollPane(recipePanel), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.BLACK);

        JButton executeButton = new JButton("ΕΚΤΕΛΕΣΗ");
        executeButton.addActionListener(e -> execute());
        executeButton.setEnabled(false);

        JButton shoppingListButton = new JButton("ΛΙΣΤΑ ΑΓΟΡΩΝ");
        shoppingListButton.addActionListener(e -> shoppingList());
        shoppingListButton.setEnabled(false);

        JButton exitButton = new JButton("ΕΞΟΔΟΣ");
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(executeButton);
        buttonPanel.add(shoppingListButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void recipeSelection(String recipe, boolean selected) {

        if (selected) {
            recipes.add(recipe);
        } else {
            recipes.remove(recipe);
        }

    }

    private void updateButtons() {

        int selectedCount = recipes.size();

        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                for (Component button : panel.getComponents()) {
                    if (button instanceof JButton) {
                        JButton btn = (JButton) button;
                        if ("ΕΚΤΕΛΕΣΗ".equals(btn.getText())) {
                            btn.setEnabled(selectedCount == 1);
                        } else if ("ΛΙΣΤΑ ΑΓΟΡΩΝ".equals(btn.getText())) {
                            btn.setEnabled(selectedCount > 1);
                        }
                    }
                }
            }
        }

    }


    private void execute() {

        if (recipes.size() == 1) {
            JOptionPane.showMessageDialog(this, "Εκτέλεση: " + recipes.get(0));
        }

    }

    private void shoppingList() {

        if (recipes.size() > 1) {
            JOptionPane.showMessageDialog(this, "Δημιουργείται λίστα αγορών για: " + String.join(", ", recipes));
        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });

    }

}