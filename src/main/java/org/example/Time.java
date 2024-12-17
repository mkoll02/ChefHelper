package org.example;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time extends Display {
    @Override
    public void display(String recipe) {
        // Εντοπισμός δεικτών του συμβόλου '~'
        List<Integer> indexes = indexes("~", recipe);

        // Υπολογισμός συνολικού χρόνου
        int totalMinutes = 0;

        for (int index : indexes) {
            // Εντοπισμός χρονικής τιμής και μονάδας
            String timeSegment = extractTimeSegment(recipe, index);

            // Μετατροπή της τιμής σε λεπτά και προσθήκη στο σύνολο
            totalMinutes += convertToMinutes(timeSegment);
        }

        // Εμφάνιση του συνολικού χρόνου
        System.out.println("Συνολική Ώρα: " + formatTotalTime(totalMinutes));
    }

    private String extractTimeSegment(String recipe, int start) {
        int end = recipe.indexOf("}", start);
        if (end == -1) end = recipe.length(); // Αν δεν υπάρχει '}', πάρε μέχρι το τέλος

        return recipe.substring(start + 2, end).trim(); // Παράλειψε το '~{' και κράτα το περιεχόμενο
    }

    private int convertToMinutes(String timeSegment) {
        Pattern pattern = Pattern.compile("(\\d+)%?(\\w+)");
        Matcher matcher = pattern.matcher(timeSegment);

        if (matcher.find()) {
            int value = Integer.parseInt(matcher.group(1));
            String unit = matcher.group(2).toLowerCase();

            // Μετατροπή σε λεπτά
            if (unit.contains("hour") || unit.contains("ώρα")) {
                return value * 60; // Μετατροπή ωρών σε λεπτά
            } else if (unit.contains("minute") || unit.contains("λεπτά")) {
                return value; // Ήδη σε λεπτά
            }
        }

        return 0; // Επιστροφή 0 αν δεν βρεθεί έγκυρη χρονική τιμή
    }

    private String formatTotalTime(int totalMinutes) {
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;

        if (hours > 0) {
            return hours + " ώρα και " + minutes + " λεπτά";
        } else {
            return minutes + " λεπτά";
        }
    }
}