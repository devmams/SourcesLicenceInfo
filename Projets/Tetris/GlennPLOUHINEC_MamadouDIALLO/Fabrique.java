/**
 * Classe permettant de generer toutes les 
 * pièces du jeu Tetris.
 * 
 * 
 * @author Glenn PLOUHINEC / Mamadou DIALLO
 */
public class Fabrique {
	//Entier Aléatoire correspondant à la pièce.
	private int nb;
	
	//Entier Aléatoire correspondant à l'appartion en haut de la pièce.
	private double x;
	
	//Entier utilisé pour la rotation des pièces. 
	private static int pos;
	
	//Pièce Aléatoire retournée.
	private Piece p;
	
	//Pièce corespondant à la rotation de la pièce.
	private Piece pr;
	
	//Réel utilisé pour la rotation des pièce.
	private double cx;
	
	//Réel utilisé pour la rotation des pièce.
	private double cy;

	/**
	 * Construit une Fabrique vide.
	 */
	public Fabrique(){}
	
	/**
	 * Retourne une pièce Aléatoire.
	 */
	public Piece creerPiece(){
	pos = 1;
	//retourne un entier entre 1.5 et 6.5.
	x = 1.5 + (int)(Math.random() * 6 );
	
	//retourne un entier entre 1 et 7.
	nb = 1 + (int)(Math.random() * 7 );
	switch (nb) {
		case 1: p = creerCarre();break;
		case 2:	p = creerBatonVertical();break;
		case 3: p = creerTe();break;
		case 4: p = creerBiais();break;
		case 5: p = creerBiaisInverse();break;
		case 6: p = creerL();break;
		case 7: p = creerLInverse();break;
	}
		return p;
    }
	
	/**
	 * Crée la pièce L inversé.
	 */
	Piece creerLInverse(){
		x = 1.5 + (int)(Math.random() * 6 );
		return new Piece(new Cellule(x,0.5,'g'),new Cellule(x,1.5,'g'),new Cellule(x,2.5,'g'),new Cellule(x+1,2.5,'g'));
	}
	
	/**
	 * Crée la pièce L.
	 */
	Piece creerL(){
		x = 1.5 + (int)(Math.random() * 6 );
		return new Piece(new Cellule(x,0.5,'f'),new Cellule(x,1.5,'f'),new Cellule(x,2.5,'f'),new Cellule(x-1,2.5,'f'));
	}
	
	/**
	 * Crée la pièce Biais Inverse.
	 */
	Piece creerBiaisInverse(){
		x = 1.5 + (int)(Math.random() * 6 );
		return new Piece(new Cellule(x,0.5,'e'),new Cellule(x,1.5,'e'),new Cellule(x-1,1.5,'e'),new Cellule(x-1,2.5,'e'));
	}
	
	/**
	 * Crée la pièce Biais.
	 */
	Piece creerBiais(){
		x = 1.5 + (int)(Math.random() * 6 );
		return new Piece(new Cellule(x,0.5,'d'),new Cellule(x,1.5,'d'),new Cellule(x+1,1.5,'d'),new Cellule(x+1,2.5,'d'));
	}
	
	/**
	 * Crée la pièce Té.
	 */
	Piece creerTe(){
		x = 1.5 + (int)(Math.random() * 6 );
		return new Piece(new Cellule(x,0.5,'c'),new Cellule(x,1.5,'c'),new Cellule(x,2.5,'c'),new Cellule(x+1,1.5,'c'));
	}
	
	/**
	 * Crée la pièce Baton Vertical.
	 */
	Piece creerBatonVertical(){
		x = 1.5 + (int)(Math.random() * 6 );
		return new Piece(new Cellule(x,0.5,'b'),new Cellule(x,1.5,'b'),new Cellule(x,2.5,'b'),new Cellule(x,3.5,'b'));
	}
	
	/**
	 * Crée la pièce creer Carre.
	 */
	Piece creerCarre(){
		x = 1.5 + (int)(Math.random() * 6 );
		return new Piece(new Cellule(x,0.5,'a'),new Cellule(x+1,0.5,'a'),new Cellule(x+1,1.5,'a'),new Cellule(x,1.5,'a'));
	}
	
	/**
	 * retourne une pièce correspondant à la rotation.
	 * @param piece à tourner.
	 */
	public Piece rotationPiece(Piece p){
		pos++;
		if(p.getCelluleUn().getC() == 'a'){//Pièce Carré
			cx = p.getCelluleUn().getX();
			cy = p.getCelluleUn().getY();
			switch (pos) {
			case 1:pr = p ;break;//carre
			case 2:pr = p ;break;//carre
			case 3:pr = p ;break;//carre
			case 4:pr = p ; pos = 0 ;break;//carre

			}
		}
		else if(p.getCelluleUn().getC() == 'b'){//Pièce BatonVertical
			cx = p.getCelluleUn().getX();
			cy = p.getCelluleUn().getY();
			switch (pos) {
			case 1:pr = new Piece(new Cellule(cx+1,cy,'b'),new Cellule(cx+1,cy+1,'b'),new Cellule(cx+1,cy+2,'b'),new Cellule(cx+1,cy+3,'b'));break;
			case 2:pr = new Piece(new Cellule(cx-1,cy,'b'),new Cellule(cx,cy,'b'),new Cellule(cx+1,cy,'b'),new Cellule(cx+2,cy,'b'));break;
			case 3:pr = new Piece(new Cellule(cx+1,cy,'b'),new Cellule(cx+1,cy+1,'b'),new Cellule(cx+1,cy+2,'b'),new Cellule(cx+1,cy+3,'b'));break;
			case 4:pr = new Piece(new Cellule(cx-1,cy,'b'),new Cellule(cx,cy,'b'),new Cellule(cx+1,cy,'b'),new Cellule(cx+2,cy,'b'));pos=0;break;
			}
		}
		else if(p.getCelluleUn().getC() == 'c'){//Pièce Té
			cx = p.getCelluleUn().getX();
			cy = p.getCelluleUn().getY();
			switch (pos) {
			case 1:pr = new Piece(new Cellule(cx,cy,'c'),new Cellule(cx,cy+1,'c'),new Cellule(cx,cy+2,'c'),new Cellule(cx+1,cy+1,'c'));break;//T
			case 2:pr = new Piece(new Cellule(cx,cy+2,'c'),new Cellule(cx+1,cy+2,'c'),new Cellule(cx+2,cy+2,'c'),new Cellule(cx+1,cy+1,'c'));break;
			case 3:pr = new Piece(new Cellule(cx+2,cy+2,'c'),new Cellule(cx+2,cy+1,'c'),new Cellule(cx+2,cy,'c'),new Cellule(cx+1,cy+1,'c'));break;
			case 4:pr = new Piece(new Cellule(cx-2,cy,'c'),new Cellule(cx-1,cy,'c'),new Cellule(cx,cy,'c'),new Cellule(cx-1,cy+1,'c'));pos=0;break;
			}
		}
		else if(p.getCelluleUn().getC() == 'd'){//Pièce Biais
			cx = p.getCelluleUn().getX();
			cy = p.getCelluleUn().getY();
			switch (pos) {
			case 1:pr = new Piece(new Cellule(cx,cy-2,'d'),new Cellule(cx,cy-1,'d'),new Cellule(cx+1,cy-1,'d'),new Cellule(cx+1,cy,'d'));break;
			case 2:pr = new Piece(new Cellule(cx,cy+2,'d'),new Cellule(cx+1,cy+2,'d'),new Cellule(cx+1,cy+1,'d'),new Cellule(cx+2,cy+1,'d'));break;
			case 3:pr = new Piece(new Cellule(cx,cy-2,'d'),new Cellule(cx,cy-1,'d'),new Cellule(cx+1,cy-1,'d'),new Cellule(cx+1,cy,'d'));break;
			case 4:pr = new Piece(new Cellule(cx,cy+2,'d'),new Cellule(cx+1,cy+2,'d'),new Cellule(cx+1,cy+1,'d'),new Cellule(cx+2,cy+1,'d'));pos=0;break;
			}
		}
		else if(p.getCelluleUn().getC() == 'e'){//Biais Inverse
			cx = p.getCelluleUn().getX();
			cy = p.getCelluleUn().getY();
			switch (pos) {
			case 1:pr = new Piece(new Cellule(cx+1,cy-1,'e'),new Cellule(cx+1,cy,'e'),new Cellule(cx,cy,'e'),new Cellule(cx,cy+1,'e'));break;
			case 2:pr = new Piece(new Cellule(cx-1,cy+1,'e'),new Cellule(cx,cy+1,'e'),new Cellule(cx,cy+2,'e'),new Cellule(cx+1,cy+2,'e'));break;
			case 3:pr = new Piece(new Cellule(cx+1,cy-1,'e'),new Cellule(cx+1,cy,'e'),new Cellule(cx,cy,'e'),new Cellule(cx,cy+1,'e'));break;
			case 4:pr = new Piece(new Cellule(cx-1,cy+1,'e'),new Cellule(cx,cy+1,'e'),new Cellule(cx,cy+2,'e'),new Cellule(cx+1,cy+2,'e'));pos=0;break;
			}
		}
		else if(p.getCelluleUn().getC() == 'f'){//Pièce L
			cx = p.getCelluleUn().getX();
			cy = p.getCelluleUn().getY();
			switch (pos) {
			case 1:pr = new Piece(new Cellule(cx-1,cy-2,'f'),new Cellule(cx-1,cy-1,'f'),new Cellule(cx-1,cy,'f'),new Cellule(cx-2,cy,'f'));break;//L
			case 2:pr = new Piece(new Cellule(cx-1,cy+1,'f'),new Cellule(cx,cy+1,'f'),new Cellule(cx+1,cy+1,'f'),new Cellule(cx+1,cy+2,'f'));break;//
			case 3:pr = new Piece(new Cellule(cx,cy+1,'f'),new Cellule(cx,cy,'f'),new Cellule(cx,cy-1,'f'),new Cellule(cx+1,cy-1,'f'));break;//
			case 4:pr = new Piece(new Cellule(cx+2,cy,'f'),new Cellule(cx+1,cy,'f'),new Cellule(cx,cy,'f'),new Cellule(cx,cy-1,'f'));pos=0;break;//
			}
		}
		else if(p.getCelluleUn().getC() == 'g'){//Pièce L inversé
			cx = p.getCelluleUn().getX();
			cy = p.getCelluleUn().getY();
			switch (pos) {
			case 1:pr = new Piece(new Cellule(cx-2,cy-1,'g'),new Cellule(cx-2,cy,'g'),new Cellule(cx-2,cy+1,'g'),new Cellule(cx-1,cy+1,'g'));break;//L2
			case 2:pr = new Piece(new Cellule(cx,cy+2,'g'),new Cellule(cx+1,cy+2,'g'),new Cellule(cx+2,cy+2,'g'),new Cellule(cx+2,cy+1,'g'));break;
			case 3:pr = new Piece(new Cellule(cx+1,cy,'g'),new Cellule(cx+1,cy-1,'g'),new Cellule(cx+1,cy-2,'g'),new Cellule(cx,cy-2,'g'));break;
			case 4:pr = new Piece(new Cellule(cx+1,cy-1,'g'),new Cellule(cx,cy-1,'g'),new Cellule(cx-1,cy-1,'g'),new Cellule(cx-1,cy,'g'));pos=0;break;
			}
		}
		return pr;
	}
			
}
