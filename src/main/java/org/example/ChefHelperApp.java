package org.example;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class ChefHelperApp {
    public static void main(String[] args) {

        String fileName = args[0];

        // Ελέγχουμε αν δόθηκε αρχείο ως όρισμα
        if (args.length != 1) {
            System.out.println("Χρήση: java -jar recipes.jar <filename.cook>");
            return;
        }

        try {
            // Χρησιμοποιούμε τον ClassLoader για να βρούμε το αρχείο μέσα στο JAR
            InputStream inputStream = ChefHelperApp.class.getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                System.out.println("Το αρχείο " + fileName + " δεν βρέθηκε στο JAR.");
                return;
            }

            // Διαβάζουμε το περιεχόμενο του αρχείου
            try (Scanner scanner = new Scanner(inputStream)) {
                StringBuilder content = new StringBuilder();
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append("\n");
                }
                System.out.println("Περιεχόμενο συνταγής:");
                System.out.println(content.toString());
            }
        } catch (Exception e) {
            System.out.println("Σφάλμα κατά την επεξεργασία του αρχείου: " + e.getMessage());
        }
    }
}
