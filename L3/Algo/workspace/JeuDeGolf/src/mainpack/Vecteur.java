package mainpack;

public class Vecteur {

	public double v1;
	public double v2;
	
	public Vecteur(Point p1,Point p2) {
		v1 = p2.x - p1.x;
		v2 = p2.y - p1.y;
	}
	
	public Vecteur(Segment s)
	{
		v1 = s.P2.x - s.P1.x;
		v2 = s.P2.y - s.P1.y;
	}
	
	public Vecteur(double u1,double u2)
	{
		v1 = u1;
		v2 = u2;
	}
	
	public double produitScalaire(Vecteur vect)
	{
		return(this.v1*vect.v1+this.v2*vect.v2);
	}
	
	public Vecteur appliquerAngle(double a)
	{
		return(new Vecteur(v1*Math.cos(a)-v2*Math.sin(a),v1*Math.sin(a)+v2*Math.cos(a)));
	}

	public double angle(Vecteur vo)
	{
		return(Math.acos(produitScalaire(vo)/(norme()*vo.norme())));
	}
	
	public Vecteur mult(double a)
	{			
		return new Vecteur(a*v1,a*v2);
	}
	
	public boolean egale(Vecteur autre) {
		if(this.v1==autre.v1 && this.v2==autre.v2) {
			return true;
		}else {
			return false;
		}
	}
	public double norme() {
		return(Math.sqrt(Math.pow(v1, 2)+Math.pow(v2, 2)));
	}
	
	public void afficher() {
		System.out.println("("+v1+","+v2+")");
	}
	
}
