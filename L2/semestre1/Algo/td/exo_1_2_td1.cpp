#include <iostream>
#include <cassert>
#include <cmath>

/*-------------------- 1 ----------------------------*/
/**
 *role : calcul d'une fonction f(x)
 *entree : x réel
 *sortie : y = f(x) réel
 *pré-condition : x réel
 *post-condition : y = f(x) réel
**/

int main(){
  float x,y;
  std::cout << "entrez un réel ?" << std::endl;
  std::cin >> x;
  if(x<-1){
    y = log(-x);
  }
  else if(x>=-1 && x<=1){
    y = 0;
  }
  else{
    y = sqrt(x-1);
  }
  std::cout << "y = " << y << std::endl;
  return 0;
}
