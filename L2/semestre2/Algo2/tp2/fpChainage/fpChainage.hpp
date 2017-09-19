/**
 * @file FP_ch.hpp
 * @author Fatma Maouloud, Mamadou Diallo
 * @date 22/03/2017 Création
 * @brief classe FP_ch.
**/
#ifndef FP_CH_HPP
#define FP_CH_HPP

#include <iostream> // cout et cin
#include <string>   // utilisation du type string
using namespace std;

template < typename T>
class FP_ch {

  private:
    struct Maillon {
      T elt;
      Maillon* suiv;
    };
    Maillon *tete;
    int nb;
    bool estDans(T e);

  public:
    FP_ch();
    ~FP_ch();
    bool estVide();
    void enfiler(T e);
    void defiler();
    T premier();

};

#include "File_chainage.tpp"
#endif
