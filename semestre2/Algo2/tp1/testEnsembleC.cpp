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
  //je m'appelle mamadou ousmane diallo
  vector<ensembleC> v;
  vector<string> mots;
  string texte;
  getline(cin,texte);
  cout<< texte <<endl;
  mots = decomposeEnMot(texte);
  for(int i=0 ;i<mots.size() ;i++){
    cout << mots[i] <<endl;
  }
  cout << mots.size() <<endl;
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
