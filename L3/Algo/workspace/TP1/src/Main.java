public class Main {

	
	public static void main(String[] args) {
		/*
		 * EXERCICE 1
		 * int[] tab = {1,2,4,5,8,9,7,0};
		Tri tri = new Tri();
		tri.afficherTab(tab);
		tri.triFusion(tab, 0, 7);
		tri.afficherTab(tab);*/
		
		//--------------------------------------------
		String exp = "((c*((a+p)+b))/(a-d))";
		System.out.println("exp          : " +exp);
		Analyseur A = new Analyseur();
		Arbre ABR = A.analyse(exp);
		System.out.print("exp -> arbre : ");
		ABR.afficherArbre(ABR.getRacine());
		
		//---------------------------------------------
		System.out.println("\n"+ABR.hauteur(ABR.getRacine()));
	}

}



