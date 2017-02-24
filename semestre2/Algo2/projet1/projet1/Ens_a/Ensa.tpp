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

//--------------------------------------------------------------------
/**
 * @brief fonction estVide
 *
 * @b Role : Indiquer si l'Ensemble est vide ou non
 * @b Entrée :
 *       - Rien
 *
 * @b Sortie :
 *       - @e booléen qui rend TRUE si l'Ensemble est vide, FALSE sinon
 *
 * @pre - aucune
 * @post - aucune
 *
 * @b Complexité - θ(1)
 *
**/
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

//--------------------------------------------------------------------
/**
 * @brief procédure retire
 *
 * @param l'élément de l'Ensemble à Retirer
 *
 * @b Role : Supprime l'élément pris en parametre dans l'ensemble
 * @b Entrée :
 *       - @e pm: l'élément à supprimer
 *
 * @b Sortie :
 *       - @e ch: L'ensemble (chainage) modifiée
 *
 * @pre - (!estVide()) : L'ensemble est non vide
 * @post - aucune
 *
 * @b Complexité - θ(n)
 *
**/
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
//attention à l'affichage d'une chaine vide..........
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

template < typename T>
Ensa<T> Ensa<T>::intersectionEns(Ensa<T> e){

	Ensa<T> ens_res;
	Maillon *tmp = this->tete;

	 for(int i=0; i<this->nb ; i++)
	 {
		if(e.contient(tmp->ch))
		{
			ens_res.ajoute(tmp->ch);
		}
		tmp = tmp->suiv;
	}
		return(ens_res);

	}

template < typename T>
Ensa<T> Ensa<T>::unionEns(Ensa<T> e){

	Ensa<T> ens_res = e;
	Maillon *tmp = this->tete;

	for(int i=0; i<this->nb ; i++){
		if(!ens_res.contient(tmp->ch))
		{
			ens_res.ajoute(tmp->ch);
		}
		tmp = tmp->suiv;

		}
		return(ens_res);
}

//ici on considére la différence entre l'élément passer en parametre avec celui qu'on a à la base!!!
template < typename T>
Ensa<T> Ensa<T>::differenceEns(Ensa<T> e){

	Ensa<T> ens_res;
	Maillon *tmp = this->tete;


	for(int i=0; i<this->nb ; i++){
		if(!e.contient(tmp->ch))
		{
			ens_res.ajoute(tmp->ch);
		}
		tmp = tmp->suiv;

		}
		return(ens_res);



}

template < typename T>
int Ensa<T>::nbelem(){
  return this->nb;
}
