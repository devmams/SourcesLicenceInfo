public class Produit{
  private String nomProduit;
  private double prixHT;
  private double tVA;
  private double prixTTC;

  public Produit(String nomProduit , double prixHT , double tVA){
    this.nomProduit = nomProduit;
    this.prixHT = prixHT;
    this.tVA = tVA;
  }

  public void calculPrixTTC(){
    this.prixTTC = this.prixHT + (this.tVA*this.prixHT/100);
  }

  public double getPrixTTC(){
    return this.prixTTC;
  }

  public void afficherProduit(){
    System.out.println(
    "Nom : "+this.nomProduit +
    "\nPrixHT : "+this.prixHT +
    "\nTVA : "+this.tVA +
    "\nprixTTC : "+this.prixTTC +
    "\n------------------------------"
    );
  }

}
