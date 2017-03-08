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
#include "Ensc.hpp"

template class Ensc<>;
template class Ensc<int>;

int main(){
  Ensc<string> e0; //création prémier ensemble.
  Ensc<string> e1; //création deuxième ensemble.
  cout<< "estVide ? : " << e0.estVide() <<" contient : "<< e0.contient("tres")<< " nbElement : "<< e0.nbelem() <<endl;
  e0.ajoute("bien");
  e0.ajoute("tres");
  e0.ajoute("assez");
  e0.ajoute("bien");
  e0.ajoute("bravo");
  cout<< "e0 : "<< e0.contenu() <<endl;
  e1.ajoute("tres");
  e1.ajoute("assez");
  e1.ajoute("bien");
  cout<< "e1 : "<< e1.contenu() <<endl;
  e0.differenceEns(e1);
  cout<< "-e : "<< e0.contenu() <<endl;
  /*cout<< "e0 : "<< e0.contenu() <<endl;
  e1.ajoute("tres");
	e1.ajoute("bien");
	e1.ajoute("assez");
  cout<< "e1 : "<< e1.contenu() <<endl;
	e0.unionEns(e1);
  cout <<"-e : "<< e0.contenu() <<endl; //union et affichage des deux ensembles.
  */
  return 0;
}
//gpp Ensa.cpp -o test && ./test
