package mainpack;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class TerrainPanel extends JGPanel implements ActionListener{
	
	private JMenuBar menu;
	
	private JMenu menu_actions;
	
	private JCheckBoxMenuItem action_qt,action_tri,action_testpoint;
	
	private JMenuItem action_retour;
	
	Point ptest;
	Region rtest;
	
	public TerrainPanel(Terrain terrain,Affichage a){
		super(terrain,a);
		
		menu = new JMenuBar();
		a.setJMenuBar(menu);
		
		
		menu_actions = new JMenu("Actions");
		action_qt = new JCheckBoxMenuItem("Afficher le QuadTree");
		action_tri = new JCheckBoxMenuItem("Afficher la triangulation");
		action_testpoint = new JCheckBoxMenuItem("Tester un point");
		action_retour = new JMenuItem("Retour vers le Menu");
		menu_actions.add(action_qt);
		menu_actions.add(action_tri);
		menu_actions.add(action_testpoint);
		menu_actions.add(action_retour);
		action_testpoint.addActionListener(this);
		action_qt.addActionListener(this);
		action_tri.addActionListener(this);
		action_retour.addActionListener(this);
		menu.add(menu_actions);
		
		ptest = null;
		rtest = null;
	}
	
	public void paintComponent(Graphics g) {
		ArrayList<Surface> listeSurfaces = t.getSurfaces();
		ArrayList<Trace> listeTraces = t.getTraces();
		Surface surface_cour;
		Trace trace_cour;
		Polygone poly_cour;
		for(int i=0;i<listeSurfaces.size();i++) {
			surface_cour = listeSurfaces.get(i);
			poly_cour = surface_cour.getPoly();
			poly_cour.GAfficher(g, assignerCouleur(surface_cour.getCouleur()), true);
		}
		for(int i=0;i<listeTraces.size();i++)
		{
			trace_cour = listeTraces.get(i);
			
			trace_cour.depart.GAfficher(g, COLOR_BLACK);
			trace_cour.arrive.GAfficher(g, COLOR_BLUE);
		}

		
		if(action_testpoint.isSelected() && ptest != null && rtest != null)
		{
			ptest.GAfficher(g, COLOR_YELLOW);
			rtest.getCarre().GAfficher(g, COLOR_MAGENTA, false);
			rtest.RecherchePointTriangle(ptest).GAfficher(g, COLOR_BLUE, false);
		}
		
		/*Point ptest = new Point(4,6);
		ptest.GAfficher(g, COLOR_YELLOW);
		Region Recherche = t.getQT().RecherchePointQT(t.getQT().getRacine(), ptest);
		System.out.println(Recherche.getTriangles().size());
		Recherche.getCarre().GAfficher(g, COLOR_MAGENTA, false);
		Recherche.RecherchePointTriangle(ptest).GAfficher(g, COLOR_MAGENTA, false);*/
		
		if(action_tri.isSelected())
		{
			afficherTriangles(g);
		}
		
		if(action_qt.isSelected())
		{
			afficherQT(g);
		}
		
		
		
	}
	
	public void afficherTriangles(Graphics g) {
		ArrayList<TriangleSurface> listeTriangles = t.getTriangles();
		for(int i=0;i<listeTriangles.size();i++) {
			TriangleSurface triangle_cour = listeTriangles.get(i);
			triangle_cour.GAfficher(g, COLOR_BLACK,false);
		}
	}
	
	public void afficherQT(Graphics g)
	{
		afficherDecoupage(g,t.getQT().getRacine());
	}
	
	public void afficherDecoupage(Graphics g,Noeud n)
	{		
		n.getR().getCarre().GAfficher(g, COLOR_WHITE,false);
		for(int i=0;i<4;i++)
		{
			if(n.n[i]!=null)
			{
				afficherDecoupage(g,n.n[i]);
			}
		}
	}
	
	public Point demanderPoint()
	{
		String input_point = (String) JOptionPane.showInputDialog(this,
				"Entrez un point à tester sous la forme: x;y",
				JOptionPane.PLAIN_MESSAGE
				);
		Point cible = new Point();
		cible.x = Double.parseDouble(input_point.split(";")[0]);
		cible.y = Double.parseDouble(input_point.split(";")[1]);
		cible.existe = true;
		return cible;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == action_tri ||source == action_qt)
		{
			paintComponent(super.getGraphics());
		}
		else if(source==action_testpoint && action_testpoint.isSelected())
		{
			ptest = demanderPoint();
			rtest = t.getQT().RecherchePointQT(t.getQT().getRacine(), ptest);
			paintComponent(super.getGraphics());
		}else if(source==action_testpoint)
		{
			ptest = null;
			rtest = null;
			paintComponent(super.getGraphics());
		}
		else if(source == action_retour)
		{
			aff.afficherMenu();
		}
	}

}
