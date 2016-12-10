import java.awt.Color;
import java.util.Random;

public abstract class Entite {
	private Color couleur;
	protected int abs;
	protected int ord;
	private static Entite[] listeEntites = new Entite[300];
	private char typeEntite; // 'v' pour vaisseaux et 'p' pour planete

	public Entite(Color c){
		Random rand = new Random();
		abs = rand.nextInt(Constantes.Largeur); //abscisse
		ord = rand.nextInt(Constantes.Hauteur); //ordonnée
		while(occupee(abs, ord)){
			abs = rand.nextInt(Constantes.Largeur); //abscisse
			ord = rand.nextInt(Constantes.Hauteur); //ordonnée
		}
		couleur = c ;
		typeEntite = 'p';
	}

	public Entite(Planetes p,Color c){
		caseAdjacente(p);
		couleur = c;
		typeEntite = 'v';
	}
	
	public abstract void infligeDegat();
	
	public void modifCouleur(Color newColor){
		couleur = newColor;
	}
	
	public Entite getListeEntites(int pos){
		return listeEntites[pos];
	}
	
	public void ajoutListeEntite(Entite e){
		listeEntites[e.getNumeroEntite()] = e;
	}
	
	public void supprListeEntite(Entite e){
		listeEntites[e.getNumeroEntite()] = null;
	}
	
	public void modifPositionEntite(int anciennePos, int nouvellePos){
		//System.out.println("Entite.modifPositionEntite() " + Thread.currentThread().getName());
		listeEntites[nouvellePos] = listeEntites[anciennePos];
		listeEntites[anciennePos] = null;
	}
	
	public char getTypeEntite(){
		return typeEntite;
	}
	
	public void caseAdjacente(Planetes p){
		System.err.println("1----------");
		abs = p.getAbscisse()-1;
		ord = p.getOrdonnee()-1;
		recadrerCoordonnees(abs, ord);
		System.out.println(occupee(abs, ord));
		if(occupee(abs, ord)){
			System.err.println("2----------");
			abs = p.getAbscisse();
			ord = p.getOrdonnee()-1;
			recadrerCoordonnees(abs, ord);
			if(occupee(abs, ord)){
				abs = p.getAbscisse()+1;
				ord = p.getOrdonnee()-1;
				recadrerCoordonnees(abs, ord);
				if(occupee(abs, ord)){
					abs = p.getAbscisse()+1;
					ord = p.getOrdonnee();
					recadrerCoordonnees(abs, ord);
					if(occupee(abs, ord)){
						abs = p.getAbscisse()+1;
						ord = p.getOrdonnee()+1;
						recadrerCoordonnees(abs, ord);
						if(occupee(abs, ord)){
							abs = p.getAbscisse();
							ord = p.getOrdonnee()+1;
							recadrerCoordonnees(abs, ord);
							if(occupee(abs, ord)){
								abs = p.getAbscisse()-1;
								ord = p.getOrdonnee()+1;
								recadrerCoordonnees(abs, ord);
								if(occupee(abs, ord)){
									abs = p.getAbscisse()-1;
									ord = p.getOrdonnee();
									recadrerCoordonnees(abs, ord);
								}
							}
						}
					}
				}
			}
		}
	}

	public Color getColorEntite(){
		return couleur;
	}

	public int getAbscisse(){
		return abs;
	}

	public int getOrdonnee(){
		return ord;
	}

	public int recadrerAbscisse(int x){
		int temp = x;
		if(x<0)
			temp = Constantes.Largeur + x;
		if(x>Constantes.Largeur-1){
			temp = x - Constantes.Largeur;
		}
		return temp;
	}

	public int recadrerOrdonnee(int y){
		int temp = y;
		if(y<0)
			temp = Constantes.Hauteur + y;
		if(y>Constantes.Hauteur-1)
			temp = y - Constantes.Hauteur;
		return temp;
	}

	public void recadrerCoordonnees(int x,int y){
		abs = recadrerAbscisse(x);
		ord = recadrerOrdonnee(y);
	}

	//renvoie vrai si la case est occupee
	public boolean occupee(int x, int y){
		boolean occupee = false;
		int tmp = Constantes.Largeur*y + x;
		if(listeEntites[tmp] != null){
			occupee = true;
		}
		return occupee;
	}

	public int getNumeroEntite(){
		return Constantes.Largeur*ord + abs;
	}
	
	public int min(int a ,int b){
		int tmp = a;
		if(a > b){
			tmp = b;
		}
		return tmp;
	}
	
	public int retrouverAbs(int num){
		int ordon = retrouverOrd(num);
		return num - (ordon*Constantes.Largeur);
	}
	
	public int retrouverOrd(int num){
		return num/Constantes.Largeur;
	}

}