package Auto2;

import java.util.*;


public class AutomateNonDeterministe extends Automate {
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
    
    public AutomateNonDeterministe(ArrayList<String> etats) {
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
    public void ajouteTransition(String source, String mot1, String but) {
    	/*int ne=numeroEtat(source);
    	if (transitionPresente(ne,mot1)) // automate déterministe => on n'ajoute pas une autre transition avec le même symbole
    	{ 
    		transitions.get(numeroEtat(source)).add(new Transition(mot1,numeroEtat(but)));
    	} 
    	else {
    		transitions.get(numeroEtat(source)).add(new Transition(mot1,numeroEtat(but)));
    	}*/
		transitions.get(numeroEtat(source)).add(new Transition(mot1,numeroEtat(but)));
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
    private void trouveDepart(String mot1) {
        for (int i=0;i<nbEtats;i++) {
        	if (estInitial(i)){
        		//System.out.println("mot1 : "+mot1);
        	   for (int j=0;j<transitions.get(i).size();j++){
        		    if (mot1.equals(transitions.get(i).get(j).symbole)){
        			   courant=i;
        			   start=true;
        		    }
        	   }
        	}
        }
        if (!start) { // Aucun état ne permet de faire la première transition
            errMsg = "Aucun état initial ne permet de faire ";  // + mot1
            erreur = true;
        }
        else {
        	erreur=false;
        }
    }

       // une suite de "mot1s" est reconnue 
       //    si les transitions se sont bien passées 
       //          et que l'état obtenu est final
    public boolean estReconnu() {
        return !erreur && estFinal(courant);
    }
       // effectue une transition à partir de l'état courant 
       //    avec la "mot1" donnée, met à jour l'état courant
   public void transite(String mot) {	   
	   String mot1 = mot.substring(0,2);
	   if (!start) {   // Si pas encore démarré
           trouveDepart(mot1);   // On cherche l'état de départ candidat
       }
       System.out.println("etat courant "+nomEtat(courant)+" transition par "+mot1);
       if (erreur) // on ne continue pas après la première erreur rencontrée
           { errMsg+=mot1; }  // on empile les "mot1s restantes"
	   if(mot.length()==2 && transitionPresente(courant, mot1)){
	   		for(int i=0 ;i<transitions.get(courant).size() ;i++){
	   			if(estFinal(transitions.get(courant).get(i).etatArrivee)){
	       			courant = transitions.get(courant).get(i).etatArrivee;
	       			erreur = false;
	       			break;
	   			}
	   			else if(i+1 == transitions.get(courant).size()){
	                errMsg="l'état "+nomEtat(transitions.get(courant).get(0).etatArrivee)+" n'est pas final ";// message si on s'arête là
	                erreur = true;
	   			}
	   		}
	   	}
	   	else if(mot.length()==2 && !transitionPresente(courant, mot1)){
			errMsg="Pas de transition de l'état "+nomEtat(courant)+" avec "+mot1;// + la suite des mot1s
			erreur = true;
			return;
		}
	   	else{
        // recherche de la transition correspondante
	   		if(transitionPresente(courant, mot1)){
	    		for(int i=0 ;i<transitions.get(courant).size() ;i++){
	    			int tmp = courant;
	    			courant = transitions.get(courant).get(i).etatArrivee;
	        		transite(mot.substring(2,mot.length()));
	        		if(estReconnu() && mot.length() == 2){
	        			return;
	        		}
	        		courant = tmp;
	    		}
	   		}
        }
    }
    
    public void determinisation(){
    	
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
}