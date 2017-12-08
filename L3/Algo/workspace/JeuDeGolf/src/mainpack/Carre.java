/**
 * Classe permettant de créer un Carre
 * 
 * @author Nicolas VANNIER / Mamadou DIALLO
 */
package mainpack;
import java.util.ArrayList;

public class Carre extends Polygone {

	/**
	 * Construit un carre
	 * @param les quatres sommets du carre
	 */
	public Carre(Point A,Point B,Point C,Point D) {
		points = new ArrayList<Point>();
		points.add(A);
		points.add(B);
		points.add(C);
		points.add(D);
	}

	/**
	 * Construit un carre
	 * @param une Arraylist contenant les quatres points du carre
	 */
	public Carre(ArrayList<Point> ensPoints) {
		super(ensPoints);
	}

	
	/**
	 * vérifie si un point est à l'interieur d'un carre ou pas
	 * @param le point à vérifier
	 * @return un booleen "true" si le point est à l'intérieur et "false" sinon
	 */
	public boolean estInterieurCarre(Point p)
	{
		Point A = points.get(0);
		Point B = points.get(1);
		Point C = points.get(2);
		Point D = points.get(3);
		if( (p.x > A.x && p.x < D.x) && (p.y < B.y && p.y > A.y) )
		{
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * vérifie si un point est à l'interieur ou sur les bords d'un carre
	 * @param le point à vérifier
	 * @return un booleen "true" si le point est à l'intérieur ou sur les bords et "false" sinon
	 */
	public boolean estInterieurOuBordCarre(Point p)
	{
		Point A = points.get(0);
		Point B = points.get(1);
		Point C = points.get(2);
		Point D = points.get(3);
		if( (p.x >= A.x && p.x <= D.x) && (p.y <= B.y && p.y >= A.y) )
		{
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * vérifie si triangle est à l'interieur d'un carre ou pas
	 * @param le triangle à vérifier
	 * @return un booleen "true" si le triangle est à l'intérieur et "false" sinon
	 */
	public boolean triangleDansCarre(Triangle t)
	{
		
		ArrayList<Segment> cotesCarre = this.cotesPoly();
		ArrayList<Segment> cotesTriangle = t.cotesPoly();
		int nbInter;
		Point pInter;
		for(Segment st:cotesTriangle)
		{
			nbInter = 0;
			for(Segment sc:cotesCarre)
			{
				if(st.intersecte(sc))
				{
					pInter = st.pointIntersection(sc);
					if(
					!t.estPointPoly(pInter)
					&&
					!sc.estPointSegment(pInter)
					)
					{
						return true;
					}else if(t.estPointPoly(pInter) && !this.estPointPoly(pInter))
					{
						nbInter++;
					}else if(t.estPointPoly(pInter) && this.estPointPoly(pInter) && nbInter==1)
					{
						nbInter++;
					}
					
					if(nbInter==2)
					{
						return true;
					}
				}
			}
		}
		
		for (Point p:t.getPoints())
		{
			if(this.estInterieurCarre(p))
			{
				return true;
			}
		}
		
		int i=0;
		for (Point p:this.getPoints())
		{
			if(t.posPointTriangle(p)==1)
			{
				return true;
			}
			else if(t.posPointTriangle(p)==0)
			{
				i++;
			}
		}
		if(i>1)
		{
			return true;
		}
		
		return false;
	}
}
