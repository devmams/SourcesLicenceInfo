import java.awt.Color;
import java.util.Random;


public class Espece {
	private static int idEspece;
	private int tauxNatalite;
	private int tauxProductivite;
	private Empire empire;
	private Color coul;
	private int nouvelleIntegrite;
	private int ancienneIntegrite = 0;
	
	public Espece(){
		Random rand = new Random();
		idEspece++;
		tauxNatalite = Math.abs(rand.nextInt())%(Constantes.TauxNataliteMax-Constantes.TauxNataliteMin+1) + Constantes.TauxNataliteMin;
		tauxProductivite = Math.abs(rand.nextInt())%(Constantes.TauxProductiviteMax-Constantes.TauxProductiviteMin+1) + Constantes.TauxProductiviteMin;
		coul = creerCouleur(idEspece);
		empire = new Empire(coul);
	}
	
	public static Color creerCouleur(int x){
		Color col = Color.white;//noir
		switch(x){
		case 1 : col = Color.red;break;//rouge
		case 2 : col = new Color(0,192,0);break;//vert
		case 3 : col = new Color(0,128,224);break;//bleu
		case 4 : col = Color.orange;break;//orange
		}
		return col;
	}
	
	public Color getCouleur(){
		return coul;
	}
	
	public Empire getEmpire() {
		return empire;
	}
	
	/*public void construction(){
		for(Empire e : empire){
			for(int i=0 ; i<empire.getPlanetes().size() ;i++){
				nouvelleIntegrite = min(,ancienneIntegrite+poulation*tau);
				ancienneIntegrite = nouvelleIntegrite;
			}
		}
		
	}*/
}











