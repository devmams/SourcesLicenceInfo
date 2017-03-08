#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "Ensb.hpp"

template class Ensb<>;
template class Ensb<int>;

int main(){
  Ensb<int> eInt;
  Ensb<int> eInt2;
  Ensb<string> eString;
	eInt.ajoute(1);
	eInt.ajoute(5);
  eInt.ajoute(4);
  eInt.ajoute(3);
  eInt.ajoute(0);
  eInt.ajoute(0);
  eInt.ajoute(10);
  eInt.ajoute(10);
  eInt.ajoute(2);
  cout<<"eInt : "<< eInt.contenu() <<endl;

  eInt2.ajoute(0);
  eInt2.ajoute(0);
  eInt2.ajoute(10);
  eInt2.ajoute(10);
  eInt2.ajoute(2);
  cout<<"eInt2 : "<< eInt2.contenu() <<endl;
  /*eString.ajoute("tres");
	eString.ajoute("bien");
  eString.ajoute("avec");
  eString.ajoute("bravo");
  eString.ajoute("aaac");
  eString.ajoute("aaaab");
  cout<<"eString : "<< eString.contenu() <<endl;*/
  eInt.differenceEns(eInt2);
  cout<<"eIntF : "<< eInt.contenu() <<endl;

	return 0;
}
