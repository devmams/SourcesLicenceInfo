/**
 * @file Ensb.hpp
 * @author Fatma Maouloud, Mamadou Diallo
 * @date 20/02/2017 Création
 * @brief classe Ensb.
**/
#ifndef ENSB_HPP
#define ENSB_HPP

#include <iostream> // cout et cin
#include <string> // utilisation du type string
#define Taille 40000 //capacité du tableau tab

template < typename T = std::string >
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
  std::string contenu();
  void intersectionEns(Ensb & e);
  void unionEns(const Ensb & e);
  void differenceEns(Ensb & e);
};
#include "Ensb.tpp"
#endif
