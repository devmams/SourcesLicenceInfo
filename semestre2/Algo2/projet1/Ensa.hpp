
#ifndef ENSA_HPP
#define ENSA_HPP


#include <iostream>
#include <string>
using namespace std;

template < typename T = string >
class Ensa {

  private:

    struct Maillon {
      T ch;
      Maillon* suiv;
    };
    Maillon *tete;
    int nb;

  public:

    Ensa();
    ~Ensa();
    bool estVide();
    bool contient(T mot);
    void ajoute(T mot);
    void retire(T mot);
    string contenu();
    void intersectionEns(Ensa e);
    void unionEns(Ensa e);
    void differenceEns(Ensa e);
    int nbelem();
};

#include "Ensa.tpp"
#endif
