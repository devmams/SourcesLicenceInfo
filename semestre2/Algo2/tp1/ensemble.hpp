
#ifndef GENERIQUEENSEMBLEC_HPP
#define GENERIQUEENSEMBLEC_HPP


#include <iostream>
#include <string>
using namespace std;

template < typename Chaine = string >
class ensemble {

  private:

    struct Maillon {
      string ch;
      Maillon* suiv;
    };
    Maillon *tete;
    int nb;

  public:

    ensemble();
    ~ensemble();
    bool estVide();
    bool contient(string mot);
    void ajoute(string mot);
    void retire(string mot);
    string contenu();
    int nbelem();
};

#include "ensemble.tpp"
#endif
