package Auto2;



import java.util.*;

import org.omg.CORBA.NVList;

import Auto2.Automate;


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
    ArrayList<Integer> courant = new ArrayList<Integer>(); // l'état actuel de l'automate après quelques transitions
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
    	
		transitions.get(numeroEtat(source)).add(new Transition(mot1,numeroEtat(but)));
    }

       // initialisation de l'automate à un état donné, aucune transition n'a encore été effectuée
    public void initialise(String e) {
    	courant = new ArrayList<Integer>();
        courant.add(numeroEtat(e));
        erreur=!estInitial(e);
        errMsg="l'état "+e+" n'est pas inital";
        start = true;
    }

       // Réinitialisation de l'automate
    public void reinitialise() {
        erreur=false;
        errMsg="";
        //courant=null;
        start = false;
    }
    
    // Recherche un état initial correspondant à une première transition
    private void trouveDepart(String mot1) {
        for (int i=0;i<nbEtats;i++) {
        	if (estInitial(i)){
        		//System.out.println("mot1 : "+mot1);
        	   for (int j=0;j<transitions.get(i).size();j++){
        		    if (mot1.equals(transitions.get(i).get(j).symbole)){
        			   courant.add(i);
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
    	boolean bool = false;
    	for(int i=0 ;i<courant.size() ;i++){
    		if(estFinal(courant.get(i))){
    			bool = true;
    		}
    		if(!bool && i+1 == courant.size()){
    			errMsg="l'état "+nomEtat(courant.get(courant.size()-1)) +" n'est pas final ";// + la suite des mot1s
    		}
    	}
        return !erreur && bool;
    }
       // effectue une transition à partir de l'état courant 
       //    avec la "mot1" donnée, met à jour l'état courant
   public void transite(String mot) {
	   if (!start) {   // Si pas encore démarré
           trouveDepart(mot);   // On cherche l'état de départ candidat
       }
	   if (erreur) // on ne continue pas après la première erreur rencontrée
       { errMsg+=mot; }  // on empile les "mot1s restantes"
	   ArrayList<Integer> tmp = new ArrayList<Integer>();
	   for(int i=0 ;i<courant.size() ;i++){
	       System.out.println("etat courant "+nomEtat(courant.get(i))+" transition par "+mot);
	       for(int j=0 ; j<transitions.get(courant.get(i)).size() ;j++){
	    	   if(transitions.get(courant.get(i)).get(j).symbole.equals(mot)){
					tmp.add(transitions.get(courant.get(i)).get(j).etatArrivee);
			   }
			   else{
				   errMsg="Pas de transition de l'état "+nomEtat(courant.get(i))+" avec "+mot;// + la suite des mot1s
			   }
	       }
	   }
	   if(tmp.size() == 0){
		   errMsg="Pas de transition de l'état "+nomEtat(courant.get(courant.size()-1))+" avec "+mot;// + la suite des mot1s
		   erreur = true;
		   return;
	   }
	   courant = tmp;
    }
    
    public void determinisation(){
    	ArrayList<String> alphabet = new ArrayList<String>();
    	boolean present;
    	for(int i=0 ;i<transitions.size() ;i++){
    		for(int j=0 ;j<transitions.get(i).size() ;j++){
    			present = false;
    			for(int k=0 ;k<alphabet.size() ;k++){
    				if(transitions.get(i).get(j).symbole.equals(alphabet.get(k))){
    					present = true;
    					break; 
    				}
    			}
    			if(!present){
    				alphabet.add(transitions.get(i).get(j).symbole);
    			}
    		}
    	}
		System.out.println(alphabet);
    	
    	ArrayList<ArrayList<String>> nouvEtat = new ArrayList<ArrayList<String>>();
    	ArrayList<String> listeSymbol = new ArrayList<String>();
    	String but;
    	String symb;
    	boolean pasTerminee = true ;
    	ArrayList<String> cour = new ArrayList<String>();
    	cour.add(nomEtat(0));
    	int ind = 0;
    	nouvEtat.add(new ArrayList<String>());
    	nouvEtat.get(ind).add(cour.get(0));
		System.out.println("courant :"+ cour);
    	while(pasTerminee){
			but="";
	    	symb="";
			for(int a=0 ;a<alphabet.size() ;a++){
				for(int z=0 ; z<cour.size() ;z++){
					if(transitionPresente(numeroEtat(cour.get(z)), alphabet.get(a))){
						ind++;	
						nouvEtat.add(new ArrayList<String>());
						listeSymbol.add(alphabet.get(a));
						for(int y=0 ; y<cour.size() ;y++){
							for(int j=0 ;j<transitions.get(numeroEtat(cour.get(y))).size() ;j++){
								symb = transitions.get(numeroEtat(cour.get(y))).get(j).symbole;
								if(symb.equals(alphabet.get(a))){
									System.out.println("---------"+symb);
									but = nomEtat(transitions.get(numeroEtat(cour.get(y))).get(j).etatArrivee);
									nouvEtat.get(ind).add(but);
								}
							}
							z = y;
						}
					}
				}
			}
			cour = nouvEtat.get(ind);
			System.out.println("courant :"+ cour);
			for(int i=0 ;i<nouvEtat.size()-1 ;i++){
				if(cour.equals(nouvEtat.get(i))){
					pasTerminee = false;
					break;
				}
			}
    	}
    	//transitions= new ArrayList<ArrayList<Transition>>();
    	for(int i=0 ;i<listeSymbol.size()-1 ;i++){
    		ajouteTransition(nouvEtat.get(0).get(0), listeSymbol.get(0), nouvEtat.get(0).get(0));
    	}
    	System.out.println("nvEt "+nouvEtat);
    	System.out.println("Lsymb "+listeSymbol);
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