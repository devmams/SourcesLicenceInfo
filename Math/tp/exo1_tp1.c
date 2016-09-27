#include <stdio.h>

int fibonacci(int n){

  if (n < 2)
    return n;
  return fibonacci(n-1) + fibonacci(n-2);
}

int main(){
  int n = 10;
  printf("F_%d = %d\n", n , fibonacci(n));

}
