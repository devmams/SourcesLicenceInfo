/**
 * @file descendants.cpp
 * @date 21/03/2017 Création
 * @brief Définition des méthodes de la classe Descendants
**/

#include "descendants.hpp" // pour le type Descendants
// EN AJOUTER SI BESOIN

//--------------------------------------------------------------------
Descendants::Descendants(const Individu & ind)
{
    // À COMPLÉTER
}

//--------------------------------------------------------------------
Descendants::Descendants(const Individu & ind, const Ancetres & anc)
{
    // À COMPLÉTER
}

//--------------------------------------------------------------------
Descendants::~Descendants()
{
    // À COMPLÉTER
}

//--------------------------------------------------------------------
void Descendants::afficher(std::ostream & os) const
{
    // À COMPLÉTER
}

//--------------------------------------------------------------------
bool Descendants::estPresent(const Individu & ind) const
{
    // À COMPLÉTER
}

//--------------------------------------------------------------------
void Descendants::ajouter(const Individu & par, const Individu & enf)
{
    // À COMPLÉTER
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
