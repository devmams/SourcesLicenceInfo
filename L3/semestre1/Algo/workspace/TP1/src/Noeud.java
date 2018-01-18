
public class Noeud {

	private Noeud g;
	private Noeud d;
	private char elt;
	
	private boolean estNul;
	
	public Noeud() {
		estNul = true;
	}
	
	public Noeud(Noeud g,Noeud d,char elt){
		this.g = g;
		this.d = d;
		this.elt = elt;
		estNul = false;
	}
	
	public boolean getEstNul(){
		return estNul;
	}
	
	public Noeud gauche(){
		return g;
	}
	
	public Noeud droit(){
		return d;
	}
	
	public char getElt(){
		return elt;
	}
}