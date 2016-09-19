public class MainScore{
  public static void main(String[] args) {
    Score match = new Score();
    //Score vi = new Score();

    match.setScoreLocaux(2);
    match.setScoreVisiteurs(3);
    match.setScoreVisiteurs(1);
    match.setScoreLocaux(2);

    match.scoreFinal();
  }
}
