
public class Plateau {
	private int largeur = 10;
	private int hauteur = 21;
	private Cellule grille[][];
	
	public Plateau(){
		this.grille = new Cellule[largeur][hauteur];
		for(int i=0 ; i<10 ; i++){
			for(int j=0 ; j<20 ; j++){
				this.grille[i][j] = new Cellule(0,0,'n'); 
			}
			for(int k=0 ; k<10 ; k++){
				this.grille[k][20] = new Cellule(0,0,'z');
			}
		}
	}
	
	public boolean accepter(Piece p){
		boolean res = false;
		if((p.getCelluleUn().getX() < 0 || p.getCelluleDeux().getX() < 0 || p.getCelluleTrois().getX() < 0 || p.getCelluleQuatre().getX() < 0)
		|| (p.getCelluleUn().getX() >10 || p.getCelluleDeux().getX() >10 || p.getCelluleTrois().getX() >10 || p.getCelluleQuatre().getX() >10)){
			res = false;
		}
		else if(this.grille[(int)p.getCelluleUn().getX()][(int)p.getCelluleUn().getY()].getC() == 'n'
		&& this.grille[(int)p.getCelluleDeux().getX()][(int)p.getCelluleDeux().getY()].getC() == 'n'
		&& this.grille[(int)p.getCelluleTrois().getX()][(int)p.getCelluleTrois().getY()].getC() == 'n'
		&& this.grille[(int)p.getCelluleQuatre().getX()][(int)p.getCelluleQuatre().getY()].getC() == 'n'){
			res = true;
		}
		return res;
	}
	
	public void verifieLigne(){
		int cpt;
		for(int i=0 ; i<20 ; i++){
			cpt = 0;
			for(int j=0 ; j<10 ; j++){
				if(this.grille[j][i].getC() != 'n'){
					cpt++;
				}
			}
			if(cpt == 10){
				for(int k=0 ; k<10 ; k++){
					this.grille[k][i] = new Cellule(0,0,'n');
				}
				System.out.println("ligne : "+ i +" supprimé avec succès !");
				for(int l=i-1 ; l>=0 ;l--){
					for(int m=0 ; m<10 ; m++){
						this.grille[m][l+1] = this.grille[m][l];
					}
				}
			}
			else{
				System.out.println("la ligne n'est pas pleine");
			}
		}
	}
	
	public void retirer(Piece p){
		this.grille[(int)p.getCelluleUn().getX()][(int)p.getCelluleUn().getY()] = new Cellule(0,0,'n');
		this.grille[(int)p.getCelluleDeux().getX()][(int)p.getCelluleDeux().getY()] = new Cellule(0,0,'n');
		this.grille[(int)p.getCelluleTrois().getX()][(int)p.getCelluleTrois().getY()] = new Cellule(0,0,'n');
		this.grille[(int)p.getCelluleQuatre().getX()][(int)p.getCelluleQuatre().getY()] = new Cellule(0,0,'n');
	}
	
	public void ajouter(Piece p){
		this.grille[(int)p.getCelluleUn().getX()][(int)p.getCelluleUn().getY()] = p.getCelluleUn();
		this.grille[(int)p.getCelluleDeux().getX()][(int)p.getCelluleDeux().getY()] = p.getCelluleDeux();
		this.grille[(int)p.getCelluleTrois().getX()][(int)p.getCelluleTrois().getY()] = p.getCelluleTrois();
		this.grille[(int)p.getCelluleQuatre().getX()][(int)p.getCelluleQuatre().getY()] = p.getCelluleQuatre();
	}

	public Cellule getCellule(int k, int l) {
		return grille[k][l];
	}
	
}