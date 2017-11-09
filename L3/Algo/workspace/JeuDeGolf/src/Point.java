
public class Point {
	private double x;
	private double y;
	private boolean existe;
	
	public Point(double x,double y){
		this.x = x;
		this.y = y;
		this.existe = true;
	}
	
	public Point(){
		this.existe = false;
	}
	
	public boolean pointExiste(){
		return existe;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
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