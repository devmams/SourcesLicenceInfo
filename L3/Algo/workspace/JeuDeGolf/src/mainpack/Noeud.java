/**
 * Classe permettant de créer un Noeud
 * 
 * @author Nicolas VANNIER / Mamadou DIALLO
 */
package mainpack;

public class Noeud {

	private Region r;
	public Noeud[] n;

	/**
	 * Construit un noeud
	 * @param une region r
	 */
	public Noeud(Region r) {
		this.setR(r);
		n = new Noeud[4];
		for(int i=0;i<4;i++) {
			n[i] = null;
		}
	}

	/**
	 * retourne la region
	 * @return une region
	 */
	public Region getR() {
		return r;
	}

	/**
	 * Modifie une region
	 * @param une region r
	 */
	public void setR(Region r) {
		this.r = r;
	}
	
	
	
}
