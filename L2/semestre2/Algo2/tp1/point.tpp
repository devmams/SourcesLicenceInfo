/* 
   Fichier point.tpp

   Définition des méthodes de Point

   Remarque : s'agissant d'une classe générique, ce fichier ne sera pas compilé 
              mais simplement inclus dans le fichier entête de la classe.
*/

#include <cmath> // utile pour les fonctions mathématiques
#include <sstream> // pour les conversions en chaine

/////////////////////////////////////////////////////////////////////////
template < typename Nombre >
Point< Nombre >::Point()
{
   _x = 0.0f; // initialisation au moyen du 0 de type float
   _y = 0.0f;
}

/////////////////////////////////////////////////////////////////////////
template < typename Nombre >
Point< Nombre >::~Point()
{} // rien à faire

/////////////////////////////////////////////////////////////////////////
template < typename Nombre >
std::string Point< Nombre >::enChaine() const
{
   std::stringstream sst; // flux chaine pour la création du résultat
   sst << "(" << _x << "," << _y << ")";
   return sst.str();
}

/////////////////////////////////////////////////////////////////////////
template < typename Nombre >
void Point< Nombre >::deplacer(Nombre dx, Nombre dy)
{
   // calcul au moyen des formules de translation 2D et enregistrement
   _x = _x + dx;
   _y = _y + dy;
}

/////////////////////////////////////////////////////////////////////////
template < typename Nombre >
void Point< Nombre >::tourner(Nombre phi)
{
   Nombre rx,ry; // coordonnées après rotation
   // degrés->radians
   float phirad = float(std::acos(-1.0))*float(phi)/180.0f;
   // calcul au moyen des formules de rotation 2D
   rx = Nombre(_x*cos(phirad) - _y*sin(phirad));
   ry = Nombre(_x*sin(phirad) + _y*cos(phirad));
   // enregistrement des nouvelles coordonnées
   _x = rx;
   _y = ry;
}
// C.Jermann fevrier 2013
