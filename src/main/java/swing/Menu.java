package swing;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu extends JFrame {

    private final List<String> recipes = new ArrayList<>();

    public Menu() {

        Common.setup(this, "ChefHelperApp");

        JLabel welcomeMessage = Common.createLabel("Καλώς ήρθατε στην εφαρμογή μας!", Common.TITLE, Common.TEXT);
        JLabel teamMessage = Common.createLabel("Μαρία Κόλλια ~ Μαρία Τσαρούχη ~ Ιωάννα Ράντη", Common.TEAM, Common.TEXT);

        JPanel messagePanel = new JPanel(new GridLayout(2, 1));
        messagePanel.setBackground(Common.BACKGROUND);
        messagePanel.add(welcomeMessage);
        messagePanel.add(teamMessage);
        add(messagePanel, BorderLayout.NORTH);

        JPanel recipePanel = Common.createPanel(recipes, this::recipeSelection, this::updateButtons);
        add(new JScrollPane(recipePanel), BorderLayout.CENTER);

        JPanel buttonPanel = Common.createButtonPanel(
                new JButton[]{
                        Common.createButton("ΕΚΤΕΛΕΣΗ", e -> execute()),
                        Common.createButton("ΛΙΣΤΑ ΑΓΟΡΩΝ", e -> shoppingList()),
                        Common.createButton("ΕΞΟΔΟΣ", e -> System.exit(0))
                },
                recipes
        );

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
            String recipeFileName = recipes.get(0);
            Executor executor = new Executor(this, recipeFileName);
            executor.execute();
        } else {
            JOptionPane.showMessageDialog(this, "Επιλέξτε μία συνταγή για εκτέλεση.", "Προσοχή", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void shoppingList() {
        try {
            ShoppingList shoppingList = new ShoppingList();
            shoppingList.setRecipeNames(recipes); //set recipes
            shoppingList.handleShoppingList(true);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "An error occurred while generating the shopping list.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


}
