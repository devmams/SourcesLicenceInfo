package mainpack;
import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JGPanel extends JPanel {
	
	protected Terrain t;
	protected int upScale;
	protected Affichage aff;
	protected static Color C = new Color(111,244,5); // Vert Clair
	protected static Color B = new Color(5,237,244); // Bleu
	protected static Color V = new Color(22,211,37); // Vert
	protected static Color J = new Color(188,204,115); // Beige
	protected static Color S = new Color(27,124,25); // Vert Sapin
	protected static Color COLOR_BLACK = new Color(0,0,0);
	protected static Color COLOR_WHITE = new Color(255,255,255);
	protected static Color COLOR_BLUE = new Color(0,0,255);
	protected static Color COLOR_GRAY = new Color (90,90,90);
	protected static Color COLOR_YELLOW = new Color(255,255,0);
	protected static Color COLOR_MAGENTA = new Color(255,0,255);
	
	public JGPanel(Terrain terrain,Affichage a)
	{
		t=terrain;
		this.upScale = Affichage.upScale;
		aff = a;
	}
	
	protected Color assignerCouleur(char c) {
		Color res;
		switch(c) {
			case 'C':
				res= C;
				break;
			case 'B':
				res= B;
				break;
			case 'V':
				res= V;
				break;
			case 'J':
				res= J;
				break;
			case 'S':
				res= S;
				break;
			default:
				res= new Color(0,0,0);
		}
		return res;
	}
}
