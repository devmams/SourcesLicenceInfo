public class MainPolynome{
  public static void main(String[] args) {
    Polynome pol = new Polynome(1,0,-1);
    System.out.println(pol.getCalculPolyPoint(2));
    System.out.println(pol.getCalculDeriveePol(2));
    pol.racinePol();

  }
}
