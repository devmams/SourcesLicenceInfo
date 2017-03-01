#ifndef ENSB_HPP
#define ENSB_HPP

#include <iostream>
#include <string>
using namespace std;
#define Taille 40000


template < typename T = string >
class Ensb {

 private:

	T tab[Taille];
	int nb;

 public:

  Ensb();
  ~Ensb();
  bool estVide();
  bool contient(T elt);
  void ajoute(T elt);
  void retire(T elt);
  string contenu();
  Ensb<T> intersectionEns(Ensb e);
  Ensb<T> unionEns(Ensb e);
  Ensb<T> differenceEns(Ensb e);
  int nbelem();

};
#include "Ensb.tpp"
#endif
