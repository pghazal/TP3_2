package Application;

import Controller.GestionDroguerie;
import View.BadRequestException;
import java.util.InputMismatchException;

public class Main {

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        GestionDroguerie gd = new GestionDroguerie();

        boolean exit = false;
        int choice;

        while (!exit) {
            gd.displayMenu();
            try {
                choice = gd.getRequest();
                
                if (choice == 0) {
                    exit = true;
                } 
                else {
                    gd.processRequest(choice);
                }
            }
            catch (InputMismatchException ex) {
                System.out.println("\n\tL'entrée utilisateur n'est pas une entrée valide.\n");
            } 
            catch (BadRequestException ex) {
                ex.message();
            }
        }
    }
}
