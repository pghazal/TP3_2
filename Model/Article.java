package Model; 

/**
 * Class Article inherits of class Montant
 * Article represents a product from the "Droguerie" Store
 */
public class Article extends Lot { 
    private int code;
    private String designation;
    private String productFamily;
    private int qteOrdered;
    private int qteSold;
    private float pricePerUnitTTC;
    
    /**
     * Constructor Article : initialize the object
     * @param c : code product
     * @param desig : designation
     * @param pF : product family
     * @param qteO : quantity ordered
     * @param qteS : quantity sold
     * @param pTTC : price per unit TTC
     */
    public Article(int c, String desig, String pF, int qteO, int qteS, float pTTC)
    {
        code = c;
        designation = desig;
        productFamily = pF;
        qteOrdered = qteO;
        qteSold = qteS;
        pricePerUnitTTC = pTTC;
    }
    
    /**
     * @return float : the amount of the article
     */
    @Override
    public float getMontant() {
        return ((float) qteSold) * pricePerUnitTTC;
    }
    
    public int getRemainQuantity() {
        return (qteOrdered - qteSold);
    }

    /**
     * @return String : containing all the data of the article
     */
    @Override
    public String toString()
    {
        String str;
        String pattern = "%-7s %-29s %-21s %-11s %-15s %-11s %-16s %-1s";
        
        str = String.format(pattern, Integer.toString(code), designation, productFamily, 
                Integer.toString(qteOrdered), Integer.toString(qteSold), 
                Integer.toString(getRemainQuantity()), Float.toString(pricePerUnitTTC), 
                Float.toString(getMontant()));
        
        return str;
    }
     
    public String getProductFamily()
    {
        return productFamily;
    }
} 