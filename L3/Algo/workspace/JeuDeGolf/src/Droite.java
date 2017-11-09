public class Droite {
	
	protected double a,b,c;
	
	public Droite(Point A,Point B) {
		CalculCoefficientsDroite(A,B);
	}
	
	public Droite(double a,double b,double c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public void CalculCoefficientsDroite(Point A, Point B){
		b = -B.getX() + A.getX();
		a = B.getY() - A.getY();
		c = -a*A.getX()-b*A.getY();
	}
	
	public void afficheDroite(){
		System.out.println("eq : "+a+ "X + "+ b +"Y +"+c+" = 0" );
	}
	
	public int appartient(Point p){
		double res = a*p.getX()+b*p.getY()+c;
		if(res == 0)
			return 0;
		else if (res>0)
			 return 1;
		else
			 return -1;
	}
	
	public boolean intersecte(Droite d){
		if(a==0 && d.a==0){
			return false;
		}else if(b==0 && d.b==0){
			return false;
		}else if(b==0 || d.b==0){
			return true;
		}else if(a/b==d.a/d.b){
			return false;
		}else{
			return true;
		}
	}
	
	public Point pointIntersection(Droite d){
		double x,y;
		if(this.intersecte(d)){
			x = 0;
			y = 0;
			if(a == 0 && (b != 0 )){
				y = -(c/b);
				if(d.a != 0)
					x = (((-d.b/d.a)*y)-(d.c/d.a));
			}
			else if(b == 0 && a != 0){
				x = -(c/a);
				if(d.b != 0)
					y = (((-d.a/d.b)*x)-(d.c/d.b));
			}				
			else if(d.a == 0 && d.b != 0){
				y = -(d.c/d.b);
				if(a != 0)
					x = (((-b/a)*y)-(c/a));
			}
			else if(d.b == 0 && d.a != 0){
				x = -(d.c/d.a);
				if(b != 0)
					y = (((-a/b)*x)-(c/b));
			}
			else{
				x = ((d.c/d.b)-(c/b))/((a/b)-(d.a/d.b));
				y = ((-a/b)*x) - (c/b);
			}
			return new Point(x, y);
		}
		else{
			return new Point();
		}
	}
}