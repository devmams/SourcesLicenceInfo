#include <iostream>
#include <cassert>
#include <fstream>
#include <string>

#include "commune.hpp"

#include "file_chainage.hpp"
//#include "file_tableau.hpp"

using namespace std;

int main(int argc, char **argv)
{
  // il faut que FilePriorite soit implémenté pour que ça puisse compiler
  FilePriorite fp;

  // il faut que creer soit implémenté pour que ça puisse compiler
  creer( fp );

  // il faut que est_vide soit implémenté pour que ça puisse compiler
  assert( est_vide(fp) );

  Commune commune;

  std::string line;
  std::ifstream file;
  file.open( "test_commune.txt", std::ifstream::in );

  // Cette boucle while va lire le fichier liste_communes.txt, placer
  // les données dans commune et enfiler commune dans fp.
  // Bien entendu, il faut que vous implémentiez enfiler d'abord...
  while( getline( file, line ) )
  {
    get_data( line, commune );
    enfiler( fp, commune );
  };

  file.close();

  afficher(fp);
  //cout << premier(fp) << endl;


}
