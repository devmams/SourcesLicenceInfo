
public class Point {
	private double x;
	private double y;
	
	public Point(double x,double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public boolean egale(Point p){
		boolean res = false;
		if(x == p.getX() && y == p.getY()){
			res = true;
		}
		return res;
	}
	
	public void affPoint(){
		System.out.println(x +" , "+y);
	}
}
