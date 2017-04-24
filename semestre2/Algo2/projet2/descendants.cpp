/**
 * @file descendants.cpp
 * @date 21/03/2017 Création
 * @brief Définition des méthodes de la classe Descendants
**/
#include <list>
#include <iostream>
#include "descendants.hpp" // pour le type Descendants
using namespace std;

// EN AJOUTER SI BESOIN

//--------------------------------------------------------------------
Descendants::Descendants(const Individu & ind)
{
    racine.ind = ind;
    racine.fils = NULL;
    racine.frere = NULL;
}

//--------------------------------------------------------------------
Descendants::Descendants(const Individu & ind, const Ancetres & anc)
{
  racine.ind = ind;
  racine.fils = NULL;
  racine.frere = NULL;
  std::list<Noeud*> ch;
  std::vector<Individu> tabFils;
  ch.push_back(&racine);
  while(ch.size() > 0){
    Noeud *pre = new Noeud;
    pre = ch.front();
    tabFils = anc.lesFils(pre->ind);
    for(int k=0 ; k<(int)tabFils.size() ;k++){
      Noeud *nv = new Noeud;
      nv->ind = tabFils[k];
      nv->fils = NULL;
      nv->frere = NULL;
      if(pre->fils == NULL || pre->fils->ind < nv->ind){
        nv->frere = pre->fils;
        pre->fils = nv;
      }
      else{
        Noeud* cour = pre->fils;
        Noeud* avant = cour;
        while(cour != NULL && nv->ind < cour->ind){
          avant = cour;
          cour = cour->frere;
        }
        avant->frere = nv;
        nv->frere = cour;
      }
      ch.push_back(nv);
    }
    ch.pop_front();
  }
}

//--------------------------------------------------------------------
Descendants::~Descendants()
{
    // À COMPLÉTER
}

//--------------------------------------------------------------------
void Descendants::afficher(std::ostream & os) const
{
  Noeud rac = racine;
  std::list<Noeud*> chn;
  chn.push_back(&rac);
  os << rac.ind <<endl;
  while(chn.size() > 0){
    Noeud *cour = chn.front()->fils;
    while(cour != NULL){
      os << cour->ind <<endl;
      chn.push_back(cour);
      cour = cour->frere;
    }
    chn.pop_front();
  }
}

//--------------------------------------------------------------------

bool Descendants::estPresent(const Individu & ind) const
{
  Noeud rac = racine;
  std::list<Noeud*> chn;A
  chn.push_back(&rac);
  bool res = false;
  while(chn.size() > 0 && res == false){
    Noeud *cour = chn.front()->fils;
    cout<< "entré 1" <<endl;
    while(cour != NULL && res == false){
      cout<< "entré 2" <<endl;
      cout << "cour : " << cour->ind.nom << " ; ind : "<< ind.nom <<endl;
      if(cour->ind == ind){
        res = true;
        cout<< "entré" <<endl;
      }
      chn.push_back(cour);
      cour = cour->frere;
    }
    chn.pop_front();
  }
  return res;
}

//--------------------------------------------------------------------
void Descendants::ajouter(const Individu & par, const Individu & enf)
{
  // for(auto i=inds.begin() ; i!=inds.end() ;++i){
  //   Individu enf = *i;
}

//--------------------------------------------------------------------
std::set<Individu> Descendants::auDegre(unsigned int k) const
{
    // À COMPLÉTER
}

//--------------------------------------------------------------------
std::set<Individu> Descendants::descendantsCommuns(const Descendants & des) const
{
    // À COMPLÉTER
}

//--------------------------------------------------------------------
std::ostream & operator<<(std::ostream & os, const Descendants & anc)
{
    anc.afficher(os);
    return os;
}
