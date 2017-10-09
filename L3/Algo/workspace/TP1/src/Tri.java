public class Tri {
	
	public Tri(){}
	
	public void triFusion(int[] tab,int d, int f){
		if(d != f){
			int milieu = (d+f)/2;
			triFusion(tab, d, milieu);
			triFusion(tab, milieu+1, f);
			fusion(tab , d , milieu , f);
		}
			
	}
	
	private void fusion(int[] tab ,int d ,int f1, int f){
		int d2 = f1+1;
		int[] tab1= new int[f1-d+1];
		for(int i=d ;i<=f1 ;i++){
			tab1[i-d] = tab[i];
		}
		int a = d;
		int b = d2;
		for(int i=d ;i<=f ;i++){
			if(a == d2){}
			else if(b == f+1){
				tab[i] = tab1[a-d];
				a++;
			}
			else if(tab1[a-d] < tab[b]){
				tab[i] = tab1[a-d];
				a++;
			}
			else{
				tab[i] = tab[b];
 				b++;
			}	
		}
	}
	
	public void afficherTab(int[] tab){
		System.out.print("[");
		for(int i=0 ;i<tab.length-1;i++){
			System.out.print(tab[i] + " ,");
		}
		System.out.print(tab[tab.length-1] + "]");
		System.out.println();
	}
}
