import java.util.Random;

public class Planetes extends Entite {

		int taillePlanete;
		int populaltion;
		boolean construction;
		
		public Planetes(){
			super();
			Random rand = new Random();
			taillePlanete= Math.abs(rand.nextInt())%(Constantes.PlaneteTailleMax-Constantes.PlaneteTailleMin+1) + Constantes.PlaneteTailleMin;//taille
		}
}
