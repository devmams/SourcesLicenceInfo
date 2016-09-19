public class Polynome{
  private float a;
  private float b;
  private float c;

  public Polynome(float a ,float b ,float c){
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public double getCalculPolyPoint(double x){
    return (this.a*x*x + this.b*x + this.c);
  }

  public double getCalculDeriveePol(double x){
    return (2*this.a*x + this.b);
  }

  public void racinePol(){
    System.out.print("Les Racine sont : ");
    for(int i=-5 ;i<5 ;i++){
      if((this.a*i*i + this.b*i + this.c) == 0){
        System.out.print(i + " , ");
      }
    }
    System.out.println();
  }
}
