package org.example;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Γράψε το όνομα σου (in english please).");

        Scanner in = new Scanner(System.in);
        String name = in.nextLine();

        if ((name.equals("Maria")) || (name.equals("Ioanna"))) {
            System.out.println("Koritsara mou<3");
        } else {
            System.out.println("Φύγε μωρή καρακαιδόνα");
        }
        System.out.println("Ήταν μία σκληρή προσπάθεια 3 όμορφων κοριτσιών που λένε την γνώμη τους!");
    }
}