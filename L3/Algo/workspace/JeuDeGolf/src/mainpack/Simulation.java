package mainpack;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;


public class Simulation {
	
	static int T = 10;
	
	 public static double round(double d, int decimales) {
         return BigDecimal.valueOf(d).setScale(decimales,BigDecimal.ROUND_HALF_DOWN).doubleValue();
	 }
	 
	 public static void testerMethode(String mth) throws IOException
	 {
		 Scanner sc;
		 String s_input,p1s,p2s,p3s,p4s,cf;
		 int i_input,val_t;
		 Point p1,p2,p3,p4;
		 Terrain ter;
		 Region reg;
		 Triangle t1;
		 switch(mth)
		 {
		 	case "TriangulationTerrain":
		 		System.out.println("Lancer l'interface graphique ( option -g ) -> Bouton Terrain -> Actions -> Afficher la triangulation");
		 		break;
		 	case "ConstructionQT":
		 		System.out.println("Lancer l'interface graphique ( option -g ) -> Bouton Terrain -> Actions -> Afficher le QuadTree");
		 		break;
		 	case "CalculCoefficientsDroite":
		 		sc = new Scanner(System.in);
		 		System.out.println("Entrez un premier point ( format x;y ): ");
		 		s_input = sc.nextLine();
		 		p1 = new Point(Double.parseDouble(s_input.split(";")[0]),Double.parseDouble(s_input.split(";")[1]));
		 		System.out.println("Entrez un second point ( format x;y ): ");
		 		s_input=sc.nextLine();
		 		p2 = new Point(Double.parseDouble(s_input.split(";")[0]),Double.parseDouble(s_input.split(";")[1]));
		 		Droite d = new Droite(p1,p2);
		 		d.afficheDroite();
		 		break;
		 	case "TestIntersectionDeuxSegments":
		 		sc = new Scanner(System.in);
		 		
		 		System.out.println("Entrez un premier point( format x;y ):");
		 		s_input = sc.nextLine();
		 		p1 = new Point(Double.parseDouble(s_input.split(";")[0]),Double.parseDouble(s_input.split(";")[1]));
		 		System.out.println("Entrez un second point ( format x;y ): ");
		 		s_input=sc.nextLine();
		 		p2 = new Point(Double.parseDouble(s_input.split(";")[0]),Double.parseDouble(s_input.split(";")[1]));
		 		System.out.println("Entrez un troisieme point( format x;y ):");
		 		s_input = sc.nextLine();
		 		p3 = new Point(Double.parseDouble(s_input.split(";")[0]),Double.parseDouble(s_input.split(";")[1]));
		 		System.out.println("Entrez un quatrieme point( format x;y ):");
		 		s_input = sc.nextLine();
		 		p4 = new Point(Double.parseDouble(s_input.split(";")[0]),Double.parseDouble(s_input.split(";")[1]));	
		 		Segment.TestIntersectionDeuxSegments(p1,p2,p3,p4);
		 		break;
		 	case "TestRegionIntersecteTriangle":
		 		sc = new Scanner(System.in);
		 		System.out.println("Entrez un point( format x;y ):");
		 		s_input = sc.nextLine();
		 		p1 = new Point(Double.parseDouble(s_input.split(";")[0]),Double.parseDouble(s_input.split(";")[1]));
		 		System.out.println("Entrez trois points pour former un triangle ( format 'x1;y1 x2;y2 x3;y3' ):");
		 		s_input = sc.nextLine();
		 		p2s = s_input.split(" ")[0];
		 		p3s = s_input.split(" ")[1];
		 		p4s = s_input.split(" ")[2];
		 		
		 		p2 = new Point(Double.parseDouble(p2s.split(";")[0]),Double.parseDouble(p2s.split(";")[1]));	
		 		p3 = new Point(Double.parseDouble(p3s.split(";")[0]),Double.parseDouble(p3s.split(";")[1]));	
		 		p4 = new Point(Double.parseDouble(p4s.split(";")[0]),Double.parseDouble(p4s.split(";")[1]));	
		 		
		 		t1 = new Triangle(p2,p3,p4);
		 		System.out.println("Triangle entré:");
		 		t1.affPoly();
		 		
		 		System.out.println("Entrez un chemin de fichier descripteur pour la construction du QuadTree: ");
		 		s_input = sc.nextLine();
		 		cf = s_input;
		 		System.out.println("Entrez le nombre maximum de triangles intersectés par une région du QuadTree: ");
		 		i_input = sc.nextInt();
		 		val_t = i_input;
		 		ter = new Terrain(cf,val_t);
		 		reg = ter.getQT().RecherchePointQT(ter.getQT().getRacine(), p1);
		 		if(Region.TestRegionIntersecteTriangle(reg, t1))
		 		{
		 			System.out.println("La region du point donné intersecte le triangle donné.");
		 		}
		 		else
		 		{
		 			System.out.println("La region du point donné n'intersecte pas le triangle donné.");
		 		}
		 		break;
		 	case "TestTriangleContientPoint":
		 		sc = new Scanner(System.in);
		 		System.out.println("Entrez un point( format x;y ):");
		 		s_input = sc.nextLine();
		 		p1 = new Point(Double.parseDouble(s_input.split(";")[0]),Double.parseDouble(s_input.split(";")[1]));
		 		System.out.println("Entrez trois points pour former un triangle ( format 'x1;y1 x2;y2 x3;y3' ):");
		 		s_input = sc.nextLine();
		 		p2s = s_input.split(" ")[0];
		 		p3s = s_input.split(" ")[1];
		 		p4s = s_input.split(" ")[2];
		 		
		 		p2 = new Point(Double.parseDouble(p2s.split(";")[0]),Double.parseDouble(p2s.split(";")[1]));	
		 		p3 = new Point(Double.parseDouble(p3s.split(";")[0]),Double.parseDouble(p3s.split(";")[1]));	
		 		p4 = new Point(Double.parseDouble(p4s.split(";")[0]),Double.parseDouble(p4s.split(";")[1]));	
		 		
		 		t1 = new Triangle(p2,p3,p4);
		 		
		 		System.out.println("Triangle entré: ");
		 		t1.affPoly();
		 		
		 		if(Triangle.TestTriangleContientPoint(t1, p1))
		 		{
		 			System.out.println("Le triangle donné contient le point donné.");
		 		}else
		 		{
		 			System.out.println("Le triangle donné ne contient pas le point donné.");
		 		}
		 		break;
		 	case "RecherchePointQT":
		 		System.out.println("Test également disponible en version graphique.");
		 		sc = new Scanner(System.in);
		 		System.out.println("Entrez un point( format x;y ):");
		 		s_input = sc.nextLine();
		 		p1 = new Point(Double.parseDouble(s_input.split(";")[0]),Double.parseDouble(s_input.split(";")[1]));
		 		System.out.println("Entrez un chemin de fichier descripteur pour la construction du QuadTree: ");
		 		s_input = sc.nextLine();
		 		cf = s_input;
		 		System.out.println("Entrez le nombre maximum de triangles intersectés par une région du QuadTree: ");
		 		i_input = sc.nextInt();
		 		val_t = i_input;
		 		ter = new Terrain(cf,val_t);
		 		reg = ter.getQT().RecherchePointQT(ter.getQT().getRacine(), p1);
		 		if(reg!=null)
		 		{
		 			System.out.println("La plus petite région du quadtree à laquelle appartient le point donné est: ");
		 			reg.getCarre().affPoly();
		 		}
		 		else
		 		{
		 			System.out.println("Le point n'est pas sur le terrain.");
		 		}
		 		break;
		 	case "RecherchePointTriangle":
		 		sc = new Scanner(System.in);
		 		System.out.println("Entrez un point( format x;y ):");
		 		s_input = sc.nextLine();
		 		p1 = new Point(Double.parseDouble(s_input.split(";")[0]),Double.parseDouble(s_input.split(";")[1]));
		 		System.out.println("Entrez un chemin de fichier descripteur pour la construction du QuadTree: ");
		 		s_input = sc.nextLine();
		 		cf = s_input;
		 		System.out.println("Entrez le nombre maximum de triangles intersectés par une région du QuadTree: ");
		 		i_input = sc.nextInt();
		 		val_t = i_input;
		 		ter = new Terrain(cf,val_t);
		 		reg = ter.getQT().RecherchePointQT(ter.getQT().getRacine(), p1);
		 		if(reg!=null)
		 		{
		 			t1 = reg.RecherchePointTriangle(p1);
		 			System.out.println("Le triangle du quadtree auquel appartient le point est: ");
		 			t1.affPoly();
		 		}
		 		else {
		 			System.out.println("Le point n'est pas sur le terrain.");
		 		}
		 		break;
		 	case "CalculePointAtterrissageBalle":
		 		System.out.println("Demonstration disponible sur l'interface graphique (lancement avec option -g).");
		 		break;
		 	case "CalculePointDepartBalle":
		 		System.out.println("Demonstration disponible sur l'interface graphique (lancement avec option -g).");
		 		break;
		 	case "CalculeScore":
		 		System.out.println("Demonstration disponible sur l'interface graphique (lancement avec option -g).");
		 		break;
		 		
		 }
	 }
	 
	public static void main(String[] args) throws IOException {
		if(args.length>0)
		{
			if(args[0].equals("-g"))
			{
				System.out.println("Lancement avec interface graphique:");
				@SuppressWarnings("unused")
				Affichage aff = new Affichage();
			}
			else if(args[0].equals("-t"))
			{
				if(args.length>1)
				{
					String testmethode = args[1];
					testerMethode(testmethode);
				}
				else
				{
					System.out.println("Aucune méthode à tester detectée.");
				}
			}
		}
		else {
			System.out.println("Aucune option de lancement detectée, lancement par défaut:");
			@SuppressWarnings("unused")
			Affichage aff = new Affichage();
		}
		
	}
	
	
}
