package Auto2;

import java.util.*;


public class AutomateDeterministe extends Automate {
	private class Transition{
		public String symbole;  // remarque : la classe étant privée, les attributs publics 
		public int etatArrivee; // ne sont en fait accessibles que de la classe englobante
		// constructeur
		public Transition(String symb, int arrivee){
			this.symbole=symb;
			this.etatArrivee=arrivee;
		}
	}
    private ArrayList<ArrayList<Transition>> transitions; // déterministe => un seul état d'arrivée possible, défini par son indice 
                                                          // pour chaque état de départ et chaque élément de vocabulaire
    int courant; // l'état actuel de l'automate après quelques transitions
    boolean start; // l'automate est prêt à fonctionner ?
    
    public AutomateDeterministe(ArrayList<String> etats) {
        super(etats);
        transitions= new ArrayList<ArrayList<Transition>>();
        for(int i=0;i<nbEtats;i++){
        	transitions.add(new ArrayList<Transition>());
        }
        start = false;
    }
        // utilitaire de test de présence d'une transition
    private boolean transitionPresente(int depart, String symb){
    	int pos=0;
    	while(pos<transitions.get(depart).size() && !symb.equals(transitions.get(depart).get(pos).symbole)){
    		pos++;
    	}
    	return pos<transitions.get(depart).size() ;
    }
        // ajout d'une transition 
    public void ajouteTransition(String source, String lettre, String but) {
    	int ne=numeroEtat(source);
    	if (transitionPresente(ne,lettre))
    	{ } // automate déterministe => on n'ajoute pas une autre transition avec le même symbole
    	else {
    		transitions.get(numeroEtat(source)).add(new Transition(lettre,numeroEtat(but)));
    	}
    }

       // initialisation de l'automate à un état donné, aucune transition n'a encore été effectuée
    public void initialise(String e) {
        courant=numeroEtat(e);
        erreur=!estInitial(e);
        errMsg="l'état "+e+" n'est pas inital";
        start = true;
    }

       // Réinitialisation de l'automate
    public void reinitialise() {
        erreur=false;
        errMsg="";
//        courant=null;
        start = false;
    }
    
    // Recherche un état initial correspondant à une première transition
    private void trouveDepart(String lettre) {
        for (int i=0;i<nbEtats;i++) {
        	if (estInitial(i)){
        	   for (int j=0;j<transitions.get(i).size();j++){
        		    if (lettre.equals(transitions.get(i).get(j).symbole)){
        			   courant=i;
        			   start=true;
        		    }
        	   }
        	}
        }
        if (!start) { // Aucun état ne permet de faire la première transition
            errMsg = "Aucun état initial ne permet de faire ";  // + lettre
            erreur = true;
        }
        else {
        	erreur=false;
        }
    }

       // une suite de "lettres" est reconnue 
       //    si les transitions se sont bien passées 
       //          et que l'état obtenu est final
    public boolean estReconnu() {
        return !erreur && estFinal(courant);
    }
       // effectue une transition à partir de l'état courant 
       //    avec la "lettre" donnée, met à jour l'état courant
    public void transite(String lettre) {
        if (!start) {   // Si pas encore démarré
            trouveDepart(lettre);   // On cherche l'état de départ candidat
        }
        System.out.println("etat courant"+nomEtat(courant)+" transition par "+lettre);
        if (erreur) // on ne continue pas après la première erreur rencontrée
            { errMsg+=lettre; }  // on empile les "lettres restantes"
        else // pas d'erreur, on essaie de transiter
        { // recherche de la transition correspondante
            int rech=0;
            while (rech<transitions.get(courant).size() && !lettre.equals(transitions.get(courant).get(rech).symbole))
                  {rech++;}
        	if (rech<transitions.get(courant).size()){ // si il y a  bien une transition avec cette lettre
                    // System.out.print(courant); // DEBUG
                courant=transitions.get(courant).get(rech).etatArrivee; // mise à jour de courant
                errMsg="l'état "+nomEtat(courant)+" n'est pas final ";           // message si on s'arête là
                    // System.out.println("-"+t+"->"+courant); // DEBUG
            }
            else{  // il n'y a pas de transition avec cette lettre 
                errMsg="Pas de transition de l'état "+nomEtat(courant)+" avec "+lettre+ " avant ";   // + la suite des lettres
                erreur=true;
                }
        }
    }

    @Override
    public String toString() {
        String aff="";
        for(int i=0;i<nbEtats;i++){
            aff+=nomEtat(i)+" : ";
            for (Transition t : transitions.get(i)){
                    aff+=" / "+nomEtat(i)+"-"+t.symbole+"->"+nomEtat(t.etatArrivee);    
                }
            aff+="\n"; // passage à la ligne   
        }
        return aff;
    }
	@Override
	public void determinisation() {
		// TODO Auto-generated method stub
		
	}
}