
public class Plateau {
	private static final int largeur = 10;
	private static final int hauteur = 20;
	private Cellule tab[][];
	
	public Plateau(){
		this.tab = new Cellule[largeur][hauteur];
		for(int i=0 ; i<10 ; i++){
			for(int j=0 ; j<20 ; j++){
				this.tab[i][j] = new Cellule(0,0,'n'); 
			}
		}
	}
	
	public Cellule getCase(Cellule cl){
		return tab[][];
	}
	
	public boolean accepter(Piece p){
		boolean res = false;
		if(p.getCelluleUn().getC() == 'a'){
			if(p.getMouvement() == 1){
				
			}
			else if (.getMouvement() == 2){
				
			}
			else{
				
			}
			if(tab[(int)p.getCelluleUn().getX()+1][(int)p.getCelluleUn().getY()+1] == new Cellule(0,0,'n')
			&& tab[(int)p.getCelluleDeux().getX()+1][(int)p.getCelluleDeux().getY()+1] == new Cellule(0,0,'n')
			&& tab[(int)p.getCelluleTrois().getX()+1][(int)p.getCelluleTrois().getY()+1] == new Cellule(0,0,'n')
			&& tab[(int)p.getCelluleQuatre().getX()+1][(int)p.getCelluleQuatre().getY()+1] == new Cellule(0,0,'n'))
				res = true;
			else
				res = false;
		}
		else if(p.getCelluleUn().getC() == 'b'){
			
		}
		else{
			
		}
		
		return res;
	}
	
	public void retirer(Piece p){
		
	}
	
	public void ajouter(Piece p){
		
	}
}