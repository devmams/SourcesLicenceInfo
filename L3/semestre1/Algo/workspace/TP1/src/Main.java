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
		String exp = "((c*(z+b))/(a-d))";
		System.out.println("exp          : " +exp);
		Analyseur A = new Analyseur();
		Arbre ABR = A.analyse(exp);
		//System.out.print("exp -> arbre : ");
		System.out.println(ABR.getRacine().getElt());
		System.out.print("niv: ");
		System.out.println(ABR.niveau(ABR.getRacine(), 'a', 0));
		//---------------------------------------------
		//System.out.println("\n"+ABR.hauteur(ABR.getRacine()));
	}

}


