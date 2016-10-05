
public class Fabrique {
	private Piece creerCarre;
	private Piece creerBatonVertical;
	private Piece creerBatonHorizontal;
	public Fabrique(){
		this.creerCarre = new Piece(new Cellule(5.5,18.5),new Cellule(5.5,19.5),new Cellule(6.5,19.5),new Cellule(6.5,18.5));
		this.creerBatonVertical = new Piece(new Cellule(5.5,16.5),new Cellule(5.5,17.5),new Cellule(5.5,18.5),new Cellule(5.5,19.5));
		this.creerBatonHorizontal = new Piece(new Cellule(4.5,19.5),new Cellule(5.5,19.5),new Cellule(6.5,19.5),new Cellule(7.5,19.5));

	}

}