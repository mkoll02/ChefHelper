package org.example;
import java.io.IOException;
import java.nio.file.*;

public class RecipeReader {
    public static String recipeAsString(String recipeName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(recipeName)));
    }
}
