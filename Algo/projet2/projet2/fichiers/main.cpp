/**
 * @file main.cpp
 * @author Glenn Plouhinec, Mamadou Diallo
 * @date 16/12/2016 Création
 * @date 16/12/2016 Actualisation du commentaire d'entête
 * @brief Utilisation des deux structures de données implémentées pour gérer une file de priorité contenant une liste de communes issues d'un fichier texte
**/
#include <iostream>   // cout et endl
#include <cassert>    // assert()
#include <fstream>    // lecture et écriture fichiers
#include <string>     // utilisation du type string
#include "commune.hpp"// le fichier contenant la structure Commune


//----------------------------------------------------------------------------------------------------------

// Mise en commentaire du fichier d'en-tête non utilisé pour ne pas avoir de conflit lors de la compilation

#include "file_chainage.hpp"    // le fichier contenant la structure de données chaînée
//#include "file_tableau.hpp"   // le fichier contenant la structure de données tabulaire

 //----------------------------------------------------------------------------------------------------------


using namespace std;


//--------------------------------------------------------------------
/**
 * @brief fonction principale
 *
 * @param argc, **argv
 *
 * @b Role : Ouvrir un fichier texte en lecture afin de remplir notre file et effectuer des tests de manipulations sur les files de priorité
 * @pre - aucune
 * @post - aucune
 *
 * @b Complexité - θ(N)
 *
**/
int main(int argc, char **argv)
{
  FilePriorite fp;
  FilePriorite fdp;

  creer(fp);
  assert(est_vide(fp));

  Commune commune;

  std::string line;
  std::ifstream file;

//----------------------------------------------------------------------------------------------------------

// Mise en commentaire du fichier que l'on n'ouvrira pas, pour la lisibilité des tests

  //file.open( "liste_communes.txt", std::ifstream::in );
  file.open( "communes_test.txt", std::ifstream::in );

//----------------------------------------------------------------------------------------------------------


  // Cette boucle while va lire le fichier liste_communes.txt, placer
  // les données dans commune et enfiler commune dans fp.
  while( getline( file, line ) )
  {
    get_data( line, commune );
    enfiler( fp, commune );
  };
  file.close();


// Jeu de tests
//--------------------------------------------------------------------
  //cout << "result : " << est_vide(fp) <<endl;

  //cout << "File de base: " << endl;
  cout << endl;
  afficher(fp);
  cout << endl;
  cout << trouver_une_commune(fp , 46) <<endl;
  cout << endl;
  /*cout << "File avec l'élément 1 supprimé: " << endl;
  supprimer(fp , 1);
  afficher(fp);*/

  /*defiler(fp);
  defiler(fp);
  cout << "File défilée 2 fois: " << endl;
  afficher(fp);
  cout << endl;

  cout << "Le premier élément de la file précédente est: "<< premier(fp) << endl;

  string nomCommune = "Lucelle";
  unsigned int nvellePrio = 15;
  cout << "Changement de priorité pour la commune \"" << nomCommune << "\" avec une nouvelle priorité à "<< nvellePrio << ": " << endl;
  changer_priorite(fp, nomCommune, nvellePrio);
  afficher(fp);
  cout << endl;

  string nvCommune = "Nantes";
  unsigned int prioCommune = 51;
  Commune com;
  com.nom = nvCommune;
  com.priorite = prioCommune;
  cout << "Ajout de la commune " << nvCommune << " avec la priorité " << prioCommune << " : " << endl;
  enfiler(fp, com);
  afficher(fp);
  cout << endl;

  unsigned int cherchePrio = 48;
  cout << "Nom d'une commune dont la priorité vaut " << cherchePrio << ": " << trouver_une_commune(fp, cherchePrio) << endl << endl;

  unsigned int prioInf = 70;
  cout << "Communes dont la priorité est inférieure à " << prioInf << ": " << endl;
  fdp = communes_inferieures(fp,prioInf);
  afficher(fdp);
  cout << endl;

  unsigned int budget = 500000;
  cout << "Communes les plus prioritaires pouvant être prises en charge par l'Etat avec un budget de " << budget << "€ : " << endl;
  fdp = communes_couteuses(fp, budget);
  afficher(fdp);
  cout << endl;*/
}
