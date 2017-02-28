/**
 * @file Ensa.tpp
 * @author Fatma Maouloud, Mamadou Diallo
 * @date 20/02/2017 Création
 * @brief Implémentation des méthodes de la classe Ensa.
**/
#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "Ensa.hpp"

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
Ensa<T>::Ensa(){
  this->tete = NULL;
  this->nb = 0;
}

//--------------------------------------------------------------------
/**
 * @brief destructeur ~Ensa().
 * @b Role : détruit l'ensemble
 * @b Entrée : Rien
 * @b Sortie : Rien
 * @pre - aucune
 * @post - aucune
 * @b Complexité - θ(?)
 *
**/
template < typename T>
Ensa<T>::~Ensa(){}

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
bool Ensa<T>::estVide(){
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
bool Ensa<T>::contient(T elt){
  bool res = false;
  Maillon *cour = this->tete;
  for(int i=0 ;i<nb ;i++){
    if(cour->ch == elt){
      res = true;
    }
    cour = cour->suiv;
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
void Ensa<T>::ajoute(T elt){
  Maillon *nv = new Maillon;
  nv->ch = elt;
  if(estVide()){
    nv->suiv = this->tete;
    this->tete = nv;
    this->nb++;
  }
  else if(!contient(elt)){
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
 * @brief fonction retire()
 * @b Role : retire un élément dans l'Ensemble
 * @b Entrée : - @e elt : élément à supprimer
 * @b Sortie : - Rien mais l'ensemble est modifié
 * @pre - !estVide()
 * @post - aucune
 * @b Complexité - O(nb)
 *
**/
template < typename T>
void Ensa<T>::retire(T elt){
  Maillon *cour = this->tete;
  Maillon *prec = this->tete;
  if(!estVide() || contient(elt)){
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

//--------------------------------------------------------------------
/**
 * @brief fonction intersectionEns()
 * @b Role : fait l'intersection de deux ensembles
 * @b Entrée : - @e e : ensemble à intersecter
 * @b Sortie : - @ e : ensemble résultant de l'intersection
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(nb²)
 *
**/
template < typename T>
Ensa<T> Ensa<T>::intersectionEns(Ensa<T> e){
	Ensa<T> ens_res;
	Maillon *tmp = this->tete;
	 for(int i=0; i<this->nb ; i++){
     if(e.contient(tmp->ch)){
  			ens_res.ajoute(tmp->ch);
      }
  		tmp = tmp->suiv;
    }
		return(ens_res);
	}

  //--------------------------------------------------------------------
  /**
   * @brief fonction unionEns()
   * @b Role : fait l'union de deux ensembles
   * @b Entrée : - @e e : ensemble à unir
   * @b Sortie : - @ e : ensemble résultant de l'union
   * @pre - aucune
   * @post - aucune
   * @b Complexité - O(nb²)
   *
  **/
template < typename T>
Ensa<T> Ensa<T>::unionEns(Ensa<T> e){
	Ensa<T> ens_res = e;
	Maillon *tmp = this->tete;
	for(int i=0; i<this->nb ; i++){
		if(!ens_res.contient(tmp->ch)){
      ens_res.ajoute(tmp->ch);
    }
		tmp = tmp->suiv;
	}
	return(ens_res);
}

//--------------------------------------------------------------------
/**
 * @brief fonction differenceEns()
 * @b Role : fait la difference entre deux ensembles
 * @b Entrée : - @e e : ensemble à rétrancher
 * @b Sortie : - @e ens_res : ensemble résultant de la différence
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(nb²)
 *
**/
template < typename T>
Ensa<T> Ensa<T>::differenceEns(Ensa<T> e){
	Ensa<T> ens_res;
	Maillon *tmp = this->tete;
	for(int i=0; i<this->nb ; i++){
		if(!e.contient(tmp->ch)){
			ens_res.ajoute(tmp->ch);
	  }
		tmp = tmp->suiv;
	}
	return(ens_res);
}

//--------------------------------------------------------------------
/**
 * @brief fonction nbelem()
 * @b Role : retourne le nombre d'élément d'un ensemble.
 * @b Entrée : Rien
 * @b Sortie : -@e nb : le nombre d'élément.
 * @pre - aucune
 * @post - aucune
 * @b Complexité - θ(1)
 *
**/
template < typename T>
int Ensa<T>::nbelem(){
  return this->nb;
}
