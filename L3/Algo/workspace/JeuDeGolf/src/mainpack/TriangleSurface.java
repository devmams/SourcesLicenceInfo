/**
 * Classe permettant de créer un TriangleSurface
 * 
 * @author Nicolas VANNIER / Mamadou DIALLO
 */

package mainpack;

public class TriangleSurface extends Triangle {

	private int sParente;
	
	/**
	 * Construit un triangleSurface
	 * @param les trois sommets du triangle et l'identifiant entier du parent is
	 */
	public TriangleSurface(Point A, Point B, Point C,int is) {
		super(A, B, C);
		sParente = is;
	}
	
	
	/**
	 * retourne l'identifiant du parent
	 * @return un entier
	 */
	public int getSurface() {
		return sParente;
	}
}
