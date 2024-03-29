
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
		if(!rac.gauche().getEstNul()){
			//System.out.print('(');
			afficherArbre(rac.gauche());			
		}
		if(!rac.droit().getEstNul()){
			afficherArbre(rac.droit());		
			//System.out.print(')');
		}
		System.out.print(rac.getElt());
	}
	
	/*public void niveau(Noeud rac , char c , int n){
		if(rac.getElt() == c){
			System.out.println(n);
		}
		else{
			if(!rac.gauche().getEstNul()){
				n++;
				niveau(rac.gauche(),c,n);
				niveau(rac.droit(),c,n);
			}
		}
	}*/
		
	public int niveau(Noeud rac , char c , int n){
		if(rac.getElt() == c){
			return n;
		}
		else{
			if(!rac.gauche().getEstNul()){
				n++;
				return niveau(rac.gauche(),c,n) + niveau(rac.droit(),c,n);
			}
			else{
				return 0;
			}
		}
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
