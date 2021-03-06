/**
 * Classe permettant l'affichage graphique du jeu.
 * 
 * 
 * @author Glenn PLOUHINEC / Mamadou DIALLO
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Jeu extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	//nécessaire à la méthode de double buffering
	Image offI;
	//nécessaire à la méthode de double buffering
	Graphics offG;
	//création d'un objet plateau.
	Plateau plt;
	//création d'un objet Fabrique.
	Fabrique f;
	//création d'un objet Pièce.
	Piece p;
	//variable permettant de reprendre une patie
	boolean partieEnCours = true;

	/**
	 * Construit une Parie de Jeu.
	 */
	public Jeu() {
		plt = new Plateau();
		f = new Fabrique();
		p = f.creerPiece();
		// Ajout de l'écouteur de clavier
		this.addKeyListener(this);
	}

	/**
	 * Fonction Principale d'une partie de jeu.
	 */
	public static void main(String[] args) {
		Jeu g = new Jeu();
		g.setVisible(true);
		g.setSize(200, 420);
	    g.setLocationRelativeTo(null);
	    g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.jouer();
	}

	/**
	 * crée une nouvelle piece et la deplace vers le bas chaque 3 secondes.
	 */
	private void jouer () {
		// Boucle infinie (tant que mon_process existe et n'est pas interrompu)
		while(true){
			while (partieEnCours){	
				plt.ajouter(p);
				repaint();
				Piece tmp = p;
				p = p.versLeBas();
				plt.retirer(tmp);
				if(!plt.accepter(p)){
					p = tmp;
					plt.ajouter(p);
					plt.supprimerLigne();
					repaint();
					p = f.creerPiece();
					if(!plt.accepter(p.versLeBas())){
						partieEnCours = false;
					}
				}
				plt.ajouter(p);
				repaint();
				//suspend la methode de 0.3 secondes.
				try {
					Thread.sleep(300);
				}
				catch (InterruptedException e) {
					return;
				}
			}
			partieEnCours = true;
			repaint();
			//colorie tout notre plateau en vers à la fin d'une partie.
			plt = new Plateau('a');
			repaint();
			//suspend la methode de 0.3 secondes.
			try {
				Thread.sleep(300);
			}
			catch (InterruptedException e) {
				return;
			}
		}
	}

	/**
	 * actualise notre écran graphique
	 */
	public void update(Graphics g){
		if(offI == null){
			//crée une Image 200x420.
			offI = createImage(200, 420);
			offG = offI.getGraphics();
		}
		offG.clearRect(0, 0, 200, 420);
		paint(offG);
		g.drawImage(offI, 0, 0, null);
	}

	/**
	 * colorie chaque cellule de notre plateau
	 * @param un Graphique.
	 */
	public void paint (Graphics g) {
		for (int k=0;k<10;k++) {
			for (int l=0;l<20;l++) {
				if (plt.getCellule(k,l).getC() == 'n') {
					g.setColor(Color.black);//noir
					g.fill3DRect (20*k,20*l,20,20,true);
				}
				else{
					switch (plt.getCellule(k,l).getC()) {
					case 'a':     g.setColor(new Color(0,192,0));    g.fill3DRect (20*k,20*l,20,20,true); break; //vert
					case 'b':     g.setColor(Color.pink);            g.fill3DRect (20*k,20*l,20,20,true); break; //rouge
                    case 'c':     g.setColor(new Color(0,128,224));  g.fill3DRect (20*k,20*l,20,20,true); break; //bleu
                    case 'd':     g.setColor(new Color(0,192,192));  g.fill3DRect(20*k,20*l,20,20,true); break; //cyan
                    case 'e':     g.setColor(Color.orange);          g.fill3DRect(20*k,20*l,20,20,true); break; //orange
                    case 'f':     g.setColor(Color.white);        	 g.fill3DRect(20*k,20*l,20,20,true); break; //blanc
                    case 'g':     g.setColor(Color.magenta);         g.fill3DRect(20*k,20*l,20,20,true); break; //magenta
					}
				}
			}
		}
	}

	/**
	 * deplace la pièce en fonction de la direction choisie.
	 * @param un keyEvent.
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT : if (p != null) {//deplace la pièce à gauche.

			Piece tmp = p; 
			p = p.versLaGauche();
			plt.retirer(tmp);
			if(!plt.accepter(p)){
				p = tmp;
			}
			plt.ajouter(p);
			repaint();
		}
		break;
		case KeyEvent.VK_RIGHT : if (p != null){//deplace la pièce à droite.
			Piece tmp = p;
			p = p.versLaDroite();
			plt.retirer(tmp);
			if(!plt.accepter(p)){
				p = tmp;
			}
			plt.ajouter(p);
			repaint();
		}
		break;
		case KeyEvent.VK_UP : if (p != null) {//fais tourner la pièce.
			Piece tmp = p;
			p = f.rotationPiece(p);
			plt.retirer(tmp);
			if(!plt.accepter(p)){
				p = tmp;;
			}
			plt.ajouter(p);
			repaint();
		}
		case KeyEvent.VK_DOWN : if (p != null) {//deplace la pièce en bas.
			plt.retirer(p);
			if(!plt.accepter(p.versLeBas())){
				plt.ajouter(p);
				repaint();
			}
			else{
				p = p.versLeBas();
				plt.ajouter(p);
				repaint();
			}
		}
		break;
		default : repaint();
		}
	}
	
	/**
	 * utile pour le KeyListener.
	 * @param un keyEvent.
	 */
	public void keyReleased(KeyEvent e){
	}

	/**
	 * utile pour le KeyListener.
	 * @param un keyEvent.
	 */
	public void keyTyped(KeyEvent e){
	}

}
