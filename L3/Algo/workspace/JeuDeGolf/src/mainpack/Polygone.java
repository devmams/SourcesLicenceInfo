/**
 * Classe permettant de créer un polygone
 * 
 * @author Nicolas VANNIER / Mamadou DIALLO
 */
package mainpack;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Polygone {
	protected ArrayList<Point> points;
	
	/**
	 * Construit un polygone sans point
	 */
	public Polygone(){
		points = new ArrayList<Point>();
	}
	
	/**
	 * Construit un polygone
	 * @param une Arraylist de Points
	 */
	public Polygone(ArrayList<Point> ensPoints){
		points = ensPoints;
	}
	
	/**
	 * Affiche les points d'un polygone
	 */
	public void affPoly() {
		for(Point p: points)
		{
			System.out.print("("+p.x+","+p.y+") ");
		}
		System.out.println("");
	}
	
	/**
	 * Permet d'ajouter un point dans un polygone
	 * @param le point à rajouter
	 */
	public void addPoint(Point p){
		points.add(p);
	}
	
	/**
	 * Retourne une Arraylist de Points
	 * @return une Arraylist
	 */
	public ArrayList<Point> getPoints(){
		return points;
	}
	
	/**
	 * Vérifie si un point est présent dans une arraylist ou pas
	 * @param le point à vérifier
	 * @return "true" si le point est présent et "false" sinon
	 */
	public boolean estPointPoly(Point p)
	{
		for(Point ppoly : points)
		{
			if(ppoly.egale(p))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Vérifie si un point est présent dans un triangle ou pas
	 * @param le triangle t
	 * @return "true" si le point est présent et "false" sinon
	 */
	private boolean pointPolyDansTriangle(Triangle t)
	{
		for(Point p : points) {
			if(!(t.estPointPoly(p)))
			{
				if(t.posPointTriangle(p) == 1)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Vérifie si un segment ouvert contient un point ou pas
	 * @param le segment ouvert
	 * @return "true" le segment ouvert contient un point et "false" sinon
	 */
	private boolean pointPolySurSegment(Segment s)
	{
		
		for(Point p: points) {
			if(s.appartientSegment(p) && !(s.estPointSegment(p)))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Recherche une oreille pour la triangulation
	 * @return un entier qui est l'indice de l'oreille
	 */
	public int rechercheOreille() {
		
		
		int indexOreille = 0;
		int indexPrecedent,indexSuivant;
		Segment sNouveau,sExt;
		boolean trouve = false;
		Triangle tri;
		Vecteur u;
		Point pointExt;
		int nbIntPropre;
		
		while(!trouve) {
						
			indexPrecedent = indexPointPrec(indexOreille);
			indexSuivant = indexPointSuiv(indexOreille);		
		
			// Nouveau segment representant la diagonale du triangle en construction
			sNouveau = new Segment(points.get(indexPrecedent),points.get(indexSuivant));
			
			// Triangle � tester
			tri = new Triangle(points.get(indexPrecedent),points.get(indexOreille),points.get(indexSuivant));
			
			
			// On v�rifie qu'aucun point du polygone except�s ceux du triangle ne se trouve dans le triangle
			// On v�rifie qu'il n'y a aucun point du polygone appartenant au segment except�s les extr�mit�es du segment
			if(!(pointPolyDansTriangle(tri)) && !(pointPolySurSegment(sNouveau))) {
				u = new Vecteur(sNouveau);
				u = u.mult(10*Simulation.T);
				
				pointExt = new Point(sNouveau.P2.x+u.v1,sNouveau.P2.y+u.v2);
				sExt = new Segment(sNouveau.P1,pointExt);
				
				nbIntPropre = this.intersectionPropre(sExt);

				if(nbIntPropre%2!=0) {
					trouve = true;
				}else {
					trouve = false;
				}
			}
			
			if(!trouve) {
				if(indexOreille==points.size()-1)
				{
					indexOreille=0;
				}else {
					indexOreille++;
				}
			}
		}
		return indexOreille;
	}
	
	/**
	 * Retourne tous les côtes d'un polygone
	 * @return l'ensemble des côtes du polygone
	 */
	public ArrayList<Segment> cotesPoly(){
		ArrayList<Segment> tabSeg = new ArrayList<Segment>();
        for(int i=0 ;i<points.size()-1;i++){
                tabSeg.add(new Segment(points.get(i), points.get(i+1)));
        }
        tabSeg.add(new Segment(points.get(points.size()-1),points.get(0)));
        return tabSeg;
	}
	
	/**
	 * Vérifie si un segment intersecte un polygone
	 * @param le segment s
	 * @return "true" si y a intersection et "false" sinon
	 */
	public boolean intersectionAvecSegment(Segment s){
		boolean resultat = false;        
        ArrayList<Segment>tabSeg = cotesPoly();
        for(Segment seg : tabSeg){
        	if(seg.intersecte(s)){
        		return true;
        	}
        }
        
		return resultat;
	}
	
	/**
	 * Retourne l'indice d'un point dans un polygone
	 * @param un point p 
	 * @return un entier qui est l'indice du point
	 */
	public int indexPoint(Point p) {
		int ind= -1;
		for(int i=0;i<points.size();i++)
		{
			if(p.egale(points.get(i))) {
				ind = i;
			}
		}
		return ind;
	}
	
	/**
	 * Retourne l'indice precedent d'un point dans un polygone
	 * @param un point p 
	 * @return un entier qui l'indice precedent du point
	 */
	public int indexPointPrec(int index) {
		if(index==0) {
			return(points.size()-1);
		}else {
			return(index-1);
		}
	}
	
	/**
	 * Retourne l'indice suivant d'un point dans un polygone
	 * @param un point p 
	 * @return un entier qui l'indice suivant du point
	 */
	public int indexPointSuiv(int index) {
		if(index==points.size()-1) {
			return(0);
		}else {
			return(index+1);
		}
	}
	
	/**
	 * Retourne le nombre d'intersection propre d'un segment avec un polygone
	 * @param un segment s
	 * @return un entier qui est le nobre d'intersection propre
	 */
	public int intersectionPropre(Segment s) {
		Point p;
		int cpt=0;
		int pindex,pindex_prec,pindex_suiv;
		ArrayList<Segment> cotes = cotesPoly();
		ArrayList<Point> pointsIntersectes = new ArrayList<Point>();
		boolean appPI;
		for(Segment seg: cotes)
		{
			if(seg.intersecte(s)) {
				
				p = s.pointIntersection(seg);
				// Le segment test� intersecte le polygone en un point qui n'est pas un sommet du polygone
				if(!(s.estPointSegment(p)) && !(seg.estPointSegment(p)))
				{
					cpt++;
				}
				else if(seg.estPointSegment(p)) // Le segment test� intersecte le polygone en un sommet du polygone
				{
					pindex = indexPoint(p);
					pindex_prec = indexPointPrec(pindex);
					pindex_suiv = indexPointSuiv(pindex);
					if(!(s.appartient(points.get(pindex_prec)) == s.appartient(points.get(pindex_suiv))) && !(s.estPointSegment(p)))
					{
						appPI=false;
						for(Point pi : pointsIntersectes)
						{
							if(pi.egale(points.get(pindex)))
							{
								appPI=true;
							}
						}
						if(!appPI)
						{
							cpt++;
							pointsIntersectes.add(points.get(pindex));
						}
					}
					
				}
				else if(s.appartientSegment(seg.P1) && s.appartientSegment(seg.P2) && !(s.estPointSegment(seg.P1)) && !(s.estPointSegment(seg.P2)))
				{ // Le segment test� contient un segment du polygone
					pindex = indexPoint(seg.P1);
					pindex_prec = indexPointPrec(pindex);
					
					pindex = indexPoint(seg.P2);
					pindex_suiv = indexPointSuiv(pindex);
					
					
					if(!(s.appartient(points.get(pindex_prec)) == s.appartient(points.get(pindex_suiv))))
					{
						cpt++;
					}
				}
			}
		}
		return cpt;
			
			
	}
	
	/**
	 * Retourne le point qui est à l'indice index d'un polygone
	 * @param un entier index
	 * @return un point 
	 */
	public Point getPointIndex(int index){
		return this.getPoints().get(index);
	}
	
	/**
	 * Retourne le nombre de points d'un polygone
	 * @return un entier
	 */
	public int nbPoints() {
		return points.size();
	}
	
	/**
	 * Supprime le point qui est à l'indice i dans un polygone
	 */
	public void retirerPoint(int i) {
		points.remove(i);
	}
	
	@SuppressWarnings("unchecked")
	public void clone(Polygone cible) {
		cible.points = (ArrayList<Point>) points.clone();
	}
	
	public void GAfficher(Graphics g,Color col,boolean fill)
	{
		g.setColor(col);
		int nPoints = this.getPoints().size();
		int[] xPoints = new int[nPoints];
		int[] yPoints = new int[nPoints];
		int upScale = Affichage.upScale;
		
		for(int i=0;i<nPoints;i++)
		{
			xPoints[i] = (int) (upScale*this.getPointIndex(i).x);
			yPoints[i] = Simulation.T*upScale-(int) (upScale*this.getPointIndex(i).y);
		}
		if(fill)
		{
			g.fillPolygon(xPoints, yPoints, nPoints);

		}
		else
		{
			g.drawPolygon(xPoints, yPoints, nPoints);
		}
	}
}
