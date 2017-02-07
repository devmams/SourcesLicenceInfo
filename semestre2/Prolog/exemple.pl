%------------------- HOMME ------------------------%
homme(jean).
homme(michel).
homme(popeye).
homme(gaston).
homme(david).
homme(charly).
homme(mimosa).
%--------------------- FEMME ----------------------%
femme(marie).
femme(gertrude).
femme(laure).
femme(olive).
femme(virginie).
femme(julie).
femme(sidonie).
%---------------------- PERE ----------------------%
pere(jean,popeye).
pere(jean,laure).
pere(jean,gaston).
pere(popeye,mimosa).
pere(popeye,sidonie).
pere(michel,charly).
pere(michel,julie).
pere(gaston,david).
pere(gaston,virginie).
%---------------------- MERE ----------------------%
mere(marie,gaston).
mere(marie,laure).
mere(marie,popeye).
mere(gertrude,david).
mere(gertrude,virginie).
mere(laure,charly).
mere(laure,julie).
mere(olive,mimosa).
mere(olive,sidonie).
%--------------------- EPOUX ---------------------%
epoux(jean,marie).
epoux(gaston,gertrude).
epoux(michel,laure).
epoux(popeye;olive).
%------------------- EPOUSE ----------------------%
epouse(Y,X) :- epoux(X,Y).
%------------------- MARIER ----------------------%
marier(X,Y) :- epoux(X,Y) ; epouse(X,Y).
%------------------- PARENT ----------------------%
parent(X,Y) :- pere(X,Y) ; mere(X,Y).
parent(X) :- parent(X,_).
%------------------- ENFANT ----------------------%
enfant(X,Y) :- parent(Y,X).
%------------------- FILLE -----------------------%
fille(X,Y) :- femme(X) , parent(Y,X).
%------------------- FILS ------------------------%
fils(X,Y) :- homme(X) , parent(Y,X).
%------------------- FRERE -----------------------%
frere(X,Y) :- homme(X) , parent(Z,X) , parent(Z,Y) , X \== Y.
%------------------- SOEUR -----------------------%
soeur(X,Y) :- femme(X) , frere(Y,X).
%------------------- FRERE_OU_SOEUR --------------%
frere_ou_soeur(X,Y) :- frere(X,Y) ; soeur(X,Y).
%---------------- GRAND_PARENT -------------------%
grand_parent(X,Y) :- parent(Z,Y) , parent(X,Z).
%------------------- ONCLE -----------------------%
oncle(X,Y) :- homme(X) , parent(Z,Y) , frere(Z,X).
%------------------- TANTE -----------------------%
tante(X,Y) :- femme(X) , parent(Z,Y) , soeur(Z,X).
%------------------- ANCETRE ---------------------%
ancetre(X,Y) :- parent(X,Y).
ancetre(X,Y) :- parent(X,Z) , ancetre(Z,Y).
