#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "Ensb.hpp"

///////////////////////
template < typename T>
Ensb<T>::Ensb(){
	this->nb = 0;
}

///////////////////////////
template < typename T>
Ensb<T>::~Ensb(){}

/////////////////////////////////////////
template < typename T>
bool Ensb<T>::estVide(){
  return this->nb == 0;
}

//////////////////////////////////////////////

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

///////////////////////////////////////
template < typename T>
void Ensb<T>::ajoute(T elt){
	if(!contient(elt)){
		this->tab[this->nb] = elt;
	}
	this->nb++;
}

////////////////////////////////
template < typename T>
void Ensb<T>::retire(T elt){
	if(!estVide() && contient(elt){
		if(this->tab[nb-1]==elt){
			this->nb--;
		}
		else{
			for(int i = 0; i<nb ; i++){
				this->tab[i]=this->tab[i+1];
				this->nb--;
			}
		}
}

/*
template < typename T>
Ensb<T> Ensb<T>::intersectionEns(Ensb<T> e){

		Ensb<T> res;

		for(int i=0; i< this->nb ; i++)
		{

			if(e.contient(this->tab[i])
			{
					res.ajoute(this->tab[i]);

			}


		}

		return res;

}

template < typename T>
Ensb<T> Ensb<T>::unionEnsb(Ensb<T> e){

	Ensb<T> res;

		res = e;
		for(int i=0; i< this->nb ; i++)
			{
					if(!res.contient(this->tab[i]))
					{
							res.ajoute(this->tab[i]);

					}
			}

			return res;
}

template <typename T>

// on peut discutter du cas ou on modfie l'élement e passer en parametre afin de le radre à la fin pour utiliser la méthode "retirer"
//mais penser au couts pour le mettre dans le rapport.............
Ensb<T> Ensb<T>::differenceEnsb(Ensb<T> e){

	Ensb<T> res;

	for(int i=0; i< this->nb; i++)
	{
		if(!this.contient(e->tab[i])
		{
				res.ajoute(e->tab[i]);
		}


	}
		return res;
}




////////////////////////////////

template < typename T>
string Ensb<T>::contenu(){
  stringstream sst;
  for(int i = 0; i<nb ; i++)
    sst << tab[i] << " ";
  }
  return sst.str();

}*/
