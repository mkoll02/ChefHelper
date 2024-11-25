package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recipe  {

    List<String> ingredients = new ArrayList<> ();
    List<String> utensils = new ArrayList<> ();
    int totalTime = 0;
    List<String> steps = new ArrayList<> ();

    public String name;
    public String recipe;

    public Recipe(String name, String recipe) {
        // Αποθήκευση του ονόματος της συνταγής
        this.name = name;
        this.recipe = recipe;
        //parseContent(content);
    }


//    public void printSteps() {
//        System.out.println("Βήματα:");
//        int i=0;
//
//        while ( i <= steps.size()) {
//
//            System.out.printf("%d. %s " , i+1, Arrays.toString(steps.get(i).split("\\n")));
//            i++;
//        }
//    }

    //method για πόσα άτομα είναι η συνταγή & λειτουργίες
    public void numberOfPeople(int people) {
    }

}
