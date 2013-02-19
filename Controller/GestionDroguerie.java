package Controller;

import Model.Article;
import Model.FamilleProduits;
import Model.FichierDroguerie;
import View.BadRequestException;
import View.Bilan;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionDroguerie {

    /**
     * Number of product family : constant
     */
    private static final int NB_PRODUCT = 8;

    /**
     * Enumeration of product family
     */
    public static enum TYPE_PRODUIT {

        MENAGE, BEAUTE, ENTRETIEN, PHARMACIE, LAVAGE, TABLE, AUTOMOBILE, ECLAIRAGE
    };
    /**
     * All the articles load from a file : ArrayList
     */
    private ArrayList<Article> dataDroguerie;

    /**
     * Function which print the menu
     */
    public void displayMenu() {
        System.out.println("\t\tMenu");
        System.out.println("\n\t1. Charger un fichier."
                + "\n\t2. Afficher le bilan du fichier chargé."
                + "\n\n\t0. Quitter.");
    }

    /**
     * Function which asks the user a choice
     *
     * @return choice : int : the choice of the user
     */
    public int getRequest() throws BadRequestException, InputMismatchException {
        int choice;
        Scanner kb = new Scanner(System.in);

        System.out.print("\n\tVotre choix : ");
        choice = kb.nextInt();

        if (choice < 0 || choice > 2) {
            throw new BadRequestException();
        }

        return choice;
    }

    /**
     * Launch process according to choice
     *
     * @param choice : the choice of the user given by function getRequest()
     */
    public void processRequest(int choice) {
        FichierDroguerie fd;
        String fName;

        Bilan vue = new Bilan();

        switch (choice) {
            case 1: // Charger un fichier
                fd = new FichierDroguerie();
                fd.getRequestFile();
                try {
                    dataDroguerie = fd.load();
                } catch (NullPointerException ex) {
                    System.out.println("\tLe fichier " + fd.getFileName() + " n'existe pas.\n");
                } catch (IllegalStateException ex) {
                    System.out.println(ex.toString() + "\n");
                }
                break;
            case 2:

                // L'affichage du bilan se fera par famille de produits.
                // D'où la répartition des articles suivant leur famille
                // dans un tableau de FamilleProduits ayant pour taille le nombre de 
                // famille de produits (ici NB_PRODUCT = 8)
                try {
                    FamilleProduits[] fp = cutInProductFamily(dataDroguerie);
                    System.out.println("\n\t\t\tBILAN");

                    for (FamilleProduits fp2 : fp) {
                        vue.displayBilan(fp2.toString());
                    }
                } catch (NullPointerException ex) {
                    System.out.println("\n\tAucun fichier n'a été chargé.\n");
                }
                break;
            default:
                break;
        }
    }

    /**
     *
     * @param ar : ArrayList<Article> : ArrayList to cut in FamilleProduits
     * @return fp : FamilleProduits [] : contains a sublist of ar sorted by
     * product family
     */
    public FamilleProduits[] cutInProductFamily(ArrayList<Article> ar) {
        // Un tableau de FamilleProduits ayant pour taille le nombre de 
        // famille de produits (ici NB_PRODUCT = 8)
        FamilleProduits[] fp = new FamilleProduits[NB_PRODUCT];

        // ArrayList temporaire qui récupère une sous-liste de l'ArrayList en paramètre
        ArrayList<Article> aL = new ArrayList();

        // Permettre de cerner la sous-liste
        int firstIndex = 0;
        int lastIndex = 0;

        // Pour chaque famille de produit
        for (TYPE_PRODUIT tp : TYPE_PRODUIT.values()) {
            // On cerne avec 2 boucles for le premier et dernier article
            // faisant partie de la famille de produits tp.name() qui renvoie
            // le nom exact
            for (int i = 0; i < ar.size() - 1; i++) {
                if (ar.get(i).getProductFamily().equals(tp.name())) {
                    firstIndex = i;
                    break;
                }
            }

            for (int i = ar.size() - 1; i > 0; i--) {
                if (ar.get(i).getProductFamily().equals(tp.name())) {
                    lastIndex = i;
                    break;
                }
            }

            // On récupère la sous-liste 
            aL.addAll(ar.subList(firstIndex, lastIndex + 1));
            fp[tp.ordinal()] = new FamilleProduits(aL);
            // On nettoie la subList temporaire
            aL.clear();
        }

        return fp;
    }
}