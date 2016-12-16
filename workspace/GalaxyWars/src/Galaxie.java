import java.awt.Color;


public class Galaxie {

	// grille deux dimension representant notre galaxie.
	private static Entite[][] grilleEntites;
	// largeur de la galaxie
	private int largeur;
	// hauteur de la galaxie
	private int hauteur;
	//nombre d'entites présentes dans la galaxie.
	private static int nbEntites = 0;
	
	
	/**
	 * Construit une galaxie.
	 */
	public Galaxie(){
		largeur = Constantes.Largeur;
		hauteur = Constantes.Hauteur;
		grilleEntites = new Entite[largeur][hauteur];
	}
	
	/**
	 * Accesseur.
	 * @return la largeur de la galaxie.
	 */
	public int getGalaxieLargeur(){
		return largeur;
	}
	
	/**
	 * Accesseur.
	 * @return la hauteur de la galaxie.
	 */
	public int getGalaxieHauteur(){
		return hauteur;
	}
	
	/**
	 * ajoute une entité dans notre galaxie.
	 * @param entité à ajouter.
	 */
	public void ajoutEntite(Entite ent){
		grilleEntites[ent.getAbscisse()][ent.getOrdonnee()] = ent;
		nbEntites++;
	}
	
	/**
	 * Accesseur.
	 * @return le nombre d'entites presentes dans la galaxie.
	 */
	public int getNbEntites(){
		return nbEntites;
	}
	
	/**
	 * supprime une entité dans notre galaxie.
	 * @param entité à supprimer.
	 */
	public void supprEntite(Entite ent){
		grilleEntites[ent.getAbscisse()][ent.getOrdonnee()] = null;
		nbEntites--;
	}
	
	/**
	 * modifie la position d'une entité dans la galaxie.
	 * @param ancienne et nouvelle position.
	 */
	public void modifPositionEntite(int xAnc,int yAnc,int xNouv,int yNouv){
		grilleEntites[xNouv][yNouv] = grilleEntites[xAnc][yAnc];
		grilleEntites[xAnc][yAnc] = null;
	}
	
	/**
	 * @return une entité présente à une position donnée 
	 * dans la galaxie.
	 */
	public Entite getEntite(int id){
		int y = id/Constantes.Largeur;
		int x = id - (y*Constantes.Largeur);
		return grilleEntites[x][y];
	}

	/**
	 * @return vrai Ssi la position est occupée dans la galaxie.
	 */
	public boolean occupee(int x,int y){
		boolean res = false;
		if(grilleEntites[x][y] != null ){
			res = true;
		}
		return res;
	}
}
