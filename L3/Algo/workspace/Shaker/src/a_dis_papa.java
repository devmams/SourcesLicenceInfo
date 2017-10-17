import java.util.Scanner;


public class a_dis_papa {

	
	static void disPapa(String s){
		System.out.println(s.substring(4, s.length()));
	} 
	
	
	public static void main(String[] args) {
		String s;
		Scanner sc = new Scanner(System.in);
		s = sc.nextLine();
		disPapa(s);
	}

}
