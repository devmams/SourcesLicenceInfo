#include <iostream>
#include <string>
#include <cstdio>
#include "file_tableau.hpp"

using namespace std;

void afficher( const FilePriorite & Tab)
{
	cout << "[";
	for (unsigned int i = 0; i < Tab.nb; ++i)
	{
		cout << Tab.TabCommune[i].nom << ": " << Tab.TabCommune[i].priorite << "; ";
	}
	cout << "]";
}


void creer(FilePriorite & Tab)
{
	Tab.nb = 0;
}


bool est_vide(const FilePriorite & Tab)
{
	if(Tab.nb == 0){
			return true;
	}
	else{
			return false;
	}
}

//On ajoute à la fin de la file qu'on considère comme la case TabCommune[0] (élément le moins prioritaire), et le premier élément (le plus prioritaire) de la file est donc TabCommune[nb]
void enfiler(FilePriorite & Tab, const Commune & com)
{
	unsigned int i;
	Tab.nb++;
	for (i = Tab.nb; i > 0 && Tab.TabCommune[i].priorite >= com.priorite; --i)
	{
		Tab.TabCommune[i] = Tab.TabCommune[i-1];
	}
	Tab.TabCommune[i] = com;
}

string premier(const FilePriorite & Tab)
{
	return Tab.TabCommune[Tab.nb].nom;
}


void defiler( FilePriorite & Tab)
{
	Tab.nb--;
}


void changer_priorite(FilePriorite & Tab, const std::string & nomCherche, const unsigned int & nvPrio)
{
	unsigned int pos = 0;

	while(Tab.TabCommune[pos].nom != nomCherche && pos <= Tab.nb+1)
	{
		++pos;
	}

	if(Tab.TabCommune[pos].nom == nomCherche && pos != Tab.nb+1) //Verification que la commune cherchée ait été trouvée
	{
		for (unsigned int i = pos; i < Tab.nb; ++i)
		{
			Tab.TabCommune[i] = Tab.TabCommune[i+1];
		}
		Tab.nb--;
	
		Commune nvCom;
		nvCom.nom = nomCherche;
		nvCom.priorite = nvPrio;
	
		enfiler(Tab, nvCom);
	}
}

string trouver_une_commune(const FilePriorite & Tab, const unsigned int & cherchePrio)
{
	string result;
	unsigned int gauche, droite, milieu;
	gauche = 1;
	droite = Tab.nb;
	milieu = (gauche+droite)/2;

	while(Tab.TabCommune[milieu].priorite != cherchePrio || gauche != droite || gauche < droite)
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
	return result = (Tab.TabCommune[milieu].priorite == cherchePrio) ? Tab.TabCommune[milieu].nom : " " ;
}
