#pragma once

#include <string>
#include "commune.hpp"

#define TAILLE 40000

struct FilePriorite{
  Commune TabCommune[TAILLE];
  unsigned int nb;
};



void creer( FilePriorite & ); // initialiser tableau vide

bool est_vide( const FilePriorite & );

void enfiler( FilePriorite &, const Commune & );

std::string premier( const FilePriorite & );

void defiler( FilePriorite & );

void changer_priorite( FilePriorite &, const std::string &, const unsigned int & );

std::string trouver_une_commune( const FilePriorite &, const unsigned int & );

void afficher( const FilePriorite & );