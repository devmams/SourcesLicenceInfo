#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "Ensb.hpp" 


///////////////////////
template < typename T>
Ensc<T>::Ensc(){
	this->nb = 0;
}

///////////////////////////
template < typename T>
Ensc<T>::~Ensc(){}

/////////////////////////////////////////
template < typename T>
bool Ensc<T>::estVide(){
  return this->nb == 0;
}


template < typename T>
bool Ensc<T>::contient(T mot){
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


template <typename T>
void Ensc<T>::ajoute(T mot){
	Maillon* nouv = new Maillon;
			nouv->chaine = mot;
			
	if(estvide())
	{
			tete = nouv 
	}
		else{
				Maillon* tmp new Maillon;
				tmp = tete;
				
				nouv->suiv = tmp->suiv;
				tete = nouv;
				
			}
				this->nb++;
	}
	
template <typename T>
void Ensc<T>::retire(T mot){
	Maillon* tmp = this->tete;
	
		if(contient(mot)|| estVide())
			{
				for(int i=0; i< this->nb; i++)
					{
						
						/////////////////////////////////
						
					}
			
			
			
			}
	
	
	
	
	
	}
	
