/**
 * @file Ensc.hpp
 * @author Fatma Maouloud, Mamadou Diallo
 * @date 20/02/2017 Création
 * @brief classe Ensc.
**/
#ifndef ENSC_HPP
#define ENSC_HPP
#include <iostream> // cout et cin
#include <string> // utilisation du type string
using namespace std;

template < typename T = string >
class Ensc{
	private:
	struct Maillon{
		T ch;
		Maillon* suiv;
	};
	Maillon* tete;
	int nb;

	public:
    Ensc();
    ~Ensc();
    bool estVide();
    bool contient(T elt);
    void ajoute(T elt);
    void retire(T elt);
    string contenu();
    void intersectionEns(Ensc & e);
    void unionEns(const Ensc & e);
    void differenceEns(Ensc & e);
    int nbelem();

};

#include "Ensc.tpp"
#endif
