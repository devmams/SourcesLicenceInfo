#include <stdio.h>
#include <stdlib.h>
#define BUFFSIZE 512
#define PROMPT ">>>"

int fibonacci(int n){
  if(n == 0){
    return 0;
  }
  long a = 0;
  long b = 1;
  long tmp;
  while (n >= 2) {
    tmp = a;
    a = b;
    b = a + b;
    --n;
  }
  return b;
}


/*int fibonacci(int n){
  if (n < 2)
    return abs(n);
  return fibonacci(n-1) + fibonacci(n-2);
}*/


void repl() {
  char buff[BUFFSIZE] ;
  int  n = 0 ;
  printf(PROMPT) ;
  while(fgets(buff, BUFFSIZE, stdin) != NULL)
    if (buff[0] == 'q' || buff[0] == 'Q')  break;
    else {
      if (sscanf(buff, "%d", &n) > 0)
        printf("F_%d=%d\n" PROMPT, n, fibonacci(n));
      else
        printf("incomprehensible : %s" PROMPT, buff) ;
    }
    puts("Good bye.") ;
}

int main(){
  repl() ;
}
