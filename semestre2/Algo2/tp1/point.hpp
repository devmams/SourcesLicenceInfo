/* 
   Fichier point.hpp
   Définition du type point générique par type de coordonnées
*/

#ifndef POINT_HPP
#define POINT_HPP

#include <string> // pour le type std::string

/******************************************************************************/
// classe Point générique, paramétrée par le type Nombre à utiliser pour
// représenter les coordonnées (par défaut : float) 
// Hypothèses sur le type Nombre : 
//    - être transtypable en float
//    - admettre l'opération d'addition (opérateur +)
//    - admettre l'envoi dans un flux de sortie (opérateur <<)
template < typename Nombre = float >
class Point
{
   private :
      Nombre _x,_y; // coordonnées cartésiennes

   public :
      /* création/destruction */
      Point(); // constructeur par défaut --> point origine
      ~Point(); // destructeur
   
      std::string enChaine() const; // au format "(x,y)"

      void deplacer(Nombre dx, Nombre dy); // translation de (dx,dy)
      void tourner(Nombre phi); // rotation de phi°
}; // class Point<Nombre>

#include "point.tpp" // définition des méthodes directement incluses dans le .hpp pour les classes génériques
/******************************************************************************/
// C.Jermann fevrier 2013
#endif // POINT_HPP
