import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * @brief Classe gérant la simulation de conquête galactique
 */
public class Simulation {

	/**
	 * 
	 * @return Vrai ssi la partie est terminée
	 */
	public static Boolean victoire() {
		// TODO : à modifier
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// création du panneau d'affichage
		Affichage panneau = new Affichage();
		
		// création de la fenêtre principale contenant le panneau
		Fenetre fenetre = new Fenetre(panneau);

		ArrayList<Espece> especes = new ArrayList<Espece>();
		
		ArrayList<Planetes> planetesInoccupee = new ArrayList<Planetes>();

		//création d'espace initiales
		for(int i=0 ; i<3 ;i++){
			especes.add(new Espece());
		}
		
		//création de planètes inoccupées
		for(int ind=0 ; ind<Constantes.NbPlanetesInocupee ;ind++){
			planetesInoccupee.add(new Planetes(Color.white));
		}
		
		// boucle de simulation
		int tour = 0;
		while (!victoire() && tour<Constantes.TourMax) {
			// décompte des tours
			tour += 1;
			// Exécution des étapes du tour courant
			// TODO : à compléter
			
			
			// Affichage d'un bref rapport textuel
			System.out.println("Tour " + tour + " :");
			
			
			ArrayList<Planetes> listePlanetes = new ArrayList<Planetes>();
			ArrayList<Vaisseaux> listeVaisseaux = new ArrayList<Vaisseaux>();
			
			for (Espece e : especes) {
				for (int p=0; p<e.getEmpire().getPlanetes().size(); p++) {
					listePlanetes.add(e.getEmpire().getPlanetes().get(p));
					//e.getEmpire().getPlanetes().get(i).reproduction();
				}	
				for (int v=0; v<e.getEmpire().getVaisseaux().size(); v++) {
					listeVaisseaux.add(e.getEmpire().getVaisseaux().get(v));
					e.getEmpire().getVaisseaux().get(v).deplacement();
					e.getEmpire().autoDestruction();
				}
				e.constructioin();
			}
			
			//ajout des planètes inoccupées
			for(int i=0 ; i<Constantes.NbPlanetesInocupee; i++){
				listePlanetes.add(planetesInoccupee.get(i));
			}
			
			panneau.rafraichir(listePlanetes,listeVaisseaux);
			/////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////
			
			// temporisation avant prochain tour
			try {
				Thread.sleep(Constantes.TourMs);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("fin");
		// fermeture de la fenêtre
		//fenetre.dispose();
	}

}
