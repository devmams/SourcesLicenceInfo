public class MainProduit{
  public static void main(String[] args) {
    double sommePrixTTC = 0;
    Produit produit[] = new Produit[4];
    produit[0] = new Produit("Beurre",1.35,5.5);
    produit[1] = new Produit("Lait",1.03,5.5);
    produit[2] = new Produit("Pull",23.62,20.0);
    produit[3] = new Produit("Echarpe",12.45,20.0);
    for(int i=0 ;i<4 ;i++){
      produit[i].calculPrixTTC();
      produit[i].afficherProduit();
      sommePrixTTC += produit[i].getPrixTTC();
    }
    System.out.println("sommePrixTTC : " + sommePrixTTC);

  }
}
