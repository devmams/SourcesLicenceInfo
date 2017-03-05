#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "Ensb.hpp"

template class Ensb<>;
template class Ensb<int>;

int main(){
  Ensb<int> eInt;
  Ensb<string> eString;
	/*e0.ajoute(1);
	e0.ajoute(5);
  e0.ajoute(4);
  e0.ajoute(3);
  e0.ajoute(0);
  e0.ajoute(10);
  e0.ajoute(2);*/
  eString.ajoute("tres");
	eString.ajoute("bien");
  eString.ajoute("avec");
  eString.ajoute("bravo");
  eString.ajoute("aaac");
  eString.ajoute("aaaab");
  cout<<"eString : "<< eString.contenu() <<endl;;
	return 0;
}
