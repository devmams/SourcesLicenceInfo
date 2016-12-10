import java.awt.Color;
import java.util.ArrayList;

public class Empire {

	private Color couleur;
	private ArrayList<Planetes> planetes;
	private Planetes p;
	private ArrayList<Vaisseaux> vaisseaux;
	private Vaisseaux v1;
	private Vaisseaux v2;

	public Empire(Color col){
		couleur = col;
		planetes = new ArrayList<Planetes>();
		vaisseaux = new ArrayList<Vaisseaux>();
		p = new Planetes(couleur);
		ajouterPlanete(p);
		System.out.println("p : " + p.getNumeroEntite());
		v1 = new Vaisseaux(p,couleur);
		ajoutVaisseaux(v1);
		System.out.println("v1 : " + v1.getNumeroEntite());
		v2 = new Vaisseaux(p,couleur);
		System.out.println("v2 : " + v2.getNumeroEntite());
		ajoutVaisseaux(v2);
	}	
	
	public void ajouterPlanete(Planetes p){
		planetes.add(p);
		p.ajoutListeEntite(p);
		nouveauVaisseauxEnConstruction(p);
	}
	
	public void ajouterPlaneteIno(Planetes p){
		planetes.add(p);
		nouveauVaisseauxEnConstruction(p);
	}
	
	public void supprPlanete(int i){
		planetes.get(i).supprListeEntite(planetes.get(i));
		planetes.remove(i);
	}

	public void ajoutVaisseaux(Vaisseaux v){
		vaisseaux.add(v);
		v.ajoutListeEntite(v);
	}
	
	public void supprVaisseaux(int i){
		vaisseaux.get(i).supprListeEntite(vaisseaux.get(i));
		vaisseaux.remove(i);
	}
	
	private void nouveauVaisseauxEnConstruction(Planetes p){
		p.vaisseauxEnConstruction(p);
	}
	
	public Color getEmpirColor(){
		return couleur;
	}
	
	public ArrayList<Planetes> getPlanetes(){
		return planetes;
	}
	
	public void autoDestruction(){
		for(int i=0 ;i<vaisseaux.size() ;i++){
			if(!vaisseaux.get(i).verifCarburant() || vaisseaux.get(i).getIntegrite() == 0){
				supprVaisseaux(i);
			}
		}
	}
	
	public void constructionVaisseaux(int t){
		for(int i=0 ; i<planetes.size() ;i++){
			if(planetes.get(i).constructionTerminee(t)){
				int x = planetes.get(i).vaisseauxConstruit().retrouverAbs(planetes.get(i).vaisseauxConstruit().getNumeroEntite());
				int y = planetes.get(i).vaisseauxConstruit().retrouverOrd(planetes.get(i).vaisseauxConstruit().getNumeroEntite());
				if(!planetes.get(i).vaisseauxConstruit().occupee(x, y)){
					ajoutVaisseaux(planetes.get(i).vaisseauxConstruit());
					nouveauVaisseauxEnConstruction(planetes.get(i));
				}
			}
		}
	}
	
	public void deplacementVaisseaux(){
		for(int i=0 ; i<vaisseaux.size() ;i++){
			vaisseaux.get(i).deplacement();
		}
	}
	
	public void interaction(){
		
	}
	
	public ArrayList<Vaisseaux> getVaisseaux(){
		return vaisseaux;
	}
	
	
}




