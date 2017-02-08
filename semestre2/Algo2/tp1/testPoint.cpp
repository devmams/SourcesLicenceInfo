/* 
   Fichier testPoint.cpp

   Programme illustrant l'utilisation de la bibliothèque Point<Nombre>
*/

#include <iostream> // utile pour les entrées/sorties
#include "point.hpp" // pour utiliser le type Point


using namespace std;

// instanciation des types génériques utilisés dans ce programme
template class Point<>;
template class Point<int>;

// fonction principale
int main()
{
   Point<> p1; // point à coordonnées float (par défaut)
   Point<int> p2; // point à coordonnées int
   
   // affichage des points :
   cout << "Initiaux : p1 = " << p1.enChaine() << " ; p2 = " << p2.enChaine() << endl;
   
   // application de la même translation aux deux points :
   p1.deplacer(1,2); p2.deplacer(1,2); 
   cout << "Translatés : p1 = " << p1.enChaine() << " ; p2 = " << p2.enChaine() << endl;
   
   // application de la même rotation aux deux points :
   p1.tourner(30); p2.tourner(30);
   cout << "Tournés : p1 = " << p1.enChaine() << " ; p2 = " << p2.enChaine() << endl;

   return 0;
} // main
// C.Jermann fevrier 2013
