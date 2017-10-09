
public class Analyseur {
	
	public Analyseur() {}
	
	public Arbre analyse(String exp){
		if(exp.length() != 1){
			int racine = rechercheRacine(exp);
			String expG = "", expD = "";
			expG = exp.substring(1, racine);
			expD = exp.substring(racine+1, exp.length()-1);
			Arbre ABRG = analyse(expG);
			Arbre ABRD = analyse(expD);
			Arbre A = new Arbre(new Noeud(ABRG.getRacine(),ABRD.getRacine(),exp.charAt(racine)));
			return A;
		}
		else{
			return new Arbre (new Noeud(new Noeud(),new Noeud(),exp.charAt(0)));
		}
		
	}
	
	
	private int rechercheRacine(String ch)
	{
		char c_cour;
		int p_compte=0;
		int r_ind = 0;
		int r_prio = ch.length();
		for(int i=0;i<ch.length();i++)
		{
			c_cour = ch.charAt(i);
			if(c_cour == '('){
				p_compte++;
			}else if(c_cour == ')'){
				p_compte--;
			}else if(estOperateur(c_cour) && r_prio > p_compte){
				r_prio = p_compte;
				r_ind = i;
			}
		}
		return r_ind;
	}
	
	private boolean estOperateur(char c)
	{
		if(c == '+' || c == '-' || c == '*' || c == '/')
		{
			return true;
		}else{return false;}
	}

}
