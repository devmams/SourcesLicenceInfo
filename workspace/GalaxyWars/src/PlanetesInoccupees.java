/**
 * @brief Classe permettant de créer et gérer les planètes inoccupées
 * 
 * @author Glenn PLOUHINEC / Mamadou DIALLO
 */
import java.awt.Color;
import java.util.ArrayList;


public class PlanetesInoccupees {

	// liste des planète inoccupées.
	ArrayList<Planetes> planetesInoccupees;

	/**
	 * Construit les planètes inoccupées.
	 * @param leur galaxie.
	 */
	public PlanetesInoccupees(Galaxie galaxie){
		planetesInoccupees = new ArrayList<Planetes>();
		for(int i=0 ; i<Constantes.NbPlanetesInocupee ;i++){
			Planetes p = new Planetes(Color.white,galaxie);
			planetesInoccupees.add(p);
			galaxie.ajoutEntite(p);
		}
	}
	
	/**
	 * Accesseur.
	 * @return la liste des planètes inoccupées
	 */
	public ArrayList<Planetes> getPlanetesInoccupees(){
		return planetesInoccupees;
	}
	
	/**
	 * Ajoute une planète à la liste des planètes inoccupées.
	 * @param la planète à rajouter.
	 */
	public void ajoutPlaneteInoccupee(Planetes p){
		planetesInoccupees.add(p);
	}
	
	/**
	 * supprime une planète de la liste des planètes inoccupées.
	 * @param la planète à supprimer.
	 */
	public void supprPlaneteInoccupee(Planetes p){
		for(int i=0 ;i<planetesInoccupees.size() ;i++){
			if(planetesInoccupees.get(i).equals(p)){
				planetesInoccupees.remove(i);
			}
		}
	}
	
	/**
	 * Accesseur.
	 * @return le nombre de planetes inoccupées dans la galaxie.
	 */
	public int getNbPlanetesInoccupees(){
		return planetesInoccupees.size();
	}
}
