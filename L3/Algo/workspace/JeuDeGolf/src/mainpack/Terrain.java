package mainpack;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class Terrain {

	private QuadTree qt;
	private ArrayList<Surface> surfaces;
	private ArrayList<Trace> traces;
	private ArrayList<TriangleSurface> triangles;
	
	
	public Terrain(String chemin_fichier,int t) throws IOException{
		surfaces = new ArrayList<Surface>();
		traces = new ArrayList<Trace>();
		triangles = new ArrayList<TriangleSurface>();
		lireDescription(chemin_fichier);
		TriangulationTerrain();
		qt = new QuadTree(this,t);
	}
	
	public QuadTree getQT()
	{
		return this.qt;
	}
	
	private void ajouterSurface(ArrayList<Double> coords,char couleur){
		Surface s = new Surface(coords,couleur);
		surfaces.add(s);
	}
	
	public ArrayList<Surface> getSurfaces(){
		return surfaces;
	}
	
	public ArrayList<Trace> getTraces(){
		return traces;
	}
	
	public ArrayList<TriangleSurface> getTriangles(){
		return triangles;
	}
	
	private void lireDescription(String chemin_fichier) throws IOException
	{
		int data,prevdata;
		int par_lu;
		int nb_trace;
		InputStreamReader r = null;
		String buffer = "";
		boolean lecturePoint;
		r = new FileReader(chemin_fichier);
		
		data = r.read();
		
		while(data != -1 && (char) data != '\n')
		{
			buffer+=(char) data;
			data = r.read();
		}
		int nbSurfaces = Integer.parseInt(buffer);
		for(int i=0;i<nbSurfaces;i++){
			data = r.read();
			lecturePoint = false;
			ArrayList<Double> listeCoords= new ArrayList<Double>();
			while((char) data !='\n'){
				if((char) data =='('){
					lecturePoint = true;
					buffer = "";
				}
				if(lecturePoint && (char) data !=',' && (char) data != '(' && (char) data != ')'){
					buffer+=(char) data;
				}
				else if(lecturePoint && (char) data == ','){
					listeCoords.add(Double.parseDouble(buffer));
					buffer = "";
				}
				else if(lecturePoint && (char) data == ')'){
					lecturePoint = false;
					listeCoords.add(Double.parseDouble(buffer));
				}
				prevdata = data;
				data = r.read();
				if((char) data == '\n')
				{
					ajouterSurface(listeCoords,(char) prevdata);
				}
				
			}
		}
		data = r.read(); // Lecture du nombre de traces
		nb_trace = data;
		data = r.read(); // Lecture du saut de ligne
		data = r.read();
		lecturePoint = false;
		buffer = "";
		int compteur = 0;
		int indice_surface;
		Point depart = new Point();
		Point arrive = new Point();
		ArrayList<Surface> parcours = new ArrayList<Surface>();
		while((char) data != '\n'){
			if(compteur<4 && (char) data == ','){
				indice_surface = Integer.parseInt(buffer);
				parcours.add(surfaces.get(indice_surface));
				buffer = "";
				compteur++;
				
			}
			else if(compteur <4 && (char) data != ','){
				buffer+=(char) data;
			}
			else if(compteur<6){
				if((char) data == '('){
					lecturePoint=true;
					buffer = "";
				}
				
				if(lecturePoint && (char) data !=',' && (char) data != '(' && (char) data != ')'){
					buffer+=(char) data;
				}
				else if(lecturePoint && (char) data == ','){
					if(compteur == 4){
						depart.x = Double.parseDouble(buffer);
					}else{
						arrive.x = Double.parseDouble(buffer);
					}
					buffer = "";
				}
				else if(lecturePoint && (char) data == ')'){
					if(compteur == 4){
						depart.y = Double.parseDouble(buffer);
						depart.existe = true;
					}else{
						arrive.y = Double.parseDouble(buffer);
						arrive.existe = true;
					}
					lecturePoint = false;
					buffer = "";
					compteur++;
					
				}
			}
			else{
				if((char) data !=','){
					buffer+=(char) data;
				}
			}
			data = r.read();
		}
		par_lu = Integer.parseInt(buffer);
		traces.add(new Trace(parcours,depart,arrive,par_lu));
		r.close();
	}



	public void TriangulationTerrain(){
				int indexOreille,indexPrecedent,indexSuivant;
				Surface s;
                for(int i=0;i<surfaces.size();i++){
                	s = surfaces.get(i);
                	Polygone polyCour = new Polygone();
                	s.getPoly().clone(polyCour);
                	                	
                	// Methode des oreilles
                	
                	
                	while(polyCour.nbPoints()>=3)
                	{	
	                	indexOreille = polyCour.rechercheOreille();
	        			if(indexOreille == 0) {
	        				indexPrecedent = polyCour.getPoints().size()-1;
	        				indexSuivant = 1;
	        			}else if(indexOreille == polyCour.getPoints().size()-1){
	        				indexSuivant = 0;
	        				indexPrecedent = polyCour.getPoints().size()-2;
	        			}else {
	        				indexPrecedent = indexOreille-1;
	        				indexSuivant = indexOreille+1;
	        			}
	        			// Ajout du Triangle trouvé à la liste.
	                	triangles.add(new TriangleSurface(polyCour.getPointIndex(indexPrecedent),polyCour.getPointIndex(indexOreille),polyCour.getPointIndex(indexSuivant),i));
	                	// Retrait de l'oreille du triangle trouvé du polygone analysé
	                	polyCour.retirerPoint(indexOreille);
                	}
                }
        }
	
		public boolean pointDansTerrain(Point p)
		{
			Point p1 = new Point(0,0);
			Point p2 = new Point(0,10);
			Point p3 = new Point(10,10);
			Point p4 = new Point(10,0);
			Carre carre_terrain = new Carre(p1,p2,p3,p4);
			return(carre_terrain.estInterieurOuBordCarre(p));
		}

}
