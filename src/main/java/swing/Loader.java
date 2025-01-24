package swing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    public static String[] getRecipes() {

        List<String> recipeNames = new ArrayList<>();
        File directory = new File(Common.PATH);

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

        fileName = fileName.replace(".cook", "");
        return fileName;

    }

}
