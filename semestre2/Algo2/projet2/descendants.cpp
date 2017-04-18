/**
 * @file descendants.cpp
 * @date 21/03/2017 Création
 * @brief Définition des méthodes de la classe Descendants
**/
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
  std::List<*Noeud> ch;
  std::vector<Individu> tabFils;
  ch.push_back(&racine);
  while(ch.size() > 0){
    Noeud *pre = ch.front();
    tabFils = lesFils(pre->ind);
    for(int i=0 ; i<tabFils.size() ;i++){
      Noeud nv;
      nv.ind = tabFils[i];
      if(racine.fils == NULL){
        racine.fils =
      }
    }


  }

  for(auto i=inds.begin() ; i!=inds.end() ;++i){
    Individu enf = *i;

}

//--------------------------------------------------------------------
Descendants::~Descendants()
{
    // À COMPLÉTER
}

//--------------------------------------------------------------------
void Descendants::afficher(std::ostream & os) const
{
  Noeud *cour = racine.fils;
  // cout<< "rac : "<< cour->ind <<endl;
/*  while(cour->fils != NULL){
    os << cour->ind << std::endl;
    cour = cour->fils;
  }*/
}

//--------------------------------------------------------------------
bool Descendants::estPresent(const Individu & ind) const
{
    // À COMPLÉTER
}

//--------------------------------------------------------------------
void Descendants::ajouter(const Individu & par, const Individu & enf)
{
  Noeud *nv;
  nv->ind = enf;
  nv->fils = NULL;
  nv->frere = NULL;
  if(racine.fils == NULL)
    racine.fils = nv;
  else{
    if(racine.fils->ind < enf){
      nv->frere = racine.fils;
      racine.fils = nv;
    }
    else{
      Noeud *cour = racine.fils;
      Noeud *prec ;
      while(cour != NULL && enf < cour->ind){
        prec = cour;
        cour = cour->frere;
      }
      if(cour->ind < enf){
        prec->frere = nv;
        nv->frere = cour;
      }
    }
  }

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
