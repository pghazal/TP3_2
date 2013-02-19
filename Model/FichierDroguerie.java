package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class FichierDroguerie which loads a file from a given path
 */
public class FichierDroguerie {

    private String fName;

    /**
     * Function which load a given file
     *
     * @return ArrayList<Article> : array list containing all the data of the
     * file
     */
    public ArrayList load() throws NullPointerException, IllegalStateException {

        ArrayList<Article> dataDroguerie = new ArrayList();
        
        InputStream is = getClass().getResourceAsStream(fName);

        Scanner sc = new Scanner(is);
        Scanner scData;
        String data;

        int c;
        String desig, pF;
        int qteO, qteS;
        float pTTC;

        // Saute la 1ere ligne du fichier texte
        sc.nextLine();
        
        System.out.println("\tChargement du fichier...\n");

        // Tant qu'il y a une nouvelle ligne dans le fichier
        while (sc.hasNextLine()) {
            // On récupère une ligne
            data = sc.nextLine();

            // On récupère les infos séparemment en utilisant un Delimiter
            scData = new Scanner(data).useDelimiter("\\s*;\\s*");

            c = scData.nextInt();
            desig = scData.next();
            pF = scData.next();
            qteO = scData.nextInt();
            qteS = scData.nextInt();
            pTTC = scData.nextFloat();

            // On ajoute l'article à notre Array List
            dataDroguerie.add(new Article(c, desig, pF, qteO, qteS, pTTC));
        }
        
        System.out.println("\tFichier chargé\n");

        // Fermeture du fichier
        sc.close();

        return dataDroguerie;
    }
    
    /**
     * Ask the user the name of the file to load
     */
    public void getRequestFile() {
        String ftmp = "/data/";
        String f;
        Scanner kb = new Scanner(System.in);
        System.out.println("\n\tPar défaut, le fichier chargé est : droguerie.csv\n"
                + "\tSi vous souhaitez charger un autre fichier, déplacez le dans le dossier data de l'application\n"
                + "\tet entrez le nom du fichier.\n"
                + "\tLe fichier doit être exactement structuré comme droguerie.csv.\n"
                + "\tSinon appuyez sur Entrée pour charger le fichier par défaut.");
        System.out.print("\n\tEntrez le nom du fichier à charger ou appuyez sur Entrée : ");
        f = kb.nextLine().replaceAll(" ", "");
        
        System.out.println();

        if (f.isEmpty()) {
            fName = ftmp.concat("droguerie.csv");
        } else {
            fName = ftmp.concat(f);
        }
    }
    
    /**
     * 
     * @return fName : file name to load
     */
    public String getFileName()
    {
        return fName;
    }
}
