
public class MainPrincipal {
	public static void main(String[] args) {
		Fabrique f = new Fabrique();
		Piece p = f.creerCarre();
		Plateau plat = new Plateau();
		plat.jouer(p);
		p = p.versLaDroite();
		plat.jouer(p);
		p = p.versLaDroite();
		plat.jouer(p);
		p = p.versLaDroite();
		plat.jouer(p);
		p = p.versLaDroite();
		plat.jouer(p);
		//p = p.versLaDroite();
		plat.jouer(p);
		p = p.versLeBas();
		plat.jouer(p);
		Piece pp = f.creerBatonHorizontal();
		plat.jouer(pp);
		pp = pp.versLaDroite();
		plat.jouer(pp);
		
	
	}

}
