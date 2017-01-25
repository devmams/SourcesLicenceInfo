#include <iostream>
#include <cassert>
#include <cmath>

/*-------------------- 1 ----------------------------*/
/**
 *role : calcul quotient et reste entre deux entiers naturels
 *entree : a,b entiers naturels
 *sortie : q entier naturel , r réel
 *pré-condition : a,b entiers naturels
 *post-condition : q entier naturel et r réel
**/

int main(){
  int a,b,q,r;
  std::cout << "entrez un entier (dividende)" << std::endl;
  std::cin >> a;
  std::cout << "entrez un entier (diviseur)" << std::endl;
  std::cin >> b;
  q = a / b;
  r = a % b;
  std::cout << "q : " << q << "\nr : " << r << std::endl;
  return 0;
}
