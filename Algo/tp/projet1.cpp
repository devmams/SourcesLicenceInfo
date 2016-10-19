#include <iostream>  // cout, cin
#include <cstdlib>   // rand(), srand()
//#include <ctime>     // time(), clock_t, clock(), CLOCKS_PER_SEC
#include <cassert>   // assert()
//#include <climits>   // UINT_MAX
#include <time.h>    // DBL_MAX
using namespace std;


const size_t Taille = 10;
typedef char Tabchar[Taille];


void affiche(const Tabchar & t){
  cout << "les éléments du tableau sont : ";
  for(int i=0 ; i<Taille ; i++){
    cout << t[i] << " , ";
  }
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

int main(){
  Tabchar tab;
  char elt = 'i';
  aleatoire(tab,'a','i');
  affiche(tab);
  cout<< "le nombre d'occurrence de : "<< elt << " est : " << occurrences(tab , elt) <<endl;
  cout << frequenceComptage(tab) <<endl;
  return 0;
}
