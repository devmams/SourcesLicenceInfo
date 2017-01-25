#include <iostream>
#include <cassert>
#include <cmath>
#include <string>
using namespace std;

/*-------------------- 1 ----------------------------*/
/**
 *role : determine nombre d'occurence
 *entree : x cararter et s chaine de caractere
 *sortie : occ nomnbre d'occurende de x dans s
 *pré-condition : x char , s string
 *post-condition : occ nb
**/

int main(){
  string s;
  char x;
  int occ = 0;
  std::cout << "entrez une chaine" << std::endl;
  std::cin >> s;
  std::cout << "entrez la lettre à rechercher" << std::endl;
  std::cin >> x;
  for(int i=0 ;i<s.size() ;i++){
    if(x == s[i])
      occ++;
  }
  std::cout << "il y a "<< occ << " " << x << " dans " << s << std::endl;
  return 0;
}
