/**
 * Classe permettant de créer un Point
 * 
 * @author Nicolas VANNIER / Mamadou DIALLO
 */
package mainpack;
import java.awt.Color;
import java.awt.Graphics;

public class Point {
	public double x;
	public double y;
	public boolean existe;
	
	/**
	 * Construit un point
	 * @param les coordonées du point
	 */
	public Point(double x,double y){
		this.x = x;
		this.y = y;
		this.existe = true;
	}
	
	/**
	 * Construit un point null
	 */
	public Point(){
		this.existe = false;
	}
	
	/**
	 * Vérifie si deuc points sont égaux
	 * @param le point à comparer
	 * @return un booléen "true" si y a egalité et "false" sinon
	 */
	public boolean egale(Point p){
		boolean res = false;
		
		// Arrondi des coordonn�es de point, elles peuvent �tre issues de calculs avec approximation
		double x1r = Simulation.round(x, 4);
		double y1r = Simulation.round(y,4);
		double x2r = Simulation.round(p.x, 4);
		double y2r = Simulation.round(p.y,4);
		if(x1r == x2r && y1r == y2r){
			res = true;
		}
		return res;
	}
	
	/**
	 * Affiche les coordonées d'un point
	 */
	public void affPoint(){
		if(existe) {
			System.out.println("("+x+" , "+y+")");
		}else {
			System.out.println("(?? , ??)");
		}
	}
	
	public void GAfficher(Graphics g,Color col)
	{
		g.setColor(col);
		int upScale = Affichage.upScale;
		g.fillOval((int)(this.x*upScale), Simulation.T*upScale-(int)(this.y*upScale), 12, 12);
	}
	
	/**
	 * Calcule la distance entre deux points
	 * @param l'autre point
	 */
	public double distanceEntreDeuxPoints(Point p){
		double res;
		res = ((p.x-x)*(p.x-x))+((p.y-y)*(p.y-y));
		return Math.sqrt(res);
	}
}