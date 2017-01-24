/**
 * @file file_tableau.cpp
 * @author Glenn Plouhinec, Mamadou Diallo
 * @date 14/12/2016 Création
 * @date 14/12/2016 Actualisation du commentaire d'entête
 * @brief Implémentation de la structure de données abstraite FilePriorite utilisant un tableau pour stocker les données des communes à gérer par l'Etat
**/
#include <cassert>	// assert()
#include <iostream> // cout et endl
#include <string>	// utilisation du type string
#include <cstdio>	// pour utiliser la valeur NULL notamment
#include "file_tableau.hpp"	// le fichier contenant la structure de données tabulaire

using namespace std;


//--------------------------------------------------------------------
/**
 * @brief fonction est_vide
 *
 * @param Tab la File de Priorité
 *
 * @b Role : Indiquer si la file de priorité passée en paramètre est vide ou non
 * @b Entrée :
 *       - @e Tab: La structure de données (File de Priorité) que l'on va analyser
 *
 * @b Sortie :
 *       - @e booléen qui rend TRUE si la file est vide, FALSE sinon
 *
 * @pre - aucune
 * @post - aucune
 *
 * @b Complexité - θ(1)
 *
**/
bool est_vide(const FilePriorite & Tab)
{
	return Tab.nb == 0;
}


//--------------------------------------------------------------------
/**
 * @brief procédure supprimer
 *
 * @param Tab la File de Priorité où l'on va supprimer un élément, pos la position de l'élément à supprimer
 *
 * @b Role : Supprime un élement à une position donnée dans le tableau en décalant tous les éléments qui le suivent "vers la gauche"
 * @b Entrée :
 *       - @e Tab: La structure de données (File de Priorité) que l'on va modifier
 *       - @e pos: Entier naturel indiquant la position de l'élément à supprimer
 *
 * @b Sortie :
 *       - @e Tab: La structure de données (File de Priorité) modifiée
 *
 * @pre - La File est non vide et pos < Tab.nb
 * @post - Un élément en moins
 *
 * @b Complexité - θ(nb)
 *
**/
void supprimer( FilePriorite & Tab, unsigned int pos)
{

	assert(!est_vide(Tab));
	unsigned int postcond = Tab.nb;

	for (unsigned int i = pos; i <= Tab.nb; ++i)
	{
		Tab.TabCommune[i] = Tab.TabCommune[i+1];
	}
	Tab.nb--;

	assert(Tab.nb == postcond - 1);
}


//--------------------------------------------------------------------
/**
 * @brief procédure afficher
 *
 * @param Tab la File de Priorité que l'on va afficher
 *
 * @b Role : Affiche chaque élément contenu dans la file de priorité
 * @b Entrée :
 *       - @e Tab: La structure de données (File de Priorité) que l'on va afficher
 *
 * @b Sortie :
 *       - @e Tab: La structure de données (File de Priorité) affichée à l'écran
 *
 * @pre - aucune
 * @post - aucune
 *
 * @b Complexité - θ(nb)
 *
**/
void afficher( const FilePriorite & Tab)
{
	cout << "[";

	if(est_vide(Tab))
	{
		cout << "]" << endl;
	}
	else
	{
		for (unsigned int i = 0; i < Tab.nb-1; ++i)
		{
			cout << Tab.TabCommune[i].nom << ": " << Tab.TabCommune[i].priorite << "; ";
		}
		cout << Tab.TabCommune[Tab.nb-1].nom << ": " << Tab.TabCommune[Tab.nb-1].priorite << "]" << endl;
	}
}


//--------------------------------------------------------------------
/**
 * @brief procédure creer
 *
 * @param Tab la File de Priorité que l'on va créer (initialiser)
 *
 * @b Role : Initialiser la file de priorité passée en paramètre
 * @b Entrée :
 *       - @e Tab: La structure de données (File de Priorité) que l'on va initialiser
 *
 * @b Sortie :
 *       - @e Tab: La structure de données initialisée
 *
 * @pre - aucune
 * @post - La file est vide
 *
 * @b Complexité - θ(1)
 *
**/
void creer(FilePriorite & Tab)
{
	Tab.nb = 0;

	assert(est_vide(Tab));
}


//--------------------------------------------------------------------
/**
 * @brief procédure enfiler
 *
 * @param Tab la File de Priorité à modifier, com la Commune que l'on va ajouter dans la file
 *
 * @b Role : Ajouter une nouvelle commune de façon à ce que la file soit triée par ordre croissant, TabCommune[0] contient la priorité la plus faible, et TabCommune[nb] la priorité la plus élevée
 * @b Entrée :
 *       - @e Tab: La structure de données (File de Priorité) que l'on va modifier
 *       - @e com: La Commune la commune que l'on va ajouter
 *
 * @b Sortie :
 *       - @e Tab: La File de Priorité avec un élément en plus
 *
 * @pre - Tab.nb < Taille
 * @post - File non vide et un élément en plus
 *
 * @b Complexité - θ(nb)
 *
**/
void enfiler(FilePriorite & Tab, const Commune & com)
{
	assert(Tab.nb < Taille);

	unsigned int postcond = Tab.nb;

	if(est_vide(Tab))
	{
		Tab.TabCommune[0] = com;
	}
	else
	{
		unsigned int i;
		for(i = Tab.nb; i > 0 && com.priorite < Tab.TabCommune[i-1].priorite; --i)
		{
			Tab.TabCommune[i] = Tab.TabCommune[i-1];
		}
		Tab.TabCommune[i] = com;
	}
	Tab.nb++;

	assert(!est_vide(Tab) && Tab.nb == postcond +1);
}


//--------------------------------------------------------------------
/**
 * @brief fonction premier
 *
 * @param Tab la File de Priorité
 *
 * @b Role : Retourner le nom de la commune en "tête de file", le "premier arrivé", TabCommune[nb-1]
 * @b Entrée :
 *       - @e Tab: La structure de données (File de Priorité) à analyser
 *
 * @b Sortie :
 *       - @e Chaîne de caractères référencant le nom de la commune ayant la priorité la plus élevée
 *
 * @pre - File non vide
 * @post - aucune
 *
 * @b Complexité - θ(1)
 *
**/
string premier(const FilePriorite & Tab)
{
	assert(!est_vide(Tab));

	return Tab.TabCommune[Tab.nb-1].nom;
}


//--------------------------------------------------------------------
/**
 * @brief procédure defiler
 *
 * @param Tab la File de Priorité
 *
 * @b Role : Retirer le premier élément de la file: l'élément le plus prioritaire.
 * @b Entrée :
 *       - @e Tab: La structure de données (File de Priorité) à modifier
 *
 * @b Sortie :
 *       - @e Tab: La structure de données (File de Priorité) avec un élément en moins
 *
 * @pre - File non vide
 * @post - Un élément en moins
 *
 * @b Complexité - θ(1)
 *
**/
void defiler( FilePriorite & Tab)
{
	assert(!est_vide(Tab));
	unsigned int postcond = Tab.nb;

	Tab.nb--;

	assert(Tab.nb == postcond - 1);
}


//--------------------------------------------------------------------
/**
 * @brief procédure changer_priorite
 *
 * @param Tab la File de Priorité, nomCherche  la commune recherchée, nvPrio la nouvelle priorité
 *
 * @b Role : Modifier la priorité d'une commune dont on connaît le nom, on lui affecte une nouvelle priorité nvPrio, en conservant une file triée de façon cohérente
 * @b Entrée :
 *       - @e Tab: La structure de données (File de Priorité) à modifier
 *       - @e nomCherche: le nom de la commune à modifier
 *       - @e nvPrio: la nouvelle priorité à affecter à la commune correspondant à nomCherche
 *
 * @b Sortie :
 *       - @e Tab: La structure de données (File de Priorité) de nouveau triée, avec la commune nomCherche modifiée
 *
 * @pre - File non vide, 0 <= nvPrio <= 100
 * @post - La commune existe dans la file, et la priorité de la commune = nvPrio
 *
 * @b Complexité - θ(nb)
 *
**/
void changer_priorite(FilePriorite & Tab, const std::string & nomCherche, const unsigned int & nvPrio)
{
	assert(!est_vide(Tab) && 0 <= nvPrio <= 100);

	unsigned int pos = 0;

	while(Tab.TabCommune[pos].nom != nomCherche && pos <= Tab.nb+1)
	{
		++pos;
	}

	if(Tab.TabCommune[pos].nom == nomCherche) //Verification que la commune cherchée ait été trouvée
	{
		/*for (unsigned int i = pos; i < Tab.nb; ++i)
		{
			Tab.TabCommune[i] = Tab.TabCommune[i+1];
		}
		Tab.nb--;*/
		supprimer(Tab, pos);

		Commune nvCom;
		nvCom.nom = nomCherche;
		nvCom.priorite = nvPrio;

		enfiler(Tab, nvCom);
	}


	bool postcond = false;
	pos = 0;
	while(Tab.TabCommune[pos].nom != nomCherche && pos <= Tab.nb+1)
	{
		++pos;
	}
	if(Tab.TabCommune[pos].nom == nomCherche && Tab.TabCommune[pos].priorite == nvPrio)
	{
		postcond = true;
	}
	assert(postcond);
}


//--------------------------------------------------------------------
/**
 * @brief fonction trouver_une_commune
 *
 * @param Tab la File de Priorité, cherchePrio l'entier naturel que l'on va rechercher dans la file
 *
 * @b Role : Retourner le nom d'une commune ayant la priorité recherchée, on retourne la chaine vide si elle n'existe pas
 * @b Entrée :
 *       - @e Tab: La structure de données (File de Priorité) à analyser
 *       - @e cherchePrio: L'entier naturel servant pour rechercher une commune correspondante
 *
 * @b Sortie :
 *       - @e Chaîne de caractères référencant le nom de la commune trouvée par la recherche
 *
 * @pre - File non vide et 0 <= cherchePrio <= 100
 * @post - priorite de la commune = cherchePrio ou result = ""
 *
 * @b Complexité - θ(log nb)
 *
**/
string trouver_une_commune(const FilePriorite & Tab, const unsigned int & cherchePrio)
{
	assert(!est_vide(Tab) && 0 <= cherchePrio <= 100);

	string result;
	unsigned int gauche, droite, milieu;
	gauche = 1;
	droite = Tab.nb;
	milieu = (gauche+droite)/2;

	while(Tab.TabCommune[milieu].priorite != cherchePrio && gauche != droite && gauche < droite)
	{
		milieu = (gauche+droite)/2;
		if(cherchePrio > Tab.TabCommune[milieu].priorite)
		{
			gauche = milieu +1;
		}
		else
		{
			droite = milieu;
		}
	}
	result = (Tab.TabCommune[milieu].priorite == cherchePrio) ? Tab.TabCommune[milieu].nom : "" ;

	assert(Tab.TabCommune[milieu].priorite == cherchePrio || result == "");

	return result;
}


//--------------------------------------------------------------------
/**
 * @brief fonction communes_inférieures
 *
 * @param Tab la File de Priorité, p l'entier naturel servant d'indice pour enfiler les communes ayant une priorité strictement inférieure à celui-ci
 *
 * @b Role : Retourne une file de priorité ne contenant que les communes ayant une priorité strictement inférieure à p
 * @b Entrée :
 *       - @e Tab: La structure de données (File de Priorité) à analyser
 *       - @e p: L'entier naturel servant d'indice pour enfiler les communes ayant une priorité strictement inférieure à celui-ci
 *
 * @b Sortie :
 *       - @e nvFile: File de Priorité ne contenant que les communes ayant une priorité strictement inférieure à p
 *
 * @pre - File (Tab) non vide
 * @post - priorité de la premiere commune de nvFile < p
 *
 * @b Complexité - θ(nb)
 *
**/
FilePriorite communes_inferieures(const FilePriorite & Tab, const unsigned int & p)
{
	FilePriorite nvFile;

	creer(nvFile);

	for(unsigned int i = 0; i < Tab.nb; ++i)
	{
		if(Tab.TabCommune[i].priorite < p)
		{
			enfiler(nvFile, Tab.TabCommune[i]);
		}
	}

	assert(nvFile.TabCommune[nvFile.nb-1].priorite < p);

	return nvFile;
}


//--------------------------------------------------------------------
/**
 * @brief fonction communes_couteuses
 *
 * @param Tab la File de Priorité, b l'entier naturel représentant le budget de l'Etat
 *
 * @b Role : Retourne une file de priorité ne contenant que les communes les plus prioritaires pouvant être prises en charge par l'Etat à hauteur du budget b
 * @b Entrée :
 *       - @e Tab: La structure de données (File de Priorité) à analyser
 *       - @e b: L'entier naturel représentant le budget de l'Etat
 *
 * @b Sortie :
 *       - @e nvFile: File de Priorité ne contenant que les communes les plus prioritaires pouvant être prises en charge par l'Etat à hauteur du budget b
 *
 * @pre - File (Tab) non vide et b >= 10000
 * @post - priorité de la premiere commune de nvFile <= prioMax ou nvFile est vide si le budget est trop faible
 *
 * @b Complexité - θ(nb)
 *
**/
FilePriorite communes_couteuses(const FilePriorite & Tab, const unsigned int & b)
{
	assert(!est_vide(Tab) && b >= 10000);

	FilePriorite nvFile;
	unsigned int prioMax;

	// Dans cette boucle, on détermine la priorité maximale pouvant être prises en charge par l'Etat
	prioMax = Tab.TabCommune[0].priorite;
	for(unsigned int i = 0; i < Tab.nb; ++i)
	{
		if(Tab.TabCommune[i].priorite <= b/10000 && Tab.TabCommune[i].priorite > prioMax)
		{
			prioMax = Tab.TabCommune[i].priorite;
		}
	}

	creer(nvFile); // Initialisation de la nouvelle file

	for(unsigned int i = 0; i < Tab.nb; ++i)
	{
		if(Tab.TabCommune[i].priorite == prioMax)
		{
			enfiler(nvFile, Tab.TabCommune[i]);		// On n'enfile que les communes dont la priorité est égale à la priorité maximale déterminée précédemment
		}
	}

	assert(nvFile.TabCommune[nvFile.nb-1].priorite <= prioMax || est_vide(nvFile));

	return nvFile;
}
