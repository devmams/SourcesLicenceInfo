#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "Ensb.hpp"

template class Ensb<>;
template class Ensb<int>;

int main(){
  Ensb<string> e0;
  Ensb<string> e1;
	e0.ajoute("tres");
	e0.ajoute("bien");
  e0.ajoute("bravo");
  cout<<"e0 : "<< e0.contenu() <<endl;
  e1.ajoute("tres");
	e1.ajoute("bifen");
  e1.ajoute("bravo");
  cout<<"e1 : "<< e1.contenu() <<endl;
  e0.differenceEns(e1);
  cout<<"-e : "<< e0.contenu() <<endl;
	return 0;
}
