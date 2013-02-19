package View; 

public class BadRequestException extends Exception { 
    /**
     * Print a message if bad request from user
     */
    public void message()
    {
        System.out.println("\n\tLe choix sélectionné n'est pas valide.\n");
    }
} 