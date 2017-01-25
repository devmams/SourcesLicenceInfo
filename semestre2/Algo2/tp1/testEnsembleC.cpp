#include <iostream>
#include <string>
#include <vector>
#include "ensembleC.hpp"
using namespace std;


int main(){
  ensembleC e;
  cout<< "estVide ? : " << e.estVide() << " nbElement : "<< e.nbelem()<<endl;
  e.ajoute("tres");
  e.ajoute("bien");
  cout<< "estVide ? : " << e.estVide() << " nbElement : "<< e.nbelem()<<endl;
  cout<<">> " << e.contenu() <<endl;
  e.retire("tres");
  cout<< "estVide ? : " << e.estVide() << " nbElement : "<< e.nbelem()<<endl;
  cout<<">> " << e.contenu() <<endl;
  return 0;
}
