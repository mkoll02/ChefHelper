package swing;

import gr.hua.dit.oop2.countdown.Countdown;
import gr.hua.dit.oop2.countdown.CountdownFactory;
import gr.hua.dit.oop2.countdown.Notifier;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Executor {

    private final JFrame parent;
    private final List<Step> steps;

    public Executor(JFrame parent, String recipeFileName) {
        this.parent = parent;
        this.steps = loadSteps(recipeFileName);
    }

    public void execute() {
        for (int i = 0; i < steps.size(); i++) {
            Step step = steps.get(i);

            // Εμφάνιση του βήματος
            int result = JOptionPane.showConfirmDialog(
                    parent,
                    step.getDescription(),
                    "Βήμα " + (i + 1),
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (result != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(parent, "Η εκτέλεση ακυρώθηκε!", "Ακύρωση", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Αν υπάρχει χρόνος στο βήμα, εκτέλεση του χρονομέτρου
            if (step.getTimeInSeconds() > 0) {
                boolean timeComplete = startCountdown(step.getTimeInSeconds());

                if (!timeComplete) {
                    JOptionPane.showMessageDialog(parent, "Η αντίστροφη μέτρηση διακόπηκε!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Επιβεβαίωση από τον χρήστη ότι ολοκληρώθηκε το βήμα
                JOptionPane.showMessageDialog(
                        parent,
                        "Ο χρόνος ολοκληρώθηκε! Πατήστε OK για να συνεχίσετε.",
                        "Χρονόμετρο",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }

        JOptionPane.showMessageDialog(parent, "Η συνταγή ολοκληρώθηκε!", "Ολοκλήρωση", JOptionPane.INFORMATION_MESSAGE);
    }

    private List<Step> loadSteps(String fileName) {
        List<Step> steps = new ArrayList<>();
        File file = new File(Common.PATH, fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Ελέγχουμε αν η γραμμή είναι κενή
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Αναζήτηση για χρονο
                String[] parts = line.split("~\\{");
                String description = parts[0].trim();

                int timeInSeconds = 0;
                if (parts.length > 1) {
                    String timePart = parts[1].split("}")[0];
                    timeInSeconds = parseTime(timePart); // Μετατροπή σε δευτερόλεπτα
                }


                if (!description.isEmpty()) {
                    steps.add(new Step(description, timeInSeconds));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(parent, "Σφάλμα κατά την ανάγνωση της συνταγής!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
        }

        return steps;
    }

    private int parseTime(String timePart) {

        String[] timeComponents = timePart.split("%");
        int value = Integer.parseInt(timeComponents[0]);
        String unit = timeComponents[1].toLowerCase();

        return switch (unit) {
            case "seconds", "δευτερόλεπτα" -> value;
            case "minutes", "λεπτά" -> value * 60; // Μετατροπή σε δευτερόλεπτα
            default -> 0; 
        };
    }

    private boolean startCountdown(int seconds) {
        Countdown countdown = CountdownFactory.countdown(seconds);

        // Δημιουργία αντίστροφης μέτρησης
        JDialog countdownDialog = new JDialog(parent, "Αντίστροφη Μέτρηση", true);
        countdownDialog.setSize(300, 150);
        countdownDialog.setLayout(new BorderLayout());
        countdownDialog.setLocationRelativeTo(parent);

        JLabel countdownLabel = new JLabel("Χρόνος που απομένει: " + seconds + " δευτερόλεπτα", SwingConstants.CENTER);
        countdownDialog.add(countdownLabel, BorderLayout.CENTER);

        countdown.addNotifier(new Notifier() {
            public void everySecond(Countdown c) {
                SwingUtilities.invokeLater(() -> countdownLabel.setText("Χρόνος που απομένει: " + c.secondsRemaining() + " δευτερόλεπτα"));
            }

            @Override
            public void finished(Countdown c) {
                SwingUtilities.invokeLater(() -> {
                    countdownDialog.dispose();
                    JOptionPane.showMessageDialog(parent, "Ο χρόνος ολοκληρώθηκε! Πατήστε OK για να συνεχίσετε.", "Χρονόμετρο", JOptionPane.INFORMATION_MESSAGE);
                });
            }
        });

        countdown.start();
        countdownDialog.setVisible(true); // Αναμονή μέχρι να κλείσει το παράθυρο
        countdown.stop();

        return true;
    }

    private static class Step {
        private final String description;
        private final int timeInSeconds;

        public Step(String description, int timeInSeconds) {
            this.description = description;
            this.timeInSeconds = timeInSeconds;
        }

        public String getDescription() {
            return description;
        }

        public int getTimeInSeconds() {
            return timeInSeconds;
        }
    }
}

