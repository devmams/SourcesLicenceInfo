import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class Vaisseaux extends Entite {
	
	private static final String Planetes = null;
	private int resistance;
	private Propulsion propulsion;
	private char typeDeplacement;
	private int integrite;

	public Vaisseaux(Planetes p,Color c){
		super(p,c);
		Random rand = new Random();
		resistance = Math.abs(rand.nextInt())%(Constantes.VaisseauResistanceMax-Constantes.VaisseauResistanceMin+1) + Constantes.VaisseauResistanceMin; // résistance
		integrite = rand.nextInt(resistance+1); // résistance
		propulsion = new Propulsion();
		typeDeplacement = typeDeplacement();
	} 
	
	public void setIntegrite(int nouvelleIntegrite){
		integrite = nouvelleIntegrite;
	}
	
	public int getIntegrite() {
		return integrite;
	}
	
	public void nouvelleIntegrite(int t , int p){
		integrite = min (resistance, integrite + (p * t));
	}
	
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
	
	public char getTypeDeplacement(){
		return typeDeplacement;
	}
	
	public int getResistance(){
		return resistance;
	}
	
	public Propulsion getPropulsion(){
		return propulsion;
	}
	
	public boolean verifCarburant(){
		boolean res = false;
		if(getPropulsion().getCarburant() > 0){
			res = true;
		}
		return res;
	}
	
	private void sensHaut(){
		int ancien = ord;
		int tmp = ord - getPropulsion().positionAtteinte();
		tmp = recadrerOrdonnee(tmp);
		if(!occupee(abs, tmp)){
			ord = tmp;
			int anciennePos = Constantes.Largeur*ancien + abs;
			int nouvellePos = Constantes.Largeur*ord + abs;
			modifPositionEntite(anciennePos, nouvellePos);
		}
	}
	
	private void sensBas(){
		int ancien = ord;
		int tmp = ord + getPropulsion().positionAtteinte();
		tmp = recadrerOrdonnee(tmp);
		if(!occupee(abs, tmp)){
			ord = tmp;
			int anciennePos = Constantes.Largeur*ancien + abs;
			int nouvellePos = Constantes.Largeur*ord + abs;
			modifPositionEntite(anciennePos, nouvellePos);
		}
	}
	
	private void sensGauche(){
		int ancien = abs;
		int tmp = abs - getPropulsion().positionAtteinte();
		tmp = recadrerAbscisse(tmp);
		if(!occupee(tmp, ord)){
			abs = tmp;
			int anciennePos = Constantes.Largeur*ord + ancien;
			int nouvellePos = Constantes.Largeur*ord + abs;
			modifPositionEntite(anciennePos, nouvellePos);
		}
	}
	
	private void sensDroit(){
		int ancien = abs;
		int tmp = abs + getPropulsion().positionAtteinte();
		tmp = recadrerAbscisse(tmp);
		if(!occupee(tmp, ord)){
			abs = tmp;
			int anciennePos = Constantes.Largeur*ord + ancien;
			int nouvellePos = Constantes.Largeur*ord + abs;
			modifPositionEntite(anciennePos, nouvellePos);
		}
	}
	
	private void sensDiagonale1(){
		int ancien1 = abs;
		int ancien2 = ord;
		int tmp1 = getPropulsion().getPortee();
		int x = abs + tmp1;
		int y = ord - tmp1;
		x = recadrerAbscisse(x);
		y = recadrerOrdonnee(y);
		if(!occupee(x, y)){
			abs = x;
			ord = y;
			int anciennePos = Constantes.Largeur*ancien2 + ancien1;
			int nouvellePos = Constantes.Largeur*ord + abs;
			modifPositionEntite(anciennePos, nouvellePos);
		}
	}
	
	private void sensDiagonale2(){
		int ancien1 = abs;
		int ancien2 = ord;
		int tmp1 = getPropulsion().getPortee();
		int x = abs + tmp1;
		int y = ord + tmp1;
		x = recadrerAbscisse(x);
		y = recadrerOrdonnee(y);
		if(!occupee(x, y)){
			abs = x;
			ord = y;
			int anciennePos = Constantes.Largeur*ancien2 + ancien1;
			int nouvellePos = Constantes.Largeur*ord + abs;
			modifPositionEntite(anciennePos, nouvellePos);
		}
	}
	
	private void sensDiagonale3(){
		int ancien1 = abs;
		int ancien2 = ord;
		int tmp1 = getPropulsion().getPortee();
		int x = abs - tmp1;
		int y = ord + tmp1;
		x = recadrerAbscisse(x);
		y = recadrerOrdonnee(y);
		if(!occupee(x, y)){
			abs = x;
			ord = y;
			int anciennePos = Constantes.Largeur*ancien2 + ancien1;
			int nouvellePos = Constantes.Largeur*ord + abs;
			modifPositionEntite(anciennePos, nouvellePos);
		}
	}
	
	private void sensDiagonale4(){
		int ancien1 = abs;
		int ancien2 = ord;
		int tmp1 = getPropulsion().getPortee();
		int x = abs - tmp1;
		int y = ord - tmp1;
		x = recadrerAbscisse(x);
		y = recadrerOrdonnee(y);
		if(!occupee(x, y)){
			abs = x;
			ord = y;
			int anciennePos = Constantes.Largeur*ancien2 + ancien1;
			int nouvellePos = Constantes.Largeur*ord + abs;
			modifPositionEntite(anciennePos, nouvellePos);
		}
	}
	
	private void lineaire(){
		Random rd = new Random();
		int sens = rd.nextInt(4);//sens de placement
		switch(sens){
		case 0 :sensHaut();break;
		case 1 :sensDroit();break;
		case 2 :sensBas();break;
		case 3 :sensGauche();break;
		}
	}
	
	private void diagonale(){
		Random rd = new Random();
		int sens = rd.nextInt(4);//sens de placement
		switch(sens){
		case 0 :sensDiagonale1();break;
		case 1 :sensDiagonale2();break;
		case 2 :sensDiagonale3();break;
		case 3 :sensDiagonale4();break;
		}
	}
	
	private void omnidirectionnelle(){
		Random rd = new Random();
		int sens = rd.nextInt(8);//sens de placement
		switch(sens){
		case 0 :sensHaut();break;
		case 1 :sensDiagonale1();break;
		case 2 :sensDroit();break;
		case 3 :sensDiagonale2();break;
		case 4 :sensBas();break;
		case 5 :sensDiagonale3();break;
		case 6 :sensGauche();break;
		case 7 :sensDiagonale4();break;
		}
	}
	
	private void deplace(){
		switch(typeDeplacement){
		case '+' :lineaire();break;	
		case 'x' :diagonale();break;
		case '*' :omnidirectionnelle();break;
		}
	}
	
	public void deplacement(){
		if(verifCarburant()){
			//getPropulsion().diminuerCarburant();
			deplace();
		}
	}
	
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
	
	public void infligeDegat(){
		Random rand = new Random();
		int degat = rand.nextInt(3) + 1;
		integrite -= degat;
	}
	
	public void interactionVaisseaux(ArrayList<Espece> esp,Empire e , PlanetesInoccupees pI){	
		int cible = caseCible();
		if(occupee(retrouverAbs(cible),retrouverOrd(cible))){ //verifie si la case ciblée est occupée
			Entite entiteCible = getListeEntites(cible);
			Color colorEntiteCible = entiteCible.getColorEntite();
			char typeEntite = entiteCible.getTypeEntite();
			
			if(!colorEntiteCible.equals(getColorEntite()) && !colorEntiteCible.equals(Color.white)){//verifie si la cible est ennemie
				entiteCible.infligeDegat();
				if(typeEntite == 'p'){
					for(int i=0 ;i<esp.size();i++){
						if(colorEntiteCible == esp.get(i).getCouleur()){
							esp.get(i).getEmpire().supprPlanete((Planetes) entiteCible);
						}
					}
					entiteCible.modifCouleur(Color.white);
					pI.ajoutPlaneteInoccupee((Planetes) entiteCible);
				}
			}
			else if(colorEntiteCible.equals(getColorEntite()) && typeEntite == 'p'){
				propulsion.rechargerCarburant();
			}
			else if(typeEntite == 'p' && colorEntiteCible.equals(Color.white)){
				entiteCible.modifCouleur(getColorEntite());
				pI.supprPlaneteInoccupee((Planetes) entiteCible);
				e.ajouterPlanete((Planetes) entiteCible);
				e.supprVaisseaux((Vaisseaux) getListeEntites(getNumeroEntite()));
				supprListeEntite(getListeEntites(getNumeroEntite()));
			}
		}
	}
}







