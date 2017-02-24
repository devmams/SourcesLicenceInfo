
#ifndef ENSB_HPP
#define ENSB_HPP


#include <iostream>
#include <string>
using namespace std;
#define Taille 40000 


template < typename T = string >

class Ensb {
	
 private :
	T tab[Taille];
	unsigned int nb; 

 public:

    Ensb();
    ~Ensb();
    bool estVide();
    bool contient(T mot);
    void ajoute(T mot);
    void retire(T mot);
    string contenu();
    Ensb<T> intersectionEns(Ensb e);
    Ensb<T> unionEns(Ensb e);
    Ensb<T> differenceEns(Ensb e);
    int nbelem();
    

};
#include "Ensb.tpp"
#endif
