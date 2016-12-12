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
		v1 = new Vaisseaux(p,couleur);
		ajoutVaisseaux(v1);
		v2 = new Vaisseaux(p,couleur);
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
	
	public void supprPlanete(Planetes p){
		for(int i=0 ;i<planetes.size() ;i++){
			if(p.equals(planetes.get(i))){
				planetes.remove(i);
			}
		}
	}

	public void ajoutVaisseaux(Vaisseaux v){
		vaisseaux.add(v);
		v.ajoutListeEntite(v);
	}
	
	public void supprVaisseaux(Vaisseaux v){
		for(int i=0 ;i<vaisseaux.size() ;i++){
			if(v.equals(vaisseaux.get(i))){
				vaisseaux.remove(i);
				break;
			}
		}
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
				supprVaisseaux(vaisseaux.get(i));
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
	
	public void interaction(ArrayList<Espece>especes,Empire empire,PlanetesInoccupees pI){
		for(int i=0 ;i<vaisseaux.size(); i++){
			vaisseaux.get(i).interactionVaisseaux(especes,empire,pI);
		}
	}
	
	public void reproduction(int tauxNatalite){
		for(int p=0 ; p<planetes.size() ;p++){
			planetes.get(p).reproductionPlanete(tauxNatalite);
		}
	}
	
	public ArrayList<Vaisseaux> getVaisseaux(){
		return vaisseaux;
	}
	
	
}




