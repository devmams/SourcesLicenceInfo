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
  Ensa<string> e1;
  //Ensa<int> e1

  cout<< "estVide ? : " << e0.estVide() << " nbElement : "<< e0.nbelem()<<endl;
  e0.ajoute("tres");
  e0.ajoute("bien");
  e0.ajoute("bien");
  e0.ajoute("assez");
   e0.ajoute("askkkksez");

  /*cout<< "estVide ? : " << e0.estVide() << " nbElement : "<< e0.nbelem()<<endl;
  cout<<">> " << e0.contenu() <<endl;
  e0.retire("tres");
  cout<< "estVide ? : " << e0.estVide() << " nbElement : "<< e0.nbelem()<<endl;
  cout<<">> " << e0.contenu() <<endl;*/
  
      e1.ajoute("tres");
	e1.ajoute("bien");
	e1.ajoute("bien");
	e1.ajoute("assez");
	cout<< e0.differenceEns(e1).contenu() <<endl;
		/*Ensa<int> e0;
		Ensa<int> e1;
  //Ensa<int> e1

  cout<< "estVide ? : " << e0.estVide() << " nbElement : "<< e0.nbelem()<<endl;
  e0.ajoute(1);
  e0.ajoute(33);
  e0.ajoute(2);
  e0.ajoute(1);
    e0.ajoute(4);

  cout<< "estVide ? : " << e0.estVide() << " nbElement : "<< e0.nbelem()<<endl;
  cout<<">> " << e0.contenu() <<endl;
  e0.retire("tres");
  cout<< "estVide ? : " << e0.estVide() << " nbElement : "<< e0.nbelem()<<endl;
  cout<<">> " << e0.contenu() <<endl;
  
    
    e1.ajoute(33);
	e1.ajoute(2);
	e1.ajoute(1);
	e1.ajoute(1);*/
	
	//cout<< e0.intersectionEns(e1).contenu() <<endl;
	//cout<< e0.unionEns(e1).contenu() <<endl;
	//cout<< e0.differenceEns(e1).contenu() <<endl;

  return 0;
}
//gpp testEnsa.cpp -o test && ./test
