import java.util.Random;

public abstract class Entite {
	protected static int numeroEntite;
	protected int largeur;
	protected int hauteur;
	protected int rouge;
	protected int vert;
	protected int bleu;

	
	public Entite(){
		Random rand = new Random();
		numeroEntite++;
		largeur = Math.abs(rand.nextInt())%Constantes.Largeur; //abscisse
		hauteur = Math.abs(rand.nextInt())%Constantes.Hauteur; //ordonnée
		rouge = Math.abs(rand.nextInt())%256; // composante rouge
		vert = Math.abs(rand.nextInt())%256; // composante vert
		bleu = Math.abs(rand.nextInt())%256; // composante bleu
	}
}
