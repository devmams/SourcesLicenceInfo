/**
 * @file tp1-1.cpp
 * @author C. Jerman
 * @date 15/09/2015 Création
 * @date 15/09/2016 Actualisation du commentaire d'entête
 * @brief Programme implémentant l'algorithme de calcul de la valeur absolue vu en CM
**/

#include <iostream> // pour cout et cin
#include <cassert> // pour assert
#include <cmath> // pour abs
using namespace std;

//--------------------------------------------------------------------
/**
 * @brief fonction principale
 *
 * @b Role : calculer la valeur absolue d'un entier saisi par l'utilisateur
 *
 * @b Entrée :
 *     - @e n entier positif
 *
 * @b Sortie :
 *     - @e m valeur absolue de n
 *
 * @pre Entier n
 *
 * @post m=|n|
 *
 * @test
 * | Entrees | Sorties | Justification |  Resultat |
 * |---------|---------|---------------|-----------|
 * | n=1     | m=1     | branche alors | passe     |
 * | n=0     | m=0     | cas limite    | passe     |
 * | n=-1    | m=1     | branche sinon | passe     |
 * | n='a'   | ERREUR  | illicite      | échoue (problème de saisie C++) |
 *
 * Ce programme demande à l'utilisateur un entier et en calcule la valeur
 * absolue au moyen d'une conditionnelle, puis affiche le résultat. Il
 * illustre la documentation Doxygen d'un programme et l'utilisation des
 * assertions.
*/
int main()
{
   // lexique
   int n,m;

   // saisie de l'entier à traiter
   cout << "Donnez un entier ? ";
   cin >> n;

   assert(true); // pré-condition de type pas testable
                 // sauf à mettre en place du contrôle de saisie

   // calcul de la valeur absolue par disjonction de cas
   if (n>0)
   {
      m = n;
   }
   else // n≤0
   {
      m = -n;
   }

   assert(m==abs(n)); // post-condition testée après le calcul

   // affichage du résultat
   cout << "Valeur absolue : " << m << endl;

   return 0;
} // main
