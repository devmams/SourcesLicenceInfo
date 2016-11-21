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
			planetesInoccupee.add(new Planetes());
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
			// TODO : à compléter
			
			
			// raffraîchissement de la grille
			// TODO : à modifier 
			/////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////
			// CODE FACTICE POUR ILLUSTRER UNE UTILISATION POSSIBLE DE L'AFFICHAGE.
			// PLANETES ET VAISSEAUX SONT ICI REPRESENTÉS PAR DES TABLEAUX
			// D'ENTIERS INDIQUANT LES DONNÉES PERTINENTES POUR LEUR AFFICHAGE.
			// NOTE : AUCUNE VÉRIFICATION DE SUPERPOSITION N'EST FAITE
			//Random rand = new Random();
			ArrayList<int[]> listePlanetes = new ArrayList<int[]>();
			ArrayList<int[]> listeVaisseaux = new ArrayList<int[]>();
			
			for (Espece e : especes) {
				for (int i=0; i<e.getEmpire().getPlanetes().size(); i++) {
					int[] p = new int[6];
					p[0] = e.getEmpire().getPlanetes().get(i).getAbscisse(); // abscisse
					p[1] = e.getEmpire().getPlanetes().get(i).getOrdonnee(); // ordonnée
					p[2] = e.getEmpire().getPlanetes().get(i).getTaillePlante(); // taille
					p[3] = e.getCouleur().getRed(); // composant rouge
					p[4] = e.getCouleur().getGreen(); // composante vert
					p[5] = e.getCouleur().getBlue(); // composante bleu
					listePlanetes.add(p);
					//System.out.println("pop : "+ e.getEmpire().getPlanetes().get(i).getPopulaltion());
					//e.getEmpire().getPlanetes().get(i).reproduction();
				}	
				for (int j=0; j<e.getEmpire().getVaisseaux().size(); j++) {
					int[] v = new int[7];
					v[0] = e.getEmpire().getVaisseaux().get(j).getAbscisse(); // abscisse
					v[1] = e.getEmpire().getVaisseaux().get(j).getOrdonnee(); // ordonnée
					v[2] = e.getEmpire().getVaisseaux().get(j).getResistance(); //résistance
					v[3] = (int)e.getEmpire().getVaisseaux().get(j).getTypeDeplacement(); //typeDeplacement
					v[4] = e.getCouleur().getRed(); // composant rouge
					v[5] = e.getCouleur().getGreen(); // composante vert
					v[6] = e.getCouleur().getBlue(); // composante bleu
					listeVaisseaux.add(v);
					e.getEmpire().getVaisseaux().get(j).deplacement();
					e.getEmpire().autoDestruction();
					//System.out.println("portee : "+ e.getEmpire().getVaisseaux().get(j).getPropulsion().getPortee());
				}
			}
			
			//ajout des planètes inoccupées
			for(int i=0 ; i<Constantes.NbPlanetesInocupee; i++){
				int[] pI = new int[6];
				pI[0] = planetesInoccupee.get(i).getAbscisse(); // abscisse
				pI[1] = planetesInoccupee.get(i).getOrdonnee(); // ordonnee
				pI[2] = planetesInoccupee.get(i).getTaillePlante(); // taille
				pI[3] = Color.white.getRed(); // composant rouge
				pI[4] = Color.white.getGreen(); // composante vert
				pI[5] = Color.white.getBlue(); // composante bleu
				listePlanetes.add(pI);
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
