import java.util.ArrayList;


public class Polygone {
	private ArrayList<Point> points;
	
	public Polygone(){
		points = new ArrayList<>();
	} 
	
	public Polygone(Point A,Point B,Point C){
		points = new ArrayList<>();
		points.add(A);points.add(B);points.add(C);
	}
	
	public Polygone(Point A,Point B,Point C,Point D){
		points = new ArrayList<>();
		points.add(A);points.add(B);points.add(C);points.add(D);
	}
	
	public void posPointTriangle(Point p){
		Point A = points.get(0);
		Point B = points.get(1);
		Point C = points.get(2);
		Droite AB,BC ,CA;
		AB = new Droite(A, B);
		BC = new Droite(B, C);
		CA = new Droite(C, A);
		if(AB.appartientSegment(p, A, B)){
			if(p.egale(A))
				System.out.println("le point est A");
			else if(p.egale(B))
				System.out.println("le point est B");
			else
				System.out.println("le point est sur le [AB]");
		}
		else if(BC.appartientSegment(p, B, C)){
			if(p.egale(B))
				System.out.println("le point est B");
			else if(p.egale(C))
				System.out.println("le point est C");
			else
				System.out.println("le point est sur le [BC]");
		}
		else if(CA.appartientSegment(p, C, A)){
			if(p.egale(C))
				System.out.println("le point est C");
			else if(p.egale(A))
				System.out.println("le point est A");
			else
				System.out.println("le point est sur le [CA]");
		}
		else{
			if((AB.appartient(p)==AB.appartient(C)) 
			&& (BC.appartient(p)==BC.appartient(A))
			&& (CA.appartient(p)==CA.appartient(B))){
				System.out.println("le point est à l'interieur du triangle");
			}
			else{
				System.out.println("le point est à l'extérieur du triangle");
			}
		}
	}
	
	public void addPoint(Point p){
		points.add(p);
	}
	
	public ArrayList<Point> getPolygone(){
		return points; 
	}
	
	
	
}
