/**
 * Classe permettant de créer une pièce qui est un ensemble 
 * de 4 Cellules.
 * 
 * 
 * @author Glenn PLOUHINEC / Mamadou DIALLO
 */
public class Piece {
	
	//première cellule de la pièce
	private Cellule un ;
	
	//deuxième cellule de la pièce
	private Cellule deux ;
	
	//troisième cellule de la pièce
	private Cellule trois ;
	
	//quatrième cellule de la pièce
	private Cellule quatre ;
	
	/**
	 * Construit une pièce avec les 4 différentes cellules.
	 * @param 4 Cellules.
	 */
	public Piece(Cellule c1,Cellule c2 ,Cellule c3 ,Cellule c4){
		this.un = c1;
		this.deux = c2;
		this.trois = c3;
		this.quatre = c4;
	}
	

	/**
	 * Accesseur.
	 * @return la première cellule de la pièce.
	 */
	public Cellule getCelluleUn(){
		return this.un;
	}
	
	/**
	 * Accesseur.
	 * @return la deuxième cellule de la pièce.
	 */
	public Cellule getCelluleDeux(){
		return this.deux;
	}
	
	/**
	 * Accesseur.
	 * @return la troisième cellule de la pièce.
	 */
	public Cellule getCelluleTrois(){
		return this.trois;
	}
	
	/**
	 * Accesseur.
	 * @return la quatrième cellule de la pièce.
	 */
	public Cellule getCelluleQuatre(){
		return this.quatre;
	}
	
	/**
	 * retourne une pièce qui correspond au déplacement vers le bas.
	 */
	public Piece versLeBas(){
		return new Piece(new Cellule(un.getX(),un.getY()+1,un.getC()),new Cellule(deux.getX(),deux.getY()+1,un.getC())
		,new Cellule(trois.getX(),trois.getY()+1,un.getC()),new Cellule(quatre.getX(),quatre.getY()+1,un.getC()));
	}

	/**
	 * retourne une pièce qui correspond au déplacement vers la gauche.
	 */
	public Piece versLaGauche(){
		return new Piece(new Cellule(un.getX()-1,un.getY(),un.getC()),new Cellule(deux.getX()-1,deux.getY(),un.getC())
		,new Cellule(trois.getX()-1,trois.getY(),un.getC()),new Cellule(quatre.getX()-1,quatre.getY(),un.getC()));	
	}
	
	/**
	 * retourne une pièce qui correspond au déplacement vers la droite.
	 */
	public Piece versLaDroite(){
		return new Piece(new Cellule(un.getX()+1,un.getY(),un.getC()),new Cellule(deux.getX()+1,deux.getY(),un.getC())
		,new Cellule(trois.getX()+1,trois.getY(),un.getC()),new Cellule(quatre.getX()+1,quatre.getY(),un.getC()));
	}
	
}