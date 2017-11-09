import java.util.ArrayList;


public class Polygone {
	protected ArrayList<Point> points;
	
	public Polygone(){
		points = new ArrayList<Point>();
	}
	
	public Polygone(ArrayList<Point> ensPoints){
		points = ensPoints;
	}
	
	public Polygone(Point A,Point B,Point C){
		points = new ArrayList<Point>();
		points.add(A);points.add(B);points.add(C);
	}
	
	public Polygone(Point A,Point B,Point C,Point D){
		points = new ArrayList<Point>();
		points.add(A);points.add(B);points.add(C);points.add(D);
	}
	
	public void addPoint(Point p){
		points.add(p);
	}	
	
}
