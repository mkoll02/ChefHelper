package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ingredients extends Display {
    List<String> ingredients = new ArrayList<>();

    @Override
    public void display(String recipe) {
        System.out.println("Υλικά:");
        //super.display();
    }
}

