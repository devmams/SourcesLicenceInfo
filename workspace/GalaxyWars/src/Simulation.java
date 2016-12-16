/**
 * @brief Classe gérant la simulation de conquête galactique
 * 
 * @author ...
 */

import java.awt.Color;
import java.util.ArrayList;

public class Simulation {

	// Couleur vainqueur
	private static String couleurMot;

	
	// la galaxie du jeu.
	private static Galaxie galaxie = new Galaxie();
	/**
	 * 
	 * @return Vrai ssi la partie est terminée
	 */
	public static Boolean victoire() {
		boolean victoire = true;
		Color colorEntiteInit = null;
		for(int x=0;x<galaxie.getGalaxieLargeur() ;x++){
			for(int y=0 ;y<galaxie.getGalaxieHauteur(); y++){
				if(galaxie.occupee(x, y)){
					colorEntiteInit = galaxie.getEntite(galaxie.getGalaxieLargeur()*y +x).getColorEntite();
					break;
				}
			}
		}
		
		for(int x=0 ;x<galaxie.getGalaxieLargeur() ;x++){
			for(int y=0 ;y<galaxie.getGalaxieHauteur() ;y++){
				int id = galaxie.getGalaxieLargeur()*y +x;
				if(galaxie.getEntite(id) != null){
					if(!colorEntiteInit.equals(galaxie.getEntite(id).getColorEntite())){
						victoire = false;
						break;			
					}
				}
			}
		}
		if(victoire){
			couleurMot(colorEntiteInit);
		}
		else{
			couleurMot(Color.white);
		}
		return victoire;
	}
	
	/**
	 * Determine l'espèce vainqueur .
	 */
	public static void couleurMot(Color c){
		if(c.equals(Color.red))
			couleurMot = "ROUGE";
		else if (c.equals(Color.green))
			couleurMot = "VERT";
		else if(c.equals(Color.blue))
			couleurMot = "BLEU";
		else if(c.equals(Color.orange))
			couleurMot = "ORANGE";
		else if(c.equals(Color.white))
			couleurMot = "Aucun";
	}
	
	/**
	 * Affiche l'espèce vainqueur.
	 */
	public static void afficheVainqueur(){
		System.out.println(couleurMot+" Vainqueur !");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// création du panneau d'affichage
		Affichage panneau = new Affichage();
		
		// crée une nouvelle fenetre.
		new Fenetre(panneau);
		
		// crée la liste des espèces
		ArrayList<Espece> especes = new ArrayList<Espece>();
	
		//Rajout des nouvelles especes crées , 4 espèces au totales
		for(int i=0 ; i<4 ;i++){
			especes.add(new Espece(galaxie));
		}
		
		//création de planètes inoccupées
		PlanetesInoccupees pI = new PlanetesInoccupees(galaxie);
		
		// boucle de simulation
		int tour = 0;
		while (!victoire() && tour<Constantes.TourMax) {
			// décompte des tours
			tour += 1;
			
			// Affichage d'un bref rapport textuel
			System.out.println("Tour " + tour + " :");
			
			
			// Exécution des étapes du tour courant
			for (Espece e : especes) {
				Color c = e.getCouleur();
				couleurMot(c);
				System.out.println("Populations de l'espèce "+couleurMot+" : "+e.getEmpire().getPopulationTotale());
			}
			System.out.println("Nombres d'entiés : "+galaxie.getNbEntites());
			System.out.println("Nombre de planètes occupées : "+especes.get(0).getEmpire().getNbPlanetesOccupees());
			System.out.println("Nombre de planètes inoccupées : "+pI.getNbPlanetesInoccupees());
			System.out.println("Nombre de vaisseaux : "+especes.get(0).getEmpire().getNbPlanetesOccupees());

			
			
			
			ArrayList<Planetes> listePlanetes = new ArrayList<Planetes>();
			ArrayList<Vaisseaux> listeVaisseaux = new ArrayList<Vaisseaux>();
			
			for (Espece e : especes) {
				e.getEmpire().reproduction(e.getTauxNatalite()); //reproduction de la population
				e.getEmpire().constructionVaisseaux(e.getTauxProductivite(),galaxie);
				e.getEmpire().deplacementVaisseaux(especes,galaxie);
				e.getEmpire().interaction(especes ,e.getEmpire(),pI,galaxie);
			}
			
			for (Espece e : especes) {
				for (int v=0; v<e.getEmpire().getVaisseaux().size(); v++) {
					listeVaisseaux.add(e.getEmpire().getVaisseaux().get(v));
				}
				for (int p=0; p<e.getEmpire().getPlanetes().size(); p++) {
					listePlanetes.add(e.getEmpire().getPlanetes().get(p));
				}
			}
			
			//ajout des planètes inoccupées
			for(int i=0 ;i<pI.getPlanetesInoccupees().size() ;i++){
				listePlanetes.add(pI.getPlanetesInoccupees().get(i));
			}
			
			//Raffraichissement de la Galaxie
			panneau.rafraichir(listePlanetes,listeVaisseaux);
			
			// temporisation avant prochain tour
			try {
				Thread.sleep(Constantes.TourMs);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//afficheVainqueur();
		System.out.println("fin");
		// fermeture de la fenêtre
		//fenetre.dispose();
	}

}
