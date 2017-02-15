#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "Ensa.hpp"

template < typename T>
Ensa<T>::Ensa(){
  this->tete = NULL;
  this->nb = 0;
}

template < typename T>
Ensa<T>::~Ensa(){}

template < typename T>
bool Ensa<T>::estVide(){
  return this->nb == 0;
}

template < typename T>
bool Ensa<T>::contient(T mot){
  bool res = false;
  Maillon *cour = new Maillon;
  cour = this->tete;
  for(int i=0 ;i<nb ;i++){
    if(cour->ch == mot){
      res = true;
    }
    cour = cour->suiv;
  }
  return res;
}


template < typename T>
void Ensa<T>::ajoute(T mot){
  Maillon *nv = new Maillon;
  nv->ch = mot;
  if(estVide()){
    nv->suiv = this->tete;
    this->tete = nv;
    this->nb++;
  }
  else if(!contient(mot)){
    Maillon *cour = this->tete;
    while(cour->suiv != NULL){
      cour = cour->suiv;
    }
    nv->suiv = cour->suiv;
    cour->suiv = nv;
    this->nb++;
  }

}

template < typename T>
void Ensa<T>::retire(T mot){
  Maillon *cour = this->tete;
  Maillon *prec = this->tete;
  if(!estVide() || contient(mot)){
      while(cour->ch != mot){
        prec = cour;
        cour = cour->suiv;
      }
      if(cour == this->tete){
        prec = cour->suiv;
        this->tete = prec;
      }
      else{
        prec->suiv = cour->suiv;
      }
      delete(cour);
      this->nb--;
    }
}

template < typename T>
string Ensa<T>::contenu(){
  Maillon *cour = this->tete;
  stringstream sst;
  while(cour->suiv != NULL){
    sst << cour->ch << " ";
    cour = cour->suiv;
  }
  sst << cour->ch;
  return sst.str();
}

/*template < typename T>
void Ensa<T>::intersectionEns(Ensa e){}

template < typename T>
void Ensa<T>::unionEns(Ensa e){}

template < typename T>
void Ensa<T>::differenceEns(Ensa e){}
*/
template < typename T>
int Ensa<T>::nbelem(){
  return this->nb;
}
