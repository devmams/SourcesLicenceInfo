import java.text.DecimalFormat;
import java.util.Scanner;


public class c_argent_de_poche {
	
	public static void main(String[] args) {
		int n;
		double somme;
		double nb;
		String[] doubl;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		sc.nextLine();
		doubl = sc.nextLine().split(" ");
		double min = 20;
		double max = 0;
		double moy = 0;
		for(String d : doubl){
			nb = Double.valueOf(d);
			moy+= nb;
			if(nb<min){
				min = nb;
			}
			if(max<nb){
				max = nb;
			}
		}
		moy = moy/n;
		somme = (20 - (max-min))*(moy*moy)*0.01;
		DecimalFormat df = new DecimalFormat ( ) ;
		df.setMaximumFractionDigits ( 2 ) ;
		System.out.println( df.format (somme));
	}

}