#pragma once

#include <string>
#include "commune.hpp"

#define Taille 40000

struct FilePriorite{
  Commune TabCommune[Taille];
  unsigned int nb;
};



void creer( FilePriorite & ); // initialiser tableau vide

bool est_vide( const FilePriorite & );

void supprimer( FilePriorite & Tab, unsigned int pos);

void enfiler( FilePriorite &, const Commune & );

std::string premier( const FilePriorite & );

void defiler( FilePriorite & );

void changer_priorite( FilePriorite &, const std::string &, const unsigned int & );

std::string trouver_une_commune( const FilePriorite &, const unsigned int & );

void afficher( const FilePriorite & );

FilePriorite communes_inferieures(const FilePriorite & Tab, const unsigned int & p);

FilePriorite communes_couteuses(const FilePriorite & Tab, const unsigned int & b);
