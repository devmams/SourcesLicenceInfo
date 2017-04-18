/**
 * @file ancetres.cpp
 * @date 21/03/2017 Création
 * @brief Définition des méthodes de la classe Ancetre
**/
#include "ancetres.hpp" // pour le type Ancetres
#include <fstream> // pour le type std::ifstream
#include <sstream> // pour le type std::stringstream
#include <cassert> // pour les assertions
#include <iostream>
using namespace std;

//--------------------------------------------------------------------
Ancetres::Ancetres()
: noeuds(0) // vecteur vide
{}

//--------------------------------------------------------------------
Ancetres::Ancetres(std::string fic)
: noeuds(0) // vecteur vide
{
    // ouverture du fichier ...
    std::ifstream f(fic);
    // ... supposé fonctionner
    assert(f.is_open());

    // tampon de lecture
    std::string ligne;

    // première partie du fichier : créer les individus
    std::getline(f,ligne);
    while ( f.good()  and  ( ligne.at(0) == 'f'  or  ligne.at(0) == 'm' ) )
    {
        std::stringstream ss(ligne);
        Individu ind;
        ss >> ind.sexe >> ind.nom >> ind.date; // déchiffrage des données
        this->ajouter(ind); // ajout au moyen de la méthode éponyme
        std::getline(f,ligne); // prochaine ligne
    }

    // deuxième partie : lier les individus
    while ( f.good() )
    {
        std::stringstream ss(ligne);
        int i, p, m; // numéros de l'individu, son père, sa mère
        ss >> i >> p >> m; // déchiffrage des liens
        Individu ind, per, mer; // individus correspondants
        ind = noeuds.at(i-1).ind;
        // enregistrement du père si connu
        if ( p != 0 )
        {
            per = noeuds.at(p-1).ind;
            //cout<<"-----"<< per <<endl;
            this->setPere(ind,per);
        }
        // enregistrement de la mère si connue
        if ( m != 0 )
        {
            mer = noeuds.at(m-1).ind;
            this->setMere(ind,mer);
        }
        std::getline(f,ligne); // prochaine ligne
    }
    f.close();
}

//--------------------------------------------------------------------
void Ancetres::afficher(std::ostream & os) const
{ // affichage formaté des individus, puis de leurs liens de filiation

    // affichage des individus
    for ( auto & nd : noeuds )
    {
        os << nd.ind << std::endl;
    }

    // affichage des liens de filiation
    for ( size_t i = 0 ; i < noeuds.size() ; ++ i )
    {
        os << i+1 << '\t' << noeuds.at(i).pere+1 << '\t' << noeuds.at(i).mere+1 << std::endl;
    }
}

//--------------------------------------------------------------------
bool Ancetres::estPresent(const Individu & ind) const
{
    return indTOnd.count(ind)>0;
}

//--------------------------------------------------------------------
void Ancetres::ajouter(const Individu & ind)
{
  assert(!estPresent(ind));
  unsigned int val = noeuds.size();
  Noeud nv;
  nv.ind = ind;
  nv.pere = -1;
  nv.mere = -1;
  noeuds.push_back(nv);
  indTOnd[ind] = val;
}

//--------------------------------------------------------------------
bool Ancetres::hasPere(Individu ind) const
{
    bool hp = false;
    std::vector<Noeud> tempNoeuds = noeuds;
    unordered_map<Individu,unsigned int> tempTab = indTOnd;
    unsigned int i = tempTab[ind];
    if(tempNoeuds[i].pere != -1)
      hp = true;
    return hp;
}

//--------------------------------------------------------------------
Individu Ancetres::getPere(Individu ind) const
{
    Individu p;
    std::vector<Noeud> tempNoeuds = noeuds;
    unordered_map<Individu,unsigned int> tempTab = indTOnd;
    unsigned int i = tempTab[ind];
    unsigned int ip = tempNoeuds[i].pere;
    p = tempNoeuds[ip].ind;
    return p;
}

//--------------------------------------------------------------------
void Ancetres::setPere(Individu ind, Individu pere)
{
  vector<Noeud> tempNoeuds = noeuds;
  unsigned int ip = indTOnd[pere];
  unsigned int i = indTOnd[ind];
  noeuds[i].pere = ip;
}

//--------------------------------------------------------------------
bool Ancetres::hasMere(Individu ind) const
{
    bool hm = false;
    std::vector<Noeud> tempNoeuds = noeuds;
    unordered_map<Individu,unsigned int> tempTab = indTOnd;
    unsigned int i = tempTab[ind];
    if(tempNoeuds[i].mere != -1)
      hm = true;
    return hm;
}

//--------------------------------------------------------------------
Individu Ancetres::getMere(Individu ind) const
{
    Individu m;
    std::vector<Noeud> tempNoeuds = noeuds;
    unordered_map<Individu,unsigned int> tempTab = indTOnd;
    unsigned int i = tempTab[ind];
    unsigned int im = tempNoeuds[i].mere;
    m = tempNoeuds[im].ind;
    return m;
}

//--------------------------------------------------------------------
void Ancetres::setMere(Individu ind, Individu mere)
{
  unsigned int im = indTOnd[mere];
  unsigned int i = indTOnd[ind];
  noeuds[i].mere = im;
}

//--------------------------------------------------------------------
std::set<Individu> Ancetres::racines() const
{
    std::set<Individu> rac;
    std::vector<Noeud> tempNoeuds = noeuds;
    bool estParent;
    for(int i=0 ; i<(int)tempNoeuds.size() ;i++){
      estParent = false;
      Individu ind = tempNoeuds[i].ind;
      for(int j=0 ; j<(int)tempNoeuds.size() ;j++){
        if(tempNoeuds[j].pere == i || tempNoeuds[j].mere == i){
          estParent = true;
        }
      }
      if(!estParent){
        cout<< "ind rac : "<< ind <<endl;
        rac.insert(ind);
      }
    }
    return rac;
}

//--------------------------------------------------------------------
std::set<Individu> Ancetres::individus() const
{
    std::set<Individu> inds;
    std::vector<Noeud> tempNoeuds = noeuds;
    for(int i=0 ;i<(int)tempNoeuds.size() ;i++){
      inds.insert(tempNoeuds[i].ind);
      cout<< "indsss : "<<tempNoeuds[i].ind <<endl;
    }
    return inds;
}

//--------------------------------------------------------------------
Ancetres Ancetres::ancetresCommuns(Individu ind1, Individu ind2) const
{
    Ancetres com;
    Ancetres ancInd1 = ancetreIndividus(ind1);
    Ancetres ancInd2 = ancetreIndividus(ind2);
    for(int i=0 ;i<(int)ancInd1.noeuds.size() ;i++){
      if(ancInd2.estPresent(ancInd1.noeuds[i].ind)){
        com.ajouter(ancInd1.noeuds[i].ind);
      }
    }
    return com;
}

//--------------------------------------------------------------------
Ancetres Ancetres::ancetreIndividus(Individu ind) const{
  Ancetres ancInd;
  if(this->hasPere(ind)){
    Individu p = getPere(ind);
    ancInd.ajouter(p);
    ancInd.fusion(ancetreIndividus(p));
  }
  if(this->hasMere(ind)){
    Individu m = getMere(ind);
    ancInd.ajouter(m);
    ancInd.fusion(ancetreIndividus(m));
  }
  return ancInd;
}
//--------------------------------------------------------------------

std::vector<Individu> Ancetres::lesFils(Individu ind) const{
  std::vector<Individu> v;
  std::vector<Noeud> tempNoeuds = noeuds;
  for(int i=0 ;i<tempNoeuds.size() ;i++){
    individu f = tempNoeuds[i].ind;
    if(getPere(f) == ind || getMere(f) == ind)
      v.push_back(f);
  }
  return v;
}



//--------------------------------------------------------------------
void Ancetres::fusion(Ancetres anc)
{
  for(int i=0 ;i<(int)anc.noeuds.size() ;i++){
    this->ajouter(anc.noeuds[i].ind);
  }
}

//--------------------------------------------------------------------
std::ostream & operator<<(std::ostream & os, const Ancetres & anc)
{
    anc.afficher(os);
    return os;
}
