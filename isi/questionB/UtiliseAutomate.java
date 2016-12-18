package Auto2;

import java.util.ArrayList;

public class UtiliseAutomate {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ////////// exemple d'automate ; phase 1 construction
        int N=4;
        ArrayList<String> noms=new ArrayList<String>();
        /*for (int i=0;i<N;i++){
        	noms.add("E"+i);
        }*/
        noms.add("De");
        noms.add("Ho");
        noms.add("Fi");
        noms.add("Ba");

        Automate A = new AutomateDeterministe(noms);
        A.rendInitial("De");
        A.rendInitial("Ba");

        A.rendFinal("Ba");
        A.rendFinal("Fi");

        /*for (int i=1;i<N*N;i++){
        	int source=(i+i/3) % N ;
        	int numsymb= (i*i+5)%N ;
        	String symb="abcdefghijklmno".substring(numsymb,numsymb+1) ;
        	int but=(i+i/5+1)%N ;
            A.ajouteTransition("E"+source, symb,"E"+but );
        }*/
        A.ajouteTransition("De", "tri" ,"Ho" );
        A.ajouteTransition("De", "cat" ,"Ba" );
        A.ajouteTransition("Ho", "mar" ,"Fi" );
        A.ajouteTransition("Fi", "mar" ,"Fi" );
        A.ajouteTransition("Fi", "ran" ,"Ba" );
        A.ajouteTransition("Ba", "tam" ,"Ho" );

        System.out.println(A);
        
        ///////// phase 2 utilisation
        // d'abord un mot reconnu
        String mot="cattam";  // mot à reconnaitre caractère par caractère
        A.reinitialise(); // on trouvera un sommet initial à la première transition
        for (int i=0;i<mot.length();i = i + 3){
            A.transite(mot.substring(i,i+3)); // on transite avec chaque caractère
        }
        System.out.println(mot + " "+ A.resultat()); // reconnu
        // puis un mot non reconnu (absence de transition)
        mot="tammarran";  // mot à reconnaitre caractère par caractère
        A.initialise("Ba"); // on part d'un sommet initial donné
        for (int i=0;i<mot.length();i = i + 3){
            A.transite(mot.substring(i,i+3)); // on transite avec chaque caractère
        }
        System.out.println(mot + " "+A.resultat()); // Pas de transition de l'état 4 avec a

        // puis un mot non reconnu (état d'arrivée non final)
        mot="tritammar";  // mot à reconnaitre caractère par caractère
        A.initialise("De"); // on part d'un sommet initial
        for (int i=0;i<mot.length();i = i + 3){
            A.transite(mot.substring(i,i+3)); // on transite avec chaque caractère
        }
        System.out.println(mot + " "+A.resultat()); // Pas final
    }
}