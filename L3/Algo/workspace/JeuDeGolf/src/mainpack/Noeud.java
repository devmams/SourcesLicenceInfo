package mainpack;

public class Noeud {

	private Region r;
	public Noeud[] n;

	
	public Noeud(Region r) {
		this.setR(r);
		n = new Noeud[4];
		for(int i=0;i<4;i++) {
			n[i] = null;
		}
	}

	public Region getR() {
		return r;
	}

	public void setR(Region r) {
		this.r = r;
	}
	
	
	
}
