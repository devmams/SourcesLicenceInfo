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

//template class FP_ch<>;
template class FP_ch<int>;

int main(){
  FP_ch<int> e0; //création prémier ensemble.
  e0.enfiler(5);
  e0.enfiler(4);
  e0.enfiler(9);
  e0.enfiler(0);
  e0.enfiler(2);
  e0.enfiler(11);
  cout<<e0.premier()<<endl; 
  e0.defiler();  
  cout<<e0.premier()<<endl; 
  e0.defiler();  
  cout<<e0.premier()<<endl; 
  

  return 0;
}
