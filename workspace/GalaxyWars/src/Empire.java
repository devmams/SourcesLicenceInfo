import java.awt.Color;
import java.util.ArrayList;

public class Empire {

	private ArrayList<Planetes> planetes;
	private Planetes p;
	private ArrayList<Vaisseaux> vaisseaux;
	private Vaisseaux v1;
	private Vaisseaux v2;
	private Vaisseaux v3;
	private static ArrayList<Integer> caseOccupees = new ArrayList<Integer>();
	
	public Empire(){
		planetes = new ArrayList<Planetes>();
		vaisseaux = new ArrayList<Vaisseaux>();
		p = new Planetes();
		v1 = new Vaisseaux(p);
		v2 = new Vaisseaux(p);

		planetes.add(p);
		vaisseaux.add(v1);
		vaisseaux.add(v2);
		
	}
	
	public ArrayList<Planetes> getPlanetes(){
		return planetes;
	}
	
	public ArrayList<Vaisseaux> getVaisseaux(){
		return vaisseaux;
	}
}
