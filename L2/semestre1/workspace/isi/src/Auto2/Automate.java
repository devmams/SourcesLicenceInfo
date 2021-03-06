package Auto2;
import java.util.*;

public abstract class Automate {
    private ArrayList<String> etats;       // les étiquettes des états, le tableau permet de leur associer un numéro
    protected int nbEtats;                  // taille de etats, stockage redondant pour faciliter la lecture du code 
    private  ArrayList<Boolean> finaux, initiaux; // quels états sont initiaux, quels état sont finaux
    protected boolean erreur;     // vrai si une transition inexistante est demandée
    protected String errMsg;      // le message d'erreur correspondant
    
    // le stockage des transitions n'est pas défini ici, 
    // il est différent selon le type d'automate (déterministe ou pas)
    
    // constructeur (ne doit pas être utilisé de l'extèrieur, doit être redéfini pour les classes filles)
   protected Automate(ArrayList<String> etats) {
    // Remarque : les états sont définis à la création de l'instance et ne sont plus modifables
        this.etats=etats; nbEtats=etats.size();
        initiaux=new ArrayList<Boolean>(); finaux=new ArrayList<Boolean>();
        for(int i=0;i<nbEtats;++i){  // au départ les états ne sont ni finaux ni initiaux
        	initiaux.add(false);finaux.add(false); // il y a ci-dessous des méthodes pour changer cela ensuite
        }
    }
  
//////////////////// utilitaires de recherche : trouve l'entier associé en interne à un état ////////////////
   protected int numeroEtat(String etat){ //PRE : etat est présent dans etats
	   int pos=0;
	   while (pos<nbEtats && !(etat.equals(etats.get(pos)))) {pos++;}
	   return pos;	   
   }
//////////////////// accesseurs (remarque : aucune vérification sur les données n'est effectuée) ////////////////
   protected String nomEtat(int num){  // étiquette d'un état dont on connait le numéro interne
	   return etats.get(num);
   }
   protected boolean estInitial(int nume){ // vrai si l'état est initial ; version privée car utilisant le codage interne
	   return initiaux.get(nume);
   }
   public boolean estInitial(String e){ // version publique à partir de l'étiquette de l'état
	   return estInitial(numeroEtat(e));
   }
   protected boolean estFinal(int nume){// vrai si l'état est initial ; version privée car utilisant le codage interne
	   return finaux.get(nume);
   }
   public boolean estFinal(String e){// version publique à partir de l'étiquette de l'état
	   return estFinal(numeroEtat(e));
   }
//////////////////// on complète l'automate (remarque : aucune vérification sur les données n'est effectuée) ////////////////
    public void rendFinal(String e) { // déclare un état final, l'état est supposé déja exister (être dans le tableau etats)
    			finaux.set(numeroEtat(e),true);
    }
    public void rendInitial(String e) { // déclare un état initial, l'état est supposé déja exister (être dans le tableau etats)
    			initiaux.set(numeroEtat(e),true);
    }
    
    // A DEFINIR dans la classe fille
    public abstract void determinisation();

    
    // A DEFINIR dans la classe fille
    public abstract void ajouteTransition(String source, String transition, String but);

/////////////////// utilisation de l'automate ////////////////
    
    // A DEFINIR dans la classe fille
    public abstract void initialise(String e); // met l'automate sur un état initial, aucune transition n'a été effectuée 
    public abstract void reinitialise(); // met l'automate sans état courant, aucune transition n'a été effectuée 

    // A DEFINIR dans la classe fille 
    public abstract boolean estReconnu(); // vrai si la dernière transition a mené à un état final

    // A DEFINIR dans la classe fille
    public abstract void transite(String t); // effectue une transition 
 
    // bilan d'activité
    public String resultat() { // "reconnu" ou la raison pour laquelle il n'est pas reconnu
        if (estReconnu())
            { return "reconnu, tout va bien ^_^  "; }
        else 
            { return "non reconnu : "+errMsg; }
    } 
}

