
public class Test {
	
	
	public static void main(String[] args) {
		Tirelire ib = new Tirelire();
		ib.ajouterPiece(200, 5);
		ib.ajouterPiece(200, 15);

		System.out.println(ib.nbPiece(200));
	}

}
