public class Compte{
  private String proprietaire;
  private int numero;
  private double solde;
  private static int nbOperations = 0;
  private static int prochainNumero = 1;

  public Compte(String proprietaire,double solde){
    this.proprietaire = proprietaire;
    this.numero = prochainNumero;
    this.solde = solde;
    prochainNumero++;
  }

  public void infoCompte(){
    System.out.println(
    "Nom du Compte : " + this.proprietaire
    +"\nNumero : " + this.numero
    +"\nSolde : " + this.solde
    );
  }

  public String getProprietaire(){
    return this.proprietaire;
  }

  public int getNumero(){
    return this.numero;
  }

  public double getSolde(){
    return this.solde;
  }

  public void crediter(double val){
    this.solde += val;
    nbOperations++;
  }

  public void debiter(double val){
    this.solde -= val;
    nbOperations++;
  }

  public int getNbOperations(){
    return nbOperations;
  }
}
