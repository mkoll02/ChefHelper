package swing;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.BiConsumer;

public class Common {

    public static final String PATH = "src/main/resources";
    public static final Color BACKGROUND = Color.BLACK;
    public static final Color TEXT = Color.PINK;
    public static final Font TITLE = new Font("Arial", Font.BOLD, 18);
    public static final Font TEAM = new Font("Arial", Font.BOLD, 14);


    public static void setup(JFrame frame, String title) {

        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(BACKGROUND);

    }

    public static JLabel createLabel(String text, Font font, Color color) {

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(font);
        label.setForeground(color);
        return label;

    }


    public static JButton createButton(String text, java.awt.event.ActionListener listener) {

        JButton button = new JButton(text);
        button.addActionListener(listener);
        button.setFocusPainted(false);
        return button;

    }


    public static JPanel createPanel(List<String> selectedRecipes, BiConsumer<String, Boolean> recipeSelection, Runnable updateButtons) {

        JPanel recipePanel = new JPanel();
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
        recipePanel.setBackground(BACKGROUND);

        String[] recipes = Loader.getRecipes();

        for (String recipe : recipes) {
            String displayName = Loader.replace(recipe);
            JCheckBox recipeCheckBox = new JCheckBox(displayName);
            recipeCheckBox.setForeground(Color.WHITE);
            recipeCheckBox.setBackground(BACKGROUND);
            recipeCheckBox.setFocusPainted(false);
            recipeCheckBox.addActionListener(e -> {
                recipeSelection.accept(recipe, recipeCheckBox.isSelected());
                updateButtons.run();
            });
            recipePanel.add(recipeCheckBox);
        }

        return recipePanel;

    }


    public static JPanel createButtonPanel(JButton[] buttons, List<String> selectedRecipes) {

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(BACKGROUND);
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }
        return buttonPanel;

    }


    public static void updateButton(Container container, List<String> selectedRecipes) {

        int count = selectedRecipes.size();
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JButton button) {
                switch (button.getText()) {
                    case "ΕΚΤΕΛΕΣΗ" -> {
                        button.setEnabled(count == 1);
                        button.setForeground(count == 1 ? Color.BLACK : Color.GRAY);
                    }
                    case "ΛΙΣΤΑ ΑΓΟΡΩΝ" -> {
                        button.setEnabled(count > 1);
                        button.setForeground(count > 1 ? Color.BLACK : Color.GRAY);
                    }
                    default -> button.setEnabled(true);
                }
            }
        }

    }

    public static void message(Component parent, String message, String title) {

        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);

    }



}