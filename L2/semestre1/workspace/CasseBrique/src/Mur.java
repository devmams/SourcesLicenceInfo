
public class Mur {

	private Cellule[][] tabC;
	
	public Mur(){
		tabC = new Cellule[4][10];
		for(int y=0 ; y<4 ;y++){
			for(int x=0; x<10 ;x++){
				tabC[y][x] = new Cellule(x+0.5,y+0.5,'a');
			}
		}
	}
	
	public Cellule[][] getTabC(){
		return tabC;
	}
	
	public char getMurCol(){
		return tabC[0][0].getC();
	}
}