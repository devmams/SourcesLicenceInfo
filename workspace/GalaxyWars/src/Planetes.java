import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Planetes extends Entite {

	private int taillePlanete;
	private int population;
	private int tauxNantalite;
	private int integrite;
	
	public Planetes(Color c){
		super(c);
		Random rand = new Random();
		taillePlanete = Math.abs(rand.nextInt())%(Constantes.PlaneteTailleMax-Constantes.PlaneteTailleMin+1) + Constantes.PlaneteTailleMin;//taille
		population = taillePlanete/2;
		tauxNantalite = Math.abs(rand.nextInt())%(Constantes.TauxNataliteMax-Constantes.TauxNataliteMin+1) + Constantes.TauxNataliteMin;//tauxNatalite
		integrite = 0;
	}
	
	private void nouvelleIntegrite(int t ,Vaisseaux v){
		integrite = min ( v.getResistance(), integrite + population * t);
	}
	
	public boolean construire(int tauxProductivite , Vaisseaux v){
		nouvelleIntegrite(tauxProductivite, v);
		if(integrite == v.getResistance())
			return true;
		else 
			return false;
	} 
	
	public int getTaillePlante(){
		return taillePlanete;
	}
	
	
	public int getPopulaltion(){
		return population;
	}
	
	public int getTauxNatalite(){
		return taillePlanete;
	}
	
	public void reproduction(){
		population = min(taillePlanete, population*(1+tauxNantalite));
	}
	
	public int min(int a ,int b){
		int tmp = a;
		if(a > b){
			tmp = b;
		}
		return tmp;
	}
		
		
}
