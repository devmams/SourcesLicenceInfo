/**
 * @file Ensb.tpp
 * @author Fatma MAOULOUD, Mamadou DIALLO
 * @date 20/02/2017 Création
 * @brief Implémentation des méthodes de la classe Ensb.
**/
#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "Ensb.hpp"

template class Ensb<>;
template class Ensb<int>;

int main(){
  Ensb<string> e0; //création premier ensemble.
  Ensb<string> e1; //création deuxième ensemble.
  cout<< "estVide ? : " << e0.estVide()<<endl;
  e0.ajoute("tres");
  e0.ajoute("bien");
  e0.ajoute("assez");
  e0.ajoute("bravo");
  e0.ajoute("bravo");

  e1.ajoute("tres");
  e1.ajoute("bien");
  e1.ajoute("assez");

  cout <<"e0  : "<< e0.contenu() <<endl; // contenu de e0
  cout <<"e1  : "<< e1.contenu() <<endl; // contenu de e1
  e0.unionEns(e1);
  //e0.intersectionEns(e1);
  //e0.differenceEns(e1);
  cout <<"res : "<< e0.contenu() <<endl; // resultat

  return 0;
}
