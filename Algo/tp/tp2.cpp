/**
 * @file tp2.cpp
 * @author C. Jermann
 * @date 21/09/2015 Création
 * @date 19/07/2016 Actualisation des commentaires & retrait du comptage temporel
 * @brief Programme implémentant l'algorithme de recherche dans un tableau d'entier et testant ses performances par comptage des unités de temps
**/
#include <iostream>  // cout, cin
#include <cstdlib>   // rand(), srand()
#include <ctime>     // time(), clock_t, clock(), CLOCKS_PER_SEC
#include <cassert>   // assert()
#include <climits>   // UINT_MAX
#include <cfloat>    // DBL_MAX
using namespace std;

//--------------------------------------------------------------------
// Éléments de mesure de performance

/**
 * @var chrono
 * @brief chronomètre du programme
**/
clock_t chrono;

/**
 * @def START
 * @brief démarre le chronomètre
**/
#define START chrono=clock();

/**
 * @def STOP
 * @brief arrête le chronomètre
**/
#define STOP chrono=clock()-chrono;

/**
 * @def TEMPS
 * @brief donne le temps du chronomètre (après arrêt)
**/
#define TEMPS double(chrono)/CLOCKS_PER_SEC

/**
 * @def MAX(a,b)
 * @brief donne le maximum de a et b
**/
#define MAX(a,b) ((a<b)?b:a)

/**
 * @def MIN(a,b)
 * @brief donne le minimum de a et b
**/
#define MIN(a,b) ((a<b)?a:b)

//--------------------------------------------------------------------
// Définition du type tableau

/**
 * @var N 
 * @brief Constante représentant la taille des tableaux considérés
**/
const unsigned int N = 100;

/**
 * @typedef t_tabint
 * @brief Type des tableaux considérés
**/
typedef int t_tabint[N];


//--------------------------------------------------------------------
/**
 * @brief procédure tabint_croissant
 * 
 * @param tab le tableau à remplir
 * 
 * @b Role : remplit un t_tabint avec les entiers de 1 à N
 * 
 * @b Entrée : 
 *       - @e tab le tableau qu'on va remplir
 * 
 * @b Sortie :
 *       - @e tab le tableau d'entrée rempli (par modification)
 * 
 * @pre -
 * @post pour tout i dans 1..N, tab[i-1]=i
 * 
 * @b Complexité : θ(N), pas de meilleur/pire cas
 * 
**/
void tabint_croissant(t_tabint & tab)
{
   // lexique
   unsigned int i;
   
   // pré-condition
   assert(true);

   for (i=0; i<N; ++i)
   {
      tab[i] = i+1;
   }

   // post-condition
   for (i=0; i<N; ++i)
   {
      assert(tab[i]==int(i)+1); 
   }
}


//--------------------------------------------------------------------
/**
 * @brief procédure recherche_tabint
 * 
 * @b Role : recherche un entier donné dans un t_tabint et retourne son indice
 * 
 * @b Entrées :
 *      - @e ti le tableau dans lequel on cherche
 *      - @e val la valeur recherchée
 * 
 * @b Sortie :
 *      - @e pos l'indice de @e val dans @e ti
 * 
 * @pre val est présent dans ti
 * @post (0≤pos<N) et (ti[pos]=val)
 * 
 * @b Complexité :
 *      - @e meilleur : Ω(1), quand ti[0]=val (premier sondage)
 *      - @e pire : O(N), quand ti[N-1]=val (dernier sondage)
**/
unsigned int recherche_tabint(const t_tabint & ti, int val)
{
   // lexique
   unsigned int pos;

   // pré-condition : test reporté en fin de calcul

   // boucle de recherche
   pos = 0;
   while ((pos<N) and (ti[pos]!=val))
   {
      pos = pos+1;
   }

   // pré-condition
   assert(pos!=N);
   
   // post-condition
   assert((0<=pos) and (pos<N) and (ti[pos]==val));

   return pos;
}


//--------------------------------------------------------------------
/**
 * @brief fonction principale
 * 
 * @b Role : évaluer le coût de la recherche dans un tableau en meilleur cas, pire cas et en moyenne
 * 
 * @b Entrée : -
 * 
 * @b Sortie : -
 * 
 * @pre -
 * @post -
**/
int main()
{
   //--------------------
   // Lexique
   t_tabint t;              // le tableau utilisé pour les tests
   unsigned int i;          // variable de boucle
   int v;                   // valeur recherchée
   unsigned int nb;         // nombres de répétitions pour l'analyse statistique
   double tmin,tmoy,tmax;   // mesures statistiques des performances

   
   //--------------------
   // Remplissage du tableau
   tabint_croissant(t);   // remplissage du tableau avec les entiers 1..N


   //--------------------
   // Meilleur cas : valeur trouvée au premier sondage
   v = 1;                   // valeur recherchée : première du tableau
   START;                   // démarrage du chronomètre
   recherche_tabint(t,v);   // recherche
   STOP;                    // arrêt du chronomètre
   cout << "meilleur cas   : " << TEMPS << "s" << endl; // affichage de la mesure

   
   //--------------------
   // Pire cas : valeur trouvée au dernier sondage
   v = N;                   // valeur recherchée : dernière du tableau
   START;                   // démarrage du chronomètre
   recherche_tabint(t,v);   // recherche
   STOP;                    // arrêt du chronomètre
   cout << "pire cas       : " << TEMPS << "s" << endl; // affichage de la mesure
   

   //--------------------
   // Analyse statistique
   nb = 5;          // nb exécutions aléatoires
   srand(time(0));  // initialisation du tirage aléatoire
   tmin = DBL_MAX;  // temps minimum sur tous les tirages
   tmoy = 0.0;      // temps moyen sur tous les tirages
   tmax = 0.0;      // temps maximum sur tous les tirages
   for (i=0; i<nb; ++i)
   { // Effectue nb recherches aléatoires et calcule les indicateurs statistiques
      v = rand()%N + 1;         // valeur recherchée : aléatoire entre 1 et N
      START;                    // démarrage du chronomètre
      recherche_tabint(t,v);    // recherche
      STOP;                     // arrêt du chronomètre
      // Mise à jour des indicateurs en fonction du résultat
      tmin = MIN(tmin,TEMPS);
      tmoy += TEMPS/nb;
      tmax = MAX(tmax,TEMPS);
   }
   // Affichage des indicateurs statistiques calculés
   cout << "Analyse statistique (sur " << nb << " exécutions) : " << endl;
   cout << " - temps minimum : " << tmin << "s" << endl;
   cout << " - temps moyen   : " << tmoy << "s" << endl;
   cout << " - temps maximum : " << tmax << "s" << endl;

   return 0;
}
