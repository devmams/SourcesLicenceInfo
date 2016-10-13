
public class Fabrique {
	private int nb;
	private Piece p;
	public Fabrique(){}
		
	public Piece creerPiece(){
		nb = 1 + (int)(Math.random() * 3 );
		if(nb == 1)
	      p = new Piece(new Cellule(4.5,0.5,'a'),new Cellule(5.5,0.5,'a'),new Cellule(5.5,1.5,'a'),new Cellule(4.5,1.5,'a'));
	    else if(nb == 2)
	      p = new Piece(new Cellule(4.5,0.5,'b'),new Cellule(4.5,1.5,'b'),new Cellule(4.5,2.5,'b'),new Cellule(4.5,3.5,'b'));
	    else if(nb == 3)
	      p = new Piece(new Cellule(4.5,0.5,'c'),new Cellule(5.5,0.5,'c'),new Cellule(6.5,0.5,'c'),new Cellule(7.5,0.5,'c'));
	    return p;
	}
			
	/*public Piece creerCarre(){
		return new Piece(new Cellule(4.5,0.5,'a'),new Cellule(5.5,0.5,'a'),new Cellule(5.5,1.5,'a'),new Cellule(4.5,1.5,'a'));
	}

	public Piece creerBatonVertical(){
		return new Piece(new Cellule(4.5,0.5,'b'),new Cellule(4.5,1.5,'b'),new Cellule(4.5,2.5,'b'),new Cellule(4.5,3.5,'b'));
	}

	public Piece creerBatonHorizontal(){
		return new Piece(new Cellule(4.5,0.5,'c'),new Cellule(5.5,0.5,'c'),new Cellule(6.5,0.5,'c'),new Cellule(7.5,0.5,'c'));

	}*/
}
