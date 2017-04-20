/**
 * @file ancetres.hpp
 * @date 21/03/2017 Création
 * @brief Définition de la classe Ancetre
**/

#ifndef _ANCETRES_HPP_
#define _ANCETRES_HPP_

#include <string> // pour le type std::string
#include <ostream> // pour le type std::ostream
#include <set> // pour le type std::set
#include <vector> // pour le type std::vector
#include <unordered_map> // pour le type std::unordered_map
#include "individu.hpp" // pour le type Individu

/**
 * @brief La classe Ancetres représente une forêt généalogique d'ancêtres
**/
class Ancetres
{
    public:
        /**
         * @brief Constructeur d'une forêt vide
         * @post La forêt est vide
         *
         * @b Complexité Θ(1)
         **/
        Ancetres();

        /**
         * @brief Constructeur à partir d'un fichier
         * @param fic Le fichier généalogique
         * @pre le fichier existe et son format est correct
         * @post La forêt contient les individus du fichier et leurs liens de filiation
         *
         * @b Complexité Θ(N) avec N = nombre d'individus dans fic
         **/
        Ancetres(std::string fic);

        /**
         * @brief Affiche dans un flux de sortie (fichier ou écran)
         * @param os Le flux de sortie
         *
         * @b Complexité Θ(N) avec N = nombre d'individus dans la forêt
         **/
        void afficher(std::ostream & os) const;

        /**
         * @brief Indique si un individu est présent dans la forêt
         * @param ind L'individu à rechercher
         * @return Vrai ssi l'individu est dans la forêt
         *
         * @b Complexité À INDIQUER
         **/
        bool estPresent(const Individu & ind) const;

        /**
         * @brief Ajoute un nouvel individu à la forêt des ancêtres
         * @param ind L'individu à ajouter
         * @pre L'individu n'est pas déjà dans la forêt
         * @post L'individu inséré n'a ni père ni mère
         *
         * @b Complexité À INDIQUER
         **/
        void ajouter(const Individu & ind);

        /**
         * @brief Indique si le père d'un individu est connu
         * @param ind L'individu considéré
         * @return VRAI ssi le père est présent dans la forêt
         * @pre L'individu est dans la forêt
         *
         * @b Complexité À INDIQUER
         **/
        bool hasPere(Individu ind) const;

        /**
         * @brief Accède au père d'un individu
         * @param ind L'individu considéré
         * @return L'individu père de ind
         * @pre L'individu et son père sont dans la forêt
         *
         * @b Complexité À INDIQUER
         **/
        Individu getPere(Individu ind) const;

        /**
         * @brief Modifie le père d'un individu
         * @param ind L'individu à modifier
         * @param pere L'individu père
         * @pre L'individu et son père sont déjà dans la forêt
         * @post L'individu a changé de père
         *
         * @b Complexité À INDIQUER
         **/
        void setPere(Individu ind, Individu pere);

        /**
         * @brief Indique si la mère d'un individu est connue
         * @param ind L'individu considéré
         * @return VRAI ssi la mère est présente dans la forêt
         * @pre L'individu est dans la forêt
         *
         * @b Complexité À INDIQUER
         **/
        bool hasMere(Individu ind) const;

        /**
         * @brief Accède à la mère d'un individu
         * @param ind L'individu considéré
         * @return L'individu mère de ind
         * @pre L'individu et sa mère sont dans la forêt
         *
         * @b Complexité À INDIQUER
         **/
        Individu getMere(Individu ind) const;

        /**
         * @brief Modifie la mère d'un individu
         * @param ind L'individu à modifier
         * @param mere L'individu mère
         * @pre L'individu et sa mère sont déjà dans la forêt
         * @post L'individu a changé de mère
         *
         * @b Complexité À INDIQUER
         **/
        void setMere(Individu ind, Individu mere);

        /**
         * @brief Calcule l'ensemble des individus sans enfants dans la forêt
         * @return Les individus racines de la forêt
         *
         * @b Complexité À INDIQUER
         **/
        std::set<Individu> racines() const;

        /**
         * @brief Accède à l'ensemble des individus dans la forêt
         * @return Tous les individus de la forêt
         *
         * @b Complexité À INDIQUER
         **/
        std::set<Individu> individus() const;

        /**
         * @brief Calcule l'ensemble des ancêtres communs à deux individus
         * @pre Les individus sont bien dans la forêt
         * @return La forêt des ancêtres communs
         *
         * @b Complexité À INDIQUER
         **/
        Ancetres ancetresCommuns(Individu ind1, Individu ind2) const;

        /**
         * @brief Fusionne l'instance avec la forêt passée en paramètre
         * @param anc La forêt à fusionner
         *
         * @b Complexité À INDIQUER
         **/
        void fusion(Ancetres anc);

        ///////////////
        Ancetres ancetreIndividus(Individu ind) const;
        std::vector<Individu> lesFils(Individu ind) const;

    private:
        // Un nœud de la forêt contient un individu et les indices de ses parents (-1 si inconnus)
        struct Noeud {
            Individu ind; // l'individu représenté
            int pere, mere; // son père, sa mère
        };
        // Attributs
        std::vector<Noeud> noeuds; // Le tableau des nœuds
        std::unordered_map<Individu,unsigned int> indTOnd; // table associative Individu->Nœud
        // Méthodes
        // EN AJOUTER AU BESOIN
};

/// surcharge de l'opérateur d'affichage << pour les Ancetres
std::ostream & operator<<(std::ostream & os, const Ancetres & anc);

#endif // _ANCETRES_HPP_
