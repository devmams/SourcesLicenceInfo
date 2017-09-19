/**
 * @file file_chainage.cpp
 * @author Glenn Plouhinec, Mamadou Diallo
 * @date 15/12/2016 Création
 * @date 15/12/2016 Actualisation du commentaire d'entête
 * @brief Implémentation de la structure de données abstraite FilePriorite utilisant un chainage pour stocker les données des communes à gérer par l'Etat
**/
#include <cassert>	// assert()
#include <iostream>	// cout et endl
#include <cstdio>	// pour utiliser la valeur NULL notamment
#include <string>	// utilisation du type string
#include "file_chainage.hpp"	// le fichier contenant la structure de données chaînée

using namespace std;


//--------------------------------------------------------------------
/**
 * @brief fonction est_vide
 *
 * @param ch la File de Priorité
 *
 * @b Role : Indiquer si la file de priorité passée en paramètre est vide ou non
 * @b Entrée :
 *       - @e ch: La structure de données (File de Priorité) que l'on va analyser
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
bool est_vide(const FilePriorite & ch)
{
	return ch.tete == NULL;
}


//--------------------------------------------------------------------
/**
 * @brief procédure supprimer
 *
 * @param ch la File de Priorité où l'on va supprimer un élément, pm l'adresse du Maillon à supprimer
 *
 * @b Role : Supprime un élement à une position donnée dans le chainage en décalant en reconstruisant le chainage après suppression
 * @b Entrée :
 *       - @e ch: La structure de données (File de Priorité) que l'on va modifier
 *       - @e pm: Pointeur vers Maillon indiquant l'adresse du Maillon à supprimer
 *
 * @b Sortie :
 *       - @e ch: La structure de données (File de Priorité) modifiée
 *
 * @pre - La File est non vide
 * @post - aucune
 *
 * @b Complexité - θ(n)
 *
**/
void supprimer(FilePriorite & ch, Maillon * pm)
{
	assert(!est_vide(ch));

	if(pm == ch.tete)
	{
		ch.tete = (*pm).suiv;
	}
	else
	{
		Maillon* pred;
		pred = ch.tete;
		while((*pred).suiv != pm)
		{
			pred = (*pred).suiv;
		}
		(*pred).suiv = (*pm).suiv;
	}
	delete pm;
}


//--------------------------------------------------------------------
/**
 * @brief procédure afficher
 *
 * @param ch la File de Priorité que l'on va afficher
 *
 * @b Role : Affiche chaque élément contenu dans la file de priorité
 * @b Entrée :
 *       - @e ch: La structure de données (File de Priorité) que l'on va afficher
 *
 * @b Sortie :
 *       - @e ch: La structure de données (File de Priorité) affichée à l'écran
 *
 * @pre - aucune
 * @post - aucune
 *
 * @b Complexité - θ(n)
 *
**/
void afficher(const FilePriorite & ch)
{
	Maillon* courant;

	courant = ch.tete;
	cout << "[";

	if(est_vide(ch))
	{
		cout << "]" << endl;
	}
	else
	{
		//boucle où la chaine se termine par NULL
		while ((*courant).suiv != NULL)
		{
			cout << (*courant).elt.nom << ": " << (*courant).elt.priorite << "; ";
			courant = (*courant).suiv;
		}
		cout << (*courant).elt.nom << ": " << (*courant).elt.priorite << "]" << endl;
	}
}


//--------------------------------------------------------------------
/**
 * @brief procédure creer
 *
 * @param ch la File de Priorité que l'on va créer (initialiser)
 *
 * @b Role : Initialiser la file de priorité passée en paramètre
 * @b Entrée :
 *       - @e ch: La structure de données (File de Priorité) que l'on va initialiser
 *
 * @b Sortie :
 *       - @e ch: La structure de données initialisée
 *
 * @pre - aucune
 * @post - La file est vide
 *
 * @b Complexité - θ(1)
 *
**/
void creer(FilePriorite & ch)
{
	ch.tete = NULL;

	assert(est_vide(ch));
}


//--------------------------------------------------------------------
/**
 * @brief procédure enfiler
 *
 * @param ch la File de Priorité à modifier, com la Commune que l'on va ajouter dans la file
 *
 * @b Role : Ajouter une nouvelle commune de façon à ce que la file soit triée par ordre croissant, la queue du chainage contient la priorité la plus faible, et la tete la priorité la plus élevée
 * @b Entrée :
 *       - @e ch: La structure de données (File de Priorité) que l'on va modifier
 *       - @e com: La Commune la commune que l'on va ajouter
 *
 * @b Sortie :
 *       - @e ch: La File de Priorité avec un élément en plus
 *
 * @pre - aucune
 * @post - File non vide.
 *
 * @b Complexité - θ(n)
 *
**/
void enfiler(FilePriorite & ch, const Commune & com)
{
	Maillon* courant;
	Maillon* tmp;

	courant = ch.tete;

	if(courant == NULL)
	{
		ch.tete = new Maillon;
		courant = ch.tete;
		(*courant).elt = com;
	}
	else if(com.priorite >= (*courant).elt.priorite)
	{
		tmp = new Maillon;
		(*tmp).elt = com;
		(*tmp).suiv = courant;
		ch.tete = tmp;
	}
	else
	{

		while((*courant).suiv != NULL && com.priorite < (*(*courant).suiv).elt.priorite)
		{
			courant = (*courant).suiv;
		}

		tmp = (*courant).suiv; // Le pointeur vers maillon apres le maillon courant est affecté à une variable temporaire tmp
		(*courant).suiv = new Maillon; // On alloue un nouveau maillon apres le maillon courant
		courant = (*courant).suiv; // On accède au nouveau maillon (vide) venant d'etre créé
		(*courant).elt = com; // on lui affecte la valeur de la commune passée en parametre
		(*courant).suiv = tmp; // On reconstruit le chainage
	}

	assert(!est_vide(ch));
}


//--------------------------------------------------------------------
/**
 * @brief fonction premier
 *
 * @param ch la File de Priorité
 *
 * @b Role : Retourner le nom de la commune en "tête de file", le "premier arrivé", c-a-d la tête du chaînage, qui contient la priorité la plus élevée
 * @b Entrée :
 *       - @e ch: La structure de données (File de Priorité) à analyser
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
string premier(const FilePriorite & ch)
{
	assert(!est_vide(ch));

	Maillon* courant;

	courant = ch.tete;

	return (*courant).elt.nom;
}


//--------------------------------------------------------------------
/**
 * @brief procédure defiler
 *
 * @param ch la File de Priorité
 *
 * @b Role : Retirer le premier élément de la file: l'élément le plus prioritaire.
 * @b Entrée :
 *       - @e ch: La structure de données (File de Priorité) à modifier
 *
 * @b Sortie :
 *       - @e ch: La structure de données (File de Priorité) avec un élément en moins
 *
 * @pre - File non vide
 * @post - aucune
 *
 * @b Complexité - θ(1)
 *
**/
void defiler(FilePriorite & ch)
{
	assert(!est_vide(ch));

	Maillon* apres;

	apres = (*ch.tete).suiv;	//On affecte la tete dans une variable temporaire
	delete(ch.tete);	//On désalloue la tête
	ch.tete = apres;	//On recrée une nouvelle tête
}


//--------------------------------------------------------------------
/**
 * @brief procédure changer_priorite
 *
 * @param ch la File de Priorité, nomCherche  la commune recherchée, nvPrio la nouvelle priorité 
 *
 * @b Role : Modifier la priorité d'une commune dont on connaît le nom, on lui affecte une nouvelle priorité nvPrio, en conservant une file triée de façon cohérente
 * @b Entrée :
 *       - @e ch: La structure de données (File de Priorité) à modifier
 *       - @e nomCherche: le nom de la commune à modifier
 *       - @e nvPrio: la nouvelle priorité à affecter à la commune correspondant à nomCherche
 *
 * @b Sortie :
 *       - @e ch: La structure de données (File de Priorité) de nouveau triée, avec la commune nomCherche modifiée
 *
 * @pre - File non vide, 0 <= nvPrio <= 100
 * @post - La commune existe dans la file, et la priorité de la commune = nvPrio
 *
 * @b Complexité - θ(n)
 *
**/
void changer_priorite(FilePriorite & ch, const string & nomCherche, const unsigned int & nvPrio)
{
	assert(!est_vide(ch) && 0 <= nvPrio <= 100);

	Maillon* courant;

	courant = ch.tete;

	while((*courant).elt.nom != nomCherche && (*courant).suiv != NULL)	// On recherche la commune correspondante
	{
		courant = (*courant).suiv;
	}

	if((*courant).elt.nom == nomCherche && (*courant).suiv != NULL)		// On vérifie que la commune ait été trouvée
	{
		supprimer(ch, courant);		// On supprime l'élément de la file

		Commune nvCom;
		nvCom.nom = nomCherche;
		nvCom.priorite = nvPrio;	// On reconstruit l'élément, avec la nouvelle priorité
	
		enfiler(ch, nvCom);			// On enfile la commune modifiée dans notre file
	}


	bool postcond = false;
	courant = ch.tete;
	while((*courant).elt.nom != nomCherche && (*courant).suiv != NULL)
	{
		courant = (*courant).suiv;
	}
	if((*courant).elt.nom == nomCherche && (*courant).elt.priorite == nvPrio)
	{
		postcond = true;
	}
	assert(postcond);
}


//--------------------------------------------------------------------
/**
 * @brief fonction trouver_une_commune
 *
 * @param ch la File de Priorité, cherchePrio l'entier naturel que l'on va rechercher dans la file
 *
 * @b Role : Retourner le nom d'une commune ayant la priorité recherchée, on retourne la chaine vide si elle n'existe pas
 * @b Entrée :
 *       - @e ch: La structure de données (File de Priorité) à analyser
 *       - @e cherchePrio: L'entier naturel servant pour rechercher une commune correspondante
 *
 * @b Sortie :
 *       - @e Chaîne de caractères référencant le nom de la commune trouvée par la recherche
 *
 * @pre - File non vide
 * @post - priorite de la commune = cherchePrio ou result = ""
 *
 * @b Complexité - θ(n)
 *
**/
string trouver_une_commune(const FilePriorite & ch, const unsigned int & cherchePrio)
{
	assert(!est_vide(ch));

	Maillon* courant;
	Maillon* trouve;

	string result = "";
	courant = ch.tete;

	while(courant != NULL)
	{
		if((*courant).elt.priorite == cherchePrio)
		{
			result = (*courant).elt.nom;
			trouve = courant;
		}
		courant = (*courant).suiv;
	}

	bool postcond = false;
	if((*trouve).elt.priorite == cherchePrio || result == "")
	{
		postcond = true;
	}
	assert(postcond);

	return result;
}


//--------------------------------------------------------------------
/**
 * @brief fonction communes_inférieures
 *
 * @param ch la File de Priorité, p l'entier naturel servant d'indice pour défiler les communes ayant une priorité supérieure ou égale à celui-ci
 *
 * @b Role : Retourne une file de priorité ne contenant que les communes ayant une priorité strictement inférieure à p
 * @b Entrée :
 *       - @e ch: La structure de données (File de Priorité) à analyser
 *       - @e p: L'entier naturel servant d'indice pour défiler les communes ayant une priorité supérieure ou égale à celui-ci
 *
 * @b Sortie :
 *       - @e nvFile: File de Priorité ne contenant que les communes ayant une priorité strictement inférieure à p
 *
 * @pre - File (ch) non vide
 * @post - priorité de la premiere commune de nvFile < p
 *
 * @b Complexité - θ(n)
 *
**/
FilePriorite communes_inferieures(const FilePriorite & ch, const unsigned int & p)
{
	assert(!est_vide(ch));

	FilePriorite nvFile;
	Maillon* courant;

	courant = ch.tete;

	//Méthode 1 (fonctionne)
	nvFile = ch;
	while(courant != NULL && (*courant).elt.priorite >= p)	//On défile notre nouvelle file tant que la priorité de la commune est supérieure ou égale à p
	{
		defiler(nvFile);
		courant = (*courant).suiv;
	}

	//Méthode 2 (ne fonctionne pas)
	/*creer(nvFile);
	while(courant != NULL)
	{		
		if((*courant).elt.priorite < p)
		{
			enfiler(nvFile, (*courant).elt);	// On enfile uniquement les communes strictement inférieures à p
		}
		courant = (*courant).suiv;
	}*/

	assert((*nvFile.tete).elt.priorite < p);

	return nvFile;
}


//--------------------------------------------------------------------
/**
 * @brief fonction communes_couteuses
 *
 * @param ch la File de Priorité, b l'entier naturel représentant le budget de l'Etat
 *
 * @b Role : Retourne une file de priorité ne contenant que les communes les plus prioritaires pouvant être prises en charge par l'Etat à hauteur du budget b
 * @b Entrée :
 *       - @e ch: La structure de données (File de Priorité) à analyser
 *       - @e b: L'entier naturel représentant le budget de l'Etat
 *
 * @b Sortie :
 *       - @e nvFile: File de Priorité ne contenant que les communes les plus prioritaires pouvant être prises en charge par l'Etat à hauteur du budget b
 *
 * @pre - File (ch) non vide et b >= 10000
 * @post - priorité de la premiere commune de nvFile <= prioMax ou nvFile est vide si le budget est trop faible
 *
 * @b Complexité - θ(n)
 *
**/
FilePriorite communes_couteuses(const FilePriorite & ch, const unsigned int & b)
{
	assert(!est_vide(ch) && b >= 10000);

	FilePriorite nvFile;
	Maillon* courant;
	unsigned int prioMax;

	courant = ch.tete;
	prioMax = (*courant).elt.priorite;

	// Dans cette boucle, on détermine la priorité maximale pouvant être prises en charge par l'Etat
	while(courant != NULL && (*courant).elt.priorite > b/10000)
	{			
		courant = (*courant).suiv;
		prioMax = (*courant).elt.priorite;
	}

	courant = ch.tete;

	nvFile = ch;	// On affecte la file de base ch à notre nouvelle file
	while(courant != NULL)
	{
		if((*courant).elt.priorite != prioMax)
		{
			supprimer(nvFile, courant);		// On supprime dans notre nouvelle file tous les éléments dont la priorité est différente de prioMax
		}
		courant = (*courant).suiv;
	}

	assert((*nvFile.tete).elt.priorite <= b || est_vide(nvFile));

	return nvFile;
}
