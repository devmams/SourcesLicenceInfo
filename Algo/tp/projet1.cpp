#include <iostream>  // cout, cin
#include <cstdlib>   // rand(), srand()
#include <cassert>   // assert()
#include <time.h>    // DBL_MAX
#include <algorithm> // std::swap
using namespace std;


const size_t Taille = 10;
typedef char Tabchar[Taille];


void affiche(const Tabchar & t){
  cout << "les éléments du tableau sont : ";
  for(int i=0 ; i<Taille-1 ; i++){
    cout << t[i] << " , ";
  }
  cout << t[Taille-1];
  cout<<endl;
}

void aleatoire(Tabchar & t, char inf, char sup){
  srand(time(NULL));
  for(int i=0 ; i<Taille ; i++){
      t[i] = static_cast<char>( rand() % (sup-inf+1) + inf);
  }
}

size_t occurrences(Tabchar & t, char c){
  int cpt = 0;
  for(int i=0 ; i<Taille ; i++){
    if(t[i] == c)
      cpt++;
  }
  return cpt;
}

char frequenceComptage(Tabchar & t){
  int occ , max = 0;
  char res = t[0];
  for(int i=0 ; i<Taille ; i++){
    occ = occurrences(t,t[i]);
    if(occ > max || (occ == max && res > t[i])){
      max = occ;
      res = t[i];
    }
  }
  return res;
}

void tri(Tabchar tab){
  bool modif = false;
  int nblem = Taille;
  while(!modif){
    modif = false;
    for(int i=1 ; i< nblem-1 ; i++){
      if(static_cast<int>(tab[i]) > static_cast<int>(tab[i+1])){
        swap(tab[i],tab[i+1]);
        modif = true;
      }
    }
    cout<< "-------"<<endl;
    nblem--;
  }


  /*int cpt = 0;
  cout << "----" << static_cast<int>(tab[0]) <<endl;
  cout << "----" << static_cast<int>(tab[1]) <<endl;
  for(int i=0 ; i<Taille ; i++){
    for(int j=cpt+1 ; j<Taille ; j++){
      if(tab[cpt] == tab[j]){
        swap(tab[cpt+1],tab[j]);
        cpt++;
      }
    }
    i += cpt+1;
    cout << cpt <<endl;
  }*/
}

/*char frequenceTri (Tabchar tab){
  int max = 0 , occ = 0;
  char res , c;

  tri(tab);
}*/


int main(){
  Tabchar tab;
  char elt = 'd';
  aleatoire(tab,'a','i');
  affiche(tab);
  cout << "le nombre d'occurrence de : "<< elt << " est : " << occurrences(tab , elt) <<endl;
  cout << "la lettre qui apparait le plus est : " << frequenceComptage(tab) << " ,elle apparait : " << occurrences(tab,frequenceComptage(tab)) <<endl;
  tri(tab);
  affiche(tab);
  return 0;
}
