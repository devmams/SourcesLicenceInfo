import java.awt.Color;


public class Fabrique {
	private int nb;
	private double x;
	private Piece p;
	public Fabrique(){}
		
	public Piece creerPiece(){
		x = 1.5 + (int)(Math.random() * 6 );
		nb = 1 + (int)(Math.random() * 7 );
		switch (nb) {
		case 1:p = new Piece(new Cellule(x,0.5,'a'),new Cellule(x+1,0.5,'a'),new Cellule(x+1,1.5,'a'),new Cellule(x,1.5,'a'));break;//carre
		case 2:p = new Piece(new Cellule(x,0.5,'b'),new Cellule(x,1.5,'b'),new Cellule(x,2.5,'b'),new Cellule(x,3.5,'b'));;break;//carre
		case 3:p = new Piece(new Cellule(x,0.5,'c'),new Cellule(x,1.5,'c'),new Cellule(x,2.5,'c'),new Cellule(x+1,1.5,'c'));break;//carre
		case 4:p = new Piece(new Cellule(x,0.5,'d'),new Cellule(x,1.5,'d'),new Cellule(x+1,1.5,'d'),new Cellule(x+1,2.5,'d'));break;//carre
		case 5:p = new Piece(new Cellule(x,0.5,'e'),new Cellule(x,1.5,'e'),new Cellule(x-1,1.5,'e'),new Cellule(x-1,2.5,'e'));break;//carre
		case 6:p = new Piece(new Cellule(x,0.5,'f'),new Cellule(x,1.5,'f'),new Cellule(x,2.5,'f'),new Cellule(x-1,2.5,'f'));break;//carre
		case 7:p = new Piece(new Cellule(x,0.5,'g'),new Cellule(x,1.5,'g'),new Cellule(x,2.5,'g'),new Cellule(x+1,2.5,'g'));break;//carre
		}
	    return p;
	}
			
}
