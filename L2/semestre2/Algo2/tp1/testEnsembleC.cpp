#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "ensembleC.hpp"

vector<string> decomposeEnMot(string ch){
  vector<string> vCh;
  string mot = "";
  for(int i=0 ; i<ch.length() ;i++){
    if(ch[i] != ' '){
      mot = mot + ch[i];
    }
    else{
      vCh.push_back(mot);
      mot = "";
    }
  }
  return vCh;
}

void principale(){
  //ceci est le texte qui vérifie si j'ai fait un bon boulot
  ensembleC tab[26];
  vector<string> mots;
  string texte;
  getline(cin,texte);
  cout<< texte <<endl;
  mots = decomposeEnMot(texte);
  for(int m=0 ; m<mots.size() ; m++){
    cout << mots[m] <<endl;
  }
  for(int ind=97 ; ind<123 ;ind++){
    for(int i=0 ;i<mots.size() ;i++){
      if((int) mots[i][ind-97] == ind){
        tab[ind-97].ajoute(mots[i]);
        cout << mots[i][ind-97]  << mots[i] << endl;
      }
    }
  }
  for(int m=0 ; m<26 ; m++){
    //cout << tab[m].contenu() <<endl;
  }
}

int main(){
  principale();
  /*ensembleC e;
  cout<< "estVide ? : " << e.estVide() << " nbElement : "<< e.nbelem()<<endl;
  e.ajoute("tres");
  e.ajoute("bien");
  cout<< "estVide ? : " << e.estVide() << " nbElement : "<< e.nbelem()<<endl;
  cout<<">> " << e.contenu() <<endl;
  e.retire("tres");
  cout<< "estVide ? : " << e.estVide() << " nbElement : "<< e.nbelem()<<endl;
  cout<<">> " << e.contenu() <<endl;*/
  return 0;
}
