package org.example;

public abstract class Display extends RecipeProcessor {

    protected int people = 1; // Προεπιλεγμένος αριθμός ατόμων

    // Μέθοδος για να ορίσουμε τον αριθμό ατόμων
    public void setNumberOfPeople(int people) {
        this.people = people;
    }

    //override this in inheritors to print there
    public void display(String recipe) {
    }

    public static Display[] printer() {
        Display[] display = new Display[4];
        display[0] = new Ingredients();
        display[1] = new Utensils();
        display[2] = new Time();
        display[3] = new Steps();
        return display;
    }
}

