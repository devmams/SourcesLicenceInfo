/**
 * @brief Classe permettant de créer un vaisseaux.
 * 
 * @author Glenn PLOUHINEC / Mamadou DIALLO
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class Vaisseaux extends Entite {
	// résistance du vaisseaux.
	private int resistance;
	// propulsion du vaisseaux.
	private Propulsion propulsion;
	// type de deplacement du vaisseaux.
	private char typeDeplacement;
	// integrité du vaisseaux.
	private double integrite;
	// la liste d'equipement du vaisseaux entre (1 et 4).
	private String[] listeEquipement;
	
	/**
	 * Construit un vaisseaux.
	 * @param la planete du vaisseaux , sa couleur et sa galaxie.
	 */
	public Vaisseaux(Planetes p,Color c,Galaxie galaxie){
		super(p,c,galaxie);
		Random rand = new Random();
		resistance = rand.nextInt(Constantes.VaisseauResistanceMax-Constantes.VaisseauResistanceMin+1) + Constantes.VaisseauResistanceMin;
		integrite = rand.nextInt(resistance+1); // résistance
		propulsion = new Propulsion();
		typeDeplacement = typeDeplacement();
		listeEquipement = new String[rand.nextInt(4) + 1];
		creerEquipements();
	} 
	
	/**
	 * crée la liste d'équipements.
	 */
	private void creerEquipements(){
		if(listeEquipement.length == 1)
			listeEquipement[0] = "s";
		else if(listeEquipement.length == 2){
			listeEquipement[0] = "s";
			listeEquipement[1] = "t";
		}
		else if(listeEquipement.length == 3){
			listeEquipement[0] = "s";
			listeEquipement[1] = "t";
			listeEquipement[2] = "c";
		}
		else if(listeEquipement.length == 4){
			listeEquipement[0] = "s";
			listeEquipement[1] = "t";
			listeEquipement[2] = "c";
			listeEquipement[3] = "p";
		}
	}
	
	/**
	 * permet d'avancer les équipements chaque équipement prend la place de l'équipement qui le precède.
	 *  le prémier devient le dernier.
	 */
	public void avancerEquipement(){
		if(listeEquipement.length > 1){
			String tmp = listeEquipement[0];
			for(int i=0 ; i<listeEquipement.length-1 ;i++){
				listeEquipement[i] = listeEquipement[i+1];
			}
			listeEquipement[listeEquipement.length-1] = tmp;
		}
	}
	
	/**
	 * Accesseur.
	 * @return le premier equipement c-a-d l'équipement actuellement utilisé.
	 */
	public String equipementCourant(){
		return listeEquipement[0];
	}
	
	/**
	 * Mutateur.
	 * affecte une nouvelle intégrité au vaisseau.
	 * @param la nouvelle integrité.
	 */
	public void setIntegrite(int nouvelleIntegrite){
		integrite = nouvelleIntegrite;
	}
	
	/**
	 * Accesseur.
	 * @return l'integrité du vaisseaux.
	 */
	public double getIntegrite() {
		return integrite;
	}
	
	/**
	 * modifie l'intégrité du vaisseau pendant la construction du vaisseau par une planète.
	 * @param le taux de productivité de l'espèce et la population de la planete.
	 */
	public void nouvelleIntegrite(double tauxProductivite , double pop){
		integrite = min (resistance, integrite + (pop * tauxProductivite));
	}
	
	/**
	 * fonction créant le type de deplacement du vaisseaux.
	 */
	public char typeDeplacement(){
		char s = '+';
		Random r = new Random();
		int tD = r.nextInt(3); //abscisse
		switch(tD){
		case 0 : s = '+';break;
		case 1 : s = 'x';break;
		case 2 : s = '*';break;
		}
		return s;
	}
	
	/**
	 * Accesseur.
	 * @return le type de deplacement du vaisseaux.
	 */
	public char getTypeDeplacement(){
		return typeDeplacement;
	}
	
	/**
	 * Accesseur.
	 * @return la résistance du vaisseaux.
	 */
	public int getResistance(){
		return resistance;
	}
	
	/**
	 * Accesseur.
	 * @return la propulsion du vaisseaux.
	 */
	public Propulsion getPropulsion(){
		return propulsion;
	}
	
	/**
	 * méthode vérifiant le carburant du vaisseaux .
	 * 	renvoie vrai si le vaisseaux dispose toujours de carburant.
	 */
	public boolean verifCarburant(){
		boolean res = false;
		if(getPropulsion().getCarburant() > 0){
			res = true;
		}
		return res;
	}
	
	/**
	 * déplace le vaisseaux vers le haut.
	 * @param la galaxie contenat le vaisseaux
	 */
	private void sensHaut(Galaxie galaxie){
		int ancien = ord;
		int tmp = ord - getPropulsion().positionAtteinte();
		tmp = recadrerOrdonnee(tmp);
		if(!galaxie.occupee(abs, tmp)){
			ord = tmp;
			galaxie.modifPositionEntite(abs, ancien, abs, ord);
		}
	}
	
	/**
	 * déplace le vaisseaux vers le bas.
	 * @param la galaxie contenat le vaisseaux
	 */
	private void sensBas(Galaxie galaxie){
		int ancien = ord;
		int tmp = ord + getPropulsion().positionAtteinte();
		tmp = recadrerOrdonnee(tmp);
		if(!galaxie.occupee(abs, tmp)){
			ord = tmp;
			galaxie.modifPositionEntite(abs, ancien, abs, ord);
		}
	}
	
	/**
	 * déplace le vaisseaux vers la gauche.
	 * @param la galaxie contenat le vaisseaux
	 */
	private void sensGauche(Galaxie galaxie){
		int ancien = abs;
		int tmp = abs - getPropulsion().positionAtteinte();
		tmp = recadrerAbscisse(tmp);
		if(!galaxie.occupee(tmp, ord)){
			abs = tmp;
			galaxie.modifPositionEntite(ancien, ord, abs, ord);
		}
	}
	
	/**
	 * déplace le vaisseaux vers la droite.
	 * @param la galaxie contenat le vaisseaux
	 */
	private void sensDroit(Galaxie galaxie){
		int ancien = abs;
		int tmp = abs + getPropulsion().positionAtteinte();
		tmp = recadrerAbscisse(tmp);
		if(!galaxie.occupee(tmp, ord)){
			abs = tmp;
			galaxie.modifPositionEntite(ancien, ord, abs, ord);
		}
	}
	
	/**
	 * déplace le vaisseaux en diagonale en haut à droite.
	 * @param la galaxie contenat le vaisseaux
	 */
	private void sensDiagonaleHD(Galaxie galaxie){
		int ancien1 = abs;
		int ancien2 = ord;
		int tmp1 = getPropulsion().getPortee();
		int x = abs + tmp1;
		int y = ord - tmp1;
		x = recadrerAbscisse(x);
		y = recadrerOrdonnee(y);
		if(!galaxie.occupee(x, y)){
			abs = x;
			ord = y;
			galaxie.modifPositionEntite(ancien1, ancien2, abs, ord);
		}
	}
	
	/**
	 * déplace le vaisseaux en diagonale en bas à droite.
	 * @param la galaxie contenat le vaisseaux
	 */
	private void sensDiagonaleBD(Galaxie galaxie){
		int ancien1 = abs;
		int ancien2 = ord;
		int tmp1 = getPropulsion().getPortee();
		int x = abs + tmp1;
		int y = ord + tmp1;
		x = recadrerAbscisse(x);
		y = recadrerOrdonnee(y);
		if(!galaxie.occupee(x, y)){
			abs = x;
			ord = y;
			galaxie.modifPositionEntite(ancien1, ancien2, abs, ord);
		}
	}
	
	/**
	 * déplace le vaisseaux en diagonale en bas à gauche.
	 * @param la galaxie contenat le vaisseaux
	 */
	private void sensDiagonaleBG(Galaxie galaxie){
		int ancien1 = abs;
		int ancien2 = ord;
		int tmp1 = getPropulsion().getPortee();
		int x = abs - tmp1;
		int y = ord + tmp1;
		x = recadrerAbscisse(x);
		y = recadrerOrdonnee(y);
		if(!galaxie.occupee(x, y)){
			abs = x;
			ord = y;
			galaxie.modifPositionEntite(ancien1, ancien2, abs, ord);
		}
	}
	
	/**
	 * déplace le vaisseaux en diagonale en haut à gauche.
	 * @param la galaxie contenat le vaisseaux
	 */
	private void sensDiagonaleHG(Galaxie galaxie){
		int ancien1 = abs;
		int ancien2 = ord;
		int tmp1 = getPropulsion().getPortee();
		int x = abs - tmp1;
		int y = ord - tmp1;
		x = recadrerAbscisse(x);
		y = recadrerOrdonnee(y);
		if(!galaxie.occupee(x, y)){
			abs = x;
			ord = y;
			galaxie.modifPositionEntite(ancien1, ancien2, abs, ord);
		}
	}
	
	/**
	 * détermine aléatoirement la direction et le sens linéaire que va prendre le vaisseau.
	 * @param la galaxie contenat le vaisseaux
	 */
	private void lineaire(Galaxie galaxie){
		Random rd = new Random();
		int sens = rd.nextInt(4);//sens de placement
		switch(sens){
		case 0 :sensHaut(galaxie);break;
		case 1 :sensDroit(galaxie);break;
		case 2 :sensBas(galaxie);break;
		case 3 :sensGauche(galaxie);break;
		}
	}
	
	/**
	 * détermine aléatoirement la direction et le sens diagonale que va prendre le vaisseau.
	 * @param la galaxie contenat le vaisseaux
	 */
	private void diagonale(Galaxie galaxie){
		Random rd = new Random();
		int sens = rd.nextInt(4);//sens de placement
		switch(sens){
		case 0 :sensDiagonaleHD(galaxie);break;
		case 1 :sensDiagonaleBD(galaxie);break;
		case 2 :sensDiagonaleBG(galaxie);break;
		case 3 :sensDiagonaleHG(galaxie);break;
		}
	}
	
	/**
	 * détermine aléatoirement la direction et le sens omnidirectionnel que va prendre le vaisseau.
	 * @param la galaxie contenat le vaisseaux
	 */
	private void omnidirectionnelle(Galaxie galaxie){
		Random rd = new Random();
		int sens = rd.nextInt(8);//sens de placement
		switch(sens){
		case 0 :sensHaut(galaxie);break;
		case 1 :sensDiagonaleHD(galaxie);break;
		case 2 :sensDroit(galaxie);break;
		case 3 :sensDiagonaleBD(galaxie);break;
		case 4 :sensBas(galaxie);break;
		case 5 :sensDiagonaleBG(galaxie);break;
		case 6 :sensGauche(galaxie);break;
		case 7 :sensDiagonaleHG(galaxie);break;
		}
	}
	
	/**
	 * choisis la méthode de deplacement qui convient en fonction du type de deplacement. 
	 * @param la galaxie contenat le vaisseaux
	 */
	private void deplace(Galaxie galaxie){
		switch(typeDeplacement){
		case '+' :lineaire(galaxie);break;	
		case 'x' :diagonale(galaxie);break;
		case '*' :omnidirectionnelle(galaxie);break;
		}
	}
	
	/**
	 * deplace le vaisseaux s'il dispose toujours de carburant. 
	 * @param espece du vaisseaux et la galaxie contenat le vaisseaux
	 */
	public void deplacement(ArrayList<Espece> esp,Galaxie galaxie){
		if(verifCarburant()){
			getPropulsion().diminuerCarburant();
			deplace(galaxie);
		}
		autoDestruction(esp,galaxie);
	}
	
	/**
	 * choisit aléatoirement une case adjacente au vaisseau 
	 * 	qui fera office de la cible. 
	 */
	public int caseCible(){
		int caseCible = 0;
		int posAbs = retrouverAbs(getNumeroEntite());
		int posOrd = retrouverOrd(getNumeroEntite());
		Random rd = new Random();
		int haz = rd.nextInt(8);//case cible de placement
		switch(haz){
		case 0 :posAbs = recadrerAbscisse(posAbs-1);posOrd = recadrerOrdonnee(posOrd-1);caseCible = Constantes.Largeur*posOrd + posAbs;break;
		case 1 :posAbs = recadrerAbscisse(posAbs);posOrd = recadrerOrdonnee(posOrd-1);caseCible = Constantes.Largeur*posOrd + posAbs;break;
		case 2 :posAbs = recadrerAbscisse(posAbs+1);posOrd = recadrerOrdonnee(posOrd-1);caseCible = Constantes.Largeur*posOrd + posAbs;break;
		case 3 :posAbs = recadrerAbscisse(posAbs+1);posOrd = recadrerOrdonnee(posOrd);caseCible = Constantes.Largeur*posOrd + posAbs;break;
		case 4 :posAbs = recadrerAbscisse(posAbs+1);posOrd = recadrerOrdonnee(posOrd+1);caseCible = Constantes.Largeur*posOrd + posAbs;break;
		case 5 :posAbs = recadrerAbscisse(posAbs);posOrd = recadrerOrdonnee(posOrd+1);caseCible = Constantes.Largeur*posOrd + posAbs;break;
		case 6 :posAbs = recadrerAbscisse(posAbs-1);posOrd = recadrerOrdonnee(posOrd+1);caseCible = Constantes.Largeur*posOrd + posAbs;break;
		case 7 :posAbs = recadrerAbscisse(posAbs-1);posOrd = recadrerOrdonnee(posOrd);caseCible = Constantes.Largeur*posOrd + posAbs;break;
		}
		return caseCible;
	}	
	
	/**
	 * inflige le degat au vaisseaux cible.
	 */
	public void infligeDegat(){
		Random rand = new Random();
		int degat = rand.nextInt(3) + 1;
		integrite -= degat;
	}
	
	/**
	 * detruit le vaisseaux s'il n'a plus de carburant et d'integrité.
	 * @param l'espece du vaisseaux , et la galaxie contenant le vaisseaux.
	 */
	public void autoDestruction(ArrayList<Espece> esp,Galaxie galaxie){
		if(!verifCarburant() || getIntegrite() <= 0){
			for(int i=0 ;i<esp.size();i++){
				if(getColorEntite() == esp.get(i).getCouleur()){
					esp.get(i).getEmpire().supprVaisseaux((Vaisseaux)galaxie.getEntite(getNumeroEntite()),galaxie);
					break;
				}
			}
		}
	
	}
	
	/**
	 * permet l'interaction entre le vaisseaux et sa cible.
	 * @param l'espece du vaisseaux ,l'emire du vaisseaux ,
	 * 		les planete inoccupées et la galaxie contenant le vaisseaux.
	 */
	public void interactionVaisseaux(ArrayList<Espece> esp,Empire e , PlanetesInoccupees pI,Galaxie galaxie){	
		int cible = caseCible();
		//if(occupee(retrouverAbs(cible),retrouverOrd(cible))){ //verifie si la case ciblée est occupée
		if(galaxie.occupee(retrouverAbs(cible), retrouverOrd(cible))){
			Entite entiteCible = galaxie.getEntite(cible);
			Color colorEntiteCible = entiteCible.getColorEntite();
			String typeEntite = entiteCible.getTypeEntite();
						
			if(!colorEntiteCible.equals(getColorEntite()) && !colorEntiteCible.equals(Color.white)){//verifie si la cible est ennemie(planete ou vaisseaux
				if(typeEntite == "vaisseaux"){//verifie si la cible est un vaisseaux
					if(equipementCourant() == "s" || equipementCourant() == "p"){
						entiteCible.infligeDegat();
						((Vaisseaux) entiteCible).autoDestruction(esp,galaxie);
					}
				}
				else if(typeEntite == "planete"){//verifie si la cible est une planete
					if(equipementCourant() == "t" || equipementCourant() == "p"){
						entiteCible.infligeDegat();
						if(typeEntite == "planete" && !((Planetes) entiteCible).populationNull()){
							for(int i=0 ;i<esp.size();i++){
								if(colorEntiteCible == esp.get(i).getCouleur()){
									esp.get(i).getEmpire().supprPlanete((Planetes) entiteCible);
									break;
								}
							}
							entiteCible.modifCouleur(Color.white);
							pI.ajoutPlaneteInoccupee((Planetes) entiteCible);
						}
					}
				}
			}
			else if(colorEntiteCible.equals(getColorEntite()) && typeEntite == "planete"){//vérifie si la planete est alliée
				propulsion.rechargerCarburant();
			}
			else if(typeEntite == "planete" && colorEntiteCible.equals(Color.white)){//verifie si la planète est inoccupée
				if(equipementCourant() == "c" || equipementCourant() == "p"){
					pI.supprPlaneteInoccupee((Planetes) entiteCible);
					entiteCible.modifCouleur(getColorEntite());
					((Planetes) entiteCible).modifPopulation(getIntegrite());
					e.ajouterPlanete((Planetes) entiteCible,galaxie);
					e.supprVaisseaux((Vaisseaux) galaxie.getEntite(getNumeroEntite()),galaxie);
				}
			}
		}
		avancerEquipement();//l'equipement courant devient l'equipement qui precede.
	}
}







