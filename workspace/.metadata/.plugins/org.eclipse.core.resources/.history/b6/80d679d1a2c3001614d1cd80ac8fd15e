/**
 * @brief Classe permettant de créer et gérer les planètes inoccupées
 * 
 * @author Glenn PLOUHINEC / Mamadou DIALLO
 */
import java.awt.Color;
import java.util.ArrayList;


public class PlanetesInoccupees {

	ArrayList<Planetes> planetesInoccupees;

	public PlanetesInoccupees(Galaxie galaxie){
		planetesInoccupees = new ArrayList<Planetes>();
		for(int i=0 ; i<Constantes.NbPlanetesInocupee ;i++){
			Planetes p = new Planetes(Color.white,galaxie);
			planetesInoccupees.add(p);
			galaxie.ajoutEntite(p);
		}
	}
	
	public ArrayList<Planetes> getPlanetesInoccupees(){
		return planetesInoccupees;
	}
	
	public void ajoutPlaneteInoccupee(Planetes p){
		planetesInoccupees.add(p);
	}
	
	public void supprPlaneteInoccupee(Planetes p){
		for(int i=0 ;i<planetesInoccupees.size() ;i++){
			if(planetesInoccupees.get(i).equals(p)){
				planetesInoccupees.remove(i);
			}
		}
	}
}
