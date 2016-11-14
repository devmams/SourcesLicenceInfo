import java.util.Scanner;

public class QuestionChaine extends Question{
	
	private String reponse;
	Scanner sc = new Scanner(System.in);
	
	public QuestionChaine(String texte,String reponse, int points) { 
		super(texte,points);
		this.reponse = reponse;
	}
	
	public boolean repondre() {
		String rep = sc.nextLine();
		if(rep.equals(reponse)){
			System.out.println("... vrai : "+ this.points +" Points ");
			return true;
		}
		else{
			System.out.println("... faux");
			return false;
		}
	}

}
