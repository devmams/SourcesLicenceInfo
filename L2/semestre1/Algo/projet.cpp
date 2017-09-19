/**
 * @file projet.cpp
 * @author Glenn Plouhinec, Mamadou Diallo
 * @date 26/10/2016 Création
 * @date 26/10/2016 Actualisation du commentaire d'entête
 * @brief Programme créant un tableau rempli de caractères aléatoires, le triant, et en ressort les occurrences les plus élevées
**/
#include <iostream>	// cout et endl
#include <cstdlib> 	// rand(), srand()
#include <ctime>	// time(), clock_t, clock(), CLOCKS_PER_SEC
#include <cassert>	// assert()
#include <climits>	// UINT_MAX
#include <cfloat> 	// DBL_MAX
#include <time.h>	// rand()
#include <ctype.h> // isalpha()
using namespace std;

/**
 * @var taille
 * @brief Constante représentant la taille du tableau considéré
**/
const size_t taille = 10000;

/**
 * @typedef Tabchar
 * @brief Type de tableau considéré : Tableau de caractères
**/
typedef char Tabchar[taille];


//--------------------------------------------------------------------
/**
 * @brief procédure affiche
 *
 * @param t le tableau à afficher
 *
 * @b Role : affiche un Tabchar avec des caractères de 'a' à 'z'
 * @b Entrée :
 *       - @e t: le tableau qu'on va afficher
 *
 * @b Sortie :
 *       - @e t: le tableau d'entrée affiché (par modification)
 *
 * @pre - le tableau est rempli de caractères
 * @post - aucune
 *
 * @b Complexité - meilleur cas: Ω(taille); pire cas: O(taille); cas moyen: θ(taille)
 *
 *
 * @test Robustesse
 * | t_d             |t_f	|Justification			|Résultat|
 * |-----------------|------|-----------------------|--------|
 * |['a', 'b', 1]    |ERREUR|1 pas caractere		|passe   |
 * |['a', 'b', '2']  |ERREUR|2 pas caractere		|passe   |
 * |['a', 'b', 3.0]  |ERREUR|3.0 pas caractere		|passe   |
 * |['a', 'b', '4.0']|ERREUR|4.0 pas caractere		|passe   |
 * |['a', 'b', ' ']  |ERREUR|' ' caractere invalide	|passe   |
 * |['a', 'b', ':']  |ERREUR|':' caractere invalide	|passe   |
 * |['a', 'b', ?]  	 |ERREUR|tableau incomplet		|passe   |
 *
 *
 * @test Fonctionnel
 * | t_d           |t_f			   |Justification		  |Résultat|
 * |---------------|---------------|----------------------|--------|
 * |['a', 'b', 'c']|['a', 'b', 'c']|tableau de caracteres |passe   |
 * |['a', 'b', 'C']|['a', 'b', 'C']|tableau de caracteres |passe   |
 * |['A', 'B', 'C']|['A', 'B', 'C']|tableau de caracteres |passe   |
 *
 *
 * @test Structurel
 * | t_d           |t_f			   |Justification|Résultat|
 * |---------------|---------------|-------------|--------|
 * |['a', 'b', 'c']|['a', 'b', 'c']|3 itérations |passe   |
 * |['a', 'b', 'C']|['a', 'b', 'C']|3 itérations |passe   |
 * |['A', 'B', 'C']|['A', 'B', 'C']|3 itérations |passe   |
 *
 *
 *
**/
void affiche(const Tabchar & t)
{
	//pré-condition
	bool precond = true;
	int nbelem = 0;
	for (unsigned int ind = 0; ind < taille; ++ind)
	{
		if(isalpha(t[ind]))
		{
			nbelem++;
		}
	}
	precond = (nbelem==taille);
	assert(precond);


	// boucle d'affichage
	for (unsigned int i = 0; i < taille; ++i)
	{
		cout << t[i] << "; ";
	}
	cout << endl;
}


//--------------------------------------------------------------------
/**
 * @brief procédure aleatoire
 *
 * @param t le tableau à remplir, inf la borne inférieure, sup la borne supérieure
 *
 * @b Role : remplit un Tabchar avec des caractères entre compris entre inf et sup
 *
 * @b Entrée :
 *       - @e t : le tableau qu'on va remplir
 *		 - @e inf : la borne inférieure
 *		 - @e sup la borne supérieure
 *
 * @b Sortie :
 *       - @e t : le tableau d'entrée rempli (par modification)
 *
 * @pre - inf <= sup, inf et sup sont des caractères
 * @post - chaque caractère est compris entre inf et sup, et t est un tableau de caractères
 *
 * @b Complexité - meilleur cas: Ω(taille); pire cas: O(taille); cas moyen: θ(taille)
 *
 *
 * @test Robustesse
 * |t_d		 |inf|sup|t_f   |Justification	 	  |Résultat|
 * |---------|---|---|------|---------------------|--------|
 * |[?, ?, ?]|'z'|'a'|ERREUR|inf > sup 	 		  |passe   |
 * |[?, ?, ?]|'a'|99 |ERREUR|99 pas caracteres    |passe   |
 * |[?, ?, ?]| 1 | 2 |ERREUR|1 et 2 pas caractères|passe   |
 * |[?, ?, ?]| 2 | 1 |ERREUR|inf > sup 			  |passe   |
 *
 *
 * @test Fonctionnel
 * |t_d		 |inf|sup|t_f  			 |Justification|Résultat|
 * |---------|---|---|---------------|-------------|--------|
 * |[?, ?, ?]|'a'|'z'|['f', 'a', 'q']|inf < sup	   |passe   |
 * |[?, ?, ?]|'a'|'a'|['a', 'a', 'a']|inf = sup    |passe   |
 *
 *
 * @test Structurel
 * |t_d		 |inf|sup|t_f  			 |Justification|Résultat|
 * |---------|---|---|---------------|-------------|--------|
 * |[?, ?, ?]|'a'|'z'|['f', 'a', 'q']|3 itérations |passe   |
 * |[?, ?, ?]|'a'|'a'|['a', 'a', 'a']|3 itérations |passe   |
 *
 *
 *
**/
void aleatoire(Tabchar & t, char inf, char sup)	//Procédure de remplissage du tableau
{
	//pré-condition
	bool precond = (inf <= sup) && isalpha(inf) && isalpha(sup);
	assert(precond);

	//boucle de remplissage
	for (unsigned int i = 0; i < taille; ++i)
	{
		t[i] = static_cast<char>(rand() % (sup-inf+1) + inf);
	}

	//post-condition
	bool postcond = true;
	for (unsigned int ind = 0; ind < taille; ++ind)
	{
		postcond = (t[ind] >= inf && t[ind] <= sup) && isalpha(t[ind]);
	}
	assert(postcond);
}


//--------------------------------------------------------------------
/**
 * @brief fonction occurrences
 *
 * @param t le tableau à remplir, c le caractère recherché
 *
 * @b Role : retourne le nombre d'occurences du caractère c
 *
 * @b Entrée :
 *       - @e t : le tableau où l'on va chercher le caractère c
 *		 - @e c : le caractère que l'on recherche
 *
 * @b Sortie :
 *       - @e nbocc le nombre d'occurences du caractère c
 *
 * @pre - c est un caractère, t est un tableau de caractères
 * @post - 0 <= nbocc <= taille
 *
 * @b Complexité - meilleur cas: Ω(taille); pire cas: O(taille); cas moyen: θ(taille)
 *
 *
 * @test Robustesse
 * |t_d			   | c |nbocc |Justification	  |Résultat|
 * |---------------|---|------|-------------------|--------|
 * |['x', 'y', 'z']|'2'|ERREUR|c pas caractère	  |passe   |
 * |['x', '5', 'z']|'x'|ERREUR|tableau invalide	  |passe   |
 * |[' ', ' ', ' ']|'a'|ERREUR|tableau invalide	  |passe   |
 *
 *
 * @test Fonctionnel
 * |t_d			   | c |nbocc |Justification	  |Résultat|
 * |---------------|---|------|-------------------|--------|
 * |['x', 'y', 'z']|'y'| 1	  |1 occurrence       |passe   |
 * |['x', 'x', 'x']|'x'| 3    |3 occurrences	  |passe   |
 * |['x', 'z', 'z']|'a'| 0    |'a' n'apparait pas |passe   |
 *
 *
 * @test Structurel
 * |t_d			   | c |nbocc |Justification	  |Résultat|
 * |---------------|---|------|-------------------|--------|
 * |['x', 'y', 'z']|'y'| 1	  |3 itérations	      |passe   |
 * |['x', 'x', 'x']|'x'| 3    |3 itérations	      |passe   |
 * |['x', 'z', 'z']|'a'| 0    |3 itérations	      |passe   |
 *
 *
**/
size_t occurrences(Tabchar & t, char c)
{
	//pré-condition
	bool precond = isalpha(c);
	for (unsigned int ind = 0; ind < taille; ++ind)
	{
		precond = precond && isalpha(t[ind]);
	}
	assert(precond);


	// lexique
	size_t nbocc = 0;

	// boucle de recherche
	for (unsigned int i = 0; i < taille; ++i)
	{
		if(t[i] == c)
		{
			nbocc += 1;
		}
	}
	return nbocc;

	//post-condition
	bool postcond = (0 <= nbocc <= taille);
	assert(postcond);
}


//--------------------------------------------------------------------
/**
 * @brief fonction frequenceComptage
 *
 * @param tab le tableau où l'on va chercher les occurences les plus fréquentes
 *
 * @b Role : Compte le nombre d'occurrences de chaque élément du tableau grâce à la fonction occurrences(), et renvoie le caractère le plus fréquent
 *
 * @b Entrée :
 *       - @e tab : le tableau où l'on va rechercher les occurrences
 *
 * @b Sortie :
 *       - @e res : le caractère le plus fréquent
 *
 * @pre - tab est un tableau de caractères
 * @post - res existe dans tab
 *
 * @b Complexité - meilleur cas: Ω(taille²); pire cas: O(taille²); cas moyen: θ(taille²)
 *
 *
 * @test Robustesse
 * |tab 	       |res   |Justification	 |Résultat|
 * |---------------|------|------------------|--------|
 * |['a', 'a', '1']|ERREUR|caractère invalide|passe   |
 * |[' ', ' ', ' ']|ERREUR|caractère invalide|passe   |
 *
 *
 * @test Fonctionnel
 * |tab 	       |res|Justification											     |Résultat|
 * |---------------|---|-------------------------------------------------------------|--------|
 * |['a', 'a', 'b']|'a'|'a' apparait deux fois 										 |passe   |
 * |['a', 'b', 'c']|'a'|pas d'occurrence maximale, le compteur n'est pas réinitialisé|passe   |
 * |['b', 'b', 'b']|'b'|'b' apparait trois fois 									 |passe   |
 *
 *
 * @test Structurel
 * |tab 	       |res|Justification |Résultat|
 * |---------------|---|--------------|--------|
 * |['a', 'a', 'b']|'a'|3 itérations  |passe   |
 * |['a', 'b', 'c']|'a'|3 itérations  |passe   |
 * |['b', 'b', 'b']|'b'|3 itérations  |passe   |
 *
 *
**/
char frequenceComptage(Tabchar & tab)
{
	//pré-condition
	bool precond;
	for (unsigned int ind = 0; ind < taille; ++ind)
	{
		precond = isalpha(tab[ind]);
	}
	assert(precond);

	// lexique
	int occ, max;
	char res;

	max = 0;
	res = tab[0];

	// boucle de recherche
	for (unsigned int i = 0; i < taille; ++i)
	{
		/* appel de la fonction occurrences, qui prend en paramètre un nouveau
		caractère à chaque tour de boucle, et retourne son nombre d'apparitions*/
		occ = occurrences(tab, tab[i]);
		if(occ > max || (occ == max && res > tab[i]))
		{
			max = occ;
			res = tab[i];
		}
	}
	return res;

	//post-condition
	bool postcond = false;
	for (unsigned int ind = 0; ind < taille; ++ind)
	{
		if(tab[ind] == res)
		{
			postcond = true;
		}
	}
	assert(postcond);
}


//--------------------------------------------------------------------
/**
 * @brief procédure tri
 *
 * @param tab le tableau que l'on va trier
 *
 * @b Role : Trier dans l'ordre croissant les élements du tableau passé en paramètre
 *
 * @b Entrée :
 *       - @e tab : le tableau que l'on va trier
 *
 * @b Sortie :
 *       - @e tab : le tableau d'entrée trié (par modification)
 *
 * @pre - tab est un tableau de caractères
 * @post - le tableau retourné est trié dans l'ordre croissant
 *
 * @b Complexité - meilleur cas: Ω(taille²); pire cas: O(taille²); cas moyen: θ(taille²)
 *
 *
 * @test Robustesse
 * |tab_d		   |tab_f |Justification	   |Résultat|
 * |---------------|------|--------------------|--------|
 * |['a', '1', 'b']|ERREUR|caractere invalide  |passe   |
 * |['1', '2', '3']|ERREUR|caracteres invalides|passe   |
 *
 *
 * @test Fonctionnel
 * |tab_d		   |tab_f		   |Justification  |Résultat|
 * |---------------|---------------|---------------|--------|
 * |['k', 'f', 'n']|['f', 'k', 'n']|'k' > 'f'	   |passe   |
 * |['d', 'm', 'b']|['b', 'd', 'm']|'m' > 'd' > 'b'|passe   |
 * |['g', 'p', 'g']|['g', 'g', 'p']|'p' > 'g'	   |passe   |
 * |['b', 'a', 'a']|['a', 'a', 'b']|'b' > 'a'	   |passe   |
 *
 *
 * @test Structurel
 * |tab_d		   |tab_f		   |Justification  |Résultat|
 * |---------------|---------------|---------------|--------|
 * |['k', 'f', 'n']|['f', 'k', 'n']| 			   |passe   |
 * |['d', 'm', 'b']|['b', 'd', 'm']| 			   |passe   |
 * |['g', 'p', 'g']|['g', 'g', 'p']| 			   |passe   |
 * |['b', 'a', 'a']|['a', 'a', 'b']| 			   |passe   |
 *
 *
**/
void tri(Tabchar & tab)
{
	//pré-condition
	bool precond;
	for (unsigned int ind = 0; ind < taille; ++ind)
	{
		precond = isalpha(tab[ind]);
	}
	assert(precond);


	// lexique
	int j;
	char elem;

	// boucle de tri
	for (unsigned int i = 1; i < taille; ++i)
	{
       elem = tab[i];
       for (j = i; j > 0 && tab[j-1] > elem; j--)
       {
			tab[j] = tab[j-1];
       }
       tab[j] = elem;
	}

    //post-condition
	bool postcond = false;
	for (unsigned int ind = 0; ind < taille; ++ind)
	{
		if(tab[ind] <= tab[ind+1])
		{
			postcond = true;
		}
	}
	assert(postcond);
}


//--------------------------------------------------------------------
/**
 * @brief fonction frequenceTri
 *
 * @param tab le tableau où l'on va rechercher les occurrences les plus fréquentes
 *
 * @b Role : Retourne le caractère res le plus présent dans le tableau tab déjà trié
 *
 * @b Entrée :
 *       - @e tab : le tableau où l'on va rechercher les occurrences les plus fréquentes
 *
 * @b Sortie :
 *       - @e res : le caractère le plus fréquent
 *
 * @pre - tab est un tableau de caractères
 * @post - res existe dans tab
 *
 * @b Complexité - meilleur cas: Ω(taille²); pire cas: O(taille²); cas moyen: θ(taille²)
 *
 *
 * @test Robustesse
 * |tab 		   |res   |Justification 	   |Résultat|
 * |---------------|------|--------------------|--------|
 * |['a', '1', 'b']|ERREUR|caractere invalide  |passe   |
 * |['1', '2', '3']|ERREUR|caracteres invalides|passe   |
 *
 *
 * @test Fonctionnel
 * |tab 	       |res|Justification											     |Résultat|
 * |---------------|---|-------------------------------------------------------------|--------|
 * |['a', 'a', 'b']|'a'|'a' apparait deux fois 										 |passe   |
 * |['a', 'b', 'c']|'a'|pas d'occurrence maximale, le compteur n'est pas réinitialisé|passe   |
 * |['b', 'b', 'b']|'b'|'b' apparait trois fois 									 |passe   |
 *
 *
 * @test Structurel
 * |tab 	       |res|Justification |Résultat|
 * |---------------|---|--------------|--------|
 * |['a', 'a', 'b']|'a'|3 itérations  |passe   |
 * |['a', 'b', 'c']|'a'|3 itérations  |passe   |
 * |['b', 'b', 'b']|'b'|3 itérations  |passe   |
 *
 *
**/
char frequenceTri(Tabchar & tab)
{
	//pré-condition
	bool precond;
	for (unsigned int ind = 0; ind < taille; ++ind)
	{
		precond = isalpha(tab[ind]);
	}
	assert(precond);

	// lexique
	int occ, max;
	char c, res;

	// appel de la fonction tri, pour trier le tableau passé en paramètre
	tri(tab);

	// initialisation des variables
	max = 0;
	res = tab[0];
	occ = 0;
	c = tab[0];

	// boucle de recherche
	for (unsigned int i = 0; i < taille; ++i)
	{
		if (tab[i] == c)
		{
			occ += 1;
		}
		else
		{
			if (occ > max)
			{
				max = occ;
				res = c;
			}
			c = tab[i];
			occ = 1;
		}
	}

	if (occ > max)
	{
		res = c;
	}

	return res;

	//post-condition
	bool postcond = false;
	for (unsigned int ind = 0; ind < taille; ++ind)
	{
		if(tab[ind] == res)
		{
			postcond = true;
		}
	}
	assert(postcond);
}


//--------------------------------------------------------------------
/**
 * @brief fonction frequenceRapide
 *
 * @param tab le tableau où l'on va rechercher les occurrences les plus fréquentes
 *
 * @b Role : Compte le nombre d'occurrences pour chaque caractère, stocke le résultat dans un tableau d'entiers, et retourne la valeur la plus grande correspondant à l'occurrence la plus fréquente
 *
 * @b Entrée :
 *       - @e tab : le tableau où l'on va rechercher les occurrences les plus fréquentes
 *
 * @b Sortie :
 *       - @e res : le caractère le plus fréquent
 *
 * @pre - tab est un tableau de caractères
 * @post - res existe dans tab
 *
 * @b Complexité - meilleur cas: Ω(taille); pire cas: O(taille); cas moyen: θ(taille)
 *
 *
 * @test Robustesse
 * |tab 		   |res   |Justification 	   |Résultat|
 * |---------------|------|--------------------|--------|
 * |['a', '1', 'b']|ERREUR|caractere invalide  |passe   |
 * |['1', '2', '3']|ERREUR|caracteres invalides|passe   |
 *
 *
 * @test Fonctionnel
 * |tab 	       |res|Justification											     |Résultat|
 * |---------------|---|-------------------------------------------------------------|--------|
 * |['a', 'a', 'b']|'a'|'a' apparait deux fois 										 |passe   |
 * |['a', 'b', 'c']|'a'|pas d'occurrence maximale, le compteur n'est pas réinitialisé|passe   |
 * |['b', 'b', 'b']|'b'|'b' apparait trois fois 									 |passe   |
 *
 *
 * @test Structurel
 * |tab 	       |res|Justification |Résultat|
 * |---------------|---|--------------|--------|
 * |['a', 'a', 'b']|'a'|3 itérations  |passe   |
 * |['a', 'b', 'c']|'a'|3 itérations  |passe   |
 * |['b', 'b', 'b']|'b'|3 itérations  |passe   |
 *
 *
**/
char frequenceRapide(Tabchar & tab)
{
	//pré-condition
	bool precond;
	for (unsigned int ind = 0; ind < taille; ++ind)
	{
		precond = isalpha(tab[ind]);
	}
	assert(precond);

	//lexique
	int freq [26];
	int max, i, j;
	char res;

	// on initialise toutes les cases de freq à 0
	for (i = 0; i < 26; ++i)
	{
		freq[i] = 0;
	}

	// boucle de comptage d'occurrences
	for (i = 0; i < taille; ++i)
	{
		j = int(tab[i]) - int('a'); // affecte à chaque caractère une valeur entière: de 'a' à 'z' = de 0 à 25
		freq[j] = freq[j] + 1; // indique le nombre d'occurrence de chaque caractère
	}

	max = 0;

	// calcul de l'occurrence maximale
	for (i = 0; i < 26; ++i)
	{
		if (freq[i] > max)
		{
			max = freq[i];
			res = char(int('a') + i); // la case du tableau correspondant à la valeur entière du caractère, on reconverti cette dernière en caractère
		}
	}
	return res;

	//post-condition
	bool postcond = false;
	for (unsigned int ind = 0; ind < taille; ++ind)
	{
		if(tab[ind] == res)
		{
			postcond = true;
		}
	}
	assert(postcond);
}


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

/*void triInverse(Tabchar tab)
{
	int t=taille;
	bool tab_en_ordre;
	while(!tab_en_ordre)
	{
	    tab_en_ordre = true;
	    for(int i=t-1 ; i >= 0 ; i--)
	    {
	        if(tab[i] < tab[i+1])
	        {
	            swap(tab[i],tab[i+1]);
	            tab_en_ordre = false;
	        }
    	}
	}
}

void pireFrequenceComptage(Tabchar tab)
{
	for (int cc= 1; cc <= 26; ++cc)
	{
		for (int i = taille/26*cc-taille/26; i < taille/26*cc; ++i)
		{
			tab[i]= char('a'+cc-1);
		}
	}
	triInverse(tab);

}

void meilleurFrequenceComptage(Tabchar tab)
{
	int t= taille-1;
	for (int cc= 1; cc <= 26; ++cc)
	{
		for (int i = t/26*cc-t/26; i < t/26*cc; ++i)
		{
			tab[i]= char('a'+cc-1);
		}
	}
	tab[taille]='z';
	triInverse(tab);

}*/

//--------------------------------------------------------------------
/**
 * @brief fonction principale
 *
 * @b Role : tester toutes les fonctions et procédures
 *
 * @pre - aucune
 * @post - aucune
**/
int main()
{
	// initialisation de rand
	srand(time(NULL));

	//lexique
	Tabchar tablo;
	unsigned int i;
	unsigned int nb;
	double tmin,tmoy,tmax;

	cout << "Pour un tableau de taille " << taille << " :" << endl << endl;

	//--------------------------------------------------------------------
	// Meilleur cas pour frequenceComptage():
	/*aleatoire(tablo, 'a', 'a');
	START;
	frequenceComptage(tablo);
	STOP;
	cout << "meilleur cas frequenceComptage(): " << TEMPS << "s" << endl;

	//pire cas pour frequenceComptage():
	aleatoire(tablo, 'a', 'z');
	START;
	frequenceComptage(tablo);
	STOP;
	cout << "pire cas frequenceComptage(): " << TEMPS << "s" << endl;*/

	// Analyse statistique
	nb = 10000;
	tmin = DBL_MAX;
	tmoy = 0.0;
	tmax = 0.0;

	for (i=0; i<nb; ++i)
	{
		aleatoire(tablo, 'a', 'z');
		START;
		frequenceComptage(tablo);
		STOP;

		tmin = MIN(tmin,TEMPS);
		tmoy += TEMPS/nb;
		tmax = MAX(tmax,TEMPS);
	}

	// Affichage des indicateurs statistiques calculés
	cout << "Analyse statistique de frequenceComptage (sur " << nb << " executions) : " << endl;
	cout << " - temps minimum : " << tmin << "s" << endl;
	cout << " - temps moyen   : " << tmoy << "s" << endl;
	cout << " - temps maximum : " << tmax << "s" << endl << endl;


	//--------------------------------------------------------------------
	// Meilleur cas pour frequenceTri():
	/*aleatoire(tablo, 'a', 'a');
	START;
	frequenceTri(tablo);
	STOP;
	cout << "meilleur cas frequenceTri(): " << TEMPS << "s" << endl;

	//pire cas pour frequenceTri():
	aleatoire(tablo, 'a', 'z');
	START;
	frequenceTri(tablo);
	STOP;
	cout << "pire cas frequenceTri(): " << TEMPS << "s" << endl;*/

	// Analyse statistique
	nb = 10000;
	tmin = DBL_MAX;
	tmoy = 0.0;
	tmax = 0.0;

	for (i=0; i<nb; ++i)
	{
		aleatoire(tablo, 'a', 'z');
		START;
		frequenceTri(tablo);
		STOP;

		tmin = MIN(tmin,TEMPS);
		tmoy += TEMPS/nb;
		tmax = MAX(tmax,TEMPS);
	}

	// Affichage des indicateurs statistiques calculés
	cout << "Analyse statistique de frequenceTri (sur " << nb << " executions) : " << endl;
	cout << " - temps minimum : " << tmin << "s" << endl;
	cout << " - temps moyen   : " << tmoy << "s" << endl;
	cout << " - temps maximum : " << tmax << "s" << endl << endl;



	//--------------------------------------------------------------------
	// Meilleur cas pour frequenceRapide():
	/*aleatoire(tablo, 'a', 'a');
	START;
	frequenceRapide(tablo);
	STOP;
	cout << "meilleur cas frequenceRapide(): " << TEMPS << "s" << endl;

	//pire cas pour frequenceRapide():
	aleatoire(tablo, 'a', 'z');
	START;
	frequenceRapide(tablo);
	STOP;
	cout << "pire cas frequenceRapide(): " << TEMPS << "s" << endl;*/

	// Analyse statistique
	nb = 10000;
	tmin = DBL_MAX;
	tmoy = 0.0;
	tmax = 0.0;

	for (i=0; i<nb; ++i)
	{
		aleatoire(tablo, 'a', 'z');
		START;
		frequenceRapide(tablo);
		STOP;

		tmin = MIN(tmin,TEMPS);
		tmoy += TEMPS/nb;
		tmax = MAX(tmax,TEMPS);
	}

	// Affichage des indicateurs statistiques calculés
	cout << "Analyse statistique de frequenceRapide (sur " << nb << " executions) : " << endl;
	cout << " - temps minimum : " << tmin << "s" << endl;
	cout << " - temps moyen   : " << tmoy << "s" << endl;
	cout << " - temps maximum : " << tmax << "s" << endl;

	return 0;
}
