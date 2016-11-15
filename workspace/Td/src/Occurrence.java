public class Occurrence{

  private String texte;

  public Occurrence(String texte){
    this.texte = texte;
  }

  public void nbLettre(){
    int cpt = 0;
    for(int i=0 ; i< this.texte.length() ;i++){
      if(this.texte.charAt(i) == ' ' || this.texte.charAt(i) == '.' ){
        cpt++;
      }
    }
    System.out.println("Nombre de Lettres : " + (this.texte.length() - cpt));
  }

  public void AffichageLettre(){
    int cpt;
    for(int i=0 ; i< this.texte.length() ;i++){
      cpt = 1;
      for(int j=i+1 ; j< this.texte.length() ;j++){
        if(this.texte.charAt(i) == this.texte.charAt(j)){
          cpt++;
        }
      }
      System.out.print(this.texte.charAt(i)+" : "+cpt +" ");
    }
  }
}
