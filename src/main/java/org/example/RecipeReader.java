package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecipeReader {

    private final String fileName;

    public RecipeReader(String name, String fileName) {
        this.fileName = fileName;
    }

    public String readRecipe() throws IOException {
        Path externalPath = Paths.get(fileName);
        if (Files.exists(externalPath)) {
            return Files.readString(externalPath);
        }

        try (InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (resourceStream == null) {
                throw new IOException("Recipe file not found: " + fileName);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }
                return content.toString().trim();
            }
        }
    }

    public void addIngredientsToShoppingList(String recipeContent, Map<String, Double> shoppingList, int people) {
        //ανάλυση υλικών
        Pattern pattern = Pattern.compile("(?m)^(\\d+(\\.\\d+)?)(\\s+)([a-zA-Z]+.*)");
        Matcher matcher = pattern.matcher(recipeContent);

        while (matcher.find()) {
            double quantity = Double.parseDouble(matcher.group(1));
            String ingredient = matcher.group(4);

            shoppingList.put(ingredient, shoppingList.getOrDefault(ingredient, 0.0) + quantity);
        }
    }
}


