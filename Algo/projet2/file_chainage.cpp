#include <iostream>
#include <cstdio>
#include <string>
#include "file_chainage.hpp"

using namespace std;

void supprimer(FilePriorite & ch, Maillon * pm)
{
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

void afficher(const FilePriorite & ch)
{
	Maillon* courant;
	courant = ch.tete;
	cout << "[";
	int cpt = 0;
	//cout << ch.tete->elt->nom <<endl;
	/*while (cpt != 10 )
	{
		cout << "test" <<endl;
		cout << courant->suiv ;//<< " : " << courant->elt.priorite <<endl;
		//courant = courant->suiv;
		cpt++;
	}*/
	cout << "]" << endl;
}

void creer(FilePriorite & ch)
{
	ch.tete = NULL;
}

bool est_vide(const FilePriorite & ch)
{
	return ch.tete == NULL;
}

//On ajoute un nouvel élément en queue (correspond au moins prioritaire), tandis que la tete est le premier élément de la file est le plus prioritaire
void enfiler(FilePriorite & ch, const Commune & com)
{
	Maillon *nouveau = new Maillon;
	nouveau->elt = com;
	nouveau->suiv = ch.tete;

	if(est_vide(ch)){
		ch.tete = nouveau;
	}
	else{
		Maillon *courant = new Maillon;
		Maillon *prec = new Maillon;
		courant = ch.tete;
		prec = courant;
		while(com.priorite < courant->elt.priorite && courant->suiv != NULL){
			prec = courant;
			courant = courant->suiv;
		}
		prec->suiv = nouveau;
		nouveau->suiv = courant;
		//ch.tete = nouveau;
	}

	/*else
	{
		while((*courant).suiv != NULL && (*courant).elt.priorite > com.priorite)
		{
			courant = (*courant).suiv;
		}

		tmp = (*courant).suiv; // Le pointeur vers maillon apres le maillon courant est affecté à une variable temporaire tmp
		(*courant).suiv = new Maillon; // On alloue un nouveau maillon apres le maillon courant
		courant = (*courant).suiv; // On accède au nouveau maillon (vide) venant d'etre créé
		(*courant).elt = com; // on lui affecte la valeur de la commune passée en parametre
		(*courant).suiv = tmp; // On reconstruit le chainage
	}*/

}


string premier(const FilePriorite & ch)
{
	Maillon* courant;

	courant = ch.tete;

	return (*courant).elt.nom;
}


void defiler(FilePriorite & ch)
{
	Maillon* apres;

	apres = (*ch.tete).suiv;
	delete(ch.tete);
	ch.tete = apres;
}


void changer_priorite(FilePriorite & ch, const string & nomCherche, const unsigned int & nvPrio)
{
	Maillon* courant;

	courant = ch.tete;

	while((*courant).elt.nom != nomCherche && (*courant).suiv != NULL)
	{
		courant = (*courant).suiv;
	}

	if((*courant).elt.nom == nomCherche && (*courant).suiv != NULL)
	{
		supprimer(ch, courant);

		Commune nvCom;
		nvCom.nom = nomCherche;
		nvCom.priorite = nvPrio;

		enfiler(ch, nvCom);
	}
}


string trouver_une_commune(const FilePriorite & ch, const unsigned int & cherchePrio)
{
	Maillon* courant;
	string result;
	courant = ch.tete;

	while(courant != NULL)
	{
		if((*courant).elt.priorite == cherchePrio)
		{
			result = (*courant).elt.nom;
		}
		courant = (*courant).suiv;
	}
	return result;
}
