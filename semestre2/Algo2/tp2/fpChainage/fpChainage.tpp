/**
 * @file FP_ch.hpp
 * @author Fatma Maouloud, Mamadou Diallo
 * @date 22/03/2017 Création
 * @brief classe FP_ch.
**/
#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "File_chainage.hpp"

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
FP_ch<T>::FP_ch(){
  this->tete = NULL;
  this->nb = 0;
}

//--------------------------------------------------------------------
/**
 * @brief destructeur ~FP_ch().
 * @b Role : détruit tous les éléments de l'ensemble.
 * @b Entrée : Rien
 * @b Sortie : Rien
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(nb)
 *
**/
template <typename T>
FP_ch<T>::~FP_ch(){
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
bool FP_ch<T>::estVide(){
  return this->nb == 0;
}


template < typename T>
T FP_ch<T>::premier(){
		
	return this->tete->elt;

}
	


//--------------------------------------------------------------------
/**
 * @brief fonction ajoute()
 * @b Role : ajoute un élément dans l'Ensemble ,l'ajout est fait en queue.
 * @b Entrée : - @e elt : élément à rajouter
 * @b Sortie : - Rien mais l'ensemble est modifié
 * @pre - aucune
 * @post - aucune
 * @b Complexité - O(nb)
 *
**/
template < typename T>
void FP_ch<T>::enfiler(T e){
	
	if(!estDans(e))
	{
		Maillon *nouv= new Maillon;
		nouv->elt = e;
		if(estVide())
		{
			nouv->suiv = NULL;
			this->tete = nouv;
		}	
		else
		{
			if(e > this->tete->elt)
			{
				nouv->suiv = this->tete;
				this->tete = nouv;
			} 
			else 
			{
				Maillon *prec = this->tete;
				while(prec->suiv != NULL && e < prec->suiv->elt)
				{
						prec = prec->suiv;
					
				}
				nouv->suiv = prec->suiv;
				prec->suiv = nouv;
			}
		}
		this->nb++;
	}
	
}


template < typename T>
bool FP_ch<T>::estDans(T e){
  Maillon *cour = this->tete;
  while(cour != NULL && cour->elt != e){
    cour = cour->suiv;
  }
  return cour != NULL;
}

template < typename T>
void FP_ch<T>::defiler(){
  Maillon *tmp = this->tete;
  this->tete = this->tete->suiv;
  delete(tmp);
  this->nb--;
}



