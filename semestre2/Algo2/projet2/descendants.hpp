/**
 * @file descendants.hpp
 * @date 21/03/2017 Création
 * @brief Définition de la classe Descendants
**/

#ifndef _DESCENDANTS_HPP_
#define _DESCENDANTS_HPP_

#include <set> // pour le type std::set
#include <ostream> // pour le type std::ostream
#include "individu.hpp" // pour le type Individu
#include "ancetres.hpp" // pour le type Ancetres

/**
 * @brief La classe Descendants représente un arbre généalogique de descendants
**/
class Descendants
{
    public:
        /**
         * @brief Constructeur d'un arbre pour un individu
         * @param ind L'individu racine
         * @post L'arbre a ind pour racine et aucun autre nœud
         * 
         * @b Complexité À INDIQUER
         **/
        Descendants(const Individu & ind);

        /**
         * @brief Constructeur d'un arbre pour un individu à partir d'une forêt d'ancêtres
         * @param ind L'individu racine
         * @param anc La forêt d'ancêtres
         * @pre ind est présent dans anc
         * @post L'arbre a ind pour racine et contient tous les descendants de ind présents dans anc
         * 
         * @b Complexité À INDIQUER
         **/
        Descendants(const Individu & ind, const Ancetres & anc);
        
        /**
         * @brief Destructeur
         * 
         * @b Complexité À INDIQUER
         **/
        ~Descendants();
        
        /**
         * @brief Affiche dans un flux de sortie (fichier ou écran)
         * @param os Le flux de sortie
         * 
         * @b Complexité À INDIQUER
         **/
        void afficher(std::ostream & os) const;

        /**
         * @brief Indique si un individu est présent dans l'arbre
         * @param ind L'individu à rechercher
         * @return Vrai ssi l'individu est dans l'arbre
         * 
         * @b Complexité À INDIQUER
         **/
        bool estPresent(const Individu & ind) const;

        /**
         * @brief Ajoute un nouvel enfant à un individu de l'arbre
         * @param par L'individu parent
         * @param enf Le nouvel individu enfant
         * @pre L'individu par est déjà dans l'arbre, pas l'individu enf
         * 
         * @b Complexité À INDIQUER
         **/
        void ajouter(const Individu & par, const Individu & enf);
        
        /**
         * @brief Calcule l'ensemble des individus descendants de l'individu racine au degré k (1 = les enfants, 2 = les petits enfants, ...)
         * @return Les descendants au k-ième degré
         * 
         * @b Complexité À INDIQUER
         **/
        std::set<Individu> auDegre(unsigned int k) const;
        
        /**
         * @brief Calcule l'ensemble des descendants communs à deux arbres
         * @return L'ensemble des descendants communs
         * 
         * @b Complexité À INDIQUER
         **/
        std::set<Individu> descendantsCommuns(const Descendants & des) const;

    private:
        // Type Nœud
        struct Noeud
        {
            Individu ind; // l'individu représenté
            Noeud *fils, *frere; // enfant aîné, prochain dans la fratrie
        };
        // Attributs
        Noeud racine; // le nœud de l'individu racine
        // Méthodes
        // EN AJOUTER AU BESOIN
};

/// surcharge de l'opérateur d'affichage << pour les Descendants
std::ostream & operator<<(std::ostream & os, const Descendants & anc);

#endif // _DESCENDANTS_HPP_
