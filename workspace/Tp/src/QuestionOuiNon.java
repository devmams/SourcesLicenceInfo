import java.util.Scanner;

public class QuestionOuiNon extends Question{

	private String reponse;
	Scanner sc = new Scanner(System.in);
	
	public QuestionOuiNon(String texte,String reponse, int points) { 
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
