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
        cout<< "ind rac : "<<ind <<endl;
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
    std::vector<Noeud> tempNoeuds = noeuds;
    unordered_map<Individu,unsigned int> tempTab = indTOnd;
    if(this->hasPere(ind1) || this->hasMere(ind1)){
      if(this->hasPere(ind1)){
        if(this->hasPere(ind2)){
          com.fusion(ancetresCommuns(this->getPere(ind1) , this->getPere(ind2)));
        }
        if(this->hasMere(ind2)){
          com.fusion(ancetresCommuns(this->getPere(ind1) , this->getMere(ind2)));
        }
      }
      if(this->hasMere(ind1)){
        if(this->hasPere(ind2)){
          com.fusion(ancetresCommuns(this->getMere(ind1) , this->getPere(ind2)));
        }
        if(this->hasMere(ind2)){
          com.fusion(ancetresCommuns(this->getMere(ind1) , this->getMere(ind2)));
        }
      }
    }else if(ind1 == ind2){
      com.ajouter(ind1);
    }
    return com;
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
