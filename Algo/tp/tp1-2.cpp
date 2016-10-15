/**
 * @file tp1-2.cpp
 * @author C. Jermann
 * @date 15/09/2015 Création
 * @date 15/09/2016 Actualisation du commentaire d'entête
 * @brief Programme implémentant l'algorithme d'insertion dans un tableau vu en CM
**/

#include <iostream> // pour cout et endl
#include <cassert> // pour assert
using namespace std;

//--------------------------------------------------------------------
/**
 * @brief procédure inserer
 * 
 * @b Role : 
 * 
 * @b Entrées : 
 * 
 * @b Sortie : 
 * 
 * @pre 
 * 
 * @post 
 * 
 * @test
 * |Entrée    | Sortie    | Justification | Résultat |
 * |--|--|--|--|
*/
void inserer(float tab[], unsigned int & nbelem, unsigned int taille, float r, unsigned int pos)
{
    // pré-condition
    assert((1<=pos) and (pos<=nbelem+1) and (nbelem<taille));
    
    // sauvegarde des données d'entrée pour pouvoir tester la post-condition
    unsigned int i;
    unsigned int nbelem_d = nbelem;
    float tab_d[nbelem];
    for(i=1; i<=nbelem; ++i)
    {
        tab_d[i-1] = tab[i-1];
    }

    // lexique
    unsigned int ind;
    
    // décalage des éléments
    for (ind=0; ind<nbelem+1-pos; ++ind)
    {
        tab[nbelem-ind] = tab[nbelem-ind-1];
    }
    
    // insertion du nouvel élément
    tab[pos-1] = r;
    
    // incrémentation du nombre d'éléments
    nbelem = nbelem+1;
    
    // post-condition
    bool postcond=true;
    for (i=1; i<=pos-1; ++i)
    {
        postcond = postcond and (tab[i-1]==tab_d[i-1]);
    }
    postcond = postcond and (tab[pos-1]==r);
    for (i=pos+1; i<=nbelem; ++i)
    {
        postcond = postcond and (tab[i-1]==tab_d[i-2]);
    }
    postcond = postcond and (nbelem==nbelem_d+1);
    assert(postcond);
}

//--------------------------------------------------------------------
/**
 * @brief fonction principale
 * 
 * @b Rôle : exécuter le jeu de tests de la procédure inserer
 * 
 * @pre aucune
 * 
 * @post aucune
 * 
 * Cette fonction illustre le concept de test unitaire. Elle applique un à un
 * l'ensemble des tests du jeu défini pour la procédure inserer, et 
 * affiche un message décrivant chaque test et indiquant s'il passe ou échoue.
*/
int main()
{
    // lexique
    const unsigned int taille=4;
    unsigned int nbelem, pos;
    float tab[taille], r;

    // Test 1
    cout << "Test 1 : " << endl;
    tab[0]=0.0; tab[1]=3.0; /*tab[2]=?; tab[3]=?;*/ nbelem=2; r=1.0; pos=2; // entrées
    cout << " - Entrées : tab=[0,3,-,-], nbelem=2, taille=4, r=1, pos=2" << endl;
    cout << " - Sorties attendues : tab=[0,1,3,-], nbelem=3" << endl;
    inserer(tab,nbelem,taille,r,pos); // appel de la procédure testée
    cout << " - Sorties observées : tab=[" << tab[0] << "," << tab[1] << "," << tab[2] << "," << tab[3] << "], nbelem=" << nbelem << endl;
    // résultat du test
    if ((tab[0]==0.0) and (tab[1]==1.0) and (tab[2]==3.0) /*and (tab[3]==?)*/ and (nbelem==3))
    {
        cout << ">>> passe" << endl;
    }
    else
    {
        cout << ">>> échoue" << endl;
    }

    // à compléter

   return 0;
} // main
