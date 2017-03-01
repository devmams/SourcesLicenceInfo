#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "Ensb.hpp"

template class Ensb<>;
template class Ensb<int>;

int main(){
  Ensb<string> e0;
	e0.ajoute("tres");
	e0.ajoute("bien");
	e0.ajoute("bien");
	e0.ajoute("assez");
	cout<< "estVide ? : " << e0.estVide() << " , contient : "<< e0.contient("tres") <<endl;
	return 0;
}
