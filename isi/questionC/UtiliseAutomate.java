package Auto2;

import java.util.ArrayList;

public class UtiliseAutomate {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ////////// exemple d'automate ; phase 1 construction
        ArrayList<String> noms=new ArrayList<String>();
        
        noms.add("Pim");
        noms.add("Poum");
        noms.add("Pipo");
        noms.add("Pam");

        Automate A = new AutomateNonDeterministe(noms);
        A.rendInitial("Pim");
        A.rendInitial("Poum");

        A.rendFinal("Poum");
        A.rendFinal("Pipo");
        
        A.ajouteTransition("Pim", "Da" ,"Pam" );
        A.ajouteTransition("Pim", "Da" ,"Poum" );
        A.ajouteTransition("Poum", "ba" ,"Pam" );
        A.ajouteTransition("Pam", "ba" ,"Pipo" );
        A.ajouteTransition("Pam", "ba" ,"Pim" );
        A.ajouteTransition("Pipo", "Da" ,"Poum" );
        A.ajouteTransition("Pipo", "Da" ,"Pam" );

        System.out.println(A);
        
        ///////// phase 2 utilisation
        // d'abord un mot reconnu
        String mot="ba  ";  // mot à reconnaitre par deux caractère
        A.reinitialise(); // on trouvera un sommet initial à la première transition
        for (int i=0;i<mot.length();i = i + 2){
        	if(!mot.substring(i,i+2).equals("  "))
        		A.transite(mot.substring(i,i+4)); // on transite par deux caractère
        }
        System.out.println(mot + A.resultat()); // reconnu
        // puis un mot non reconnu (absence de transition)
        mot="DabaDaba  ";  // mot à reconnaitre deux caractères par deux caractère
        A.initialise("Pim"); // on part d'un sommet initial donné
        for (int i=0;i<mot.length();i = i + 2){
        	if(!mot.substring(i,i+2).equals("  "))
        		A.transite(mot.substring(i,i+4)); // on transite par deux  caractère
        }
        System.out.println(mot + A.resultat()); // Pas de transition de l'état 1 avec Da

        /*// puis un mot non reconnu (état d'arrivée non final)
       mot="ba  ";  // mot à reconnaitre caractère par caractère
        A.initialise("Poum"); // on part d'un sommet initial
        for (int i=0;i<mot.length();i = i + 2){
        	if(!mot.substring(i,i+2).equals("  "))
        		A.transite(mot.substring(i,i+4)); // on transite avec chaque caractère
        }
        System.out.println(mot + A.resultat()); // Pas final*/
    }
}