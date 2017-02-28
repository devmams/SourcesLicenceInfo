/**
 * @file Ensa.hpp
 * @author Fatma Maouloud, Mamadou Diallo
 * @date 20/02/2017 Création
 * @brief teste des méthides de la classe Ensa
**/
#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "Ensa.hpp"

template class Ensa<>;
template class Ensa<int>;

int main(){
  Ensa<string> e0; //création prémier ensemble.
  Ensa<string> e1; //création deuxième ensemble.
  cout<< "estVide ? : " << e0.estVide() << " nbElement : "<< e0.nbelem() <<endl;
  e0.ajoute("tres");
  e0.ajoute("bien");
  e0.ajoute("bien");
  e0.ajoute("assez");
  e0.ajoute("askkkksez");

  e1.ajoute("tres");
	e1.ajoute("bien");
	e1.ajoute("bien");
	e1.ajoute("assez");
	cout<< e0.unionEns(e1).contenu() <<endl; //union et affichage des deux ensembles.

  return 0;
}
//gpp testEnsa.cpp -o test && ./test
