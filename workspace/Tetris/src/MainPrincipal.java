import java.util.Scanner;


public class MainPrincipal {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Fabrique f = new Fabrique();
		Piece p = f.creerCarre();
		Plateau plat = new Plateau();
		
		
		while(true){
			while(!plat.jeuPerdu(p)){
				System.out.println( "'''''");

				int dep = sc.nextInt();
				if(dep == 1)
				{
				p = p.versLeBas();
				}
				else if(dep == 2){
					System.out.println("####");
					p = p.getAncienne().getAncienne();
				}
				else if(dep == 2)
				{
				p = p.versLaDroite();
				}
				else if(dep==3)
				{
					p = p.versLaGauche();
				}
				plat.jouer(p);
			}
			if(plat.jeuPerdu(p)){
				Fabrique ff = new Fabrique(); 
				p = ff.creerCarre();
			}	
		}
		
		/*boolean jeu = false;
		while(jeu == false){
			plat.jouer(p);
			plat.retirer(p);
			p = p.versLeBas();
			if(plat.accepter(p)){
				int dep = sc.nextInt();
				if(dep == 1){
					p = p.versLaGauche();
				}
				else if(dep == 0){
					p = p.versLeBas();
				}
				else if(dep == 3){
					p = p.versLaDroite();
				}
				else if(dep==3)
				{
					p = p.versLaGauche();
				}
			}
			else{
				Fabrique ff = new Fabrique();
				System.out.println("--"+p.getAncienne().getCelluleUn().getY());
				p = ff.creerCarre();
				System.out.println("----"+p.getCelluleUn().getY());
				jeu = plat.jeuPerdu(p);
			}
			System.out.println(jeu);
		}
		//sc.close();*/
	}
}
