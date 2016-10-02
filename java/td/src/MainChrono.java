public class MainChrono{
  public static void main(String[] args) {
    Chrono t = new Chrono();
    t.start();
    double s = 0;
    for (int i=0 ;i<1000000 ;i++ ){
      s += Math.cos(i) - i*i +1;
      if(i == 10000){
        t.stop();
        t.affichage();
        t.start();
      }
    }
    t.stop();
    t.affichage();
    t.affichageTotale();
    t.RAZ();
    t.affichage();
  }
}
