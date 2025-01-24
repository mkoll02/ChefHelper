package swing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    private static final String PATH = "src/main/resources"; //path

    public static String[] getRecipes() {
        List<String> recipeNames = new ArrayList<>();
        File directory = new File(PATH);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".cook"));
            if (files != null) {
                for (File file : files) {
                    recipeNames.add(file.getName());
                }
            }
        }

        return recipeNames.toArray(new String[0]);
    }

    public static String replace(String fileName) {

        fileName = fileName.replace(".cook", "").replace("_", " ");
        return fileName;

    }
}
