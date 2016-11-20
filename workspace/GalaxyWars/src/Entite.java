import java.util.ArrayList;
import java.util.Random;

public abstract class Entite {
	//private int numeroEntite;
	private int abs;
	private int ord;
	private static ArrayList<Integer> caseOccupees = new ArrayList<Integer>();	
	
	public Entite(){
		Random rand = new Random();
		abs = rand.nextInt(Constantes.Largeur); //abscisse
		ord = rand.nextInt(Constantes.Hauteur); //ordonnée
		while(occupee(abs, ord)){
			abs = rand.nextInt(Constantes.Largeur); //abscisse
			ord = rand.nextInt(Constantes.Hauteur); //ordonnée
		}
		caseOccupees.add(getNumeroEntite());
	}
	
	public Entite(Planetes p){
		
		abs = p.getAbscisse();
		ord = p.getOrdonnee()-1;
		setCoordonnees(abs, ord);
		System.out.println(occupee(abs, ord));
		if(occupee(abs, ord)){
			abs = p.getAbscisse();
			ord = p.getOrdonnee()+1;
			setCoordonnees(abs, ord);
			if(occupee(abs, ord)){
				abs = p.getAbscisse()-1;
				ord = p.getOrdonnee();
				setCoordonnees(abs, ord);
				if(occupee(abs, ord)){
					abs = p.getAbscisse()+1;
					ord = p.getOrdonnee();
					setCoordonnees(abs, ord);
				}
			}
		}
		
		caseOccupees.add(getNumeroEntite());
		
	}
	
	public void setAbscisse(int x){
		if(x<0){
			abs = Constantes.Largeur + x;
		}
		if(x>Constantes.Largeur-1){
			abs = x - Constantes.Largeur;
		}
	}
	
	public void setOrdonnee(int y){
		if(y<0){
			ord = Constantes.Hauteur + y;
		}
		if(y>Constantes.Hauteur-1)
			ord = y - Constantes.Hauteur;
	}
	
	public void setCoordonnees(int x,int y){
		setAbscisse(x);
		setOrdonnee(y);
	}
	
	public boolean occupee(int x, int y){
		boolean occupee = false;
		int tmp = Constantes.Largeur*y + x;
		for(int i=0 ; i<caseOccupees.size() ;i++){
			if(tmp == caseOccupees.get(i)){
				occupee = true;
				break;
			}
		}
		return occupee;
	}
	
	public ArrayList<Integer> getCaseOccupees(){
		return caseOccupees;
	}
	
	public int getAbscisse(){
		return abs;
	}
	
	public int getOrdonnee(){
		return ord;
	}
	
	public int getNumeroEntite(){
		return Constantes.Largeur*ord + abs;
	}

}
