
public class Main {

	
	public static void main(String[] args) {
		int[] tab = {1,2,4,5,8,0,9,7,77,-6};
		Tri tri = new Tri();
		tri.afficherTab(tab);
		tri.triFusion(tab, 0, 7);
		tri.afficherTab(tab);
	}

}
