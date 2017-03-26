/**
 * @file Ensc.tpp
 * @author Fatma MAOULOUD, Mamadou DIALLO
 * @date 20/02/2017 Création
 * @brief Implémentation des méthodes de la classe Ensc.
**/

#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "Ensc.hpp"

//--------------------------------------------------------------------
/**
 * @brief constructeur Ensa().
 * @b Role : crée un ensemble
 * @b Entrée : Rien
 * @b Sortie : Rien
 * @pre - aucune
 * @post - aucune
 * @b Complexité - θ(1)
 *
**/
template < typename T>
Ensc<T>::Ensc(){
	this->tete = NULL;
	this->nb = 0;
}

//--------------------------------------------------------------------
/**
 * @brief destructeur ~Ensc().
 * @b Role : détruit tous les éléments de l'ensemble.
 * @b Entrée : Rien
 * @b Sortie : Rien
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(nb)
 *
**/
template < typename T>
Ensc<T>::~Ensc(){
	Maillon *cour , *efface;
  cour = this->tete;
  efface = cour;
  for(int i=0 ;i<this->nb ;i++){
    cour = cour->suiv;
    delete(efface);
    efface = cour;
  }
}

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
bool Ensc<T>::estVide(){
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
bool Ensc<T>::contient(T elt){
	  Maillon *cour = this->tete;
	  while(cour != NULL && cour->ch != elt){
	    cour = cour->suiv;
	  }
	  return cour != NULL;
}

//--------------------------------------------------------------------
/**
 * @brief fonction ajoute()
 * @b Role : ajoute un élément dans l'Ensemble , l'ajout est fait en tete.
 * @b Entrée : - @e elt : élément à rajouter
 * @b Sortie : - Rien mais l'ensemble est modifié
 * @pre - aucune
 * @post - aucune
 * @b Complexité - θ(1)
 *
**/
template <typename T>
void Ensc<T>::ajoute(T elt){
	Maillon *nv = new Maillon;
  nv->ch = elt;
  nv->suiv = this->tete;
  this->tete = nv;
  this->nb++;
}

//--------------------------------------------------------------------
/**
 * @brief fonction retire()
 * @b Role : retire un élément dans l'Ensemble
 * @b Entrée : - @e elt : élément à supprimer
 * @b Sortie : - Rien mais l'ensemble est modifié
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(nb²)
 *
**/
template <typename T>
void Ensc<T>::retire(T elt){
	Maillon *cour = this->tete;
	Maillon *prec = this->tete;
	if(!estVide()){
		while(contient(elt)){
			cour = this->tete;
			prec = this->tete;
			while(cour->ch != elt){
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
}

//--------------------------------------------------------------------
/**
 * @brief fonction contenu()
 * @b Role : concatène tous les éléments de l'ensemble
 * @b Entrée : Rien
 * @b Sortie : - @e chaine de caratère resultante des concatenations.
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(nb)
 *
**/
template < typename T>
string Ensc<T>::contenu(){
	stringstream sst;
  if(!estVide()){
    Maillon *cour = this->tete;
    while(cour->suiv != NULL){
      sst << cour->ch << " ";
      cour = cour->suiv;
    }
    sst << cour->ch;
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
void Ensc<T>::intersectionEns(Ensc<T> & e){
  Ensc<T> ensRes;
  Maillon *tmpRes = this->tete;
  for(int i=0 ;i<this->nb ;i++){
    ensRes.ajoute(tmpRes->ch);
    tmpRes = tmpRes->suiv;
  }
	cout<< "eR : "<< ensRes.contenu() <<endl;
  this->tete = NULL;//initialisation de l'objet courant.
  this->nb =0 ; //initialisation de l'objet courant.
  tmpRes = ensRes.tete;
  for(int j=0; j<ensRes.nb ; j++){
    if(ensRes.contient(tmpRes->ch) && e.contient(tmpRes->ch)){
      this->ajoute(tmpRes->ch);
    }
	  tmpRes = tmpRes->suiv;
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
void Ensc<T>::unionEns(const Ensc<T> & e){
	Maillon *tmp = e.tete;
	for(int i=0; i<e.nb ; i++){
		if(!this->contient(tmp->ch)){
      this->ajoute(tmp->ch);
    }
		tmp = tmp->suiv;
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
void Ensc<T>::differenceEns(Ensc<T> & e){
  Ensc<T> ensRes;
  Maillon *tmpRes = this->tete;
  for(int i=0 ;i<this->nb ;i++){
    ensRes.ajoute(tmpRes->ch);
    tmpRes = tmpRes->suiv;
  }
	cout<< "eR : "<< ensRes.contenu() <<endl;
  this->tete = NULL; //initialisation de l'objet courant.
  this->nb =0 ; //initialisation de l'objet courant.
  tmpRes = ensRes.tete;
  for(int j=0; j<ensRes.nb ; j++){
    if(!e.contient(tmpRes->ch)){
      this->ajoute(tmpRes->ch);
    }
	  tmpRes = tmpRes->suiv;
  }
}
