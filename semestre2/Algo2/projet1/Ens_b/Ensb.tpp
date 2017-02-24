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
bool Ensb<T>::contient(T mot){
	
	 bool res = false;
	for(int i=0; i<nb; i++)
	{
		if(this->tab[i] == mot)
		{		
			res=true;
		}
	}
	return res;	

}

///////////////////////////////////////	
template < typename T>
void Ensb<T>::ajoute(T mot){
	
	if(!contient(mot)){
		this->tab[this->nb]=mot;	
	}
	this->nb++;
		
}	

////////////////////////////////
template < typename T>
void Ensb<T>::retire(T mot){
 
	if(!estVide() || contient(mot){
		
		if(this->tab[nb-1]==mot){
				
			this->nb--;
			
			}	
				else{
			
					for(int i = 0; i<nb ; i++){
					this->tab[i]=this->tab[i+1];
					this->nb--;
						}
					}	
		
	}
	
}	
////////////////////////////////
/*	
template < typename T>
string Ensb<T>::contenu(){	
  stringstream sst;
  for(int i = 0; i<nb ; i++)
    sst << tab[i] << " ";
  }
  return sst.str();
  
}*/






