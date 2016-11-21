import java.awt.Color;
import java.util.Random;


public class Vaisseaux extends Entite {
	
	private int resistance;
	private Propulsion propulsion;
	
	public Vaisseaux(Planetes p){
		super(p);
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
		int tmp = ord - getPropulsion().positionAtteinte();
		tmp = recadrerOrdonnee(tmp);
		if(!occupee(abs, tmp)){
			ord = tmp;
		}
	}
	
	private void sensBas(){
		int tmp = ord + getPropulsion().positionAtteinte();
		tmp = recadrerOrdonnee(tmp);
		if(!occupee(abs, tmp)){
			ord = tmp;
		}
	}
	
	private void sensGauche(){
		int tmp = abs - getPropulsion().positionAtteinte();
		tmp = recadrerAbscisse(tmp);
		if(!occupee(tmp, ord)){
			abs = tmp;
		}
	}
	
	private void sensDroit(){
		int tmp = abs + getPropulsion().positionAtteinte();
		tmp = recadrerAbscisse(tmp);
		if(!occupee(tmp, ord)){
			abs = tmp;
		}
	}
	
	private void sensDiagonale1(){
		int tmp1 = getPropulsion().getPortee();
		int x = abs + tmp1;
		int y = ord - tmp1;
		x = recadrerAbscisse(x);
		y = recadrerOrdonnee(y);
		if(!occupee(x, y)){
			abs = x;
			ord = y;
		}
	}
	
	private void sensDiagonale2(){
		int tmp1 = getPropulsion().getPortee();
		int x = abs + tmp1;
		int y = ord + tmp1;
		x = recadrerAbscisse(x);
		y = recadrerOrdonnee(y);
		if(!occupee(x, y)){
			abs = x;
			ord = y;
		}
	}
	
	private void sensDiagonale3(){
		int tmp1 = getPropulsion().getPortee();
		int x = abs - tmp1;
		int y = ord + tmp1;
		x = recadrerAbscisse(x);
		y = recadrerOrdonnee(y);
		if(!occupee(x, y)){
			abs = x;
			ord = y;
		}
	}
	
	private void sensDiagonale4(){
		int tmp1 = getPropulsion().getPortee();
		int x = abs - tmp1;
		int y = ord - tmp1;
		x = recadrerAbscisse(x);
		y = recadrerOrdonnee(y);
		if(!occupee(x, y)){
			abs = x;
			ord = y;
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
	
	private void omnidirectinnelle(){
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
			omnidirectinnelle();
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
