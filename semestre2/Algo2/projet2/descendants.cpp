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
      if(pre->fils == NULL){
        nv->frere = pre->fils;
        pre->fils = nv;
      }
      else if(nv->ind < pre->fils->ind){
        nv->frere = pre->fils;
        pre->fils = nv;
      }
      else{
        Noeud* cour = pre->fils;
        Noeud* avant = cour;
        while(cour != NULL && cour->ind < nv->ind){
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
  cout << "rac : " << racine.ind << endl;
  cout << "rac fils: " << racine.fils->ind << endl;
  cout << "rac fils frere: " << racine.fils->frere->ind << endl;
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
  std::list<Noeud*> chn;
  chn.push_back(&rac);
  bool trouve = false;
  if(rac.ind == ind){
    trouve = true;
  }
  while(chn.size() > 0 && trouve == false){
    Noeud *cour = chn.front()->fils;
    while(cour != NULL && trouve == false){
      if(cour->ind == ind){
        trouve = true;
      }
      chn.push_back(cour);
      cour = cour->frere;
    }
    chn.pop_front();
  }
  return trouve;
}

//--------------------------------------------------------------------
void Descendants::ajouter(const Individu & par, const Individu & enf)
{
  std::list<Noeud*> chn;
  chn.push_back(&racine);
  bool trouve = false;
  Noeud *cour;
  if(racine.ind == par){
    cour = &racine;
    trouve = true;
  }
  while(chn.size() > 0 && trouve == false){
    cour = chn.front()->fils;
    while(cour != NULL && trouve == false){
      if(cour->ind == par){
        trouve = true;
      }
      else{
        chn.push_back(cour);
        cour = cour->frere;
      }
    }
    chn.pop_front();
  }
  if(trouve){
    Noeud *nv = new Noeud;
    nv->ind = enf;
    nv->fils = NULL;
    nv->frere = NULL;
    if(cour->fils == NULL){
      nv->frere = cour->fils;
      cour->fils = nv;
    }
    else if(nv->ind < cour->fils->ind){
      nv->frere = cour->fils;
      cour->fils = nv;
    }
    else{
      Noeud* apres = cour->fils;
      Noeud* avant = apres;
      while(apres != NULL && apres->ind < nv->ind){
        avant = apres;
        apres = apres->frere;
      }
      avant->frere = nv;
      nv->frere = cour;
    }
  }
}

//--------------------------------------------------------------------
std::set<Individu> Descendants::auDegre(unsigned int k) const
{
    const Noeud* rac = new Noeud;
    rac = &racine;
    unsigned int niveau = 0;
    std::set<Individu> res;
    while(niveau != k){
        rac = rac->fils;
        niveau++;
    }
    while(rac != NULL){
      res.insert(rac->ind);
      cout<<"tttttt " <<rac->ind<<endl;
      rac= rac->frere;
    }
    return res;
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
