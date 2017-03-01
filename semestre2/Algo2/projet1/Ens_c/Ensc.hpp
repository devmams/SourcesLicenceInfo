#ifndef ENSC_HPP
#define ENSC_HPP


#include <iostream>
#include <string>
using namespace std;

template < typename T = string >
class Ensc{

	private:
	
	struct Maillon{
		T chaine;
		Maillon* suiv;
	};
	Maillon* tete;
	int nb;
		
		
		
	public:

    Ensc();
    ~Ensc();
    bool estVide();
    bool contient(T mot);
    void ajoute(T mot);
    void retire(T mot);
    string contenu();
    Ensc<T> intersectionEns(Ensc e);
    Ensc<T> unionEns(Ensc e);
    Ensc<T> differenceEns(Ensc e);
    int nbelem();
    
		
		
		} 





};

#include "Ensc.tpp"
#endif
