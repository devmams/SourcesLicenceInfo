#include <iostream>
#include <cassert>
#include <cmath>
#include <string>
using namespace std;

/*-------------------- 1 ----------------------------*/
/**
 *role : inverse d'une chaine
 *entree : s chaine de caractere
 *sortie : t chane inverse de s
 *pré-condition : s chaine de caractere
 *post-condition : t chaine inverse de s
**/

int main(){
  string s,t;
  char lettre;
  std::cout << "entrez une chaine ?" << std::endl;
  std::cin >> s;
  t = "";
  std::cout << "size : " << s.size() << std::endl;
  for (int i=0 ; i<s.size() ; i++) {
    t = s[i] + t;
  }
  std::cout << "t : " << t << std::endl;
  return 0;
}
