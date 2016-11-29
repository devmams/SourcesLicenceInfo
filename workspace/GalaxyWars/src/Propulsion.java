import java.util.Random;


public class Propulsion {

	private int portee;
	private int carburant;
	
	public Propulsion(){
		Random rand = new Random();
		portee = Math.abs(rand.nextInt())%(Constantes.PorteeMax-Constantes.PorteeMin+1) + Constantes.PorteeMin; // portee;
		carburant = 3;// Math.abs(rand.nextInt())%(Constantes.CarburantMax-Constantes.CarburantMin+1) + Constantes.CarburantMin; // Carburant;
	}
	
	public int getPortee(){
		return portee;
	}
	
	public int getCarburant(){
		return carburant;
	}
	
	public int positionAtteinte(){
		Random r = new Random();
		int tmp = r.nextInt(portee) + 1 ;
		return tmp;
	}
	
	public void setCarburant(){
		carburant--;
	}
}
