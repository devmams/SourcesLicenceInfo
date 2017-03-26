/**
 * @file Ensa.hpp
 * @author Fatma MAOULOUD, Mamadou DIALLO
 * @date 20/02/2017 Création
 * @brief classe Ensa.
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
    void intersectionEns(Ensa & e);
    void unionEns(const Ensa & e);
    void differenceEns(Ensa & e);
};

#include "Ensa.tpp"
#endif
