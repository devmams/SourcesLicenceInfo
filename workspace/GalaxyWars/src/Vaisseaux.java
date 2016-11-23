import java.awt.Color;
import java.util.Random;


public class Vaisseaux extends Entite {
	
	private int resistance;
	private Propulsion propulsion;
	
	public Vaisseaux(Planetes p,Color c){
		super(p,c);
		Random rand = new Random();
		resistance = Math.abs(rand.nextInt())%(Constantes.VaisseauResistanceMax-Constantes.VaisseauResistanceMin+1) + Constantes.VaisseauResistanceMin; // résistance
		propulsion = new Propulsion();
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
			viderCase(Constantes.Largeur*ancien + abs);
			ajoutCase(getNumeroEntite());
		}
	}
	
	private void sensBas(){
		int ancien = ord;
		int tmp = ord + getPropulsion().positionAtteinte();
		tmp = recadrerOrdonnee(tmp);
		if(!occupee(abs, tmp)){
			ord = tmp;
			viderCase(Constantes.Largeur*ancien + abs);
			ajoutCase(getNumeroEntite());
		}
	}
	
	private void sensGauche(){
		int ancien = abs;
		int tmp = abs - getPropulsion().positionAtteinte();
		tmp = recadrerAbscisse(tmp);
		if(!occupee(tmp, ord)){
			abs = tmp;
			viderCase(Constantes.Largeur*ord + ancien);
			ajoutCase(getNumeroEntite());
		}
	}
	
	private void sensDroit(){
		int ancien = abs;
		int tmp = abs + getPropulsion().positionAtteinte();
		tmp = recadrerAbscisse(tmp);
		if(!occupee(tmp, ord)){
			abs = tmp;
			viderCase(Constantes.Largeur*ord + ancien);
			ajoutCase(getNumeroEntite());
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
			viderCase(Constantes.Largeur*ancien2 + ancien1);
			ajoutCase(getNumeroEntite());
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
			viderCase(Constantes.Largeur*ancien2 + ancien1);
			ajoutCase(getNumeroEntite());
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
			viderCase(Constantes.Largeur*ancien2 + ancien1);
			ajoutCase(getNumeroEntite());
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
			viderCase(Constantes.Largeur*ancien2 + ancien1);
			ajoutCase(getNumeroEntite());
		}
	}
	
	private void lineaire(){
		Random rd = new Random();
		int sens = rd.nextInt(4);//sens de placement
		switch(sens){
		case 0 :
			sensHaut();
		break;
		case 1 :
			sensDroit();
		break;
		case 2 :
			sensBas();
		break;
		case 3 :
			sensGauche();
		break;
		}
	}
	
	private void diagonale(){
		Random rd = new Random();
		int sens = rd.nextInt(4);//sens de placement
		switch(sens){
		case 0 :
			sensDiagonale1();
		break;
		case 1 :
			sensDiagonale2();
		break;
		case 2 :
			sensDiagonale3();
		break;
		case 3 :
			sensDiagonale4();
		break;
		}
	}
	
	private void omnidirectionnelle(){
		Random rd = new Random();
		int sens = rd.nextInt(8);//sens de placement
		switch(sens){
		case 0 :
			sensHaut();
		break;
		case 1 :
			sensDiagonale1();
		break;
		case 2 :
			sensDroit();
		break;
		case 3 :
			sensDiagonale2();
		break;
		case 4 :
			sensBas();
		break;
		case 5 :
			sensDiagonale3();
		break;
		case 6 :
			sensGauche();
		break;
		case 7 :
			sensDiagonale4();
		break;
		}
	}
	
	private void deplace(){
		switch(typeDeplacement){
		case '+' :
			lineaire();
		break;	
		case 'x' :
			diagonale();
		break;
		case '*' :
			omnidirectionnelle();
		break;
		}
	}
	
	public void deplacement(){
			if(verifCarburant()){
				getPropulsion().setCarburant();
				deplace();
			}
	}
	
}
