package Model; 

import java.util.ArrayList;

public class FamilleProduits extends Lot {

    // subList(indexOf, lastIndexOf)
    private ArrayList<Article> oneFamily;
    
    /**
     * Constructor
     * 
     * @param a : ArrayList containing one product family
     */
    public FamilleProduits(ArrayList<Article> a)
    {   
        oneFamily = new ArrayList(a);
    }
    
    /**
     * 
     * @return float : the total amount of product sold of one product family
     */
    @Override
    public float getMontant() 
    {
        float amount = 0;
        
        for(int i = 0; i < oneFamily.size(); i++)
        {
            amount += oneFamily.get(i).getMontant();
        }
        
        return amount;
    }
    /**
     * 
     * @return qte : int : quantity remaining (ordered - sold)
     */
    public int getRemainQuantity() 
    {
        int qte = 0;
        
        for(int i = 0; i < oneFamily.size(); i++)
        {
            qte += oneFamily.get(i).getRemainQuantity();
        }
        
        return qte;
    }
    
    /**
     * @return String : containing all the data of the article
     */
    @Override
    public String toString()
    {
        String str = "\n * " + oneFamily.get(0).getProductFamily() + " | Montant Total TTC : " + this.getMontant() + "€" 
                + "  Quantité restante : " + this.getRemainQuantity() +"\n\n";
        
        String categorie = "\tCode         Designation           Famille produits    Qte Cmdee    Qte Vendue    Qte Restante    Prix TTC    Montant Vendu TTC\n";
        
        str += categorie;
        
        for(Article a : oneFamily)
        {
            str += ("\t" + a.toString() + "\n");
        }
        
        return str;
    }
} 