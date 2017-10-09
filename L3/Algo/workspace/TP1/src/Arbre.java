
public class Arbre {

	private Noeud racine;
	public Arbre(Noeud r) {
		racine = r;
	}
	
	public Noeud getRacine(){
		return racine;
	}
	
	private boolean estOperateur(char c)
	{
		if(c == '+' || c == '-' || c == '*' || c == '/')
		{
			return true;
		}else{return false;}
	}
	
	public void afficherArbre(Noeud rac){
		System.out.print('(');
		Noeud pFilsG = rac.gauche().gauche();
		Noeud pFilsD = rac.droit().droit();
		if(!pFilsG.getEstNul()){
			afficherArbre(rac.gauche());			
		}
		else{
			System.out.print(rac.gauche().getElt());
		}
		System.out.print(rac.getElt());
		if(!pFilsD.getEstNul()){
			afficherArbre(rac.droit());			
		}
		else{
			System.out.print(rac.droit().getElt());
		}
		System.out.print(')');
	}
	
	int hauteur(Noeud a){
		if(a.getEstNul()){
			return -1;
		}
		else{
			int hg = hauteur(a.gauche());
			int hd = hauteur(a.droit());
			return 1 + max(hg,hd);
		}
	}
	
	int max(int a,int b){
		int res = a;
		if(b>a)
			res = b;
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
