
public class Point {
	public double x;
	public double y;
	public boolean existe;
	
	public Point(double x,double y){
		this.x = x;
		this.y = y;
		this.existe = true;
	}
	
	public Point(){
		this.existe = false;
	}
	
	public boolean egale(Point p){
		boolean res = false;
		if(x == p.x && y == p.y){
			res = true;
		}
		return res;
	}
	
	public void affPoint(){
		if(existe)
			System.out.println("("+x+" , "+y+")");
		else
			System.out.println("(?? , ??)");
	}
}