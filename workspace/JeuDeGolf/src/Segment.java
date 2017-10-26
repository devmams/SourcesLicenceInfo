
public class Segment extends Droite {

	public Segment(Point A,Point B){
		super(A,B);
	}
	
	
	
	public boolean intersecte(Point A,Point B,Point C,Point D){
		Droite d1 = new Droite(A, B);
		Droite d2 = new Droite(C, D);
		boolean res = false;
		if(d1.super.intersecte(d2)){
			Point p = d1.intersectionDroite(d2);
			if(appartient(p, A, B) && appartient(p, C, D))
				res = true;
		}
		return res;
	}
}
