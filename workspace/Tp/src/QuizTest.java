
public class QuizTest {

	public static void main(String[] args) {

		Quiz quiz = new Quiz();
		quiz.add(new QuestionChaine("Quel animal symbolise la Chine","panda",2));
		quiz.add(new QuestionNombre("En quelle annee le 21e siecle a-t-il commence",2001,1));
		quiz.add(new QuestionOuiNon("La formule logique faux=>vrai est-elle vraie","oui",3));
		int gain = quiz.jouer();
		System.out.println("Gain total : " + gain);
	}

}
