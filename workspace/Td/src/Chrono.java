public class Chrono{

  private long depart;
  private long dureeIntermediaire;
  private long dureeTotale;
  private boolean actif;

  public Chrono(){
    this.actif = false;
    this.dureeIntermediaire = 0;
    this.dureeTotale = 0;
  }

  public void start(){
    if(!actif){
      this.actif = true;
      this.depart = System.currentTimeMillis();
    }
  }

  public void stop(){
    if(actif){
      this.actif = false;
      this.dureeIntermediaire = System.currentTimeMillis() - this.depart;
      this.dureeTotale += this.dureeIntermediaire;
    }
  }

  public void affichage(){
    System.out.println("TIME : " + this.dureeIntermediaire );
  }

  public void affichageTotale(){
    System.out.println("--------TIME : " + this.dureeTotale );
  }

  public void RAZ(){
    this.actif = false;
    this.dureeIntermediaire = 0;
    this.dureeTotale = 0;
  }

}
