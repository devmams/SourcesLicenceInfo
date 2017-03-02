#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "Ensb.hpp"

//--------------------------------------------------------------------
/**
 * @brief constructeur Ensb().
 * @b Role : crée un ensemble
 * @b Entrée : Rien
 * @b Sortie : Rien
 * @pre - aucune
 * @post - aucune
 * @b Complexité - θ(1)
 *
**/
template < typename T>
Ensb<T>::Ensb(){
	this->nb = 0;
}

//--------------------------------------------------------------------
/**
 * @brief destructeur ~Ensb().
 * @b Role : détruit tous les éléments de l'ensemble.
 * @b Entrée : Rien
 * @b Sortie : Rien
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(1)
 *
**/
template < typename T>
Ensb<T>::~Ensb(){}

//--------------------------------------------------------------------
/**
 * @brief fonction estVide()
 * @b Role : Indiquer si l'Ensemble est vide ou non
 * @b Entrée : Rien
 * @b Sortie : - @e booléen qui rend TRUE si l'Ensemble est vide, FALSE sinon
 * @pre - aucune
 * @post - aucune
 * @b Complexité - θ(1)
 *
**/
template < typename T>
bool Ensb<T>::estVide(){
  return this->nb == 0;
}

//--------------------------------------------------------------------
/**
 * @brief fonction contient(T elt)
 * @b Role : indique si l'ensemble contient l'élément elt
 * @b Entrée : - @e elt : élément à chercher
 * @b Sortie : - @e booléen qui rend TRUE si l'élément est dans l'ensemble, FALSE sinon
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(nb)
 *
**/
template < typename T>
bool Ensb<T>::contient(T elt){
	bool res = false;
	for(int i=0; i<this->nb; i++){
		if(this->tab[i] == elt){
			res=true;
		}
	}
	return res;
}

//--------------------------------------------------------------------
/**
 * @brief fonction ajoute()
 * @b Role : ajoute un élément dans l'Ensemble
 * @b Entrée : - @e elt : élément à rajouter
 * @b Sortie : - Rien mais l'ensemble est modifié
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(nb)
 *
**/
template < typename T>
void Ensb<T>::ajoute(T elt){
	if(!contient(elt)){
		this->tab[this->nb] = elt;
		this->nb++;
	}
}

//--------------------------------------------------------------------
/**
 * @brief fonction retire()
 * @b Role : retire un élément dans l'Ensemble
 * @b Entrée : - @e elt : élément à supprimer
 * @b Sortie : - Rien mais l'ensemble est modifié
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(nb)
 *
**/
template < typename T>
void Ensb<T>::retire(T elt){
	if(!estVide() && contient(elt)){
		int ind=0;
		while(this->tab[ind] != elt){
			ind++;
		}
		for(int i=ind ;i<this->nb ;i++){
			this->tab[i] = this->tab[i+1];
		}
		this->nb--;
	}
}

//--------------------------------------------------------------------
/**
 * @brief fonction contenu()
 * @b Role : concatène tous les éléments de l'ensemble
 * @b Entrée : Rien
 * @b Sortie : - @e chaine de caratère resultant des concatenations.
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(nb)
 *
**/
template < typename T>
string Ensb<T>::contenu(){
	stringstream sst;
	if(!estVide()){
		for(int i=0 ;i<this->nb-1 ;i++){
			sst << this->tab[i] <<" ";
		}
		sst << this->tab[this->nb-1];
	}
	return sst.str();
}

//--------------------------------------------------------------------
/**
 * @brief fonction intersectionEns()
 * @b Role : fait l'intersection de deux ensembles
 * @b Entrée : - @e e : ensemble à intersecter
 * @b Sortie : - modification de l'objet courant.
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(nb²)
 *
**/
template < typename T>
void Ensb<T>::intersectionEns(Ensb<T> & e){
  Ensb<T> ensRes;
  for(int i=0 ;i<this->nb ;i++){
    ensRes.ajoute(this->tab[i]);
  }
  this->nb = 0 ; //initialisation de l'objet courant.
  for(int j=0; j<ensRes.nb ; j++){
    if(e.contient(ensRes.tab[j])){
      this->ajoute(this->tab[j]);
    }
  }
}

//---------------------------------------------------------------------
/**
 * @brief fonction unionEns()
 * @b Role : fait l'union de deux ensembles
 * @b Entrée : - @e e : ensemble à unir
 * @b Sortie : - modification de l'objet courant.
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(nb²)
 *
**/
template < typename T>
void Ensb<T>::unionEns(const Ensb<T> & e){
	for(int i=0; i<e.nb ; i++){
		if(!this->contient(e.tab[i])){
      this->ajoute(e.tab[i]);
    }
	}
}

//--------------------------------------------------------------------
/**
 * @brief fonction differenceEns()
 * @b Role : fait la difference entre deux ensembles
 * @b Entrée : - @e e : ensemble à rétrancher
 * @b Sortie : - modification de l'objet courant.
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(nb²)
 *
**/
template < typename T>
void Ensb<T>::differenceEns(Ensb<T> & e){
  Ensb<T> ensRes;
  for(int i=0 ;i<this->nb ;i++){
    ensRes.ajoute(this->tab[i]);
  }
  this->nb = 0 ; //initialisation de l'objet courant.
  for(int j=0; j<ensRes.nb ; j++){
    if(!e.contient(this->tab[j])){
      this->ajoute(this->tab[j]);
    }
  }
}
