package mainpack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
public class MenuPanel extends JPanel implements ActionListener {

	private Affichage aff;
	private JButton bouton_jouer,bouton_triqt,bouton_tester;
	public MenuPanel(Affichage a)
	{
		aff = a;
		bouton_jouer = new JButton("Jouer");
		bouton_triqt = new JButton("Terrain");
		bouton_triqt.addActionListener(this);
		bouton_jouer.addActionListener(this);
		this.add(bouton_jouer);
		this.add(bouton_triqt);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
			String cf = (String) JOptionPane.showInputDialog(this,
					"Entrer le chemin du fichier descripteur du terrain:",
					JOptionPane.PLAIN_MESSAGE
					);
			int t = Integer.parseInt((String) JOptionPane.showInputDialog(this,
					"Entrer le nombre maximum de triangles intersectés pour la construction du QuadTree:",
					JOptionPane.PLAIN_MESSAGE
					));
			
		if(e.getSource()==bouton_triqt)
		{
			try {
				aff.afficherTerrain(cf, t);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource()==bouton_jouer)
		{
			try {
				aff.afficherJeu(cf, t);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
