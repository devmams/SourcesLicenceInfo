import java.util.Scanner;


public class b_rectangle {

	static void dessine(int a,int b,char c){
		String res="";
		for(int i=0 ;i<b ;i++){
			res += c;
		}
		for(int j=0 ;j<a ;j++){
			System.out.println(res);
		}
	}
	public static void main(String[] args) {
		int a,b;
		char c;
		String s; 
		Scanner sc = new Scanner(System.in);
		a = sc.nextInt();
		b = sc.nextInt();
		sc.nextLine();
		s = sc.nextLine();
		c = s.charAt(0);
		dessine(a, b, c);
	}

}
