#include <stdio.h>
#include <stdlib.h>

double f(double x)
{
  return (x*x*x)-2;

}

double dichotomie(double a, double b, double eps){
  double min;
  if(a<b)
    min = a;
  else
    min = b;
  double max;
  if(a<b)
    max = b;
  else
    max = a;

  while(max - min > eps){
    double m = (min + max ) /2;
    if(f(m)*f(min) < 0){
      max = m;
    }
    else{
      min = m;
    }
  }
  return (min + max )/2;
}

int main()
{
  //printf("f(%d) = ", f(0));
  printf("F(%d) = %d\n", 0 , f(0));
  printf("dich_%d = %d\n", 0 , dichotomie(0,2,1));


}
