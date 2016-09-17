#include <iostream>
#include <cassert>
#include <cmath>
#include <string>
using namespace std;

/*-------------------- 1 ----------------------------*/
/**
 *role : extraction d'une sous chaine
 *entree : d,f entier et c chaine
 *sortie : sc sous chaine de c
 *pré-condition : 0 <= d < f < taille(c)
 *post-condition : sc sous chaine de c compris entre d et f
**/

int main(){
  string c,sc;
  int d,f;
  std::cout << "entrez une chaine" << std::endl;
  std::cin >> c;
  std::cout << "entrez le debut de la sous chaine" << std::endl;
  std::cin >> d;
  std::cout << "entrez la fin de la sous chaine" << std::endl;
  std::cin >> f;
  sc = "";
  for(int i=d ;i<=f ;i++){
    sc = sc + c[i];
  }
  std::cout << "la sc de "<< d << " à " << f << " dans " << c << " est : " << sc << std::endl;
  return 0;
}
