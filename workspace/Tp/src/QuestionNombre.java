import java.util.Scanner;

public class QuestionNombre extends Question{
	
	private int reponse;
	Scanner sc = new Scanner(System.in);
	
	public QuestionNombre(String texte,int reponse, int points) { 
		super(texte,points);
		this.reponse = reponse;
	}
	
	public boolean repondre() {
		int rep = sc.nextInt();
		if(rep == reponse){
			System.out.println("... vrai : "+ this.points +" Points ");
			return true;
		}
		else{
			System.out.println("... faux");
			return false;
		}
	}

}
