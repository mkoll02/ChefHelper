package swing;

import org.example.Ingredients;
import org.example.RecipeReader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList extends Common{

    private List<String> recipes;
    public static final Color BACKGROUND = Color.BLACK;

    public List<String> getRecipeNames() {
        return recipes;
    }

    public void setRecipeNames(List<String> recipeNames) {
        this.recipes = recipeNames;
    }

    public ShoppingList() {
    }

    //shopping list and optionally display in a new window
    public void handleShoppingList(boolean showInWindow) throws IOException {
        Ingredients i = new Ingredients();
        List<String> l = new ArrayList<>();
        for (String recipe : recipes) {
            l.addAll(readRecipe(recipe));
        }
        String listOfIngredients = i.getListOfIngredients(l);

        if (showInWindow) {
            createIngredientWindow(listOfIngredients); // here in a new window
        } else {
            Common.message(null,
                    "Λίστα Υλικών:\n" + listOfIngredients,
                    "Λίστα Αγορών");
        }
    }

    public List<String> readRecipe(String recipe) throws IOException {
        Ingredients i = new Ingredients();
        RecipeReader reader = new RecipeReader(recipe, recipe);
        String recipeContent = reader.readRecipe();
        return new ArrayList<>(i.prepareInitial(recipeContent));
    }

    //window that displays
    public void createIngredientWindow(String listOfIngredients) {
        JFrame frame = new JFrame();
        frame.setTitle("ChefHelper");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Common.BACKGROUND);

        JLabel titleLabel = new JLabel("Λίστα αγορών");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);


        JPanel panel = new JPanel(new BorderLayout());
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.setBackground(Common.BACKGROUND);

        JTextArea textArea = new JTextArea("Υλικά για τις συνταγές: " + String.join(", ", recipes)+" \n\n" + listOfIngredients);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        textArea.setBackground(Common.BACKGROUND);
        textArea.setForeground(Color.WHITE);

        frame.add(panel);
        frame.setVisible(true);
    }

}