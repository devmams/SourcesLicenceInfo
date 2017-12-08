package mainpack;
import java.io.IOException;

public class Jeu {

	private int score;
	public int traceencours;
	private Terrain tj;


	private Balle balle;
	
	public Jeu(String chemin_fichier,int t) throws IOException {
		tj = new Terrain(chemin_fichier, t);
		traceencours = 0;
		Point depart_trace = tj.getTraces().get(traceencours).depart;
		balle = new Balle(depart_trace,this,tj);
	}

	public Terrain getTj() {
		return tj;
	}
	
	public Balle getBalle() {
		return balle;
	}
	/*public void partie()
	{
		traceencours = 0;
		Point depart_trace,arrive_trace,cible,arrive;
		while(traceencours<tj.getTraces().size())
		{
			depart_trace = tj.getTraces().get(traceencours).depart;
			arrive_trace = tj.getTraces().get(traceencours).arrive;
			balle = new Balle(depart_trace,this,tj);
			while(!balle.pos.egale(arrive_trace))
			{
				cible = ;
				arrive = balle.CalculePointAtterissageBalle(balle.pos, cible);
				incrementerScore();
				balle.pos = balle.CalculePointDepartBalle(arrive, balle.pos);
				
			}
		}
	}*/
	
	public int getScore() {
		return score;
	}

	public void incrementerScore() {
		this.score++;
	}

}
