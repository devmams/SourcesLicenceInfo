/**
 * Classe permettant de créer une cellule.
 * 
 * ...
 * 
 * @author Glenn PLOUHINEC / Mamadou DIALLO
 */
public class Cellule {
	private double x;
	private double y;
	private char col;
	
	public Cellule(double x,double y , char col){
		this.x = x;
		this.y = y;
		this.col = col;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public char getC(){
		return this.col;
	}
}