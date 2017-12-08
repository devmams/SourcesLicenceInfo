/**
 * Classe permettant de créer un Quadtree
 * 
 * @author Nicolas VANNIER / Mamadou DIALLO
 */

package mainpack;
import java.util.ArrayList;

public class QuadTree {

	private Noeud racine;
	private int t;
	static double scope = 0.1;
	
	/**
	 * Construit un quadtree
	 * @param un terrain et un entier t
	 */
	public QuadTree(Terrain ter,int t){
		
		Region reg_racine = new Region(ter.getTriangles(),new Point(0,0),Simulation.T);
		racine = new Noeud(reg_racine);
		this.t = t;
		ConstructionQT();
	}
	
	/**
	 * Retourne la racine du quadtree
	 * @return un noeud
	 */
	public Noeud getRacine() {
		return this.racine;
	}
	
	/**
	 * Construit le quadtree
	 */
	private void ConstructionQT()
	{
		decoupage(racine,Simulation.T);
	}
	

	public Region RecherchePointQT(Noeud r,Point p)
	{
		
		if(r.n[0] != null && r.n[0].getR().getCarre().estInterieurOuBordCarre(p))
		{
			return RecherchePointQT(r.n[0],p);
		}
		else if(r.n[1] != null && r.n[1].getR().getCarre().estInterieurOuBordCarre(p))
		{
			return RecherchePointQT(r.n[1],p);

		}
		else if(r.n[2] != null && r.n[2].getR().getCarre().estInterieurOuBordCarre(p))
		{
			return RecherchePointQT(r.n[2],p);

		}
		else if(r.n[3] != null && r.n[3].getR().getCarre().estInterieurOuBordCarre(p))
		{
			return RecherchePointQT(r.n[3],p);

		}
		else if(r.getR().getCarre().estInterieurOuBordCarre(p)){
			return r.getR();
		}
		else
		{
			return null;
		}
	}
	
	private void decoupage(Noeud nd,double taille)
	{
		Region r = nd.getR();
		Carre cr = r.getCarre();
		ArrayList<TriangleSurface> liste_triangles = r.getTriangles();
		ArrayList<TriangleSurface> nvlt;
		
		
		Region[] sousreg = new Region[4];
		
		sousreg[0] = new Region(cr.getPointIndex(0),taille/2);
		sousreg[1] = new Region(new Point(cr.getPointIndex(0).x,cr.getPointIndex(0).y+(taille/2)),taille/2);
		sousreg[2] = new Region(new Point(cr.getPointIndex(0).x+(taille/2),cr.getPointIndex(0).y+(taille/2)),taille/2);
		sousreg[3] = new Region(new Point(cr.getPointIndex(0).x+(taille/2),cr.getPointIndex(0).y),taille/2);

		//int i=1;
		for(int i=0;i<4;i++)
		{
			nd.n[i] = new Noeud(sousreg[i]);
			nvlt = new ArrayList<TriangleSurface>();
			for(TriangleSurface tri : liste_triangles)
			{
				if(sousreg[i].getCarre().triangleDansCarre(tri))
				{
					//System.out.println("db1");
					nvlt.add(tri);
				}
			}
			sousreg[i].setTriangles(nvlt);
			if(nvlt.size()>=this.t && taille>=QuadTree.scope)
			{
				decoupage(nd.n[i],taille/2);
			}
		}
	}	
}
