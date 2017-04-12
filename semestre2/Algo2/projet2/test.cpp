/**
 * @file test.cpp
 * @date 21/03/2017 Création
 * @brief Programme de test de la classe Ancetres
**/

#include <iostream>
#include "individu.hpp"
#include "ancetres.hpp"
#include "descendants.hpp"
using namespace std;

/// Programme principal destiné à tester toutes les fonctionnalités des différentes classes implémentées
int main()
{
    // INDIVIDUS
    Individu    ind1={'m',"Camille","2007-01-15"},
                ind2={'m',"Jules","2007-01-15"},
                ind3={'f',"Camille","2007-01-15"},
                ind4={'m',"Camille","1977-01-15"},
                ind5={'m',"Camille","2007-11-15"},
                ind6={'m',"Camille","2007-01-25"};

    // Tests de la méthode Individu::enChaine et <<
    cout << "ind1 : " << ind1 << endl;
    cout << "ind2 : " << ind2 << endl;
    cout << "ind3 : " << ind3 << endl;
    cout << "ind4 : " << ind4 << endl;
    cout << "ind5 : " << ind5 << endl;
    cout << "ind6 : " << ind6 << endl;
    cout << endl;

    // Tests de la méthode Individu::operator==
    cout << boolalpha;
    cout << "ind1=ind1 ? " << (ind1==ind1) << endl;
    cout << "ind1=ind2 ? " << (ind1==ind2) << endl;
    cout << "ind1=ind3 ? " << (ind1==ind3) << endl;
    cout << "ind1=ind4 ? " << (ind1==ind4) << endl;
    cout << "ind1=ind5 ? " << (ind1==ind5) << endl;
    cout << "ind1=ind6 ? " << (ind1==ind6) << endl;
    cout << endl;

    // Tests de la méthode Individu::operator<
    cout << "ind1<ind1 ? " << (ind1<ind1) << endl;
    cout << "ind1<ind2 ? " << (ind1<ind2) << endl;
    cout << "ind1<ind3 ? " << (ind1<ind3) << endl;
    cout << "ind1<ind4 ? " << (ind1<ind4) << endl;
    cout << "ind1<ind5 ? " << (ind1<ind5) << endl;
    cout << "ind1<ind6 ? " << (ind1<ind6) << endl;
    cout << endl;

    // Tests de la fonction de hachage
    cout << "ind1 --> " << hash<Individu>{}(ind1) << endl;
    cout << "ind2 --> " << hash<Individu>{}(ind2) << endl;
    cout << "ind3 --> " << hash<Individu>{}(ind3) << endl;
    cout << "ind4 --> " << hash<Individu>{}(ind4) << endl;
    cout << "ind5 --> " << hash<Individu>{}(ind5) << endl;
    cout << "ind6 --> " << hash<Individu>{}(ind6) << endl;
    cout << endl;

    // ANCETRES
    Ancetres a, b("registre.txt");

    // Tests de l'affichage
    cout << "a :" << endl << a << endl;
    cout << "b:" << endl << b << endl;
    cout << endl;

    // À COMPLÉTER ...

    return 0;
}
