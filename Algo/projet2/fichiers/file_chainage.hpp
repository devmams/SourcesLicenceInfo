#pragma once

#include <string>
#include "commune.hpp"

struct Maillon {
	Commune elt;
	Maillon* suiv;
};

struct FilePriorite {
	Maillon* tete;
	//unsigned int nb;
};



void creer( FilePriorite & ); //initialiser chainage vide

bool est_vide( const FilePriorite &);

void enfiler( FilePriorite & , const Commune & );

std::string premier( const FilePriorite & );

void defiler( FilePriorite & );

void changer_priorite( FilePriorite &, const std::string &, const unsigned int & );

std::string trouver_une_commune( const FilePriorite &, const unsigned int & );

void afficher( const FilePriorite & );

FilePriorite communes_inferieures(const FilePriorite & ch, const unsigned int & p);

FilePriorite communes_couteuses(const FilePriorite & ch, const unsigned int & b);