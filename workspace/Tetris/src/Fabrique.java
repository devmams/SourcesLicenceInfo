
public class Fabrique {
	private Piece creerCarre;
	private Piece creerBatonVertical;
	private Piece creerBatonHorizontal;
	
	public Fabrique(){
		this.creerCarre = new Piece(new Cellule(4.5,18.5,'a'),new Cellule(4.5,19.5,'a'),new Cellule(5.5,19.5,'a'),new Cellule(5.5,18.5,'a'));
		this.creerBatonVertical = new Piece(new Cellule(5.5,16.5,'b'),new Cellule(5.5,17.5,'b'),new Cellule(5.5,18.5,'b'),new Cellule(5.5,19.5,'b'));
		this.creerBatonHorizontal = new Piece(new Cellule(4.5,19.5,'c'),new Cellule(5.5,19.5,'c'),new Cellule(6.5,19.5,'c'),new Cellule(7.5,19.5,'c'));
	}
	
	public Cellule carre(){
		return this.creerCarre.getCelluleUn();
	}

}