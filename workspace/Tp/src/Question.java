
public abstract class Question {
	
	protected String texte;
	protected int points;
	
	// Constructeur  ́etant donne le texte de la question et le nombre de points
	public Question(String texte, int points) { 
		this.texte = texte;
		this.points = points;
	}

	// Accesseurs
	public int getPoints() {
		return points;
	}

	public String getTexte() {
		return this.texte;
	}
	
	// Affiche la question
	public void poser() {
		System.out.print(getTexte() +" ? ");
	}

	// Lit la reponse et retourne true si c’est la bonne
	public abstract boolean repondre();
	
	// Affiche la question, lit la reponse et retourne le nombre de points
	public int jouer() {
		poser();
		if(repondre())
			return this.points;
		else
			return 0;
	}
}
