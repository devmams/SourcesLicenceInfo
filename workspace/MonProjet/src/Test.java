
public class Test {
	
	
	public static void main(String[] args) {
		Tirelire ib = new Tirelire();
		Tirelire mamadou = new Tirelire();

		mamadou.ajouterPiece(200, 5);
		mamadou.ajouterPiece(50, 5);

		ib.ajouterPiece(10, 10);
		ib.ajouterTirelire(mamadou);
		ib.retirerTirelire(mamadou);
		//ib.ajouterPiece(10);

		//ib.retirerPiece(10, 7);

		//System.out.println(ib.nbPiece(200));
		ib.afficher();
		
	}

}
