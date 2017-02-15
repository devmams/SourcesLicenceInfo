#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "Ensa.hpp"

template class Ensa<>;
template class Ensa<int>;

int main(){
  //principale();
  Ensa<string> e0;
  //Ensa<int> e1

  cout<< "estVide ? : " << e0.estVide() << " nbElement : "<< e0.nbelem()<<endl;
  e0.ajoute("tres");
  e0.ajoute("bien");
  e0.ajoute("bien");
  e0.ajoute("assez");
  cout<< "estVide ? : " << e0.estVide() << " nbElement : "<< e0.nbelem()<<endl;
  cout<<">> " << e0.contenu() <<endl;
  e0.retire("tres");
  cout<< "estVide ? : " << e0.estVide() << " nbElement : "<< e0.nbelem()<<endl;
  cout<<">> " << e0.contenu() <<endl;
  return 0;
}
//gpp testEnsa.cpp -o test && ./test
