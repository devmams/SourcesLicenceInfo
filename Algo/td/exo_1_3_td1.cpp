#include <iostream>
#include <cassert>
#include <cmath>

/*-------------------- 1 ----------------------------*/
/**
 *role : calcul du k-ième terme d'une suite
 *entree : k entier naturel positif
 *sortie : u = Uk entier naturel positif
 *pré-condition : k , k>0
 *post-condition : u , u = Uk
**/

int main(){
  int k,u;
  std::cout << "entrez un entier ?" << std::endl;
  std::cin >> k;
  u = 1;
  for (int i=0 ; i<k ; i++) {
    std::cout << "U"<<i<< " = " << u << std::endl;
    u = 5*u - 3;
  }
  std::cout << "U"<<k<< " = " << u << std::endl;
  return 0;
}
