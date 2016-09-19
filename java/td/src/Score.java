public class Score{

  private int[] locaux = new int[10];
  private int[] visiteurs = new int[10];
  private static int i = 0;

  public Score(){
    this.locaux[i] = 0;
    this.visiteurs[i] = 0;
    i++;
  }

  public void setScoreLocaux(int nb){
    this.locaux[i] = this.locaux[i-1] + nb;
    this.visiteurs[i] = this.visiteurs[i-1];
    i++;
  }

  public void setScoreVisiteurs(int nb){
    this.visiteurs[i] = this.visiteurs[i-1] + nb;
    this.locaux[i] = this.locaux[i-1];
    i++;
  }

  public void scoreFinal(){
    System.out.println("Locaux        " + "Visiteurs");
    for(int j = 0 ; j<i-1;j++){
      System.out.println("  " +this.locaux[j] + "       :       " + this.visiteurs[j] +"\n");
    }
  }
}
