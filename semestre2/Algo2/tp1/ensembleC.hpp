#include <iostream>
#include <string>
using namespace std;

class ensembleC {

  private:

    struct Maillon {
      string ch;
      Maillon* suiv;
    };
    Maillon *tete;
    int nb;

  public:

    ensembleC();
    ~ensembleC();
    bool estVide();
    bool contient(string mot);
    void ajoute(string mot);
    void retire(string mot);
    string contenu();
    int nbelem();
};
