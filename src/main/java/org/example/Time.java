package org.example;

import java.util.List;

public class Time extends Display {
    @Override
    public void display(String recipe) {
        // Εντοπισμός δεικτών του συμβόλου '~'
//        List<Integer> indexes = indexes("~", recipe);
//
//        // Εξαγωγή χρόνων
//        List<String> times = isolateString(recipe, indexes, "{", "}");
//
//        int totalTime = 0; // Συνολικός χρόνος
//
//
//        for (String time : times) {
//            System.out.println("- " + time.trim());
//
//            // Υπολογισμός χρόνου: αφαιρούμε τις μονάδες (π.χ., %minutes) και προσθέτουμε τον αριθμό
//            totalTime += extractTimeInMinutes(time.trim());
//        }

        // Εμφάνιση του συνολικού χρόνου
        //System.out.println("Συνολική ώρα: " + totalTime + " λεπτά.");
    }

    // Εξάγει τον χρόνο σε λεπτά από το κείμενο (π.χ., "15%minutes").
    private int extractTimeInMinutes(String time) {
        // Αφαιρούμε μονάδες όπως "%minutes" και μετατρέπουμε τον αριθμό
        String numericPart = time.replaceAll("[^0-9]", ""); // Κρατάμε μόνο τα ψηφία
        if (!numericPart.isEmpty()) {
            return Integer.parseInt(numericPart);
        }
        return 0; // Αν δεν υπάρχει αριθμός, επιστρέφουμε 0
    }
}

