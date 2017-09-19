#include <iostream>
#include <string>
#include <vector>
#include "ensembleC.hpp"

template < typename Chaine>
ensemble<Chaine>::ensemble(){
  this->tete = NULL;
  this->nb = 0;
}

template < typename Chaine>
ensemble<Chaine>::~ensemble(){}

template < typename Chaine>
bool ensemble<Chaine>::estVide(){
  return this->nb == 0;
}

template < typename Chaine>
bool ensemble<Chaine>::contient(string mot){
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

template < typename Chaine>
void ensemble<Chaine>::ajoute(string mot){
  Maillon *nv = new Maillon;
  nv->ch = mot;
  if(estVide()){
    nv->suiv = this->tete;
    this->tete = nv;
  }
  else if(!contient(mot)){
    Maillon *cour = new Maillon;
    cour = this->tete;
    while(cour->suiv != NULL){
      cour = cour->suiv;
    }
    nv->suiv = cour->suiv;
    cour->suiv = nv;
  }
  this->nb++;
}

template < typename Chaine>
void ensemble<Chaine>::retire(string mot){
  Maillon *cour = this->tete;
  if(contient(mot)){
    if(cour->ch == mot){
      Maillon *tmp1 = cour;
      this->tete = this->tete->suiv;
      delete(tmp1);
    }
    else{
      while(cour->suiv->ch != mot){
      cour = cour->suiv;
      }
      Maillon *tmp2 = cour->suiv;
      cour->suiv = cour->suiv->suiv;
      delete(tmp2);
    }
    this->nb--;
  }
}

template < typename Chaine>
string ensemble<Chaine>::contenu(){
  Maillon *cour = this->tete;
  string chaine = "";
  while(cour->suiv != NULL){
    chaine += cour->ch + " ";
    cour = cour->suiv;
  }
  chaine += cour->ch;
  return chaine;
}

template < typename Chaine>
int ensemble<Chaine>::nbelem(){
  return this->nb;
}
