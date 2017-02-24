/**
 * @file Ensa.hpp
 * @author Fatma Maouloud, Mamadou Diallo
 * @date 20/02/2017 Création
 * @brief classe Ensemble avec chainage sans doublons.
**/
#ifndef ENSA_HPP
#define ENSA_HPP


#include <iostream> // cout et cin
#include <string>   // utilisation du type string
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
    Ensa<T> intersectionEns(Ensa e);
    Ensa<T> unionEns(Ensa e);
    Ensa<T> differenceEns(Ensa e);
    int nbelem();

};

#include "Ensa.tpp"
#endif
