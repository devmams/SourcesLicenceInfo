/**
 * Classe permettant de créer une pièce qui est un ensemble 
 * de 4 Cellules.
 * 
 * ...
 * 
 * @author Glenn PLOUHINEC / Mamadou DIALLO
 */
public class Piece {
	
	private Cellule un ;
	private Cellule deux ;
	private Cellule trois ;
	private Cellule quatre ;
	
	public Piece(Cellule c1,Cellule c2 ,Cellule c3 ,Cellule c4){
		this.un = c1;
		this.deux = c2;
		this.trois = c3;
		this.quatre = c4;
	}
	
	public Cellule getCelluleUn(){
		return this.un;
	}
	
	public Cellule getCelluleDeux(){
		return this.deux;
	}
	
	public Cellule getCelluleTrois(){
		return this.trois;
	}
	
	public Cellule getCelluleQuatre(){
		return this.quatre;
	}
	
	public Piece versLeBas(){
		return new Piece(new Cellule(un.getX(),un.getY()+1,un.getC()),new Cellule(deux.getX(),deux.getY()+1,un.getC())
		,new Cellule(trois.getX(),trois.getY()+1,un.getC()),new Cellule(quatre.getX(),quatre.getY()+1,un.getC()));
	}

	public Piece versLaGauche(){
		return new Piece(new Cellule(un.getX()-1,un.getY(),un.getC()),new Cellule(deux.getX()-1,deux.getY(),un.getC())
		,new Cellule(trois.getX()-1,trois.getY(),un.getC()),new Cellule(quatre.getX()-1,quatre.getY(),un.getC()));	
	}
	
	public Piece versLaDroite(){
		return new Piece(new Cellule(un.getX()+1,un.getY(),un.getC()),new Cellule(deux.getX()+1,deux.getY(),un.getC())
		,new Cellule(trois.getX()+1,trois.getY(),un.getC()),new Cellule(quatre.getX()+1,quatre.getY(),un.getC()));
	}
	
}