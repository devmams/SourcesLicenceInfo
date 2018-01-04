/**
 * Classe permettant de créer une cellule.
 * 
 * 
 * @author Glenn PLOUHINEC / Mamadou DIALLO
 */
public class Cellule {
	
	//l'abscisse de la cellule
	private double x;
	
	//l'abscisse de la cellule
	private double y;
	
	//la couleur de la cellule
	private char col;
	
	/**
	 * Construit une celule avec les coordonnées et la couleur.
	 */
	public Cellule(double x,double y , char col){
		this.x = x;
		this.y = y;
		this.col = col;
	}
	
	/**
	 * Accesseur.
	 * @return l'abscisse de la cellule.
	 */
	public double getX(){
		return this.x;
	}

	/**
	 * Accesseur.
	 * @return l'ordonnée de la cellule.
	 */
	public double getY(){
		return this.y;
	}
	

	/**
	 * Accesseur.
	 * @return la couleur de la cellule.
	 */
	public char getC(){
		return this.col;
	}
}