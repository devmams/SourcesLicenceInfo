package mainpack;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class JeuPanel extends JGPanel implements ActionListener {

	private Jeu j;
	
	private JLabel numtrace;
	private JLabel partrace;
	private JLabel score;
	private JLabel posballe;
	private JLabel postrou;
	private JButton bouton_next;
	
	private Segment derniere_trajectoire;
	
	public JeuPanel(Jeu j,Affichage a) throws IOException
	{
		super(j.getTj(),a);
		this.j=j;
		bouton_next = new JButton("Prochain Coup");
		bouton_next.addActionListener(this);
		numtrace = new JLabel("Numero du Tracé: "+j.traceencours);
		partrace = new JLabel("Par du Tracé: "+t.getTraces().get(j.traceencours).getPar());
		score = new JLabel("Score: "+j.getScore());
		posballe = new JLabel("Position de la balle: ("+j.getBalle().pos.x+";"+j.getBalle().pos.y+")");
		postrou = new JLabel("Position du trou: ("+t.getTraces().get(j.traceencours).arrive.x+";"+t.getTraces().get(j.traceencours).arrive.y+")");
		Insets insets = this.getInsets();
		this.setLayout(null);
		score.setBounds(320+insets.left,insets.top+10,100,30);
		this.add(score);
		posballe.setBounds(10+insets.left,650+insets.top,200,30);
		this.add(posballe);
		postrou.setBounds(10+insets.left,610+insets.top,200,30);
		this.add(postrou);
		numtrace.setBounds(440+insets.left,650+insets.top,130,30);
		this.add(numtrace);
		partrace.setBounds(580+insets.left,650+insets.top,130,30);
		this.add(partrace);
		bouton_next.setBounds(290+insets.left,650+insets.top,120,30);
		this.add(bouton_next);
		derniere_trajectoire = null;
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
		trace_cour = listeTraces.get(j.traceencours);
		
		g.setColor(COLOR_WHITE);
		quadrillage(g);
		
		trace_cour.depart.GAfficher(g, COLOR_BLACK);
		trace_cour.arrive.GAfficher(g, COLOR_BLUE);
		if(derniere_trajectoire!=null)
		{
			derniere_trajectoire.GAfficher(g);
		}
		j.getBalle().GAfficher(g);
	}
	
	public void quadrillage(Graphics g)
	{
		for(int i=0;i<=10;i++)
		{
			g.drawLine(0, (Simulation.T*upScale)-(i*upScale), 10*upScale, (Simulation.T*upScale)-(i*upScale));
			g.drawLine(i*upScale, 0, i*upScale,(10*upScale));
		}
	}
	public Point demanderCible()
	{
		String input_point = (String) JOptionPane.showInputDialog(this,
				"Entrez la cible sous la forme: x;y",
				JOptionPane.PLAIN_MESSAGE
				);
		Point cible = new Point();
		cible.x = Double.parseDouble(input_point.split(";")[0]);
		cible.y = Double.parseDouble(input_point.split(";")[1]);
		cible.existe = true;
		return cible;
	}
	
	public void update_labels() {
		int tec = j.traceencours;
		numtrace.setText("Numero du Tracé: "+tec);
		numtrace.paintImmediately(numtrace.getVisibleRect());
		int par = t.getTraces().get(j.traceencours).getPar();
		partrace.setText("Par du Tracé: "+par);
		partrace.paintImmediately(partrace.getVisibleRect());
		score.setText("Score: "+j.getScore());
		double pBx = Simulation.round(j.getBalle().pos.x,2);
		double pBy = Simulation.round(j.getBalle().pos.y, 2);
		posballe.setText("Position de la balle: ("+pBx+";"+pBy+")");
		posballe.paintImmediately(posballe.getVisibleRect());
		double pTx = Simulation.round(t.getTraces().get(j.traceencours).arrive.x,2);
		double pTy = Simulation.round(t.getTraces().get(j.traceencours).arrive.y,2);
		postrou.setText("Position du trou: ("+pTx+";"+pTy+")");
		postrou.paintImmediately(postrou.getVisibleRect());
	}
	
	public void actionPerformed(ActionEvent e) {
		Point arrive_trace = t.getTraces().get(j.traceencours).arrive;
		Balle balle = j.getBalle();
		if(!j.getBalle().pos.egale(arrive_trace))
		{
			Point cible = demanderCible();
			Point arrive = balle.CalculePointAtterissageBalle(balle.pos, cible);
			derniere_trajectoire = new Segment(balle.pos,arrive);
			JOptionPane.showMessageDialog(this, "Cible: ("+cible.x+";"+cible.y+")\nla balle atterrit en: ("+arrive.x+";"+arrive.y+")");
			j.incrementerScore();
			balle.pos = balle.CalculePointDepartBalle(arrive, balle.pos);
			JOptionPane.showMessageDialog(this, "La balle sera jouée depuis: ("+balle.pos.x+";"+balle.pos.y+")");

		}
		else
		{
			if(j.traceencours == t.getTraces().size()-1)
			{
				JOptionPane.showMessageDialog(this, "Fin de Partie, Score: "+j.getScore());
			}
			else
			{
				j.traceencours++;
			}
		}
		
		paintComponent(super.getGraphics());
		update_labels();
	}
	
}
