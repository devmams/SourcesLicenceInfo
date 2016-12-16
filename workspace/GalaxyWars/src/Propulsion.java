/**
 * Classe permettant de créer la propulsion d'un 
 * vaisseaux
 * 
 * @author Glenn PLOUHINEC / Mamadou DIALLO
 */
import java.util.Random;

public class Propulsion {

	// entier correspondant à la portée.
	private int portee;
	// entier correspondant au carburant.
	private int carburant;
	
	/**
	 * Construit une propulsion
	 */
	public Propulsion(){
		Random rand = new Random();
		portee = rand.nextInt(Constantes.PorteeMax-Constantes.PorteeMin+1) + Constantes.PorteeMin; // portee entre 1 et 5;
		carburant = rand.nextInt(Constantes.CarburantMax-Constantes.CarburantMin+1) + Constantes.CarburantMin; // Carburant entre 10 et 20;
	}
	
	/**
	 * Accesseur.
	 * @return la portée du vaisseaux.
	 */
	public int getPortee(){
		return portee;
	}
	
	/**
	 * Accesseur.
	 * @return le carburant du vaisseaux.
	 */
	public int getCarburant(){
		return carburant;
	}
	
	/**
	 * retourne un entier 1 et la portée du vaisseaux
	 */
	public int positionAtteinte(){
		Random r = new Random();
		int tmp = r.nextInt(portee) + 1 ;
		return tmp;
	}

	/**
	 * Mutateur
	 * diminue le carburant du vaisseau de 1
	 */
	public void diminuerCarburant(){
		carburant--;
	}
	
	/**
	 * Mutateur
	 * augmente le carburant du vaisseau de +5
	 */
	public void rechargerCarburant(){
		carburant += 5;
	}
}
